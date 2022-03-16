package models;

public enum PanelMaterial {
    POLY_SI("Multicrystalline Silicon", "poly-Si"),
    MONO_SI("Monocrystalline Silicon", "mono-Si"),
    A_SI("Amorphous Silicon", "a-Si"),
    CD_TE("Cadmium Telluride", "CdTe"),
    CIGS("Copper Indium Gallium Selenide", "CIGS");

    private final String title;
    private final String abrv;

    PanelMaterial(String title, String abrv) {
        this.title = title;
        this.abrv = abrv;
    }

    public String getTitle() {
        return title;
    }
}
