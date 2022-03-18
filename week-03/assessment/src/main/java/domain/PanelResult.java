package domain;

import models.Panel;

import java.util.ArrayList;
import java.util.List;

public class PanelResult {
    private ArrayList<String> messages = new ArrayList<>();
    private Panel panel;

    public List<String> getMessages(){
        return new ArrayList<>(messages);
    }

    public void addErrorMessage(String message){
        messages.add(message);
    }
    public void addErrorMessage(String format, Object... args) {
        messages.add(String.format(format, args));
    }
    public boolean isSuccess(){
       return messages.size() == 0;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

}
