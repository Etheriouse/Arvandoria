public class Bloc extends Objet {

    private String texture_path;

    public Bloc() {
        super();
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

}
