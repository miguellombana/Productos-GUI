package dad.productos.listar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class ProductosController  implements Initializable {
	
	private EventHandler<ActionEvent> onBack;
	
	@FXML
	private TableColumn<?, ?> codigoColumn;

	@FXML
	private TableColumn<?, ?> congeladoColumn;

	@FXML
	private TableColumn<?, ?> denoColumn;

	@FXML
	private TableColumn<?, ?> familiaColumn;

	@FXML
	private TableColumn<?, ?> observaColumn;

	@FXML
	private TableColumn<?, ?> precioColumn;

	@FXML
	private TableView<?> productsTable;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
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
