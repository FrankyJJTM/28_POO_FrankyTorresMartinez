package vallegrande.edu.pe.agrofrutoslambayeque.controller;

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
    }
}