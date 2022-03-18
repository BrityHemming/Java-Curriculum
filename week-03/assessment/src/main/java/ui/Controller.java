package ui;
import java.util.List;
import data.DataAccessException;
import domain.PanelResult;
import domain.PanelService;
import models.Panel;
import models.PanelKey;

public class Controller {
    private final View view;
    private final PanelService service;


    public Controller(PanelService service, View view) {
        this.service = service;
        this.view = view;
    }
    public void run() throws DataAccessException {
        MenuOptions option;
        do{
            option = view.displayMenuAndSelect();
            switch(option){
                case DISPLAY_PANELS:
                    displayPanels();
                    break;
                case ADD_PANEL:
                    addPanel();
                    break;
                case UPDATE_PANEL:
                    updatePanel();
                    break;
                case DELETE_PANEL:
                    deletePanel();
                    break;
                case EXIT:
                    view.printHeader("See You Later!");
                    break;
            }
        }while(option != MenuOptions.EXIT);
    }

    ////////HANDLE MENU OPTIONS /////////
    private void displayPanels() throws DataAccessException {
        view.printHeader(MenuOptions.DISPLAY_PANELS.getTitle());
        String section = view.getSection();
        List<Panel> panels = service.findBySection(section);
        if (panels.size() == 0) {
            System.out.print("There are no panels in this section");
        } else {
            view.displayPanels(section, panels);
        }
    }
    private void addPanel() throws DataAccessException {
        view.printHeader(MenuOptions.ADD_PANEL.getTitle());
        Panel panel = view.addPanel();
        PanelResult result = service.add(panel);
        if (result.isSuccess()) {
            System.out.printf("Your panel was created successfully!%n Panel %s added.", result.getPanel().getKey());
        } else {
            System.out.println(result.getMessages());
        }

    }
    private void updatePanel() throws DataAccessException {
        view.printHeader(MenuOptions.UPDATE_PANEL.getTitle());
        PanelKey key = view.getKey();
        Panel panel = service.findByKey(key);
        if (panel != null) {
            Panel updatedPanel = view.updatePanel(panel);
            updatedPanel.setId(panel.getId());
            updatedPanel.setSection(panel.getSection());
            updatedPanel.setRow(panel.getRow());
            updatedPanel.setColumn(panel.getColumn());
            PanelResult result = service.update(updatedPanel);
            if (result.isSuccess()) {
                System.out.printf("Your panel was successfully updated.%n Panel %s updated.", result.getPanel().getKey());
            } else {
                System.out.println(result.getMessages());
            }
        } else {
            System.out.println("A panel with that key does not exist");
        }

    }
    private void deletePanel() throws DataAccessException {
        view.printHeader(MenuOptions.DELETE_PANEL.getTitle());
        PanelKey key = view.getKey();
        Panel panel = service.findByKey(key);
        if (panel != null) {
            PanelResult result = service.deleteByKey(key);
            if (result.isSuccess()) {
                System.out.printf("Your panel was successfully deleted.%n Panel %s no longer exists.", key);
            } else {
                System.out.println(result.getMessages());
            }
        } else {
            System.out.println("A panel with that key does not exist");
        }
    }

}// closes the class
