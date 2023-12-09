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
	
	private ListProperty<Object[]> productos = new SimpleListProperty<>(FXCollections.observableArrayList());
	
	
	
    // view
    @FXML
    private TableColumn<Object[], Number> Unidades;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Object[], Familia> codFamilia;

    @FXML
    private TableColumn<Object[], Number> codTienda;

    @FXML
    private TableColumn<Object[], Number> codigoProducto;

    @FXML
    private TableColumn<Object[], String> denoFamilia;

    @FXML
    private TableColumn<Object[], String> denoProducto;

    @FXML
    private TableColumn<Object[], String> denoTienda;

    @FXML
    private TableColumn<Object[], String> observaciones;

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

	    codigoProducto.setCellValueFactory(c -> new SimpleIntegerProperty(((Number) c.getValue()[0]).intValue()));
	    denoProducto.setCellValueFactory(c -> new SimpleStringProperty((String) c.getValue()[1]));
	    codFamilia.setCellValueFactory(c -> new SimpleObjectProperty<>((Familia) c.getValue()[2]));
	    // Asegúrate de adaptar los tipos de datos y las celdas según la estructura real de tus datos

	    codTienda.setCellValueFactory(c -> new SimpleIntegerProperty(((Number) c.getValue()[3]).intValue()));
	    denoFamilia.setCellValueFactory(c -> new SimpleStringProperty((String) c.getValue()[4]));
	    denoTienda.setCellValueFactory(c -> new SimpleStringProperty((String) c.getValue()[5]));
	    observaciones.setCellValueFactory(c -> new SimpleStringProperty((String) c.getValue()[6]));

	    // Configura las celdas según tus necesidades, por ejemplo:
	    Unidades.setCellValueFactory(c -> new SimpleIntegerProperty(((Number) c.getValue()[7]).intValue()));

	 
		

	} 
	
	
	public void reload() {
		productos.setAll(StockDAO.getStocks());
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
