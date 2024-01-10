module com.example.prvniprojekt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prvniprojekt to javafx.fxml;
    exports com.example.prvniprojekt;
}