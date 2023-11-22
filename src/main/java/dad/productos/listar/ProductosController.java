package dad.productos.listar;

import java.awt.Checkbox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import aed.hibernate.Familia;
import aed.hibernate.Producto;
import aed.productos.dao.ProductoDAO;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.BorderPane;

public class ProductosController  implements Initializable {
	
	//actions
	private EventHandler<ActionEvent> onBack;
	
	//model
	private ListProperty<Producto> productos = new SimpleListProperty<>(FXCollections.observableArrayList());
	
	//view
	@FXML
	private TableColumn<Producto, Number> codigoColumn;

	@FXML
	private TableColumn<Producto, Boolean> congeladoColumn;

	@FXML
	private TableColumn<Producto, String> denoColumn;

	@FXML
	private TableColumn<Producto, String> familiaColumn;

	@FXML
	private TableColumn<Producto, Familia> observaColumn;

	@FXML
	private TableColumn<Producto, Number> precioColumn;

	@FXML
	private TableView<Producto> productsTable;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		codigoColumn.setCellValueFactory (c -> new SimpleLongProperty(c.getValue().getCodProducto()));
		congeladoColumn.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue().isCongelado()));
		denoColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDenoProducto()));
	//	familiaColumn.setCellValueFactory(c -> new SimpleObjectProperty<Familia>(c.getValue().getFamilia()));
	//	observaColumn.setCellValueFactory(c -> new SimpleObjectProperty<ProductosController> (c.getValue().getObservacion()));
		precioColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrecioBase()));      
	
	//bindings
		productsTable.itemsProperty().bind(productos);
	//Cargamos los productos
		
//	productos.setAll(ProductoDAO.getProducto());
		
		
		congeladoColumn.setCellFactory(CheckBoxTableCell.forTableColumn(congeladoColumn));
		
		
		
	}


	@FXML
	private BorderPane view;

	public ProductosController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductosView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	public BorderPane getView() {
		return view;
	}
	


    @FXML
    void onBack(ActionEvent event) {
    	System.out.println("Cancelar");
    	if (onBack != null)  onBack.handle(event);
    
    }

	public void setOnBack(EventHandler<ActionEvent> onBack) {
		this.onBack =  onBack;		
	}
}
