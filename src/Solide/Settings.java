package Solide;

import java.awt.Image;
import java.util.TreeMap;


public class Settings {

    private static Image mur =Jeu.getImage("assets/mur.png");
    private static Image rocher =Jeu.getImage("assets/rocher.png");
    private static Image ville =Jeu.getImage("assets/ville.png");
    private static Image forge =Jeu.getImage("assets/forge.png");
    private static Image habitation =Jeu.getImage("assets/habitation.png");
    private static Image ferme =Jeu.getImage("assets/ferme.png");
    private static Image error =Jeu.getImage("assets/error.png");
    private static Image none =Jeu.getImage("assets/none.png");

    private static Image eau =Jeu.getImage("assets/tilemap/water/frame1sprite/frame1_water_34.png");
    private static Image herbe = Jeu.getImage("assets/herbe.png");
    private static Image montagne =Jeu.getImage("assets/moutaine.png");
    private static Image sable = Jeu.getImage("assets/sand.png");
    private static Image foret =Jeu.getImage("assets/tree.png");

    private static Image joueur =Jeu.getImage("assets/cursor.png");


    static final TreeMap<String, Image> Textures = new TreeMap<>();

    public static void setup() {
        Textures.put("Herbe", herbe);
        Textures.put("Eau", eau);
        Textures.put("Montagne", montagne);
        Textures.put("Sable", sable);
        Textures.put("Foret", foret);
        Textures.put("Mur", mur);
        Textures.put("Rocher", rocher);
        Textures.put("Ville", ville);
        Textures.put("Forge", forge);
        Textures.put("Habitation", habitation);
        Textures.put("Ferme", ferme);
        Textures.put("Error", error);
        Textures.put("None", none);
        Textures.put("Joueur", joueur);
    }

    public static void CalculePlusPetitDiviseurCommun(int a, int b, int echantillons) {
        for(int i = 2; i<echantillons; i++) {
            if(a%i == 0 && b%i == 0) {
                System.out.println(i+" est un diviseur commun de " + a + " et de " + b +".");
            }
        }
    }

    /**
     * Addapte la valeur d'une texture a sont emplacement
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
        // Monde m = new Monde(3, 3);
        // m.setCaseFloor(new Bloc(0), 0, 0);
        // m.setCaseFloor(new Bloc(1), 1, 0);
        // m.setCaseFloor(new Bloc(1), 2, 0);
        // m.setCaseFloor(new Bloc(1), 0, 1);
        // m.setCaseFloor(new Bloc(1), 1, 1);
        // m.setCaseFloor(new Bloc(1), 2, 1);
        // m.setCaseFloor(new Bloc(1), 0, 2);
        // m.setCaseFloor(new Bloc(1), 1, 2);
        // m.setCaseFloor(new Bloc(1), 2, 2);
        // m.show(true, 0);
        // int[][] t = getThreeArray2D(1, 1, m);
        // for (int[] is : t) {
        //     for (int i : is) {
        //         System.out.print(i + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();

        CalculePlusPetitDiviseurCommun(1600, 900, 500);
    }
}
