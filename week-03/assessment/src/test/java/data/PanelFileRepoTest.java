package data;

import models.Panel;
import models.PanelKey;
import models.PanelMaterial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanelFileRepoTest {
    private static final String SEED_PATH = "./data/panels-seed.csv";
    private static final String TEST_PATH = "./data/panels-test.csv";
    private PanelFileRepo repo = new PanelFileRepo(TEST_PATH);

    // we want to set a known good state for every test
    @BeforeEach
    void setup() throws IOException {
        Files.copy(
                Paths.get(SEED_PATH),
                Paths.get(TEST_PATH),
                StandardCopyOption.REPLACE_EXISTING);
    }


    //TESTS START HERE
    @Test
    void shouldFindFivePanel(){
        List<Panel> actual = repo.findAll();
        assertNotNull(actual);
        assertEquals(5, actual.size());
    }

    // test find by id method
    @Test
    void shouldFindExisitingPanel(){
        Panel four = repo.findById(4);
        assertNotNull(four);
        assertEquals("North", four.getSection());
    }

    @Test
    void ShouldNotFindNonExistingPanel(){
        Panel nope = repo.findById(900);
            assertNull(nope);
    }

    // Find by Key Tests
//    @Test
//    void shouldFindByKey(){
//        Panel result = repo.findByKey(new PanelKey("West", 2, 3));
//        assertNotNull(result);
//        //assertEquals(PanelMaterial.POLY_SI, result.getMaterial());
//    }

    // Find By Section
    @Test
    void shouldFindExistingSection(){
        List<Panel> west = repo.findBySection("West");
        assertNotNull(west);
//        assertEquals(2, west.size());
    }

//ADD
    @Test
    void shouldAddPanel() throws DataAccessException {
        Panel p = new Panel();
        p.setSection("Test");
        p.setRow(4);
        p.setColumn(6);
        p.setMaterial(PanelMaterial.POLY_SI);
        p.setYearInstalled(2014);
        p.setTracking(true);

        Panel actual = repo.add(p);

        assertNotNull(actual);
        assertEquals(6, actual.getId());
    }


 // UPDATE

    @Test
    void shouldUpdateExisting() throws DataAccessException {
        Panel panel = new Panel();
        panel.setId(4);
        panel.setSection("Test Section");
        panel.setRow(5);
        panel.setColumn(80);
        panel.setMaterial(PanelMaterial.A_SI);
        panel.setYearInstalled(2020);
        panel.setTracking(true);

        boolean success = repo.update(panel);
        assertTrue(success);

        Panel actual = repo.findById(4);
        assertNotNull(actual);
        assertEquals("Test Section", actual.getSection());

    }
    @Test
    void shouldNotUpdateMissing() throws DataAccessException {
        Panel panel = new Panel();
        panel.setId(100000);
        boolean actual = repo.update(panel);
        assertFalse(actual);

    }

    // DELETE
    @Test
    void shouldDeleteExisting() throws DataAccessException{
        boolean actual = repo.deleteById(2);
        assertTrue(actual);

        Panel p = repo.findById(2);
        assertNull(p);
    }

    @Test
    void shouldNotDeleteMissing() throws DataAccessException{
        boolean actual = repo.deleteById(100000);
        assertFalse(actual);
    }

}// closes class