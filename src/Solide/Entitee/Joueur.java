package Solide.Entitee;

import Solide.Hitbox;

public class Joueur extends Entitee {

    private Hitbox hitbox;

    public Joueur(int maxhp, int id) {
        super(maxhp, id);
        this.x = 0; // case de map
        this.y = 0; // case de map
        this.hitbox = new Hitbox(x, y);
    }

    public Joueur(int maxhp, int id, int x, int y) {
        super(maxhp, id, x, y);
        this.hitbox = new Hitbox(x, y);
    }

    public Hitbox geHitbox() {
        return hitbox;
    }
}
