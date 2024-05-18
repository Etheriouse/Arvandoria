package Solide.Batiments;

import Solide.Monde;
import Solide.Objet;

public abstract class Batiment extends Objet {

    public Batiment() {
        super();
    }

    public Batiment(int i) {
        super(i);
    }

    public abstract void apply(Monde m);

}
