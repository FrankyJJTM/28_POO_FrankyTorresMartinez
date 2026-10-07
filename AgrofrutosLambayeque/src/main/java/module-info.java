module vallegrande.edu.pe.agrofrutoslambayeque {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens vallegrande.edu.pe.agrofrutoslambayeque to javafx.graphics, javafx.fxml;
    opens vallegrande.edu.pe.agrofrutoslambayeque.view to javafx.graphics, javafx.fxml;
    opens vallegrande.edu.pe.agrofrutoslambayeque.controller to javafx.fxml;
    opens vallegrande.edu.pe.agrofrutoslambayeque.model to javafx.base;

    exports vallegrande.edu.pe.agrofrutoslambayeque;
    exports vallegrande.edu.pe.agrofrutoslambayeque.view;
    exports vallegrande.edu.pe.agrofrutoslambayeque.controller;
    exports vallegrande.edu.pe.agrofrutoslambayeque.model;
}