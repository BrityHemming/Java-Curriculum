package models;

public class Panel {
    // fields
    private int id;
    private String section;
    private int row;
    private int column;
    private int yearInstalled;
    private PanelMaterial material;
    private boolean isTracking;
    private PanelKey key;

    // constuctors
    public Panel(){

    }
    public Panel(int id, String section, int row, int column, int yearInstalled, PanelMaterial material, boolean isTracking){
        this.id = id;
        this.section = section;
        this.row = row;
        this.column = column;
        this.yearInstalled = yearInstalled;
        this.material = material;
        this.isTracking = isTracking;
        this.key = new PanelKey(section, row, column);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
        this.key = new PanelKey(section, this.row, this.column);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        // row must be a number between 0 and 250
        if(this.row < 0 || this.row > 250){
            System.out.println("row must be between 0 and 250");
        }
        this.row = row;
        this.key = new PanelKey(this.section, row, this.column);
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        // column must be a number between 0 and 250
        if(this.column < 0 || this.column > 250){
            System.out.println("column must be between 0 and 250");
        }
        this.column = column;
        this.key = new PanelKey(section, this.row, column);
    }

    public int getYearInstalled() {
        return yearInstalled;
    }

    public void setYearInstalled(int yearInstalled) {
        this.yearInstalled = yearInstalled;
    }

    public PanelMaterial getMaterial() {
        return material;
    }

    public void setMaterial(PanelMaterial material) {
        this.material = material;
    }

    public boolean isTracking() {
        return isTracking;
    }

    public void setTracking(boolean tracking) {
        isTracking = tracking;
    }

    public PanelKey getKey() {
        return key;
    }

    // will use to find by key
    public boolean isMatch(PanelKey key){
        return this.key.equals(key);
    }

}
