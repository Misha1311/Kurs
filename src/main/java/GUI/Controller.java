package GUI;

import Classes.PrintThread;
import Classes.Timer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Controller {

    private Stage stage = new Stage();

    @FXML
    private TextArea CompletedArea;

    @FXML
    private TextArea MemoryBocksArea;

    @FXML
    private Button exit;

    @FXML
    private Button generate;

    @FXML
    private TextArea textarea;


    @FXML
    void initialize() {
        Scheduler scheduler = new Scheduler(4, 3072);
        Thread printThread = new PrintThread(MemoryBocksArea, CompletedArea, textarea, scheduler);
        printThread.start();
        scheduler.start();
        Timer timer = new Timer(scheduler.getCpu());
        timer.start();
        exit.setOnAction(actionEvent -> {
            ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
            stage.close();
            scheduler.getCpu().setActive(false);
            System.exit(0);
        });
        generate.setOnAction(actionEvent -> scheduler.getQueueCPU().add(10));
    }

}
