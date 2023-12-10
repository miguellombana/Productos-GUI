package dad.productos.stock;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import aed.hibernate.Familia;
import aed.hibernate.Producto;
import aed.hibernate.Stock;
import aed.productos.dao.ProductoDAO;
import aed.productos.dao.StockDAO;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class StockController implements Initializable {

	
	// actions
	
	private EventHandler<ActionEvent> onBack;

	//model 
	
	private ListProperty<Stock> stocks = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Producto> productos = new SimpleListProperty<>(FXCollections.observableArrayList());
	
	
    // view
    @FXML
    private TableColumn<Stock, Number> Unidades;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Stock, Familia> codFamilia;

    @FXML
    private TableColumn<Stock, Number> codTienda;

    @FXML
    private TableColumn<Stock, Number> codigoProducto;

    @FXML
    private TableColumn<Stock, String> denoFamilia;

    @FXML
    private TableColumn<Producto, String> denoProducto;

    @FXML
    private TableColumn<Stock, String> denoTienda;

    @FXML
    private TableColumn<Stock, String> observaciones;  
    @FXML
    private TableView<Stock> productsTable;

    @FXML
    private BorderPane view;



	
	
	public StockController() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StockView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	 
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

			  codigoProducto.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getCodProducto().getCodProducto()));
			  codTienda.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getCodTienda().getCodTienda()));
			  Unidades.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getUnidades()));


		    productsTable.itemsProperty().bind(stocks);
	} 
	 
	
	public void reload() {
	 	 stocks.setAll(StockDAO.getStocks());

	}
	
 	
	 public BorderPane getView() {
		return view;
	}
	
	
    @FXML
    void onBack(ActionEvent event) {
    	onBack.handle(event);
    }

    public void setOnBack(EventHandler<ActionEvent> onBack) {
		this.onBack = onBack;
	}	

}
