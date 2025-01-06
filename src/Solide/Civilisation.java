package Solide;

public enum Civilisation {
    AE("Aethériens"),
    LU("Lunéthiques"),
    PY("Pyrosiens"),
    SY("Sylvaris");

    private String name;

    private Civilisation(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
