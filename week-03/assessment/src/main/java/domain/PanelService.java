package domain;

import data.DataAccessException;
import data.PanelRepo;
import models.Panel;

import java.util.Date;
import java.util.List;

public class PanelService {
    private final PanelRepo repo;

    public PanelService(PanelRepo repo){
        this.repo = repo;
    }
    //Rules for add
    /*
    can't be null
    all info required
    User must be able to select add panel from menu option
    enter a section (string) // sections can hold 250 panels
    enter a row (int 1-250)
    enter a column (int 1 - 250)
    make sure a panel does not already exist there
    enter a material (choose from list) - this will be enum
    enter the year installed
    tracking (yes/no)
    save panel
    success or failure message
    * */
// ADD
    public PanelResult add(Panel panel) throws DataAccessException {
        PanelResult result = validateInputs(panel);
        if (panel != null && panel.getId() > 0) {
            result.addErrorMessage("Panel `id` should not be set.");
        }

        if (result.isSuccess()) {
            panel = repo.add(panel);
            result.setPanel(panel);
        }

        return result;
    }

    // UPDATE

    public PanelResult update(Panel panel) throws DataAccessException{
        PanelResult result = validateInputs(panel);
        if(!result.isSuccess()){
            return result;
        }
        if (panel.getId() <= 0) {
            result.addErrorMessage("id is required.");
            return result;
        }
        // cannot update section row or column or ID
        Panel existing = repo.findById(panel.getId());
        if(existing == null){
            result.addErrorMessage("id does not exist: ");
            return result;
        }
        if(existing.getRow() != panel.getRow()){
            result.addErrorMessage("Cannot update row");

        }
        if(!existing.getSection().equalsIgnoreCase(panel.getSection())){
            result.addErrorMessage("Cannot update section");

        }
        if(existing.getColumn() != panel.getColumn()){
            result.addErrorMessage("Cannot update column");

        }
        if(result.isSuccess()){
            boolean success = repo.update(panel);
            if(!success){
                result.addErrorMessage("Could not find panel");
            }
        }

        return result;
    }

    private PanelResult validateInputs(Panel panel) throws DataAccessException {
        PanelResult result = new PanelResult();
        if(panel == null){
            result.addErrorMessage("Panel cannot be null");
            return result;
        }

        // section must not be null
        if(panel.getSection() == null || panel.getSection().trim().length() == 0){
            result.addErrorMessage("The Section is required");
        }
        // row and column need to be between 0 and 250
        if(panel.getRow() <= 0 || panel.getRow() > 250){
            result.addErrorMessage("Row must be between 0 and 250");
        }
        if(panel.getColumn() <= 0 || panel.getColumn() > 250){
            result.addErrorMessage("Column must be between 0 and 250");
        }
        // year installed must be in the past
        Date today = new Date();
        int year = today.getYear() + 1900;
        if(panel.getYearInstalled() < 1980 || panel.getYearInstalled() >= year){
            result.addErrorMessage("Year must be after 1980, and before current year");
        }
        //Material is required and can only be one of the five materials listed.
        if(panel.getMaterial() == null){
            result.addErrorMessage("Material cannot be null");
        }
        //tracking is required
        if(panel.isTracking() == false){
            result.addErrorMessage("tracking is required");
        }

        //The combined values of Section, Row, and Column may not be duplicated.
        if (result.isSuccess()) {
            Panel existingPanel = repo.findByKey(panel.getKey());

            if (existingPanel != null && existingPanel.getId() != panel.getId()) {
                result.addErrorMessage("Error Duplicate Panel - section, row, and column must be unique.");
            }
        }

        return result;
    }


}// this closes class
