public class Settings {

    /**
     *
     * @param valueOfSprite la valeur de l'objet
     * @param t un tableau de 3 par 3 avec en sont centre la sprite a determiner
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

        if(
            x11 == valueOfSprite && x12 == valueOfSprite && x13 == valueOfSprite &&
            x21 == valueOfSprite && x23 == valueOfSprite &&
            x31 == valueOfSprite && x32 == valueOfSprite && x33 == valueOfSprite
        ){
            return 34;
        } else if(
            x11 != valueOfSprite && x12 != valueOfSprite && x13 != valueOfSprite &&
            x21 != valueOfSprite && x23 != valueOfSprite &&
            x31 != valueOfSprite && x32 != valueOfSprite && x33 != valueOfSprite
        ){
            return 37;
        }else if(
            x11 != valueOfSprite && x12 != valueOfSprite && x13 != valueOfSprite &&
            x21 == valueOfSprite && x23 == valueOfSprite &&
            x31 != valueOfSprite && x32 != valueOfSprite && x33 != valueOfSprite
        ){

            return 39;
        }else if(
            x11 != valueOfSprite && x12 != valueOfSprite && x13 != valueOfSprite &&
            x21 == valueOfSprite && x23 != valueOfSprite &&
            x31 != valueOfSprite && x32 != valueOfSprite && x33 != valueOfSprite
        ){

            return 40;
        }else if(
            x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
            x21 != valueOfSprite && x23 != valueOfSprite &&
            x31 != valueOfSprite && x32 != valueOfSprite && x33 != valueOfSprite
        ){
            return 25;
        }else if(
            x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
            x21 != valueOfSprite && x23 != valueOfSprite &&
            x31 != valueOfSprite && x32 == valueOfSprite && x33 != valueOfSprite
        ){
            return 13;

        }else if(
            x11 != valueOfSprite && x12 != valueOfSprite && x13 != valueOfSprite &&
            x21 != valueOfSprite && x23 != valueOfSprite &&
            x31 != valueOfSprite && x32 == valueOfSprite && x33 != valueOfSprite
        ){
            return 1;
        }else if(
            x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
            x21 == valueOfSprite && x23 == valueOfSprite &&
            x31 != valueOfSprite && x32 == valueOfSprite && x33 != valueOfSprite
        ) {
            return 26;
        }else if(
            x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
            x21 != valueOfSprite && x23 == valueOfSprite &&
            x31 != valueOfSprite && x32 != valueOfSprite && x33 != valueOfSprite
        ) {
            return 28;
        }else if(
            x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
            x21 == valueOfSprite && x23 != valueOfSprite &&
            x31 != valueOfSprite && x32 != valueOfSprite && x33 != valueOfSprite
        ) {
            return 4;
        }else if(
            x11 != valueOfSprite && x12 != valueOfSprite && x13 != valueOfSprite &&
            x21 == valueOfSprite && x23 != valueOfSprite &&
            x31 != valueOfSprite && x32 == valueOfSprite && x33 != valueOfSprite
        ) {
            return 2;
        }else if(
            x11 != valueOfSprite && x12 != valueOfSprite && x13 != valueOfSprite &&
            x21 != valueOfSprite && x23 == valueOfSprite &&
            x31 != valueOfSprite && x32 == valueOfSprite && x33 != valueOfSprite
        ) {
            return 16;
        }else if(
            x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
            x21 == valueOfSprite && x23 != valueOfSprite &&
            x31 != valueOfSprite && x32 == valueOfSprite && x33 != valueOfSprite
        ) {
            return 3;
        }else if(
            x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
            x21 != valueOfSprite && x23 == valueOfSprite &&
            x31 != valueOfSprite && x32 == valueOfSprite && x33 != valueOfSprite
        ) {
            return 14;
        }else if(
            x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
            x21 == valueOfSprite && x23 == valueOfSprite &&
            x31 != valueOfSprite && x32 != valueOfSprite && x33 != valueOfSprite
        ) {
            return 27;
        }else if(
            x11 != valueOfSprite && x12 == valueOfSprite && x13 != valueOfSprite &&
            x21 == valueOfSprite && x23 == valueOfSprite &&
            x31 != valueOfSprite && x32 == valueOfSprite && x33 != valueOfSprite
        ) {
            return 15;
        } else {
            return 37;
        }

        /*
         *
         * x = value
         * o = other
         *
         * si aucun cas alors cas 37
         *
         * x x x
         * x x x = 34
         * x x x
         *
         * o o o
         * o x o = 37
         * o o o
         *
         * o o o
         * o x x = 38
         * o o o

         * o o o
         * x x x = 39
         * o o o

         * o o o
         * x x o = 40
         * o o o

         * o x o
         * o x o = 25
         * o o o

         * o x o
         * o x o = 13
         * o x o
         *
         * o o o
         * o x o = 1
         * o x o
        /---------------------/
        */

        /*
         * o x o
         * o x x = 26
         * o o o
         *
         * o x o
         * x x o = 28
         * o o o
         *
         * o o o
         * x x o = 4
         * o x o
         *
         * o o o
         * o x x = 2
         * o x o
         *
         * o x o
         * x x o = 16
         * o x o
         *
         * o o o
         * x x x = 3
         * o x o
         *
         * o x o
         * o x x = 14
         * o x o
         *
         * o x o
         * x x x = 27
         * o o o
         *
         * o x o
         * x x x = 15
         * o x o
         */
    }

}
