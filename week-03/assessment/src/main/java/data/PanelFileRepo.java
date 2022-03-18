package data;
import models.Panel;
import models.PanelKey;
import models.PanelMaterial;

import javax.xml.crypto.Data;
import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;


public class PanelFileRepo implements PanelRepo {
    // don't want to hardcode file paths here - we want to be flexible
    private final String filePath;

    public PanelFileRepo(String filePath){
        this.filePath = filePath;
    }

 //READ OPERATIONS

    public List<Panel> findAll() throws DataAccessException {
        ArrayList<Panel> result = new ArrayList<>();
        // try with resources will close closable resources after the block
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            // skips the header
            reader.readLine();

            // read all the lines as long as they are not null
            for(String line = reader.readLine(); line != null; line = reader.readLine()){
                String[] fields = line.split(",", -1);
                if(fields.length == 7){
                    Panel panel = new Panel();
                    panel.setId(Integer.parseInt(fields[0]));
                    panel.setSection(fields[1]);
                    panel.setRow(Integer.parseInt(fields[2]));
                    panel.setColumn(Integer.parseInt(fields[3]));
                    panel.setMaterial(PanelMaterial.valueOf(fields[4]));
                    panel.setYearInstalled(Integer.parseInt(fields[5]));
                    panel.setTracking("true".equals(fields[6]));
                    // add panel to result
                    result.add(panel);
                }
            }
        }catch(FileNotFoundException ex){
            // okay to ignore
        }
        catch(IOException ex){
            throw new DataAccessException(ex.getMessage(), ex);
        }
        return result;
    }
//FIND METHODS
// FIND PANEL BY ID
    @Override
    public Panel findById(int id) throws DataAccessException {
        for(Panel p : findAll()){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }
// FIND PANEL BY SECTION
    @Override
    public List<Panel> findBySection(String section) throws DataAccessException {
        ArrayList<Panel> result = new ArrayList<>();
        for(Panel p : findAll()){
            if(p.getSection().equalsIgnoreCase(section)){
                result.add(p);
            }
        }
        return result;
    }

     //find panel by key
    @Override
    public Panel findByKey(PanelKey key) throws DataAccessException {
        List<Panel> result = findAll();
        for(Panel p : result){
            if(p.isMatch(key)){
                return p;
            }
        }
        return null;
    }


    // ADD
    // hand the repo a Panel and receive a Panel back
    @Override
    public Panel add(Panel panel) throws DataAccessException {
       // grab all panels
        List<Panel> all = findAll();
        // create next id
        int nextId = 0;
        for(Panel p: all){
            nextId = Math.max(nextId, p.getId());
        }
        nextId++;
        panel.setId(nextId);
        // add new panel to all
        all.add(panel);
        // save
        writeAll(all);

        return panel;
    }

    // UPDATE
    // we want to see if this was successful
    @Override
    public boolean update(Panel panel) throws DataAccessException {
        //look for existing panel - if it exists update it
        List<Panel> all = findAll();
        for(int i = 0; i < all.size(); i++){
            if(all.get(i).getId() == panel.getId()){
                all.set(i, panel);
                writeAll(all);
                return true;
            }
        }
        return false;
    }

    // DELETE
    @Override
    public boolean deleteByKey(PanelKey key) throws DataAccessException {
        List<Panel> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).isMatch(key)) {
                all.remove(i);
                writeAll(all);
                return true;
            }
        }
        return false;
    }

    // method to save for all data transforming
    private void writeAll(List<Panel> panels) throws DataAccessException {
        try(PrintWriter writer = new PrintWriter(filePath)){
            // replace the file each time
            writer.println("id,Section,Row,Column,Material,Year,isTracking"); // prints header
            for(Panel p : panels){
                writer.println(serialize(p));
            }
        }catch(IOException ex){
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }
    // take a panel and turn into String
    private String serialize(Panel panel){
        return String.format("%s,%s,%s,%s,%s,%s,%s",
                panel.getId(),
                panel.getSection(),
                panel.getRow(),
                panel.getColumn(),
                panel.getMaterial(),
                panel.getYearInstalled(),
                panel.isTracking());
    }
}// closes class
