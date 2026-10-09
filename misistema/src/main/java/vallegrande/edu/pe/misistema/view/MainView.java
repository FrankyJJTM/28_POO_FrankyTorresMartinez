package vallegrande.edu.pe.misistema.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import vallegrande.edu.pe.misistema.model.Pedido;

public class MainView extends VBox {

    private TextField txtCliente;
    private TextField txtProducto;
    private TextField txtCantidad;
    private ComboBox<String> cbEstado;

    private Button btnRegistrar;
    private Button btnActualizar;
    private Button btnEliminar;

    private TableView<Pedido> tablePedidos;
    private TableColumn<Pedido, Integer> colId;
    private TableColumn<Pedido, String> colCliente;
    private TableColumn<Pedido, String> colProducto;
    private TableColumn<Pedido, Integer> colCantidad;
    private TableColumn<Pedido, String> colEstado;

    public MainView() {
        setSpacing(15);
        setPadding(new Insets(20));
        setStyle("-fx-background-color: #f8fafc;");

        // Título Principal
        Label lblTitulo = new Label("Sistema de Gestión de Pedidos");
        lblTitulo.setStyle("-fx-font-size: 18pt; -fx-font-weight: bold; -fx-text-fill: #1a365d;");

        // Grid para Formulario
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER_LEFT);

        txtCliente = new TextField();
        txtCliente.setPromptText("Ej. Juan Pérez");
        txtProducto = new TextField();
        txtProducto.setPromptText("Ej. Café Orgánico");
        txtCantidad = new TextField();
        txtCantidad.setPromptText("Ej. 10");

        cbEstado = new ComboBox<>();
        cbEstado.getItems().addAll("Pendiente", "En proceso", "Entregado");
        cbEstado.getSelectionModel().selectFirst();

        grid.add(new Label("Cliente:"), 0, 0);
        grid.add(txtCliente, 1, 0);
        grid.add(new Label("Producto:"), 2, 0);
        grid.add(txtProducto, 3, 0);

        grid.add(new Label("Cantidad:"), 0, 1);
        grid.add(txtCantidad, 1, 1);
        grid.add(new Label("Estado:"), 2, 1);
        grid.add(cbEstado, 3, 1);

        // Botones de Acción
        btnRegistrar = new Button("Registrar");
        btnRegistrar.setStyle("-fx-background-color: #2b6cb0; -fx-text-fill: white; -fx-font-weight: bold;");
        btnActualizar = new Button("Actualizar");
        btnActualizar.setStyle("-fx-background-color: #319795; -fx-text-fill: white; -fx-font-weight: bold;");
        btnEliminar = new Button("Eliminar");
        btnEliminar.setStyle("-fx-background-color: #e53e3e; -fx-text-fill: white; -fx-font-weight: bold;");

        HBox boxBotones = new HBox(10, btnRegistrar, btnActualizar, btnEliminar);
        boxBotones.setAlignment(Pos.CENTER_LEFT);

        // TableView
        tablePedidos = new TableView<>();
        colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setPrefWidth(60);

        colCliente = new TableColumn<>("Cliente");
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colCliente.setPrefWidth(200);

        colProducto = new TableColumn<>("Producto");
        colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colProducto.setPrefWidth(200);

        colCantidad = new TableColumn<>("Cantidad");
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colCantidad.setPrefWidth(100);

        colEstado = new TableColumn<>("Estado");
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colEstado.setPrefWidth(140);

        tablePedidos.getColumns().addAll(colId, colCliente, colProducto, colCantidad, colEstado);
        tablePedidos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        getChildren().addAll(lblTitulo, grid, boxBotones, tablePedidos);
    }

    public TextField getTxtCliente() { return txtCliente; }
    public TextField getTxtProducto() { return txtProducto; }
    public TextField getTxtCantidad() { return txtCantidad; }
    public ComboBox<String> getCbEstado() { return cbEstado; }
    public Button getBtnRegistrar() { return btnRegistrar; }
    public Button getBtnActualizar() { return btnActualizar; }
    public Button getBtnEliminar() { return btnEliminar; }
    public TableView<Pedido> getTablePedidos() { return tablePedidos; }
}
