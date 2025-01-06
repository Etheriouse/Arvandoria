package Solide.Entitee;

import Solide.Bloc;
import Solide.Civilisation;

public class Unite extends Entitee {

    private int atk; // nb point attaque
    private int def; // nb point defense
    private int mov; // nb point movement

    /**
     * vie / atk / def / mov
     * Chevalier: 10 / 7 / 3 / 5
     * Catapulte: 8 / 10 / 4 / 3
     * Archer: 8 / 7 / 2 / 7
     * Cavalier: 15 / 8 / 4 / 9
     */

    public Unite(int maxhp, int id, int atk, int def, int mov) {
        super(maxhp, id);
        this.atk = atk;
        this.def = def;
        this.mov = mov;
    }

    public Unite(int maxhp, int id, String name, int atk, int def, int mov) {
        super(maxhp, id, name);
        this.atk = atk;
        this.def = def;
        this.mov = mov;
    }

    public Unite(int maxhp, int id, String name, Civilisation civ, int atk, int def, int mov) {
        super(maxhp, id, name, civ);
        this.atk = atk;
        this.def = def;
        this.mov = mov;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getMov() {
        return mov;
    }
}
