module com.example.alexfanimageviewer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.alexfanimageviewer to javafx.fxml;
    exports com.example.alexfanimageviewer;
}