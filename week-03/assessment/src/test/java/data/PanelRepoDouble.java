package data;

import models.Panel;
import models.PanelKey;
import models.PanelMaterial;

import java.util.ArrayList;
import java.util.List;

public class PanelRepoDouble implements PanelRepo{

    private ArrayList<Panel> panels = new ArrayList<>();

    public PanelRepoDouble(){
        Panel testOne = new Panel();
        testOne.setId(1);
        testOne.setRow(5);
        testOne.setColumn(9);
        testOne.setSection("West");
        testOne.setTracking(true);
        testOne.setMaterial(PanelMaterial.CD_TE);
        testOne.setYearInstalled(2011);
        panels.add(testOne);
    }

    @Override
    public List<Panel> findAll() throws DataAccessException {
        return new ArrayList<>(panels);
    }

    @Override
    public Panel findById(int id) throws DataAccessException {
        return null;
    }

    @Override
    public List<Panel> findBySection(String section) throws DataAccessException {
        return null;
    }

    @Override
    public Panel add(Panel panel) throws DataAccessException {
        return null;
    }

    @Override
    public boolean update(Panel panel) throws DataAccessException {
        return false;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        return false;
    }

    @Override
    public Panel findByKey(PanelKey key) throws DataAccessException {
        return null;
    }
}
