package dad.productos.insertar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.mapping.Set;

import aed.hibernate.Familia;
import aed.hibernate.HibernateUtil;
import aed.hibernate.Producto;
import aed.hibernate.ProductoObservacion;
import aed.productos.dao.FamiliaDAO;
import aed.productos.dao.ObservacionDAO;
import aed.productos.dao.ProductoDAO;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;

public class InsertarController implements Initializable {
	
	// actions
	
	private EventHandler<ActionEvent> onBack;
	
	// model
	
	private BooleanProperty congelado = new SimpleBooleanProperty();
	private StringProperty deno = new SimpleStringProperty();
	private ObjectProperty<Familia> familia = new SimpleObjectProperty<>();
	private StringProperty observacion = new SimpleStringProperty();
	private DoubleProperty precio = new SimpleDoubleProperty();	
	
	// view 
	
    @FXML
    private CheckBox congeladoCheck;

    @FXML
    private TextField denoText;
 
    @FXML
    private ComboBox<Familia> familiaCombo;

    @FXML
    private TextField observacionText;

    @FXML
    private TextField precioText;

    @FXML
    private BorderPane view;
	
	public InsertarController() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InsertarView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// bindings
		
		congelado.bind(congeladoCheck.selectedProperty());
		deno.bind(denoText.textProperty());	
		familia.bind(familiaCombo.getSelectionModel().selectedItemProperty());
		observacion.bind(observacionText.textProperty());
		Bindings.bindBidirectional(precioText.textProperty(), precio, new NumberStringConverter());		
		
		// cargar el combo de familias
		
		familiaCombo.getItems().setAll(FamiliaDAO.getFamilias());
		
	    
	    // Establecer la familia por defecto (por ejemplo, la primera de la lista)
	    if (!familiaCombo.getItems().isEmpty()) {
	        familiaCombo.setValue(familiaCombo.getItems().get(0));
	    }
		
	}
	
	public BorderPane getView() {
		return view;
	}

    @FXML
    void onCancelar(ActionEvent event) {
    	System.out.println("cancelar");
    	onBack.handle(event);
    }

    @FXML
    void onGuardar(ActionEvent event) {
    	
	if(deno.get().isEmpty() || precio.get() == 0) {
		
	    Alert alert = new Alert(AlertType.WARNING);
	    alert.setTitle("Error En la insercion");
	    alert.setHeaderText(null);
	    alert.setContentText("No se puede insertar un producto vacío.");
	    alert.showAndWait();

		
	}else {
		
		
    	
    	Session sesion = HibernateUtil.getSessionFactory().openSession(); // crea la sesion
    	Familia frutas = FamiliaDAO.addFamilia("frutas", sesion);
    	Familia familiaSeleccionada = familiaCombo.getValue();	
		Producto producto = ProductoDAO.addProducto(deno.get(),precio.get(), familiaSeleccionada , congeladoCheck.selectedProperty().get(),sesion);
		ObservacionDAO.addObservacion(producto, observacion.get(), sesion);
		
		
		
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Inserción de producto");
	    alert.setHeaderText(null);
	    alert.setContentText("Producto insertado correctamente");
	    alert.showAndWait();

		
		
		
    	
		//Volvemos a poner valores por defecto una vez añadido el producto
        denoText.clear();;
        precioText.clear();;
        observacionText.clear();
        congeladoCheck.setSelected(false);
        familiaCombo.setValue(familiaCombo.getItems().get(0));
	onBack.handle(event);
    	

	}
    	
    	

    	
    }
    
    public void setOnBack(EventHandler<ActionEvent> onBack) {
		this.onBack = onBack;
	}

}
