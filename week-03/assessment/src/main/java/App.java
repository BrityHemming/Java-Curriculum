// FEEDBACK: We always want to include a top level package in our projects.
// The Java convention is to start with the inversion of your organization's
// domain name followed by the project name, so for amazon.com the top level
// package name might be `com.amazon.utilities` (replacing "utilities" with
// whatever our project name is). For this project, I'd use something
// like `learn.solarfarm`.

// package learn.solarfarm;

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
