package Solide.Entitee;

import Solide.Objet;

public abstract class Entitee extends Objet {

    protected int max_hp;
    protected int hp;
    protected int x;
    protected int y;


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

    public Entitee(int maxhp, int id, int x, int y) {
        super(id);
        this.x = x;
        this.y = y;
        this.max_hp = maxhp;
        this.hp = maxhp;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
