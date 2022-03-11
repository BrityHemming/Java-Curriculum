package models;

public class Panel {
    // Fields
    private int id;
    private String section;
    private int row;
    private int column;
    private int yearInstalled;
    private Materials material;
    private boolean isTracking;
    private PanelKey key;

    // Constructor
    public Panel(int id, String section, int row, int column, int yearInstalled, Materials material, boolean isTracking){
        this.id = id;
        this.section = section;
        this.row = row;
        this.column = column;
        this.yearInstalled = yearInstalled;
        this.material = material;
        this.isTracking = isTracking;
        this.key = new PanelKey(section, row, column);
    }

// Getters
    public int getId() {
        return id;
    }

    public String getSection() {
        return section;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getYearInstalled() {
        return yearInstalled;
    }

    public Materials getMaterial() {
        return material;
    }

    public boolean isTracking() {
        return isTracking;
    }

    public PanelKey getKey() {
        return key;
    }
// Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setYearInstalled(int yearInstalled) {
        this.yearInstalled = yearInstalled;
    }

    public void setMaterial(Materials material) {
        this.material = material;
    }

    public void setTracking(boolean tracking) {
        isTracking = tracking;
    }

    public void setKey(PanelKey key) {
        this.key = key;
    }
}
