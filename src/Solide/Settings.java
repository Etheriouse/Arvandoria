package Solide;
import java.awt.Image;

public class Settings {

    /**
     * Addapte la valeur d'une texture a sont emplacement
     *
     * @param valueOfSprite la valeur de l'objet
     * @param t             un tableau de 3 par 3 avec en sont centre la sprite a
     *                      determiner
     * @return le nombre associer a la sprite 1-48
     */
    public static int getGoodSpriteByPlacement(int valueOfSprite, int[][] t) {

        int x11 = t[0][0];
        int x12 = t[0][1];
        int x13 = t[0][2];
        int x21 = t[1][0];
        int x23 = t[1][2];
        int x31 = t[2][0];
        int x32 = t[2][1];
        int x33 = t[2][2];

        if (x11 == valueOfSprite && x12 == valueOfSprite && x13 == valueOfSprite &&
                x21 == valueOfSprite && x23 == valueOfSprite &&
                x31 == valueOfSprite && x32 == valueOfSprite && x33 == valueOfSprite) {
            return 34;
        } else if (x11 != valueOfSprite && x12 != valueOfSprite && x13 != valueOfSprite &&
                x21 != valueOfSprite && x23 != valueOfSprite &&
                x31 != valueOfSprite && x32 != valueOfSprite && x33 != valueOfSprite) {
            return 37;
        } else if (x11 != valueOfSprite && x12 != valueOfSprite && x13 != valueOfSprite &&
                x21 == valueOfSprite && x23 == valueOfSprite &&
                x31 != valueOfSprite && x32 != valueOfSprite && x33 != valueOfSprite) {

            return 39;
        } else if (x11 != valueOfSprite && x12 != valueOfSprite && x13 != valueOfSprite &&
                x21 == valueOfSprite && x23 != valueOfSprite &&
                x31 != valueOfSprite && x32 != valueOfSprite && x33 != valueOfSprite) {

            return 40;
        } else if (x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
                x21 != valueOfSprite && x23 != valueOfSprite &&
                x31 != valueOfSprite && x32 != valueOfSprite && x33 != valueOfSprite) {
            return 25;
        } else if (x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
                x21 != valueOfSprite && x23 != valueOfSprite &&
                x31 != valueOfSprite && x32 == valueOfSprite && x33 != valueOfSprite) {
            return 13;

        } else if (x11 != valueOfSprite && x12 != valueOfSprite && x13 != valueOfSprite &&
                x21 != valueOfSprite && x23 != valueOfSprite &&
                x31 != valueOfSprite && x32 == valueOfSprite && x33 != valueOfSprite) {
            return 1;
        } else if (x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
                x21 == valueOfSprite && x23 == valueOfSprite &&
                x31 != valueOfSprite && x32 == valueOfSprite && x33 != valueOfSprite) {
            return 26;
        } else if (x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
                x21 != valueOfSprite && x23 == valueOfSprite &&
                x31 != valueOfSprite && x32 != valueOfSprite && x33 != valueOfSprite) {
            return 28;
        } else if (x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
                x21 == valueOfSprite && x23 != valueOfSprite &&
                x31 != valueOfSprite && x32 != valueOfSprite && x33 != valueOfSprite) {
            return 4;
        } else if (x11 != valueOfSprite && x12 != valueOfSprite && x13 != valueOfSprite &&
                x21 == valueOfSprite && x23 != valueOfSprite &&
                x31 != valueOfSprite && x32 == valueOfSprite && x33 != valueOfSprite) {
            return 2;
        } else if (x11 != valueOfSprite && x12 != valueOfSprite && x13 != valueOfSprite &&
                x21 != valueOfSprite && x23 == valueOfSprite &&
                x31 != valueOfSprite && x32 == valueOfSprite && x33 != valueOfSprite) {
            return 16;
        } else if (x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
                x21 == valueOfSprite && x23 != valueOfSprite &&
                x31 != valueOfSprite && x32 == valueOfSprite && x33 != valueOfSprite) {
            return 3;
        } else if (x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
                x21 != valueOfSprite && x23 == valueOfSprite &&
                x31 != valueOfSprite && x32 == valueOfSprite && x33 != valueOfSprite) {
            return 14;
        } else if (x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
                x21 == valueOfSprite && x23 == valueOfSprite &&
                x31 != valueOfSprite && x32 != valueOfSprite && x33 != valueOfSprite) {
            return 27;
        } else if (x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
                x21 == valueOfSprite && x23 == valueOfSprite &&
                x31 != valueOfSprite && x32 == valueOfSprite && x33 != valueOfSprite) {
            return 15;
        } else {
            return 37;
        }
    }

    /**
     * Renvoie un tableau de 3x3 de centre de coordonner x y du monde donner en
     * parametre
     *
     * @param x la coordonner x
     * @param y la coordonner y
     * @param m le monde m
     * @return renvoie un tableau de 3x3
     */
    public static int[][] getThreeArray2D(int x, int y, Monde m) {
        Objet[][] world = m.getFloor();
        int array[][] = new int[3][3];

        for (int i = y - 1, a = 0; i < y + 2; a++, i++) {
            for (int j = x - 1, b = 0; j < x + 2; b++, j++) {
                if (i < 0 || j < 0 || i > world.length - 1 || j > world[i].length - 1) {
                    array[a][b] = -1;
                } else {
                    array[a][b] = world[i][j].id;
                }
            }
        }

        return array;
    }

    public static Image getImageFromNumber(int i) {
        switch (i) {
            case 34:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_34.png");
            case 37:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_37.png");
            case 39:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_39.png");
            case 40:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_40.png");
            case 25:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_25.png");
            case 13:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_13.png");
            case 1:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_1.png");
            case 26:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_26.png");
            case 28:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_28.png");
            case 4:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_4.png");
            case 2:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_2.png");
            case 16:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_16.png");
            case 3:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_3.png");
            case 14:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_14.png");
            case 27:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_27.png");
            case 15:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_15.png");
            default:
                return Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_34.png");
        }
    }

    public static void main(String[] args) {
        Monde m = new Monde(3, 3);
        m.setCaseFloor(new Bloc(0), 0, 0);
        m.setCaseFloor(new Bloc(1), 1, 0);
        m.setCaseFloor(new Bloc(1), 2, 0);
        m.setCaseFloor(new Bloc(1), 0, 1);
        m.setCaseFloor(new Bloc(1), 1, 1);
        m.setCaseFloor(new Bloc(1), 2, 1);
        m.setCaseFloor(new Bloc(1), 0, 2);
        m.setCaseFloor(new Bloc(1), 1, 2);
        m.setCaseFloor(new Bloc(1), 2, 2);
        m.show(true, 0);
        int[][] t = getThreeArray2D(1, 1, m);
        for (int[] is : t) {
            for (int i : is) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}