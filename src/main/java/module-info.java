module GUI {
    requires javafx.controls;
    requires javafx.fxml;


    opens GUI to javafx.fxml;
    exports Classes;
    exports GUI;
}
