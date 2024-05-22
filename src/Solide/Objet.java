package Solide;

import Solide.Batiments.*;

public abstract class Objet {

    public int id;

    public Objet() {
        id = -1;
    }

    public Objet(int _id) {
        this.id = _id;
    }

    public static Objet getObjet(int i) {
        switch (i) {
            case 10:
                return new Caserne(i);

            case 11:
                return new Forge(i);

            case 12:
                return new Ferme(i);

            case 13:
                return new Ville(i);

            default:
                return new Caserne(-1);
        }
    }

}
