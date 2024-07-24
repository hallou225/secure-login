module com.example.securelogin {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.securelogin to javafx.fxml;
    exports com.example.securelogin;
}