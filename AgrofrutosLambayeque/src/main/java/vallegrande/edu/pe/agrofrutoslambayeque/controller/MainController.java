package vallegrande.edu.pe.agrofrutoslambayeque.controller;

import javafx.scene.control.Alert;
import vallegrande.edu.pe.agrofrutoslambayeque.model.Contacto;
import vallegrande.edu.pe.agrofrutoslambayeque.model.ContactoDao;
import vallegrande.edu.pe.agrofrutoslambayeque.view.MainView;

public class MainController {

    private MainView view;
    private ContactoDao dao;

    public MainController(MainView view) {
        this.view = view;
        this.dao = new ContactoDao();
        configurarEventos();
    }

    private void configurarEventos() {
        view.getBtnInicio().setOnAction(e -> view.mostrarInicio());
        view.getBtnContactos().setOnAction(e -> view.mostrarContactos(dao.listarContactos()));

        view.getBtnGuardarContacto().setOnAction(e -> guardarContacto());
    }

    private void guardarContacto() {
        String nombre = view.getTxtNombre().trim();
        String apellido = view.getTxtApellido().trim();
        String telefono = view.getTxtTelefono().trim();
        String correo = view.getTxtCorreo().trim();
        String mensaje = view.getTxtMensaje().trim();

        if (nombre.isEmpty() || correo.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos incompletos", "Por favor ingresa al menos el nombre y el correo.");
            return;
        }

        Contacto nuevo = new Contacto();
        nuevo.setNombre(nombre);
        nuevo.setApellido(apellido);
        nuevo.setTelefono(telefono);
        nuevo.setCorreo(correo);
        nuevo.setMensaje(mensaje);

        boolean exito = dao.agregarContacto(nuevo);

        if (exito) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "El contacto se ha registrado correctamente.");
            view.limpiarFormulario();
            view.mostrarContactos(dao.listarContactos());
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo guardar el contacto en la base de datos.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}