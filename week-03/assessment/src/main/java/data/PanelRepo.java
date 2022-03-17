package data;

import models.Panel;
import models.PanelKey;

import java.util.List;

public interface PanelRepo {
    //READ OPERATIONS
    List<Panel> findAll() throws DataAccessException;

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
    // we want to see if this was successful
    boolean deleteById(int id) throws DataAccessException;

    Panel findByKey(PanelKey key) throws DataAccessException;
}
