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

        Panel testTwo = new Panel();
        testTwo.setId(2);
        testTwo.setRow(8);
        testTwo.setColumn(8);
        testTwo.setSection("South");
        testTwo.setTracking(true);
        testTwo.setMaterial(PanelMaterial.CD_TE);
        testTwo.setYearInstalled(2019);
        panels.add(testTwo);

        Panel testThree = new Panel();
        testThree.setId(3);
        testThree.setRow(8);
        testThree.setColumn(8);
        testThree.setSection("East");
        testThree.setTracking(true);
        testThree.setMaterial(PanelMaterial.CD_TE);
        testThree.setYearInstalled(2020);
    }



    @Override
    public Panel findById(int id) throws DataAccessException {
        ArrayList<Panel> result = new ArrayList<>();
        for(Panel p : result){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Panel> findBySection(String section) throws DataAccessException {
        ArrayList<Panel> result = new ArrayList<>();
        for (Panel p : panels) {
            if (p.getSection().equalsIgnoreCase(section)) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public Panel add(Panel panel) throws DataAccessException {
        return panel;
    }

    @Override
    public boolean update(Panel panel) throws DataAccessException {
        Panel existingSolarPanel = null;
        for (Panel p : panels) {
            if (p.getId() == panel.getId()) {
                existingSolarPanel = p;
            }
        }
        return existingSolarPanel != null;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        return false;
    }

    public boolean deleteByKey(PanelKey key) throws DataAccessException {
        return findByKey(key) != null;
    }

    @Override
    public Panel findByKey(PanelKey key) throws DataAccessException {
        for (Panel p : panels) {
            if (p.isMatch(key)) {
                return p;
            }
        }
        return null;
    }
}
