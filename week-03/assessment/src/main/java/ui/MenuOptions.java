package ui;

public enum MenuOptions {
            DISPLAY_PANELS("Display Panels"),
            ADD_PANEL("Add Panel"),
            UPDATE_PANEL("Update Panel"),
            DELETE_PANEL("Delete Panel"),
            EXIT("Exit");

    private final String title;

    MenuOptions(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


}
