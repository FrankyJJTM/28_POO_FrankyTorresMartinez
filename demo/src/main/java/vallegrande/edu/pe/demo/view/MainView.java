package vallegrande.edu.pe.demo.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainView extends BorderPane {

    private Button btnInicio;
    private Button btnUsuarios;
    private Button btnProductos;
    private Button btnReportes;
    private Button btnConfiguracion;
    private Button btnCitas;

    private VBox menu;
    private double hue = 0;

    public MainView() {
        crearMenu();
        mostrarInicio();
    }

    private void crearMenu() {
        menu = new VBox(15);
        menu.setPadding(new Insets(25));
        menu.setPrefWidth(220);

        Label titulo = new Label("Mi Sistema");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: white;");

        btnInicio = crearBoton("Inicio");
        btnUsuarios = crearBoton("Usuarios");
        btnProductos = crearBoton("Productos");
        btnReportes = crearBoton("Reportes");
        btnConfiguracion = crearBoton("Configuración");
        btnCitas = crearBoton("Citas");

        menu.getChildren().addAll(
                titulo,
                btnInicio,
                btnUsuarios,
                btnProductos,
                btnReportes,
                btnConfiguracion,
                btnCitas
        );

        setLeft(menu);
        iniciarEfectoRGB();
    }

    private void iniciarEfectoRGB() {
        Timeline rgbTimeline = new Timeline(new KeyFrame(Duration.millis(40), e -> {
            hue = (hue + 1) % 360;
            menu.setStyle(String.format("-fx-background-color: hsb(%.1f, 80%%, 90%%);", hue));
        }));
        rgbTimeline.setCycleCount(Timeline.INDEFINITE);
        rgbTimeline.play();
    }

    private Button crearBoton(String texto) {
        Button boton = new Button(texto);
        boton.setPrefWidth(170);
        boton.setPrefHeight(40);
        boton.setStyle(
                "-fx-background-color: white;" +
                        "-fx-text-fill: #1E3A8A;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;"
        );
        return boton;
    }

    public void mostrarInicio() {
        VBox contenido = new VBox(10);
        contenido.setAlignment(Pos.CENTER);
        Label titulo = new Label("Bienvenido");
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        Label texto = new Label("Panel principal del sistema");
        contenido.getChildren().addAll(titulo, texto);
        setCenter(contenido);
    }

    public void mostrarUsuarios() {
        VBox contenido = new VBox(20);
        contenido.setPadding(new Insets(30));
        Label titulo = new Label("Usuarios");
        titulo.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        HBox tarjetas = new HBox(15);
        tarjetas.getChildren().addAll(
                crearTarjeta("Carlos Perez", "Administrador"),
                crearTarjeta("Maria Perez", "Vendedora"),
                crearTarjeta("Piero Perez", "Supervisor")
        );

        contenido.getChildren().addAll(titulo, tarjetas);
        setCenter(contenido);
    }

    public void mostrarProductos() {
        VBox contenido = new VBox(20);
        contenido.setPadding(new Insets(30));
        Label titulo = new Label("Productos");
        titulo.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        HBox tarjetas = new HBox(15);
        tarjetas.getChildren().addAll(
                crearTarjeta("Laptop Lenovo", "S/ 2500"),
                crearTarjeta("Teclado RGB", "S/ 80"),
                crearTarjeta("Mouse Gamer", "S/ 180")
        );

        contenido.getChildren().addAll(titulo, tarjetas);
        setCenter(contenido);
    }

    public void mostrarReportes() {
        VBox contenido = new VBox(20);
        contenido.setPadding(new Insets(30));
        Label titulo = new Label("Reportes");
        titulo.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        HBox tarjetas = new HBox(15);
        tarjetas.getChildren().addAll(
                crearTarjeta("Ventas del Mes", "S/ 45,000"),
                crearTarjeta("Productos Vendidos", "120 Unidades"),
                crearTarjeta("Nuevos Clientes", "35 Registros")
        );

        contenido.getChildren().addAll(titulo, tarjetas);
        setCenter(contenido);
    }

    public void mostrarConfiguracion() {
        VBox contenido = new VBox(20);
        contenido.setPadding(new Insets(30));
        Label titulo = new Label("Configuración");
        titulo.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        HBox tarjetas = new HBox(15);
        tarjetas.getChildren().addAll(
                crearTarjeta("Apariencia", "Tema Claro"),
                crearTarjeta("Idioma", "Español"),
                crearTarjeta("Seguridad", "2FA Activado")
        );

        contenido.getChildren().addAll(titulo, tarjetas);
        setCenter(contenido);
    }

    public void mostrarCitas() {
        VBox contenido = new VBox(20);
        contenido.setPadding(new Insets(30));
        Label titulo = new Label("Citas Programadas");
        titulo.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        HBox tarjetas = new HBox(15);
        tarjetas.getChildren().addAll(
                crearTarjeta("Reunión Cliente A", "10:00 AM"),
                crearTarjeta("Revisión de Stock", "02:30 PM"),
                crearTarjeta("Soporte Técnico", "04:00 PM")
        );

        contenido.getChildren().addAll(titulo, tarjetas);
        setCenter(contenido);
    }

    private VBox crearTarjeta(String titulo, String texto) {
        VBox tarjeta = new VBox(8);
        tarjeta.setPadding(new Insets(20));
        tarjeta.setPrefWidth(180);
        tarjeta.setStyle("-fx-background-color: #EAF2FF; -fx-background-radius: 12;");

        Label nombre = new Label(titulo);
        nombre.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label info = new Label(texto);
        tarjeta.getChildren().addAll(nombre, info);
        return tarjeta;
    }

    public Button getBtnInicio() { return btnInicio; }
    public Button getBtnUsuarios() { return btnUsuarios; }
    public Button getBtnProductos() { return btnProductos; }
    public Button getBtnReportes() { return btnReportes; }
    public Button getBtnConfiguracion() { return btnConfiguracion; }
    public Button getBtnCitas() { return btnCitas; }
}