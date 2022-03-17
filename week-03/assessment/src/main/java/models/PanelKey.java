package models;

import java.util.Objects;

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

// borrowed methods below from James solution
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PanelKey that = (PanelKey) o;
        return row == that.row &&
                column == that.column &&
                section.equalsIgnoreCase(that.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(section, row, column);
    }

    @Override
    public String toString() {
        return String.format("%s-%s-%s", section, row, column);
    }
}
