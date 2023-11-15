package dad.productos.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuController implements Initializable {
	
	// actions
	
	private EventHandler<ActionEvent> onAddProduct;
	private EventHandler<ActionEvent> onDeleteProduct;
	private EventHandler<ActionEvent> onModifyProduct;
	private EventHandler<ActionEvent> onListProducts;
	private EventHandler<ActionEvent> onShowStockProduct;
	
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
		// TODO Auto-generated method stub		
	}
	
	public VBox getView() {
		return view;
	}

	@FXML
    void onAddProduct(ActionEvent event) {
    	System.out.println("ir a añadir producto");
    	if (onAddProduct != null) {
    		onAddProduct.handle(event);
    	}
    }

    @FXML
    void onDeleteProduct(ActionEvent event) {
    	System.out.println("ir a eliminar producto");
    	if(onDeleteProduct != null) onDeleteProduct.handle(event);;
    }

    @FXML
    void onListProducts(ActionEvent event) {
    	System.out.println("ir a listar productos");
    	if(onListProducts != null) onListProducts.handle(event);;
    }

    @FXML
    void onModifyProduct(ActionEvent event) {
    	System.out.println("ir a modificar producto");
    	if(onModifyProduct != null) onModifyProduct.handle(event);;
    }

    @FXML
    void onShowStock(ActionEvent event) {
    	System.out.println("ir a mostrar stock");
    	if(onShowStockProduct != null) onShowStockProduct.handle(event);;
    	
    }
    
    public void setOnAdd(EventHandler<ActionEvent> onInsertar) {
		this.onAddProduct = onInsertar;
	}    

    public void setOnDelete(EventHandler<ActionEvent> onDelete) {
		this.onDeleteProduct = onDelete;
	}

	public void setOnModify(EventHandler<ActionEvent> onModify) {
		this.onModifyProduct = onModify;
	}

	public void setOnListProducts(EventHandler<ActionEvent> onListProducts) {
		this.onListProducts = onListProducts;
	}

	public void setOnShowStock(EventHandler<ActionEvent> onShowStock) {
		this.onShowStockProduct = onShowStock;
	}
	
}
