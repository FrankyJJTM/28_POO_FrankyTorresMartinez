package vallegrande.edu.pe.agrofrutoslambayeque.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

import vallegrande.edu.pe.agrofrutoslambayeque.controller.MainController;
import vallegrande.edu.pe.agrofrutoslambayeque.model.Contacto;

public class MainView extends Application {

    private Button btnInicio;
    private Button btnContactos;
    private VBox contenedorCards;
    private BorderPane root;

    // Campos de texto para el formulario
    private TextField txtNombre;
    private TextField txtApellido;
    private TextField txtTelefono;
    private TextField txtCorreo;
    private TextField txtMensaje;
    private Button btnGuardarContacto;

    @Override
    public void start(Stage stage) {
        root = new BorderPane();

        // 1. Barra Lateral (Menú)
        VBox menuLateral = new VBox(15);
        menuLateral.setPadding(new Insets(20));
        menuLateral.setPrefWidth(220);
        menuLateral.setStyle("-fx-background-color: #2c3e50;");

        btnInicio = crearBotonMenu("Agrofrutos Lambayeque");
        btnContactos = crearBotonMenu("Contactos");

        menuLateral.getChildren().addAll(btnInicio, btnContactos);
        root.setLeft(menuLateral);

        // 2. Contenedor Principal
        contenedorCards = new VBox(15);
        contenedorCards.setPadding(new Insets(25));

        // Inicializar componentes del formulario
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");

        txtApellido = new TextField();
        txtApellido.setPromptText("Apellido");

        txtTelefono = new TextField();
        txtTelefono.setPromptText("Teléfono");

        txtCorreo = new TextField();
        txtCorreo.setPromptText("Correo electrónico");

        txtMensaje = new TextField();
        txtMensaje.setPromptText("Mensaje");

        btnGuardarContacto = new Button("Guardar Contacto");
        btnGuardarContacto.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 8 15 8 15; -fx-background-radius: 5;");

        mostrarInicio();

        // 3. Controlador
        new MainController(this);

        Scene scene = new Scene(root, 950, 650);
        stage.setTitle("AGROFRUTOS LAMBAYEQUE");
        stage.setScene(scene);
        stage.show();
    }

    private Button crearBotonMenu(String texto) {
        Button btn = new Button(texto);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setPrefHeight(40);
        btn.setStyle("-fx-background-color: #34495e; -fx-text-fill: white; -fx-font-size: 14px; -fx-cursor: hand; -fx-background-radius: 6;");
        return btn;
    }

    public void mostrarInicio() {
        VBox inicioBox = new VBox(10);
        inicioBox.setAlignment(Pos.CENTER);

        Label titulo = new Label("Bienvenido a Agrofrutos Lambayeque");
        titulo.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label subtitulo = new Label("Selecciona una opción del menú lateral para comenzar.");
        subtitulo.setStyle("-fx-font-size: 14px; -fx-text-fill: #555555;");

        inicioBox.getChildren().addAll(titulo, subtitulo);
        root.setCenter(inicioBox);
    }

    public void mostrarContactos(List<Contacto> lista) {
        contenedorCards.getChildren().clear();

        Label titulo = new Label("Contactos Agrofrutos");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Formulario de ingreso manual
        VBox formBox = crearFormulario();

        contenedorCards.getChildren().addAll(titulo, formBox);

        // Renderizar la lista de tarjetas existentes
        for (Contacto c : lista) {
            VBox card = new VBox(6);
            card.setPadding(new Insets(15));
            card.setStyle("-fx-background-color: #eef4ff; -fx-background-radius: 8; -fx-border-color: #d0d7de; -fx-border-radius: 8;");

            Label nombre = new Label(c.getNombre() + " " + c.getApellido());
            nombre.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #111111;");

            Label datos = new Label("Tel: " + c.getTelefono() + " | Correo: " + c.getCorreo());
            datos.setStyle("-fx-font-size: 13px; -fx-text-fill: #333333;");

            Label msg = new Label("Mensaje: " + c.getMensaje());
            msg.setStyle("-fx-font-size: 13px; -fx-text-fill: #444444;");

            card.getChildren().addAll(nombre, datos, msg);
            contenedorCards.getChildren().add(card);
        }

        ScrollPane scrollPane = new ScrollPane(contenedorCards);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: #ffffff;");

        root.setCenter(scrollPane);
    }

    private VBox crearFormulario() {
        VBox formContainer = new VBox(10);
        formContainer.setPadding(new Insets(15));
        formContainer.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #cccccc; -fx-border-radius: 8; -fx-background-radius: 8;");

        Label lblForm = new Label("Agregar Nuevo Contacto");
        lblForm.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(txtNombre, 1, 0);

        grid.add(new Label("Apellido:"), 2, 0);
        grid.add(txtApellido, 3, 0);

        grid.add(new Label("Teléfono:"), 0, 1);
        grid.add(txtTelefono, 1, 1);

        grid.add(new Label("Correo:"), 2, 1);
        grid.add(txtCorreo, 3, 1);

        grid.add(new Label("Mensaje:"), 0, 2);
        grid.add(txtMensaje, 1, 2, 3, 1);

        formContainer.getChildren().addAll(lblForm, grid, btnGuardarContacto);
        return formContainer;
    }

    public void limpiarFormulario() {
        txtNombre.clear();
        txtApellido.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtMensaje.clear();
    }

    // Getters
    public Button getBtnInicio() { return btnInicio; }
    public Button getBtnContactos() { return btnContactos; }
    public Button getBtnGuardarContacto() { return btnGuardarContacto; }

    public String getTxtNombre() { return txtNombre.getText(); }
    public String getTxtApellido() { return txtApellido.getText(); }
    public String getTxtTelefono() { return txtTelefono.getText(); }
    public String getTxtCorreo() { return txtCorreo.getText(); }
    public String getTxtMensaje() { return txtMensaje.getText(); }
}