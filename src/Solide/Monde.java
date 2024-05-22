package Solide;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Solide.Entitee.Entitee;


public class Monde implements Graph {

    /**
     * Fonction permetant d'obtenir une image a partir d'un chemin de texture
     *
     * @param filename un chemin de texture
     * @return une image correspondant au chemin de texture
     */
    public static Image getImage(String filename) {
        return new ImageIcon(filename).getImage();
    }

    private Entitee[][] entitee;
    private Objet[][] objet;
    private Bloc[][] floor;

    public Monde() {
        this.objet = new Objet[10][10];
        this.floor = new Bloc[10][10];
        for (int i = 0; i < 10; i++) {
            for (int y = 0; y < 10; y++) {
                this.floor[i][y] = new Bloc();
            }
        }
        this.entitee = new Entitee[10][10];

    }

    public Monde(int x, int y) {
        this.objet = new Objet[y][x];
        this.floor = new Bloc[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                this.floor[i][j] = new Bloc();
            }
        }
        this.entitee = new Entitee[y][x];
    }

    public Monde(String filepathMap) {
        try {
            File floorFile = new File(filepathMap);

            BufferedImage floorImage = ImageIO.read(floorFile);

            int width = floorImage.getWidth();
            int height = floorImage.getHeight();

            this.floor = new Bloc[height][width];
            this.objet = new Objet[height][width];
            this.entitee = new Entitee[height][width];

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = floorImage.getRGB(x, y);

                    int red = (pixel >> 16) & 0xFF;
                    int green = (pixel >> 8) & 0xFF;
                    //int blue = pixel & 0xFF;

                    this.floor[y][x] = new Bloc(red);
                    this.objet[x][y] = Objet.getObjet(green);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Monde(int x, int y, double type) {
        this.floor = new Bloc[y][x];
        this.objet = new Objet[y][x];
        this.entitee = new Entitee[y][x];
        int[][] perlinMap = PerlinNoise.generateProcedural(x, y, type);
        for(int height = 0; height<y; height++) {
            for(int width = 0; width<x; width++) {
                this.floor[height][width] = new Bloc(perlinMap[height][width]);
            }
        }
    }

    public void show(boolean number, int layer) {
        Objet layerMap[][];
        switch (layer) {
            case 0:
                layerMap = this.getFloor();
                break;
            case 1:
                layerMap = this.getObjet();
                break;
            case 2:
                layerMap = this.getEntitee();
                break;

            default:
                layerMap = this.getFloor();
                break;
        }

        for (int i = 0; i < layerMap.length; i++) {
            for (int y = 0; y < layerMap[i].length; y++) {
                if (number) {
                    System.out.print(layerMap[y][i].id + " ");
                } else {
                    if (layerMap[y][i] != null) {
                        switch (layerMap[y][i].id) {
                            case -1:
                                // error ou non defini
                                System.out.print("! ");
                                break;
                            case 0:
                                // herbe
                                System.out.print("~ ");
                                break;
                            case 1:
                                // rocher
                                System.out.print("% ");
                                break;
                            case 2:
                                // mur ou border
                                System.out.print("& ");
                                break;
                            case 3:
                                // ville
                                System.out.print("@ ");
                                break;
                            case 4:
                                // batiment utilitaire
                                System.out.print("& ");
                                break;

                            default:
                                break;
                        }
                    }

                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setCaseObjet(Objet obj, int x, int y) {
        this.objet[y][x] = obj;
    }

    public void setCaseFloor(Bloc obj, int x, int y) {
        this.floor[y][x] = obj;
    }

    public void setCaseEntitee(Entitee obj, int x, int y) {
        this.entitee[y][x] = obj;
    }

    public Objet[][] getFloor() {
        return floor;
    }

    public Objet[][] getEntitee() {
        return entitee;
    }

    public Objet[][] getObjet() {
        return objet;
    }

    public void setFloor(Bloc[][] map) {
        this.floor = map;
    }

    public void setEntitee(Entitee[][] entitee) {
        this.entitee = entitee;
    }

    public void setObjet(Objet[][] objet) {
        this.objet = objet;
    }

    @Override
    public void rendere(int Ts, int posCameraX, int posCameraY, int width, int height, Graphics2D offscreen) {
        int X = posCameraX - (width/2);
        int Tx = X/Ts;
        int Ex = -(X-(Tx*Ts));

        int Y = posCameraY - (height/2);
        int Ty = Y/Ts;
        int Ey = -(Y-(Ty*Ts));

        rendere(TypeFlat.Floor, Ex, Ey, (-Ex+(width)), (-Ey+(height)), X, Y, Ts, offscreen);
        rendere(TypeFlat.Objet, Ex, Ey, (-Ex+(width)), (-Ey+(height)), X, Y, Ts, offscreen);
        rendere(TypeFlat.Entiee, Ex, Ey, (-Ex+(width)), (-Ey+(height)), X, Y, Ts, offscreen);
    }

    private void rendere(TypeFlat type, int Sx, int Sy, int Ex, int Ey, int X, int Y, int Ts, Graphics2D offscreen) {

        Objet toRenderer[][] = this.floor;

        switch (type) {
            case TypeFlat.Floor:
                toRenderer = this.floor;
                break;

            case TypeFlat.Objet:
                toRenderer = this.objet;
                break;

            case TypeFlat.Entiee:
                toRenderer = this.entitee;
                break;

            default:
                toRenderer = this.floor;
                break;
        }

        for (int y = Sy, b = Y/Ts; y < Ey; b+=1, y += Ts) {
            for (int x = Sx, a = X/Ts; x < Ex; a+=1, x += Ts) {

                if(b < toRenderer.length && a < toRenderer[b].length) {
                    if (toRenderer[b][a] != null) {
                        if(TypeFlat.Floor == type) {
                            offscreen.drawImage(Settings.Textures.get("Herbe"), x, y, Ts, Ts, null);
                        }
                        switch (toRenderer[b][a].id) {
                            case -1:
                                offscreen.drawImage(Settings.Textures.get("None"), x, y, Ts, Ts, null);
                                break;
                            case 0:
                                offscreen.drawImage(Settings.Textures.get("Eau"), x, y, Ts, Ts, null);
                                break;
                            case 1:
                                offscreen.drawImage(Settings.Textures.get("Herbe"), x, y, Ts, Ts, null);
                                break;
                            case 2:
                                offscreen.drawImage(Settings.Textures.get("Sable"), x, y, Ts, Ts, null);
                                break;
                            case 3:
                                offscreen.drawImage(Settings.Textures.get("Foret"), x, y, Ts, Ts, null);
                                break;
                            case 4:
                                offscreen.drawImage(Settings.Textures.get("Montagne"), x, y, Ts, Ts, null);
                                break;
                            case 7:
                                offscreen.drawImage(Settings.Textures.get("Joueur"), x, y, Ts, Ts, null);
                                break;
                            default:
                                offscreen.drawImage(Settings.Textures.get("Error"), x, y, Ts, Ts, null);
                                break;
                        }
                    }
                }
            }
        }
    }
}
