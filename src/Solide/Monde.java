package Solide;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Monde {

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

    public Monde(String filepathFloor, String filepathObjet, String filepathEntitee) {
        try {
            File floorFile = new File(filepathFloor);
            File objetFile = new File(filepathObjet);
            File entiteeFile = new File(filepathEntitee);

            BufferedImage floorImage = ImageIO.read(floorFile);
            BufferedImage objetImage = ImageIO.read(floorFile);
            BufferedImage entiteeImage = ImageIO.read(floorFile);

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
                    int blue = pixel & 0xFF;

                    this.floor[y][x] = new Bloc(red + green + blue);


                    pixel = objetImage.getRGB(x, y);

                    red = (pixel >> 16) & 0xFF;
                    green = (pixel >> 8) & 0xFF;
                    blue = pixel & 0xFF;

                    //this.objet[y][x] = new Objet(red + green + blue);
                    //TODO

                    pixel = entiteeImage.getRGB(x, y);

                    red = (pixel >> 16) & 0xFF;
                    green = (pixel >> 8) & 0xFF;
                    blue = pixel & 0xFF;

                    //this.entitee[y][x] = new Entitee(red + green + blue);
                    //TODO
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                                System.out.print("* ");
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

    public void render() {

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
}
