public class Monde {

    private Objet[][] map;

    public Monde() {
        this.map = new Objet[10][10];
    }

    public Monde(int x, int y) {
        this.map = new Objet[y][x];
    }

    public Objet[][] getMap() {
        return map;
    }

    public void setMap(Objet[][] map) {
        this.map = map;
    }

}
