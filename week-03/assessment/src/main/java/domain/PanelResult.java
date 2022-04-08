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
    // FEEDBACK: I like this method overload, but it looks like it's not being used.
    // Also, most trainees aren't going to be comfortable with the "..." operator.
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
