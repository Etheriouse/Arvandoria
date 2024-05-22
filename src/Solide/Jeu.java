package Solide;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Toolkit;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.TreeSet;
import java.util.TreeMap;
import javax.swing.ImageIcon;

public class Jeu extends JFrame {

    private int X;
    private int Y;

    private int width = 1600;
    private int height = 900;

    private int mouseX = 0;
    private int mouseY = 0;
    private boolean mouseClicked = false;

    private TreeMap<String, Long> cooldown = new TreeMap<>();

    private int textureSize = 50;

    private int posCameraX = X*textureSize/2;
    private int posCameraY = Y*textureSize/2;

    //private int rotation = 0;
    //private float zoom = 1.0f;

    private int MarginTop = 0;


    private double fps = 0;

    private BufferedImage onscreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    private BufferedImage offscreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    private Graphics2D onscreen = onscreenImage.createGraphics();
    private Graphics2D offscreen = offscreenImage.createGraphics();

    private static TreeSet<Integer> keysDown;
    private static Hashtable<Integer, String> nomsTouches = new Hashtable<Integer, String>();

    public Jeu() {
        this.X = 1000;
        this.Y = 1000;
    }

    private void setup() {
        Settings.setup();
        this.setTitle("game");
        this.setResizable(false);
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        Image Cursor = getImage("assets/cursor.png");
        Cursor gauntletCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                Cursor, new Point(0, 0), "gauntlet cursor");
        this.setCursor(gauntletCursor);
        this.setContentPane(new JLabel(new ImageIcon(onscreenImage)));
        this.pack();
        this.requestFocusInWindow();
        this.setVisible(true);
        keysDown = new TreeSet<Integer>();

        nomsTouches.put(KeyEvent.VK_CONTROL, "CTRL");
        nomsTouches.put(KeyEvent.VK_ADD, "Add");
        nomsTouches.put(KeyEvent.VK_SUBTRACT, "Sub");
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

        MarginTop = this.getInsets().top;
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY()-MarginTop;

                mouseClicked = true;
                // TODO Auto-generated method stub
                //throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                //throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                //throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                //throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                //throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
            }

        });

        this.addMouseWheelListener(new MouseWheelListener() {

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if(e.getWheelRotation() < 0 ) {
                    unzoom(e);
                } else {
                    zoom(e);
                }
                System.out.println(textureSize);
            }

        });

        cooldown.put("ZoomIn", 0L);
        cooldown.put("ZoomOut", 0L);
        cooldown.put("resetZoom", 0L);
    }

    public void zoom(MouseWheelEvent e) {
        int xtemp = posCameraX/textureSize;
        int ytemp = posCameraY/textureSize;
         textureSize-=5;
        if(textureSize < 2) {
            textureSize = 2;
        }
        //RAY LIB CONNARD !
        posCameraX = xtemp*textureSize;
        posCameraY = ytemp*textureSize;
        //posCameraX-=334;
        //posCameraY-=198;
        //System.out.println(e.getX());
        //posCameraX-=(7*textureSize);
        //posCameraY-=(6*textureSize);
        //posCameraX+=(5*(width/textureSize))/2;
        //posCameraX = e.getX()+(posCameraX-(width/2));
        //posCameraY = e.getY()+(posCameraY-(height/2));
    }

    public void unzoom(MouseWheelEvent e) {
        int xtemp = posCameraX/textureSize;
        int ytemp = posCameraY/textureSize;
        textureSize+=5;
        if(textureSize > 500) {
            textureSize = 500;
        }
        posCameraX = xtemp*textureSize;
        posCameraY = ytemp*textureSize;
       //posCameraX = e.getX()+(posCameraX-(width/2));
        //posCameraY = e.getY()+(posCameraY-(height/2));
    }

    public void run() {
        Monde carte = new Monde(this.X, this.Y, 0.4);

//        posCameraX =
//        posCameraY =

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



            if(System.currentTimeMillis()-cooldown.get("resetZoom") > 100) {
                if(keysDown.contains(KeyEvent.VK_CONTROL) && keysDown.contains(KeyEvent.VK_F)) {
                    textureSize = 50;
                    cooldown.put("resetZoom", System.currentTimeMillis());
                }
            }


            if(System.currentTimeMillis()-cooldown.get("ZoomIn") > 100) {
                if(keysDown.contains(KeyEvent.VK_CONTROL) && keysDown.contains(KeyEvent.VK_ADD)) {
                    int xtemp = posCameraX/textureSize;
                    int ytemp = posCameraY/textureSize;
                    textureSize*=2;
                    if(textureSize > 500) {
                        textureSize = 500;
                    }
                    cooldown.put("ZoomIn", System.currentTimeMillis());
                    posCameraX = xtemp*textureSize;
                    posCameraY = ytemp*textureSize;
                }
            }

            if(System.currentTimeMillis()-cooldown.get("ZoomOut") > 100) {
                if(keysDown.contains(KeyEvent.VK_CONTROL) && keysDown.contains(KeyEvent.VK_SUBTRACT)) {
                    int xtemp = posCameraX/textureSize;
                    int ytemp = posCameraY/textureSize;

                    textureSize/=2;
                    if(textureSize < 10) {
                        textureSize = 10;
                    }
                    cooldown.put("ZoomOut", System.currentTimeMillis());
                    posCameraX = xtemp*textureSize;
                    posCameraY = ytemp*textureSize;
                }
            }

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

            if (posCameraX < width/2) {
                posCameraX = width/2;
            }

            if (posCameraY < height/2) {
                posCameraY = height/2;
            }

            if (posCameraX+(width/2) > ((X) * textureSize)) {
                posCameraX = (X*textureSize)-(width/2);
            }

            // System.out.println((Y * 50) - 550);
            if (posCameraY+(height/2) > (Y * textureSize)) {
                posCameraY = (Y * textureSize) - (height/2);
            }

            // System.out.print("x: " + posCameraX + " y: " + posCameraY);
            // System.out.println();
            if(mouseClicked) {
                System.out.println("X: " + mouseX + " Y: " + mouseY);
                System.out.println("a: " + mouseX/textureSize + " b: " + mouseY / textureSize);
                System.out.println("id texture: " + carte.getFloor()[mouseY/textureSize][mouseX/textureSize].id);
            }
            mouseClicked = false;
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

        int Ts = textureSize;
        offscreen.setColor(Color.BLACK);
        offscreen.setFont(new Font("Arial", Font.PLAIN, 10));
        carte.rendere(Ts, posCameraX, posCameraY, width, height, offscreen);

        offscreen.drawString("fps " + fps, 2, 20);
        offscreen.setColor(Color.RED);
        offscreen.setFont(new Font("Arial", Font.PLAIN, 50));
        offscreen.drawString(".", width/2, height/2);
        offscreen.setColor(Color.BLACK);

        rafraichir();

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
    @SuppressWarnings("unused")
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
