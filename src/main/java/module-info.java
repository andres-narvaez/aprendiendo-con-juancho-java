module com.acj.aprendiendoconjuancho {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens com.acj.aprendiendoconjuancho to javafx.fxml;
    exports com.acj.aprendiendoconjuancho;
}