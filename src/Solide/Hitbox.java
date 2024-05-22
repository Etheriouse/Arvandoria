package Solide;

public class Hitbox {

    public int x;
    public int y;

    public Hitbox() {
        x = 0;
        y = 0;
    }

    public Hitbox(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isCollision(int _x, int _y, int Ts) {

        boolean a = false, b = false;

        a = x == _x/Ts;
        b = y == _y/Ts;

        if(a && b) {
            return true;
        } else {
            return false;
        }
    }

}

// /**
//  * @param block l'id du block a detecter
//  * @param v1 les 2 point de gauche x haut y bas de la collision
//  * @param v1 les 2 point de droite x haut y bas de la collision
//  * @return si la collision est detecter dans la zone definie
//  */
// bool interface::checkColision(int block, rectangle r)
// {
//     int x = m->getPosPlayerX();
//     int y = m->getPosPlayerY();

//     /*
//      * a b
//      * c d
//      */

//     bool a = m->getSurface((x + r.a) / 50, (y + r.b) / 50) == block;
//     bool b = m->getSurface((x + r.c) / 50, (y + r.b) / 50) == block;
//     bool c = m->getSurface((x + r.a) / 50, (y + r.d) / 50) == block;
//     bool d = m->getSurface((x + r.c) / 50, (y + r.d) / 50) == block;

//     if (a || b || c || d)
//     {
//         return true;
//     }
//     else
//     {
//         return false;
//     }
// }

// bool interface::checkColision(int block)
// {
//     int x = m->getPosPlayerX();
//     int y = m->getPosPlayerY();

//     if (m->getSurface((x + 50) / 50, (y + 50) / 50) == block || m->getSurface((x + 0) / 50, (y + 50) / 50) == block)
//     {
//         return true;
//     }
//     else
//     {
//         return false;
//     }
// }