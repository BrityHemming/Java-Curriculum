package data;

import models.Panel;
import models.PanelKey;

import java.util.List;

public interface PanelRepo {
    //FIND METHODS
    // FIND PANEL BY ID
    Panel findById(int id) throws DataAccessException;

    // FIND PANEL BY SECTION
    List<Panel> findBySection(String section) throws DataAccessException;

    // ADD
    // hand the repo an Panel and receive a Panel back
    Panel add(Panel panel) throws DataAccessException;

    // UPDATE
    // we want to see if this was successful
    boolean update(Panel panel) throws DataAccessException;

    // DELETE
    Panel findByKey(PanelKey key) throws DataAccessException;

    boolean deleteByKey(PanelKey key) throws DataAccessException;
}
