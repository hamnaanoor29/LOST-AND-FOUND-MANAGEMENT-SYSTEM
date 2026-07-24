module com.lostandfoundsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lostandfoundsystem to javafx.fxml;
    exports com.lostandfoundsystem;
}