package Solide;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Solide.Batiments.Batiment;
import Solide.Batiments.Caserne;
import Solide.Batiments.Ferme;
import Solide.Batiments.Forge;
import Solide.Batiments.Ville;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.TreeSet;
import javax.swing.ImageIcon;

public class Jeu extends JFrame {

    private int X;
    private int Y;

    private int width = 1600;
    private int height = 900;

    private int posCameraX = 800;
    private int posCameraY = 450;

    //private int rotation = 0;
    //private float zoom = 1.0f;

    private int textureSize = 50;

    private double fps = 0;

    private BufferedImage onscreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    private BufferedImage offscreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    private Graphics2D onscreen = onscreenImage.createGraphics();
    private Graphics2D offscreen = offscreenImage.createGraphics();

    private Image herbe = getImage("assets/herbe.png");
    private Image mur = getImage("assets/mur.png");
    private Image rocher = getImage("assets/rocher.png");
    private Image ville = getImage("assets/ville.png");
    private Image forge = getImage("assets/forge.png");
    private Image habitation = getImage("assets/habitation.png");
    private Image ferme = getImage("assets/ferme.png");
    private Image error = getImage("assets/error.png");
    private Image none = getImage("assets/none.png");

    private Image eau = getImage("assets/tilemap/water/frame1sprite/frame1_water_34.png");

    private static TreeSet<Integer> keysDown;
    private static Hashtable<Integer, String> nomsTouches = new Hashtable<Integer, String>();

    public Jeu() {
        this.X = 50;
        this.Y = 50;
    }

    private void setup() {

        this.setTitle("game");
        this.setResizable(false);
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        Image Cursor = getImage("assets/cursor.png");
        java.awt.Cursor gauntletCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                Cursor, new Point(0, 0), "gauntlet cursor");
        this.setCursor(gauntletCursor);
        this.setContentPane(new JLabel(new ImageIcon(onscreenImage)));
        this.pack();
        this.requestFocusInWindow();
        this.setVisible(true);
        keysDown = new TreeSet<Integer>();

        nomsTouches.put(KeyEvent.VK_ENTER, "Entree");
        nomsTouches.put(KeyEvent.VK_ESCAPE, "Echap");
        nomsTouches.put(KeyEvent.VK_LEFT, "Gauche");
        nomsTouches.put(KeyEvent.VK_RIGHT, "Droite");
        nomsTouches.put(KeyEvent.VK_UP, "Haut");
        nomsTouches.put(KeyEvent.VK_DOWN, "Bas");
        nomsTouches.put(KeyEvent.VK_N, "N");
        nomsTouches.put(KeyEvent.VK_H, "H");
        nomsTouches.put(KeyEvent.VK_M, "M");
        nomsTouches.put(KeyEvent.VK_S, "S");
        nomsTouches.put(KeyEvent.VK_K, "K");
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keysDown.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keysDown.remove(e.getKeyCode());
            }
        });
        // this.addMouseWheelListener((MouseWheelListener) new MouseWheelListener() {
        //     @Override
        //     public void mouseWheelMoved(MouseWheelEvent e) {
        //         rotation = e.getWheelRotation();
        //     }
        // });
    }

    public void run() {
        Monde carte = new Monde(this.X, this.Y, 0.4);

        posCameraX = (X * 50) / 2;
        posCameraY = (Y * 50) / 2;

        // Batiment ville = new Ville(3);
        // Batiment forge = new Forge(4);
        // Batiment ferme = new Ferme(5);
        // Batiment caserne = new Caserne(6);
        // Obstacle rocher = new Obstacle(1);
        // Bloc eau = new Bloc(0);
        // Bloc herbe = new Bloc(1);
        // Obstacle mur = new Obstacle(2);

        // carte.show(true);
        setup();

        final double max_speed = 7.0f;
        final double acc = 1.0f;
        double playervelocityX = 0.0f;
        double playervelocityY = 0.0f;

        boolean inGame = true;
        int frame = 0;

        long start = System.currentTimeMillis();
        long lastdelta = System.currentTimeMillis();

        while (inGame) {

            long now = System.currentTimeMillis();
            float delta = (now - lastdelta) / 10.00f;

            lastdelta = now;

            // if (keysDown.contains(KeyEvent.VK_CONTROL) && rotation != 0) {
            //     if (rotation < 0) {
            //         zoom *= 2.0f;
            //     } else {
            //         zoom *= 0.5f;
            //     }
            //     if (zoom > 4) {
            //         zoom = 4;
            //     }

            //     if (zoom < 0.25f) {
            //         zoom = 0.25f;
            //     }
            //     System.out.println("zoom: " + zoom);
            // }
            // rotation = 0;

            if (keysDown.contains(KeyEvent.VK_D)) {
                playervelocityX += (acc) * delta;
                if (playervelocityX > max_speed + 1.0f) {
                    playervelocityX = max_speed + 1.0f;
                }
            } else if (keysDown.contains(KeyEvent.VK_A)) {
                playervelocityX -= acc * delta;
                if (playervelocityX < -max_speed) {
                    playervelocityX = -max_speed;
                }
            } else {
                if (playervelocityX < 0) {
                    playervelocityX += acc * delta;
                    if (playervelocityX >= 0) {
                        playervelocityX = 0.0f;
                    }
                } else {
                    playervelocityX -= acc * delta;
                    if (playervelocityX <= 0) {
                        playervelocityX = 0.0f;
                    }
                }
            }

            if (keysDown.contains(KeyEvent.VK_S)) {
                playervelocityY += acc * delta;
                if (playervelocityY > max_speed + 1.0f) {
                    playervelocityY = max_speed + 1.0f;
                }
            } else if (keysDown.contains(KeyEvent.VK_W)) {
                playervelocityY -= acc * delta;
                if (playervelocityY < -max_speed) {
                    playervelocityY = -max_speed;
                }
            } else {
                if (playervelocityY < 0) {
                    playervelocityY += acc * delta;
                    if (playervelocityY >= 0) {
                        playervelocityY = 0.0f;
                    }
                } else {
                    playervelocityY -= acc * delta;
                    if (playervelocityY <= 0) {
                        playervelocityY = 0.0f;
                    }
                }
            }

            posCameraX += (playervelocityX * delta);
            posCameraY += (playervelocityY * delta);
            // System.out.println("delta: " + delta + " velocity: " + playervelocityX + "
            // pos: " + posCameraX);

            if (posCameraX < 800) {
                posCameraX = 800;
            }

            if (posCameraY < 450) {
                posCameraY = 450;
            }

            // System.out.println((X * 50) - 900);
            if (posCameraX > (X * 50) - 900) {
                posCameraX = (X * 50) - 900;
            }

            // System.out.println((Y * 50) - 550);
            if (posCameraY > (Y * 50) - 550) {
                posCameraY = (Y * 50) - 550;
            }

            // System.out.print("x: " + posCameraX + " y: " + posCameraY);
            // System.out.println();

            render(carte);
            frame++;

            long end = System.currentTimeMillis();
            if (end - start > 1000) {
                start = System.currentTimeMillis();
                fps = frame;
                frame = 0;
                clear();
                System.out.println(textureSize);
                System.out.println(" fps: " + fps);
            }
            // System.out.println(playervelocity);

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {

            }
        }
    }

    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void render(Monde carte) {
        nettoyer();

        int xmoin = posCameraX - 800;
        int parseXmoin = xmoin / 50;
        int startCal = (parseXmoin * 50) - xmoin;

        int ymoin = posCameraY - 450;
        int parseYmoin = ymoin / 50;
        int startCalY = (parseYmoin * 50) - ymoin;

        //textureSize = (int) (50 * zoom);

        renderFloor(carte, startCal, startCalY);
        renderObjet(carte, startCal, startCalY);
        renderEntitee(carte, startCal, startCalY);

        offscreen.setColor(Color.BLACK);
        offscreen.setFont(new Font("Arial", Font.PLAIN, 20));
        offscreen.drawString("fps " + fps, 2, 20);
        offscreen.setColor(Color.WHITE);

        rafraichir();

    }

    private void renderFloor(Monde carte, int startWithX, int startWithY) {
        // TODO pour plus tard le zoom
        // for (int y = startWithY - textureSize, b = posCameraY - 450; y < startWithY +
        // 950; b += textureSize, y += textureSize) {
        // for (int i = startWithX - textureSize, n = posCameraX - 800; i < startWithX +
        // 1650; n += textureSize, i += textureSize) {
        for (int y = startWithY - 50, b = posCameraY - 450; y < startWithY + 950; b += 50, y += 50) {
            for (int i = startWithX - 50, n = posCameraX - 800; i < startWithX + 1650; n += 50, i += 50) {
                if (carte.getFloor()[b / 50][n / 50] != null) {
                    switch (carte.getFloor()[b / 50][n / 50].id) {
                        case -1:
                            offscreen.drawImage(none, i, y, textureSize, textureSize, null);
                            break;
                        case 0:
                            // eau
                            //offscreen.drawImage(Settings.getImageFromNumber(Settings.getGoodSpriteByPlacement(0, Settings.getThreeArray2D(n, b, carte))), i, y, textureSize, textureSize, null);
                            offscreen.drawImage(eau, i, y, textureSize, textureSize, null);
                            break;
                        case 1:
                            offscreen.drawImage(herbe, i, y, textureSize, textureSize, null);
                            break;
                        default:
                            offscreen.drawImage(error, i, y, textureSize, textureSize, null);
                            break;
                    }

                }
            }
        }
    }

    private void renderObjet(Monde carte, int startWithX, int startWithY) {
        for (int y = startWithY - 50, b = posCameraY - 450; y < startWithY + 950; b += 50, y += 50) {
            for (int i = startWithX - 50, n = posCameraX - 800; i < startWithX + 1650; n += 50, i += 50) {

                if (carte.getObjet()[b / 50][n / 50] != null) {
                    switch (carte.getObjet()[b / 50][n / 50].id) {
                        case -1:
                            // System.out.println("bah?");
                            offscreen.drawImage(none, i, y, textureSize, textureSize, null);
                            break;
                        case 0:
                            // herbe
                            offscreen.drawImage(herbe, i, y, textureSize, textureSize, null);
                            break;
                        case 1:
                            // rocher
                            offscreen.drawImage(rocher, i, y, textureSize, textureSize, null);
                            break;
                        case 2:
                            // mur ou border
                            offscreen.drawImage(mur, i, y, textureSize, textureSize, null);
                            break;
                        case 3:
                            // ville
                            offscreen.drawImage(ville, i, y, textureSize, textureSize, null);
                            break;
                        case 4:
                            // batiment utilitaire
                            offscreen.drawImage(forge, i, y, textureSize, textureSize, null);
                            break;
                        case 5:
                            // batiment utilitaire
                            offscreen.drawImage(ferme, i, y, textureSize, textureSize, null);
                            break;
                        case 6:
                            // batiment utilitaire
                            offscreen.drawImage(habitation, i, y, textureSize, textureSize, null);
                            break;

                        default:
                            offscreen.drawImage(error, i, y, textureSize, textureSize, null);
                            break;
                    }

                }

            }
        }
    }

    private void renderEntitee(Monde carte, int startWithX, int startWithY) {

        for (int y = startWithY - 50, b = posCameraY - 450; y < startWithY + 950; b += 50, y += 50) {
            for (int i = startWithX - 50, n = posCameraX - 800; i < startWithX + 1650; n += 50, i += 50) {

                if (carte.getEntitee()[b / 50][n / 50] != null) {
                    switch (carte.getEntitee()[b / 50][n / 50].id) {
                        case -1:
                            // System.out.println("bah?");
                            offscreen.drawImage(none, i, y, textureSize, textureSize, null);
                            break;
                        case 0:
                            // herbe
                            offscreen.drawImage(herbe, i, y, textureSize, textureSize, null);
                            break;
                        case 1:
                            // rocher
                            offscreen.drawImage(rocher, i, y, textureSize, textureSize, null);
                            break;
                        case 2:
                            // mur ou border
                            offscreen.drawImage(mur, i, y, textureSize, textureSize, null);
                            break;
                        case 3:
                            // ville
                            offscreen.drawImage(ville, i, y, textureSize, textureSize, null);
                            break;
                        case 4:
                            // batiment utilitaire
                            offscreen.drawImage(forge, i, y, textureSize, textureSize, null);
                            break;
                        case 5:
                            // batiment utilitaire
                            offscreen.drawImage(ferme, i, y, textureSize, textureSize, null);
                            break;
                        case 6:
                            // batiment utilitaire
                            offscreen.drawImage(habitation, i, y, textureSize, textureSize, null);
                            break;

                        default:
                            offscreen.drawImage(error, i, y, textureSize, textureSize, null);
                            break;
                    }

                }

            }
        }
    }

    public static Jeu newInstance() {
        return new Jeu();
    }

    /**
     * Met à jour le contenu de la fenêtre avec ce qui a été affiché depuis le
     * dernier appel de
     * rafraichir.
     */
    private void rafraichir() {
        onscreen.drawImage(offscreenImage, 0, 0, null);
        this.repaint();
    }

    /**
     * Nettoie le canvas
     */
    private void nettoyer() {
        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, width, height);
        // this.rafraichir();
    }

    /**
     * Fonction permetant d'obtenir une image a partir d'un chemin de texture
     *
     * @param filename un chemin de texture
     * @return une image correspondant au chemin de texture
     */
    public static Image getImage(String filename) {
        return new ImageIcon(filename).getImage();
    }

    /**
     * Fonction permetant de detecter quelle touche a été presser a l'aide de la
     * table de hash
     * {@link AffichageGraphique#nomsTouches} et du set
     * {@link AffichageGraphique#keysDown}
     *
     * @return la string correspondant a la touche presser
     */
    private static String trouveProchaineEntree() {
        while (true) {
            for (Integer k : nomsTouches.keySet()) {
                if (isKeyPressed(k)) {
                    while (isKeyPressed(k)) {
                        System.out.print("");
                    }
                    return nomsTouches.get(k);
                }
            }
        }
    }

    /**
     * @param keycode un entier correspondant a un keycode
     * @return si la touche associée à ce code est actuellement enfoncée.
     */
    public static boolean isKeyPressed(int keycode) {
        return keysDown.contains(keycode);
    }

}
