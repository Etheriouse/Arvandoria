package Solide;

public class Monde {

    private Objet[][] entitee;
    private Objet[][] objet;
    private Objet[][] floor;

    public Monde() {
        this.floor = new Objet[10][10];
        for(int i = 0; i<10; i++) {
            for(int y = 0; y<10; y++) {
                this.floor[i][y] = new Bloc();
            }
        }
    }

    public Monde(int x, int y) {
        this.floor = new Objet[y][x];
        for(int i = 0; i<y; i++) {
            for(int j = 0; j<x; j++) {
                this.floor[i][j] = new Bloc();
            }
        }
    }

    public void show(boolean number) {
        for (Objet[] objets : floor) {
            for (Objet objet : objets) {
                if(number) {
                    System.out.print(objet.id+" ");
                } else {
                    switch (objet.id) {
                        case -1:
                            // error ou non defini
                            System.out.print("! ");
                            break;
                        case 0:
                            // herbe
                            System.out.print("* ");
                            break;
                        case 1:
                            // rocher
                            System.out.print("% ");
                            break;
                        case 2:
                            // mur ou border
                            System.out.print("& ");
                            break;
                        case 3:
                            // ville
                            System.out.print("@ ");
                            break;
                        case 4:
                            // batiment utilitaire
                            System.out.print("& ");
                            break;

                        default:
                            break;
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void render() {

    }


    public Objet[][] getFloor() {
        return floor;
    }

    public Objet[][] getEntitee() {
        return entitee;
    }

    public Objet[][] getObjet() {
        return objet;
    }


    public void setFloor(Objet[][] map) {
        this.floor = map;
    }

    public void setEntitee(Objet[][] entitee) {
        this.entitee = entitee;
    }

    public void setObjet(Objet[][] objet) {
        this.objet = objet;
    }
}
