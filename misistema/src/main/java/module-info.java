module vallegrande.edu.pe.misistema {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // Permite que FXML acceda a los controladores y vistas
    opens vallegrande.edu.pe.misistema to javafx.fxml;
    opens vallegrande.edu.pe.misistema.controller to javafx.fxml;
    opens vallegrande.edu.pe.misistema.view to javafx.fxml;

    // Permite que la TableView de JavaFX lea los atributos de la clase Pedido
    opens vallegrande.edu.pe.misistema.model to javafx.base, javafx.fxml;

    exports vallegrande.edu.pe.misistema;
}