package dad.productos.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class MenuController implements Initializable {
	
	// actions
	
	private EventHandler<ActionEvent> onAdd;
	private EventHandler<ActionEvent> onDelete;
	private EventHandler<ActionEvent> onModify;
	private EventHandler<ActionEvent> onListProducts;
	private EventHandler<ActionEvent> onShowStock;
	
	// view
	
    @FXML
    private VBox view;
	
	public MenuController() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// do nothing
	}
	
	public VBox getView() {
		return view;
	}

	@FXML
    void onAddProduct(ActionEvent event) {
    	System.out.println("ir a añadir producto");
    	onAdd.handle(event);
    }

    @FXML
    void onDeleteProduct(ActionEvent event) {
    	System.out.println("ir a eliminar producto");
    	onDelete.handle(event);
    }

    @FXML
    void onListProducts(ActionEvent event) {
    	System.out.println("ir a listar productos");
    	onListProducts.handle(event);
    }

    @FXML
    void onModifyProduct(ActionEvent event) {
    	System.out.println("ir a modificar producto");
    	onModify.handle(event);
    }

    @FXML
    void onShowStock(ActionEvent event) {
    	System.out.println("ir a mostrar stock");
    	onShowStock.handle(event);    	
    }
    
    public void setOnAdd(EventHandler<ActionEvent> onInsertar) {
		this.onAdd = onInsertar;
	}    

    public void setOnDelete(EventHandler<ActionEvent> onDelete) {
		this.onDelete = onDelete;
	}

	public void setOnModify(EventHandler<ActionEvent> onModify) {
		this.onModify = onModify;
	}

	public void setOnListProducts(EventHandler<ActionEvent> onListProducts) {
		this.onListProducts = onListProducts;
	}

	public void setOnShowStock(EventHandler<ActionEvent> onShowStock) {
		this.onShowStock = onShowStock;
	}
	
}
