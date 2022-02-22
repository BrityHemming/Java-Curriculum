package learn.unexplained;

import learn.unexplained.data.EncounterFileRepository;

public class App {

    public static void main(String[] args) {
        EncounterFileRepository repository = new EncounterFileRepository("./data/encounters.csv");
    }
}
