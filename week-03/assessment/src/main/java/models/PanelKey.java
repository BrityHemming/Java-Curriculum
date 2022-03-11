package models;

public class PanelKey {
    // Fields
    private String section;
    private int row;
    private int column;
    // Constructor
    public PanelKey(String section, int row, int column){
        this.section = section;
        this.row = row;
        this.column = column;
    }
}
