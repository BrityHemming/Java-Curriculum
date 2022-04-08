package ui;

import models.Panel;
import models.PanelKey;
import models.PanelMaterial;

import java.util.List;
import java.util.Scanner;

public class View {
    private final Scanner console = new Scanner(System.in);

    public MenuOptions displayMenuAndSelect(){
        MenuOptions[] values = MenuOptions.values();
        System.out.println("Main Menu");
        System.out.println("*".repeat(9));
        for(int i = 0; i < MenuOptions.values().length; i++){
            System.out.printf("%s. %s%n", i+1, values[i].getTitle());
        }
        int index = readInt("Select [1 - 5]:", 1, 5);
        return values[index-1];
    }

    public void printHeader(String message){
        System.out.println();
        System.out.println(message);
        System.out.println("*".repeat(message.length()));
    }

    ////// Find By Section ///////
    public String getSection() {
        System.out.println();
        return readRequiredString("Section Name");
    }

    public void displayPanels(String section, List<Panel> panels) {
        System.out.println();
        System.out.printf("Displaying panels in section: %s%n", section);
        System.out.println("Row | Col | Year | Material | Tracking");
        for (Panel p : panels) {
            System.out.printf("%3s | %3s | %4s | %8s | %8s%n", p.getRow(), p.getColumn(), p.getYearInstalled(),
                    p.getMaterial(), p.isTracking() ? "yes" : "no");
        }
    }


    //////////// FIND BY KEY /////////
    public PanelKey getKey() {
        System.out.println();
        String section = readRequiredString("Section Name");
        int row = readInt("Row", 1, 250);
        int column = readInt("Column", 1, 250);
        return new PanelKey(section, row, column);
    }

///////// ADD PANEL ///////////

public Panel addPanel() {
    System.out.println();
    Panel result = new Panel();
    result.setSection(readRequiredString("Section"));
    result.setRow(readInt("Row", 1, 250));
    result.setColumn(readInt("Column", 1, 250));
    result.setMaterial(displayAndSelectMaterial(PanelMaterial.class));
    // FEEDBACK: You could use LocalDate.now.getYear() for the max value here.
    result.setYearInstalled(readInt("Year Installed", 1980, 2022));
    result.setTracking(readBoolean("Tracked [yes/no]"));

    return result;
}

//////// Update Panel ////////
public Panel updatePanel(Panel panel) {
    System.out.println();
    System.out.printf("Updating %s%n", panel.getKey());
    System.out.println();
    Panel result = new Panel();
    result.setId(panel.getId());
    result.setSection(panel.getSection());
    result.setRow(panel.getRow());
    result.setColumn(panel.getColumn());
    result.setMaterial(displayAndSelectMaterial(PanelMaterial.class));
    // FEEDBACK: You could use LocalDate.now.getYear() for the max value here.
    result.setYearInstalled(readInt("Year Installed", 1980, 2022));
    result.setTracking(readBoolean("Tracked [yes/no]"));

    return result;
}

    /////////////////// PRIVATE METHODS /////////////
    private String readString(String prompt){
        System.out.print(prompt);
        return console.nextLine();
    }

    private String readRequiredString(String prompt){
        String result = null;
        do{
            result = readString(prompt).trim();
            if(result.length() == 0){
                System.out.println("Value is required");
            }
        }while(result.length() == 0);
        return result;
    }
    private int readInt(String prompt){
        int result = 0;
        boolean isValid = false;
        do{
           String value = readRequiredString(prompt);
           try{
               result = Integer.parseInt(value);
               isValid = true;
           }catch(NumberFormatException ex){
               System.out.println("Value must be a number");
           }

        }while(!isValid);
        return result;
    }

    private int readInt(String prompt, int min, int max){
        int result = 0;
        do{
            result = readInt(prompt);
            if(result < min || result > max){
                System.out.printf("Value must be between %s and %s.%n", min, max);
            }
        }while(result < min || result > max);

        return result;
    }
    // FEEDBACK: Nice helper method!
    private PanelMaterial displayAndSelectMaterial(Class<PanelMaterial> material){
        PanelMaterial[] materials = PanelMaterial.values();
        System.out.println("Please select your panel material");
        System.out.println("*".repeat(34));
        for(int i = 0; i < PanelMaterial.values().length; i++){
            System.out.printf("%s. %s%n", i+1, materials[i].getTitle());
        }
        int index = readInt("Select [1 - 5]:", 1, 5);
        return materials[index-1];
    }
    private boolean readBoolean(String prompt){
        String result = readRequiredString(prompt);
        return result.equalsIgnoreCase("yes");
    }

}// closes class
