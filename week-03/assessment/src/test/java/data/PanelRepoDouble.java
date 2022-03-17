package data;

import models.Panel;
import models.PanelKey;
import models.PanelMaterial;

import java.util.ArrayList;
import java.util.List;

public class PanelRepoDouble implements PanelRepo{

    private ArrayList<Panel> panels = new ArrayList<>();

    public PanelRepoDouble(){
        Panel testOne = new Panel(1,"North",5,6,2018,PanelMaterial.MONO_SI,true );
        Panel testTwo = new Panel(2, "South", 8, 8, 2010, PanelMaterial.POLY_SI, true);
        Panel testThree = new Panel(3,"East",35,10,2018,PanelMaterial.CD_TE,true );
        Panel testFour = new Panel(4,"West",54,36,2018,PanelMaterial.CIGS,true );
        Panel testFive = new Panel(5,"North",105,26,2018,PanelMaterial.MONO_SI,true );
        panels.add(testOne);
        panels.add(testTwo);
        panels.add(testThree);
        panels.add(testFour);
        panels.add(testFive);
    }



    @Override
    public Panel findById(int id) throws DataAccessException {
        for(Panel p : panels){
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
        Panel existingPanel = null;
        for (Panel p : panels) {
            if (p.getId() == panel.getId()) {
                existingPanel = p;
            }
        }
        return existingPanel != null;
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
