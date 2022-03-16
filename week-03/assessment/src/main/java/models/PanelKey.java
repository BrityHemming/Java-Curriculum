package models;

public class PanelKey {
    // fields
    private String section;
    private int row;
    private int column;

    //constructor
    public PanelKey(String section, int row, int column){
        this.section = section;
        this.row = row;
        this.column = column;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }


}
