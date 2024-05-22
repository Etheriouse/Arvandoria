package Solide;
public abstract class Entitee extends Objet {

    private int max_hp;
    private int hp;

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

}
