package Solide;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.TreeSet;
import javax.swing.ImageIcon;

public class Jeu extends JFrame {

    private int X;
    private int Y;

    private int posCameraX;
    private int posCameraY;

    private BufferedImage onscreenImage = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB);
    private BufferedImage offscreenImage = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB);

    private Graphics2D onscreen = onscreenImage.createGraphics();
    private Graphics2D offscreen = offscreenImage.createGraphics();

    private Image herbe = getImage("assets/herbe.png");
    private Image mur = getImage("assets/mur.png");
    private Image rocher = getImage("assets/rocher.png");
    private Image ville = getImage("assets/ville.png");
    private Image forge = getImage("assets/batiment_forge.png");
    private Image habitation = getImage("assets/batiment_habitation.png");
    private Image ferme = getImage("assets/batiment_ferme.png");
    private Image error = getImage("assets/error.png");
    private Image none = getImage("assets/none.png");

    private static TreeSet<Integer> keysDown;
    private static Hashtable<Integer, String> nomsTouches = new Hashtable<Integer, String>();

    public Jeu() {
        this.X = 20;
        this.Y = 20;
    }

    public void setup() {
        this.setTitle("game");
        this.setResizable(false);
        this.setSize(1600, 900);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        Image Cursor = new ImageIcon("assets/gantlet.png").getImage();
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
    }

    public void run() {
        Monde carte = new Monde(this.X, this.Y);
        Batiment ville = new Batiment(3);
        Batiment forge = new Batiment(4);
        Batiment ferme = new Batiment(5);
        Batiment habitation = new Batiment(6);
        Obstacle rocher = new Obstacle(1);
        Bloc herbe = new Bloc(0);
        Obstacle mur = new Obstacle(2);
        carte.getFloor()[0][0] = herbe;
        carte.getFloor()[0][1] = ville;
        carte.getFloor()[0][2] = rocher;
        carte.getFloor()[0][3] = mur;
        carte.getFloor()[0][4] = ferme;
        carte.getFloor()[0][5] = forge;
        carte.getFloor()[0][6] = habitation;
        setup();
        boolean inGame = true;
        while (inGame) {
        }
    }

    public void render(Monde carte) {

    int xmoin = posCameraX - 800;
    int parseXmoin = xmoin / 50;
    int startCal = (parseXmoin * 50) - xmoin;

    int ymoin = posCameraY - 450;
    int parseYmoin = ymoin / 50;
    int startCalY = (parseYmoin * 50) - ymoin;

        for (int y = startCalY - 50, u = -1; y < startCalY + 950; u += 1, y += 50)
        {
            for (int i = startCal - 50, c = -1; i < startCal + 1650; c += 1, i += 50)
            {

                //rect = {i, y, 50, 50};
                if (parseXmoin + c < 0)
                {
                  //  printf("here\n");
                }
                if (parseYmoin + u < 0)
                {
                    //printf("here\n");
                }
                // std::cout << "x: " << i / 50 << " y: " << y / 50 << std::endl;
                switch (carte.getFloor()[parseXmoin + c][parseYmoin + u].id) {
                    case -1:
                        offscreen.drawImage(none, y, i, 50, 50, null);
                        break;
                    case 0:
                        // herbe
                        offscreen.drawImage(herbe, y, i, 50, 50, null);
                        break;
                    case 1:
                        // rocher
                        offscreen.drawImage(rocher, y, i, 50, 50, null);
                        break;
                    case 2:
                        // mur ou border
                        offscreen.drawImage(mur, y, i, 50, 50, null);
                        break;
                    case 3:
                        // ville
                        offscreen.drawImage(ville, y, i, 50, 50, null);
                        break;
                    case 4:
                        // batiment utilitaire
                        offscreen.drawImage(forge, y, i, 50, 50, null);
                        break;
                    case 5:
                        // batiment utilitaire
                        offscreen.drawImage(ferme, y, i, 50, 50, null);
                        break;
                    case 6:
                        // batiment utilitaire
                        offscreen.drawImage(habitation, y, i, 50, 50, null);
                        break;


                    default:
                        offscreen.drawImage(error, y, i, 50, 50, null);
                    break;
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
    public void rafraichir() {
        onscreen.drawImage(offscreenImage, 0, 0, null);
        this.repaint();
    }

    /**
     * Nettoie le canvas
     */
    public void nettoyer() {
        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, X, Y);
        offscreen.setColor(Color.BLACK);
        this.rafraichir();
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
    public static String trouveProchaineEntree() {
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
