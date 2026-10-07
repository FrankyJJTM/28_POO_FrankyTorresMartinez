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
import javafx.scene.layout.HBox;
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

    private TextField txtNombre;
    private TextField txtApellido;
    private TextField txtTelefono;
    private TextField txtCorreo;
    private TextField txtMensaje;
    private Button btnGuardarContacto;
    private Button btnCancelarEdicion;
    private Label lblTituloForm;

    private TextField txtBuscar;
    private Button btnBuscar;
    private Button btnLimpiarBusqueda;

    private Integer idContactoSeleccionado = null;

    @Override
    public void start(Stage stage) {
        root = new BorderPane();

        VBox menuLateral = new VBox(15);
        menuLateral.setPadding(new Insets(20));
        menuLateral.setPrefWidth(220);
        menuLateral.setStyle("-fx-background-color: #2c3e50;");

        btnInicio = crearBotonMenu("Agrofrutos Lambayeque");
        btnContactos = crearBotonMenu("Contactos");

        menuLateral.getChildren().addAll(btnInicio, btnContactos);
        root.setLeft(menuLateral);

        contenedorCards = new VBox(15);
        contenedorCards.setPadding(new Insets(25));

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

        btnCancelarEdicion = new Button("Cancelar");
        btnCancelarEdicion.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 8 15 8 15; -fx-background-radius: 5;");
        btnCancelarEdicion.setVisible(false);

        lblTituloForm = new Label("Agregar Nuevo Contacto");
        lblTituloForm.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        txtBuscar = new TextField();
        txtBuscar.setPromptText("Buscar por nombre, apellido o correo...");
        txtBuscar.setPrefWidth(300);

        btnBuscar = new Button("Buscar");
        btnBuscar.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 6 12 6 12; -fx-background-radius: 5;");

        btnLimpiarBusqueda = new Button("Limpiar");
        btnLimpiarBusqueda.setStyle("-fx-background-color: #7f8c8d; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 6 12 6 12; -fx-background-radius: 5;");

        mostrarInicio();

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

        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        searchBox.getChildren().addAll(txtBuscar, btnBuscar, btnLimpiarBusqueda);

        VBox formBox = crearFormulario();

        contenedorCards.getChildren().addAll(titulo, searchBox, formBox);

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

            // Botones de acción para cada registro (UPDATE / DELETE)
            Button btnEditar = new Button("Editar");
            btnEditar.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 4 10 4 10; -fx-background-radius: 4;");
            btnEditar.setOnAction(e -> cargarContactoEnFormulario(c));

            Button btnEliminar = new Button("Eliminar");
            btnEliminar.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 4 10 4 10; -fx-background-radius: 4;");
            btnEliminar.setUserData(c.getId()); // Guardamos el ID en el botón para el controlador

            HBox accionesBox = new HBox(10);
            accionesBox.getChildren().addAll(btnEditar, btnEliminar);

            card.getChildren().addAll(nombre, datos, msg, accionesBox);
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

        HBox btnBox = new HBox(10);
        btnBox.getChildren().addAll(btnGuardarContacto, btnCancelarEdicion);

        formContainer.getChildren().addAll(lblTituloForm, grid, btnBox);
        return formContainer;
    }

    public void cargarContactoEnFormulario(Contacto c) {
        this.idContactoSeleccionado = c.getId();
        txtNombre.setText(c.getNombre());
        txtApellido.setText(c.getApellido());
        txtTelefono.setText(c.getTelefono());
        txtCorreo.setText(c.getCorreo());
        txtMensaje.setText(c.getMensaje());

        lblTituloForm.setText("Editar Contacto (ID: " + c.getId() + ")");
        btnGuardarContacto.setText("Actualizar Contacto");
        btnCancelarEdicion.setVisible(true);
    }

    public void limpiarFormulario() {
        this.idContactoSeleccionado = null;
        txtNombre.clear();
        txtApellido.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtMensaje.clear();

        lblTituloForm.setText("Agregar Nuevo Contacto");
        btnGuardarContacto.setText("Guardar Contacto");
        btnCancelarEdicion.setVisible(false);
    }

    public Button getBtnInicio() { return btnInicio; }
    public Button getBtnContactos() { return btnContactos; }
    public Button getBtnGuardarContacto() { return btnGuardarContacto; }
    public Button getBtnCancelarEdicion() { return btnCancelarEdicion; }
    public Button getBtnBuscar() { return btnBuscar; }
    public Button getBtnLimpiarBusqueda() { return btnLimpiarBusqueda; }

    public String getTxtNombre() { return txtNombre.getText(); }
    public String getTxtApellido() { return txtApellido.getText(); }
    public String getTxtTelefono() { return txtTelefono.getText(); }
    public String getTxtCorreo() { return txtCorreo.getText(); }
    public String getTxtMensaje() { return txtMensaje.getText(); }
    public String getTxtBuscar() { return txtBuscar.getText(); }

    public Integer getIdContactoSeleccionado() { return idContactoSeleccionado; }
    public VBox getContenedorCards() { return contenedorCards; }
}