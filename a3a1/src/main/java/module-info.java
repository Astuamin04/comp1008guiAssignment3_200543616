module com.example.a3a1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.a3a1 to javafx.fxml;
    exports com.example.a3a1;
}