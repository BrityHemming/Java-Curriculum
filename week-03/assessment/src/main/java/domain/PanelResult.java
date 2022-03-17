package domain;

import models.Panel;

import java.util.ArrayList;
import java.util.List;

public class PanelResult {
    private ArrayList<String> messages = new ArrayList<>();
    private Panel payload;


    public void addErrorMessage(String message){
        messages.add(message);
    }

    public boolean isSuccess(){
       return messages.size() == 0;
    }

    public Panel getPayload() {
        return payload;
    }

    public void setPayload(Panel payload) {
        this.payload = payload;
    }

    public List<String> getMessages(){
        return new ArrayList<>(messages);
    }
}
