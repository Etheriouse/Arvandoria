package Solide;


public class Bloc extends Objet {

    private String texture_path;

    public Bloc() {
        super();
        this.texture_path = "none";
    }
    public Bloc(int i) {
        super(i);
        this.texture_path = "none";
    }

    public Bloc(String texture_path) {
        super();
        this.texture_path = texture_path;
    }

    public Bloc(String texture_path, int id) {
        super(id);
        this.texture_path = texture_path;
    }


    public String getTexture_path() {
        return texture_path;
    }

    public void setTexture_path(String texture_path) {
        this.texture_path = texture_path;
    }

    @Override
    public String toString() {
        switch (id) {
            case 0:
                return "water";
            case 1:
                return "grass";
            case 2:
                return "sand";
            case 3:
                return "forest";
            case 4:
                return "moutain";
            default:
                return "not found";
        }
    }

}
