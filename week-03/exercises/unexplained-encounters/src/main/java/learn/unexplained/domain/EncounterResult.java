package learn.unexplained.domain;

import learn.unexplained.models.Encounter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EncounterResult {

    private ArrayList<String> messages = new ArrayList<>();
    private Encounter payload;

    public Encounter getPayload() {
        return payload;
    }

    public void setPayload(Encounter payload) {
        this.payload = payload;
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public boolean isSuccess() {
        return messages.size() == 0;
    }

    public void addErrorMessage(String message) {
        messages.add(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EncounterResult that = (EncounterResult) o;
        return Objects.equals(messages, that.messages) &&
                Objects.equals(payload, that.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages, payload);
    }
}
