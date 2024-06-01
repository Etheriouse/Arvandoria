package Solide.Entitee;

import Solide.Hitbox;

public class Joueur extends Entitee {

    private Hitbox hitbox;

    public Joueur(int maxhp, int id) {
        super(maxhp, id);
    }

    public Joueur(int maxhp, int id, String name) {
        super(maxhp, id, name);
    }

    public Hitbox geHitbox() {
        return hitbox;
    }
}
