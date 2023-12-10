package dad.productos.modificar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.Transaction;

import aed.hibernate.Familia;
import aed.hibernate.HibernateUtil;
import aed.hibernate.Producto;
import aed.hibernate.ProductoObservacion;
import aed.productos.dao.FamiliaDAO;
import aed.productos.dao.ObservacionDAO;
import aed.productos.dao.ProductoDAO;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;

public class ModificarController implements Initializable {

	// actions
	
	private EventHandler<ActionEvent> onBack;
	private EventHandler<ActionEvent> onGuardar;
	private EventHandler<ActionEvent> onShowProduct;
	
	//model
	
	private SimpleIntegerProperty codEliminarProperty = new SimpleIntegerProperty();
    private  SimpleStringProperty  observacionProperty  = new SimpleStringProperty();
    private  SimpleStringProperty denoProductoProperty = new SimpleStringProperty();
    private ObjectProperty<Familia> familiaProductoProperty = new SimpleObjectProperty<>();
    private  SimpleBooleanProperty congeladoProperty = new SimpleBooleanProperty();
    private  SimpleBooleanProperty confirmacionProperty = new SimpleBooleanProperty();
    private  SimpleDoubleProperty precioProductoProperty = new SimpleDoubleProperty();
	//view
    @FXML
    private Button buttonGuardar;

    @FXML
    private Button buttonVizualizar;

    @FXML
    private Button buttonVolver;

    @FXML
    private TextField codEliminar;

    @FXML
    private CheckBox congelado;

    @FXML
    private TextField denoProducto;


    @FXML
    private ComboBox<Familia> familiaProducto;

    @FXML
    private TextField observacion;

    @FXML
    private TextField precioProducto;

    @FXML
    private BorderPane view;

	
	
	
	
	
	
	
	
	
	
	public ModificarController() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ModificarView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
 


	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		
		  Bindings.bindBidirectional(codEliminar.textProperty(), codEliminarProperty, new NumberStringConverter());	
		
		  

	        // Bindings para String
	        denoProducto.textProperty().bindBidirectional(denoProductoProperty);
	        precioProducto.textProperty().bindBidirectional(precioProductoProperty, new NumberStringConverter());
	     //   Bindings.bindBidirectional(familiaProducto.selectedProperty(), familiaProductoProperty);
	        familiaProductoProperty.bind(familiaProducto.getSelectionModel().selectedItemProperty());
	        
	        // Bindings para Boolean (CheckBox)
	        Bindings.bindBidirectional(congelado.selectedProperty(), congeladoProperty);
	        
	        familiaProducto.getItems().setAll(FamiliaDAO.getFamilias());

		    // Establecer la familia por defecto (por ejemplo, la primera de la lista)
		    if (!familiaProducto.getItems().isEmpty()) {
		        familiaProducto.setValue(familiaProducto.getItems().get(0));
		    }
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public BorderPane getView() {
		return view;
	}
	
    @FXML
    void onGuardar(ActionEvent event) {
    	
        Session sesion = HibernateUtil.getSessionFactory().openSession();

            if (ProductoDAO.modificarProducto(codEliminarProperty.get(), denoProducto.getText(), Double.parseDouble(precioProducto.getText()), congelado.isSelected(),
            		familiaProductoProperty.get(), observacion.getText(), sesion) == 1) {

                
        	    Alert alert = new Alert(AlertType.INFORMATION);
        	    alert.setTitle("Modificado");
        	    alert.setHeaderText(null);
        	    alert.setContentText("Producto Modificado Correctamente");
        	    alert.showAndWait();
        		
                
onBack(event);}else {
	
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Modificado");
    alert.setHeaderText(null);
    alert.setContentText("Producto No encontrado");
    alert.showAndWait();
	
	
	
}


        
    }

    @FXML
    void onShowProduct(ActionEvent event) {

    	
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            Producto producto = ProductoDAO.getProductoPorId(codEliminarProperty.get(), sesion);

            if (producto == null) {
                System.out.println("Es nulo");
                precioProducto.setText("0");
                denoProducto.setText("");
                congelado.setSelected(false);
                this.observacion.setText("");
                familiaProducto.setValue(familiaProducto.getItems().get(0));
                
        	    Alert alert = new Alert(AlertType.WARNING);
        	    alert.setTitle("Error");
        	    alert.setHeaderText(null);
        	    alert.setContentText("El codigo introducido no corresponde a ningun producto");
        	    alert.showAndWait();
        		
        		
                
            } else {
                Familia familia = FamiliaDAO.obtenerFamiliaPorIdProducto(producto.getCodProducto(), sesion);

                precioProducto.setText(Double.toString(producto.getPrecioBase()));
                denoProducto.setText(producto.getDenoProducto());
                congelado.setSelected(producto.isCongelado());
                familiaProducto.setValue(familia);

                ProductoObservacion observacion = ObservacionDAO.obtenerObservacionPorProducto(producto, sesion);
                if (observacion != null) {
                    this.observacion.setText(observacion.getObservacion());
                } else {
                    this.observacion.setText("");
                }
                System.out.println("producto nombre: " + producto.getDenoProducto() + "\n Property valor" + denoProductoProperty.get() + "\n valor campo" + denoProducto.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	

    	
    }

    @FXML
    void onBack(ActionEvent event) {
    	onBack.handle(event);
    }
    public void setOnBack(EventHandler<ActionEvent> onBack) {
		this.onBack = onBack;
	}

}
