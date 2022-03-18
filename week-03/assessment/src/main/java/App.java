import data.DataAccessException;
import data.PanelFileRepo;
import domain.PanelService;
import ui.Controller;
import ui.View;


import java.util.Date;

public class App {

    public static void main(String[] args) throws DataAccessException {
        PanelFileRepo repo = new PanelFileRepo("./data/panels.csv");
        PanelService service = new PanelService(repo);
        View view = new View();

        Controller controller = new Controller(service, view);
        controller.run();
    }
}
