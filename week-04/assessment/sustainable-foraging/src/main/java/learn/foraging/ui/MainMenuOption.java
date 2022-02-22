package learn.foraging.ui;

public enum MainMenuOption {

    EXIT(0, "Exit", false),
    VIEW_FORAGES_BY_DATE(1, "View Forages By Date", false),
    VIEW_ITEMS(2, "View Items", false),
    ADD_FORAGE(3, "Add a Forage", false),
    ADD_FORAGER(4, "Add a Forager", false),
    ADD_ITEM(5, "Add an Item", false),
    REPORT_KG_PER_ITEM(6, "Report: Kilograms of Item", false),
    REPORT_CATEGORY_VALUE(7, "Report: Item Category Value", false),
    GENERATE(8, "Generate Random Forages", true);

    private int value;
    private String message;
    private boolean hidden;

    private MainMenuOption(int value, String message, boolean hidden) {
        this.value = value;
        this.message = message;
        this.hidden = hidden;
    }

    public static MainMenuOption fromValue(int value) {
        for (MainMenuOption option : MainMenuOption.values()) {
            if (option.getValue() == value) {
                return option;
            }
        }
        return EXIT;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public boolean isHidden() {
        return hidden;
    }
}
