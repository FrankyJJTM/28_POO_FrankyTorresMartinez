package vallegrande.edu.pe.misistema.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import vallegrande.edu.pe.misistema.model.Pedido;
import vallegrande.edu.pe.misistema.model.PedidoDAO;
import vallegrande.edu.pe.misistema.view.MainView;

public class MainController {

    private MainView view;
    private PedidoDAO dao;
    private Pedido pedidoSeleccionado;

    public MainController(MainView view) {
        this.view = view;
        this.dao = new PedidoDAO();

        initEvents();
        cargarPedidos();
    }

    private void initEvents() {
        view.getBtnRegistrar().setOnAction(e -> registrarPedido());
        view.getBtnActualizar().setOnAction(e -> actualizarPedido());
        view.getBtnEliminar().setOnAction(e -> eliminarPedido());

        view.getTablePedidos().getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> seleccionarFila(newVal)
        );
    }

    public void cargarPedidos() {
        ObservableList<Pedido> lista = FXCollections.observableArrayList(dao.listar());
        view.getTablePedidos().setItems(lista);
    }

    private void registrarPedido() {
        if (!validarFormulario()) return;

        String cliente = view.getTxtCliente().getText().trim();
        String producto = view.getTxtProducto().getText().trim();
        int cantidad = Integer.parseInt(view.getTxtCantidad().getText().trim());
        String estado = view.getCbEstado().getValue();

        Pedido p = new Pedido(cliente, producto, cantidad, estado);
        if (dao.insertar(p)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Pedido registrado correctamente.");
            limpiarFormulario();
            cargarPedidos();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo registrar el pedido.");
        }
    }

    private void seleccionarFila(Pedido p) {
        if (p != null) {
            pedidoSeleccionado = p;
            view.getTxtCliente().setText(p.getCliente());
            view.getTxtProducto().setText(p.getProducto());
            view.getTxtCantidad().setText(String.valueOf(p.getCantidad()));
            view.getCbEstado().setValue(p.getEstado());
        }
    }

    private void actualizarPedido() {
        if (pedidoSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Seleccione un pedido de la tabla.");
            return;
        }

        if (!validarFormulario()) return;

        pedidoSeleccionado.setCliente(view.getTxtCliente().getText().trim());
        pedidoSeleccionado.setProducto(view.getTxtProducto().getText().trim());
        pedidoSeleccionado.setCantidad(Integer.parseInt(view.getTxtCantidad().getText().trim()));
        pedidoSeleccionado.setEstado(view.getCbEstado().getValue());

        if (dao.actualizar(pedidoSeleccionado)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Pedido actualizado correctamente.");
            limpiarFormulario();
            cargarPedidos();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el pedido.");
        }
    }

    private void eliminarPedido() {
        if (pedidoSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Seleccione un pedido de la tabla.");
            return;
        }

        if (dao.eliminar(pedidoSeleccionado.getId())) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Pedido eliminado correctamente.");
            limpiarFormulario();
            cargarPedidos();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el pedido.");
        }
    }

    private void limpiarFormulario() {
        view.getTxtCliente().clear();
        view.getTxtProducto().clear();
        view.getTxtCantidad().clear();
        view.getCbEstado().getSelectionModel().selectFirst();
        view.getTablePedidos().getSelectionModel().clearSelection();
        pedidoSeleccionado = null;
    }

    private boolean validarFormulario() {
        if (view.getTxtCliente().getText().trim().isEmpty() ||
                view.getTxtProducto().getText().trim().isEmpty() ||
                view.getTxtCantidad().getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Validación", "Todos los campos son obligatorios.");
            return false;
        }
        try {
            Integer.parseInt(view.getTxtCantidad().getText().trim());
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Validación", "La cantidad debe ser un número entero.");
            return false;
        }
        return true;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
