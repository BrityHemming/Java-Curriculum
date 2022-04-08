package domain;

import data.DataAccessException;
import data.PanelRepoDouble;
import models.Panel;
import models.PanelKey;
import models.PanelMaterial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanelServiceTest {
    PanelService service;

    @BeforeEach
    void setup() {
        PanelRepoDouble repo = new PanelRepoDouble();
        service = new PanelService(repo);
    }

    ////////////////////////////////////////  Find ////////////////////////////////////////////////
    @Test
    void shouldFindTwoNorthPanels() throws DataAccessException {
        List<Panel> panels = service.findBySection("North");
        assertEquals(2, panels.size());
    }

    @Test
    void shouldFindPanelByExistingKey() throws DataAccessException {
        Panel panel = service.findByKey(new PanelKey("South", 8, 8));
        assertNotNull(panel);
    }

    // Input validation tests
////////////////////////////////////////   ADD ////////////////////////////////////////////////
    @Test
    void shouldAddPanel() throws DataAccessException {
        Panel testOne = new Panel();
        testOne.setRow(8);
        testOne.setColumn(9);
        testOne.setSection("South");
        testOne.setTracking(true);
        testOne.setMaterial(PanelMaterial.CD_TE);
        testOne.setYearInstalled(2011);

        PanelResult result = service.add(testOne);

        assertNotNull(result.getPanel());
        assertTrue(result.isSuccess());

    }
    // Null Panel
    @Test
    void shouldNotAddNullPanel() throws DataAccessException {
        PanelResult result = service.add(null);
        assertFalse(result.isSuccess());
    }

    // Null Section

    @Test
    void shouldNotAddNullSection() throws DataAccessException {
        Panel testOne = new Panel();
        testOne.setRow(55);
        testOne.setColumn(9);
        testOne.setSection(null);
        testOne.setTracking(true);
        testOne.setMaterial(PanelMaterial.CD_TE);
        testOne.setYearInstalled(2011);

        PanelResult result = service.add(testOne);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        // FEEDBACK: Trainees won't often do it, but we can add another assertion to ensure
        // that we actually got back the expected error message.
//        assertEquals("The Section is required", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddBlankSection() throws DataAccessException {
        Panel testOne = new Panel();
        testOne.setRow(-5);
        testOne.setColumn(9);
        testOne.setSection("");
        testOne.setTracking(true);
        testOne.setMaterial(PanelMaterial.CD_TE);
        testOne.setYearInstalled(2011);

        PanelResult result = service.add(testOne);

        assertFalse(result.isSuccess());
        // FEEDBACK: Why are there two error messages here?
        assertEquals(2, result.getMessages().size());
    }

    //Row and Column between 0 and 250

    @Test
    void shouldNotAddInvalidRow() throws DataAccessException {
        Panel testOne = new Panel();
        testOne.setRow(-5);
        testOne.setColumn(9);
        testOne.setSection("West");
        testOne.setTracking(true);
        testOne.setMaterial(PanelMaterial.CD_TE);
        testOne.setYearInstalled(2011);

        PanelResult result = service.add(testOne);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        // FEEDBACK: Nice check!
        assertTrue(result.getMessages().get(0).contains("Row"));
    }

    @Test
    void shouldNotAddInvalidColumn() throws DataAccessException {
        Panel testOne = new Panel();
        testOne.setRow(5);
        testOne.setColumn(330);
        testOne.setSection("West");
        testOne.setTracking(true);
        testOne.setMaterial(PanelMaterial.CD_TE);
        testOne.setYearInstalled(2011);

        PanelResult result = service.add(testOne);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("Column"));

    }

    //Material is required

    @Test
    void shouldNotAddNullMaterial() throws DataAccessException {
        Panel testOne = new Panel();
        testOne.setRow(5);
        testOne.setColumn(9);
        testOne.setSection("West");
        testOne.setTracking(true);
        testOne.setMaterial(null);
        testOne.setYearInstalled(2011);

        PanelResult result = service.add(testOne);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("Material"));
    }
    // Year should be in the past but not before 1980
    @Test
    void yearShouldBePast() throws DataAccessException {
        Date today = new Date();
        int year = today.getYear() + 1900;
        Panel testOne = new Panel();
        testOne.setRow(5);
        testOne.setColumn(9);
        testOne.setSection("West");
        testOne.setTracking(true);
        testOne.setMaterial(null);
        testOne.setYearInstalled(year);

        PanelResult result = service.add(testOne);

        assertFalse(result.isSuccess());
    }
    @Test
    void yearShouldBeAfterEighty() throws DataAccessException {
        Panel testOne = new Panel();
        testOne.setRow(5);
        testOne.setColumn(9);
        testOne.setSection("West");
        testOne.setTracking(true);
        testOne.setMaterial(null);
        testOne.setYearInstalled(1979);

        PanelResult result = service.add(testOne);

        assertFalse(result.isSuccess());
    }
    //No duplicates
    @Test
    void shouldNotCreateDuplicate() throws DataAccessException {

        Panel testTwo = new Panel();
        testTwo.setRow(5);
        testTwo.setColumn(9);
        testTwo.setSection("West");
        testTwo.setTracking(true);
        testTwo.setMaterial(PanelMaterial.CD_TE);
        testTwo.setYearInstalled(2011);

        // should add the first test, should not add the section

        PanelResult result = service.add(testTwo);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("Duplicate"));
    }

    ////////////////////////////////////////   UPDATE  ////////////////////////////////////////////////
    @Test
    void shouldUpdate() throws DataAccessException{
        PanelResult result = service.update(new Panel(2, "South", 8, 8, 2020, PanelMaterial.POLY_SI, true));
        assertTrue(result.isSuccess());
    }
    @Test
    void shouldNotUpdateSection() throws DataAccessException {
        PanelResult result = service.update(new Panel(2, "North", 8, 8, 2020, PanelMaterial.POLY_SI, true));
        assertFalse(result.isSuccess());
    }
    @Test
    void shouldNotUpdateRow() throws DataAccessException {
        PanelResult result = service.update(new Panel(2, "South", 9, 8, 2020, PanelMaterial.POLY_SI, true));
        assertFalse(result.isSuccess());
    }
    @Test
    void shouldNotUpdateColumn() throws DataAccessException {
        PanelResult result = service.update(new Panel(2, "South", 8, 6, 2020, PanelMaterial.POLY_SI, true));
        assertFalse(result.isSuccess());
    }

    ////////////////////////////////////////   DELETE  ////////////////////////////////////////////////
    @Test
    void shouldDeletePanel() throws DataAccessException {
        PanelResult result = service.deleteByKey(new PanelKey("North", 5, 6));
        assertTrue(result.isSuccess());
    }
    @Test
    void shouldNotDeleteNonExistingPanel() throws DataAccessException {
        PanelResult result = service.deleteByKey(new PanelKey("West", 10, 105));
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("key"));
    }
}