package dad.productos.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.productos.insertar.InsertarController;
import dad.productos.listar.ProductosController;
import dad.productos.menu.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {
	
	// controllers
	
	private MenuController menuController;
	private InsertarController insertarController;	
	private ProductosController productosController;	
	
	// view
	
	@FXML
	private BorderPane view;

	public MainController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// instanciamos los controladores
		
		menuController = new MenuController();
		menuController.setOnAdd(e -> {
			view.setCenter(insertarController.getView());
		});
		menuController.setOnListProducts(e -> {
			productosController.reload();
			view.setCenter(productosController.getView());
		});
		
		productosController = new ProductosController();
		productosController.setOnBack(e -> {
			view.setCenter(menuController.getView());
		});
		
		insertarController = new InsertarController();
		insertarController.setOnBack(e -> {
			view.setCenter(menuController.getView());
		});

		// inicialmnte mostramos la vista del menú
		
		view.setCenter(menuController.getView());

	}
	
	public BorderPane getView() {
		return view;
	}

}
