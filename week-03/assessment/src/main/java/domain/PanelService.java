package domain;

import data.DataAccessException;
import data.PanelRepo;
import models.Panel;
import models.PanelKey;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class PanelService {
    private final PanelRepo repo;

    public PanelService(PanelRepo repo){
        this.repo = repo;
    }


    /// FIND ////////
    public List<Panel> findBySection(String section) throws DataAccessException {
        return repo.findBySection(section);
    }
    public Panel findByKey(PanelKey key) throws DataAccessException {
        return repo.findByKey(key);
    }

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

        // FEEDBACK: The assessment instructions seem to suggest that using a panel's natural key
        // (section-row-column) is the preferred way to identify panels in this project, but I steer
        // trainees away from doing this, as it's more complicated than just using arbitrary IDs (i.e. 1, 2, 3, etc.)
        // Also, using IDs allows the end user to edit a panel's section, row, and/or column.

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

    // DELETE

    public PanelResult deleteByKey(PanelKey key) throws DataAccessException {
        PanelResult result = new PanelResult();
        if (!repo.deleteByKey(key)) {
            result.addErrorMessage("That key does not exist");
        }
        return result;
    }

    ////////VALIDATION
    private PanelResult validateInputs(Panel panel) throws DataAccessException {
        PanelResult result = new PanelResult();
        if(panel == null){
            result.addErrorMessage("Panel cannot be null");
            return result;
        }

        // FEEDBACK: You can also use the String isBlank() method to check if a string value
        // is an empty string or contains whitespace characters only.
        // section must not be null
        if(panel.getSection() == null || panel.getSection().trim().length() == 0){
            result.addErrorMessage("The Section is required");
        }
        // FEEDBACK: Change the error messages here to "Row must be between 1 and 250"
        // and "Column must be between 1 and 250".
        // row and column need to be between 0 and 250
        if(panel.getRow() <= 0 || panel.getRow() > 250){
            result.addErrorMessage("Row must be between 0 and 250");
        }
        if(panel.getColumn() <= 0 || panel.getColumn() > 250){
            result.addErrorMessage("Column must be between 0 and 250");
        }
        // FEEDBACK: Use LocalDate.now().getYear() to get the current year.
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
        // FEEDBACK: Since a boolean field can only be true or false, there's no need to
        // validate the isTracking field.
        //tracking is required
        if(panel.isTracking() == false){
            result.addErrorMessage("tracking is required");
        }

        //The combined values of Section, Row, and Column may not be duplicated.
        if (result.isSuccess()) {
            Panel existingPanel = repo.findByKey(panel.getKey());
            // FEEDBACK: There's no need to set the result panel here.
            result.setPanel(panel);

            if (existingPanel != null && existingPanel.getId() != panel.getId()) {
                result.addErrorMessage("Error Duplicate Panel - section, row, and column must be unique.");
            }
        }

        return result;
    }


}// this closes class
