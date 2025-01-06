package Solide.Entitee;

import Solide.Bloc;
import Solide.Civilisation;
import Solide.Objet;
import Solide.Settings;

public abstract class Entitee extends Objet {

    protected int max_hp;
    protected int hp;
    protected String name;
    protected Civilisation civ;

    public Entitee() {
        super();
        this.max_hp = 10;
        this.hp = 10;
    }

    public Entitee(int maxhp) {
        this.max_hp = maxhp;
        this.hp = maxhp;
    }

    public Entitee(int maxhp, int id) {
        super(id);
        this.max_hp = maxhp;
        this.hp = maxhp;
    }

    public Entitee(int maxhp, int id, String name) {
        super(id);
        this.max_hp = maxhp;
        this.hp = maxhp;
        this.name = name;
    }

    public Entitee(int maxhp, int id, String name, Civilisation civ) {
        super(id);
        this.max_hp = maxhp;
        this.hp = maxhp;
        this.name = name;
        this.civ = civ;
    }

    public Civilisation getCvCivilisation() {
        return this.civ;
    }

    public boolean canMoveOnThisBlock(Bloc b) {
        return b.id != Settings.WATER_ID;
    }

    public int getMax_hp() {
        return max_hp;
    }

    public void setMax_hp(int max_hp) {
        this.max_hp = max_hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        return super.toString() + " " + name + " hp: " + hp+"/"+max_hp;
    }
}
