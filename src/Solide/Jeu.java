package Solide;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Solide.Entitee.Joueur;

import java.awt.Toolkit;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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

    private int Ts = 50;

    private int posCameraX;
    private int posCameraY;

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
        posCameraX = 40;
        posCameraY = 40;
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
        MarginTop = this.getInsets().top;

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

        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub
                // throw new UnsupportedOperationException("Unimplemented method
                // 'mouseDragged'");
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY() - MarginTop;
            }

        });

        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // mouseClicked = true;
                // System.out.println("click");
                // ne jamais utiliser sa sa marche une fois sur 3 c de la daube
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseClicked = true;
                System.out.println("click");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseClicked = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                // throw new UnsupportedOperationException("Unimplemented method
                // 'mouseEntered'");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                // throw new UnsupportedOperationException("Unimplemented method
                // 'mouseExited'");
            }

        });

        this.addMouseWheelListener(new MouseWheelListener() {

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) {
                    unzoom(e);
                } else {
                    zoom(e);
                }
                System.out.println(Ts);
            }

        });

        cooldown.put("ZoomIn", 0L);
        cooldown.put("ZoomOut", 0L);
        cooldown.put("resetZoom", 0L);
    }

    public void zoom(MouseWheelEvent e) {
        int xtemp = posCameraX / Ts;
        int ytemp = posCameraY / Ts;
        Ts -= 5;
        if (Ts < 2) {
            Ts = 2;
        }
        // RAY LIB CONNARD !
        posCameraX = xtemp * Ts;
        posCameraY = ytemp * Ts;
    }

    public void unzoom(MouseWheelEvent e) {
        int xtemp = posCameraX / Ts;
        int ytemp = posCameraY / Ts;
        Ts += 5;
        if (Ts > 700) {
            Ts = 700;
        }
        posCameraX = xtemp * Ts;
        posCameraY = ytemp * Ts;
    }

    public void checkColision(Hitbox b) {
        int Xx = posCameraX - (width / 2);
        int Yy = posCameraY - (height / 2);
        System.out.println("player touched: " + b.isCollision(Xx + mouseX, Yy + mouseY, Ts));
    }

    public void run() {
        Monde carte = new Monde(this.X, this.Y, 0.4);
        setup();

        Joueur player = new Joueur(20, 7, "player");
        Joueur knight = new Joueur(20, 8, "knight");
        Joueur archer = new Joueur(20, 9, "archer");
        Joueur catapult = new Joueur(20, 10, "catapult");
        carte.getEntitee()[5][9] = player;
        carte.getEntitee()[6][12] = knight;
        carte.getEntitee()[9][1] = archer;
        carte.getEntitee()[13][5] = catapult;

        final double max_speed = 7.0f;
        final double acc = 1.0f;
        double playervelocityX = 0.0f;
        double playervelocityY = 0.0f;

        boolean inGame = true;
        int frame = 0;

        long start = System.currentTimeMillis();
        long lastdelta = System.currentTimeMillis();

        boolean firstclick = false;

        int positionActuelEntiteeSelectX = 0;
        int positionActuelEntiteeSelectY = 0;

        while (inGame) {

            long now = System.currentTimeMillis();
            float delta = (now - lastdelta) / 10.00f;

            lastdelta = now;

            if (mouseClicked && !firstclick) {
                int tempx = ((posCameraX - (width / 2)) + mouseX)/Ts;
                int tempy = ((posCameraY - (height / 2)) + mouseY)/Ts;
                if (carte.getEntitee()[tempy][tempx] != null) {
                    positionActuelEntiteeSelectX = tempx;
                    positionActuelEntiteeSelectY = tempy;
                    firstclick = true;
                    mouseClicked = false;
                    carte.setSelecteurX(positionActuelEntiteeSelectX);
                    carte.setSelecteurY(positionActuelEntiteeSelectY);
                }
            }

            if (firstclick && mouseClicked) {
                int newCoordX = ((posCameraX - (width / 2)) + mouseX)/Ts;
                int newCoordY = ((posCameraY - (height / 2)) + mouseY)/Ts;

                if(newCoordX != positionActuelEntiteeSelectX || newCoordY != positionActuelEntiteeSelectY) {
                    carte.switchEntitee(positionActuelEntiteeSelectX, positionActuelEntiteeSelectY, newCoordX, newCoordY);
                    firstclick = false;
                    mouseClicked = false;
                    positionActuelEntiteeSelectX = newCoordX;
                    positionActuelEntiteeSelectY = newCoordY;
                    carte.setSelecteurX(-1);
                    carte.setSelecteurY(-1);
                } else {
                    carte.setSelecteurX(-1);
                    carte.setSelecteurY(-1);
                    firstclick = false;
                    mouseClicked = false;
                }
            }

            // if(mouseClicked) {
            //     mouseClicked = false;
            // }

            // System.out.println("Mouse if clicked: " + mouseClicked);
            // System.out.println("First click: " + firstclick);
            // System.out.println("Second click: " + secondclick);

            if (System.currentTimeMillis() - cooldown.get("resetZoom") > 100) {
                if (keysDown.contains(KeyEvent.VK_CONTROL) && keysDown.contains(KeyEvent.VK_F)) {
                    Ts = 50;
                    cooldown.put("resetZoom", System.currentTimeMillis());
                }
            }

            if (System.currentTimeMillis() - cooldown.get("ZoomIn") > 100) {
                if (keysDown.contains(KeyEvent.VK_CONTROL) && keysDown.contains(KeyEvent.VK_ADD)) {
                    int xtemp = posCameraX / Ts;
                    int ytemp = posCameraY / Ts;
                    Ts *= 2;
                    if (Ts > 700) {
                        Ts = 700;
                    }
                    cooldown.put("ZoomIn", System.currentTimeMillis());
                    posCameraX = xtemp * Ts;
                    posCameraY = ytemp * Ts;
                }
            }

            if (System.currentTimeMillis() - cooldown.get("ZoomOut") > 100) {
                if (keysDown.contains(KeyEvent.VK_CONTROL) && keysDown.contains(KeyEvent.VK_SUBTRACT)) {
                    int xtemp = posCameraX / Ts;
                    int ytemp = posCameraY / Ts;

                    Ts /= 2;
                    if (Ts < 10) {
                        Ts = 10;
                    }
                    cooldown.put("ZoomOut", System.currentTimeMillis());
                    posCameraX = xtemp * Ts;
                    posCameraY = ytemp * Ts;
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

            if (posCameraX < width / 2) {
                posCameraX = width / 2;
            }

            if (posCameraY < height / 2) {
                posCameraY = height / 2;
            }

            if (posCameraX + (width / 2) > ((X) * Ts)) {
                posCameraX = (X * Ts) - (width / 2);
            }

            // System.out.println((Y * 50) - 550);
            if (posCameraY + (height / 2) > (Y * Ts)) {
                posCameraY = (Y * Ts) - (height / 2);
            }

            // System.out.print("x: " + posCameraX + " y: " + posCameraY);
            // System.out.println();
            //if (mouseClicked) {
                //System.out.println("X: " + mouseX + " Y: " + mouseY);
                //System.out.println("a: " + mouseX / Ts + " b: " + mouseY / Ts);
                //System.out.println("id texture: " + carte.getFloor()[mouseY / Ts][mouseX / Ts].id);
            //    mouseClicked = false;
           ///}
            render(carte);
            frame++;

            long end = System.currentTimeMillis();
            if (end - start > 1000) {
                start = System.currentTimeMillis();
                fps = frame;
                frame = 0;
                // clear();
                //System.out.println(Ts);
                //System.out.println(" fps: " + fps);
                System.out.println("first click: " + firstclick);
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

        offscreen.setColor(Color.BLACK);
        offscreen.setFont(new Font("Arial", Font.PLAIN, 10));
        carte.rendere(Ts, posCameraX, posCameraY, width, height, offscreen);

        offscreen.drawString("fps " + fps, 2, 20);
        offscreen.setColor(Color.RED);
        offscreen.setFont(new Font("Arial", Font.PLAIN, 50));
        offscreen.drawString(".", width / 2, height / 2);
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
