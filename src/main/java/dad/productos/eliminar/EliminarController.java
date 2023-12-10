package dad.productos.eliminar;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.hibernate.Session;
import javafx.util.converter.IntegerStringConverter;
import aed.hibernate.Familia;
import aed.hibernate.HibernateUtil;
import aed.hibernate.Producto;
import aed.hibernate.ProductoObservacion;
import aed.productos.dao.FamiliaDAO;
import aed.productos.dao.ObservacionDAO;
import aed.productos.dao.ProductoDAO;
import aed.productos.dao.StockDAO;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;

public class EliminarController implements Initializable {

	// actions
	
	private EventHandler<ActionEvent> onBack;
	private EventHandler<ActionEvent> ondelete;
	private EventHandler<ActionEvent> onShowProduct;
	
	
	//model
	
	private SimpleIntegerProperty codEliminarProperty = new SimpleIntegerProperty();
    private  SimpleStringProperty  observacionProperty  = new SimpleStringProperty();
    private  SimpleStringProperty denoProductoProperty = new SimpleStringProperty();
    private  SimpleStringProperty familiaProductoProperty = new SimpleStringProperty();
    private  SimpleBooleanProperty congeladoProperty = new SimpleBooleanProperty();
    private  SimpleBooleanProperty confirmacionProperty = new SimpleBooleanProperty();
    private  SimpleDoubleProperty precioProductoProperty = new SimpleDoubleProperty();

	
	//view
	
    @FXML
    private Button buttonEliminar;

    @FXML
    private Button buttonVizualizar;

    @FXML
    private Button buttonVolver;

    @FXML
    private TextField codEliminar;

    @FXML
    private CheckBox confirmacion;

    @FXML
    private CheckBox congelado;

    @FXML
    private TextField denoProducto;

    @FXML
    private TextField familiaProducto;

    @FXML
    private TextField observacion;

    @FXML
    private TextField precioProducto;

    @FXML
    private BorderPane view;


	
	
	
	
	
	

	
	
	public EliminarController() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EliminarView.fxml"));
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
	        
	        
	        // Bindings para Boolean (CheckBox)
	        Bindings.bindBidirectional(congelado.selectedProperty(), congeladoProperty);
	        

	        // Bindings para String (CheckBox)
	        Bindings.bindBidirectional(confirmacion.selectedProperty(), confirmacionProperty);
	    
		  
		  
		
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
    
    @FXML
    void onDelete(ActionEvent event) {
        // Mostrar una alerta de confirmación antes de eliminar
        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmación de Eliminación");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("¿Estás seguro de que quieres eliminar este producto o observacion?");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (confirmacion.isSelected()) {
                // Solo elimina la observación del Producto
                System.out.println("Solo elimina la observación");
                Session sesion = HibernateUtil.getSessionFactory().openSession();
                ObservacionDAO.eliminarObservacionProducto(codEliminarProperty.get(), sesion);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Observación Eliminada");
                alert.setHeaderText(null);
                alert.setContentText("Observación Eliminada Correctamente");
                alert.showAndWait();
            } else {
                // Elimina todo el producto
                Session sesion = HibernateUtil.getSessionFactory().openSession();
                System.out.println("El código de producto a eliminar es: " + codEliminarProperty.get());
                ObservacionDAO.eliminarObservacionProducto(codEliminarProperty.get(), sesion);
                StockDAO.eliminarStock(codEliminarProperty.get(), sesion);
                ProductoDAO.deleteProducto(codEliminarProperty.get(), sesion);
                codEliminar.setText(null);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Eliminado");
                alert.setHeaderText(null);
                alert.setContentText("Producto Eliminado Correctamente");
                alert.showAndWait();
            }
        } else {
            // El usuario seleccionó Cancelar en la alerta de confirmación
            System.out.println("Eliminación cancelada por el usuario.");
        }
    }
    	


    	
     

    @FXML
    void onShowProduct(ActionEvent event) {
    	Session sesion = HibernateUtil.getSessionFactory().openSession();
    	Producto producto = ProductoDAO.getProductoPorId(codEliminarProperty.get(), sesion);
    	
    	
    	if(producto == null) {
    		
    		
    		System.out.println("Es nulo");
        	familiaProducto.setText("");
        	precioProducto.setText("");
        	denoProducto.setText("");
        	congelado.setSelected(false);
        	this.observacion.setText("");
    		
    	    Alert alert = new Alert(AlertType.WARNING);
    	    alert.setTitle("Error");
    	    alert.setHeaderText(null);
    	    alert.setContentText("El codigo introducido no corresponde a ningun producto");
    	    alert.showAndWait();
    		
    		
    		

    		
    		
    	}else {
    	
    	
    	
    	Familia familia = FamiliaDAO.obtenerFamiliaPorIdProducto(producto.getCodProducto(), sesion);
    	
    	
    	
    	
    	
    	familiaProducto.setText(familia.getDenoFamilia());
    	precioProducto.setText( Double.toString(producto.getPrecioBase()));
    	denoProducto.setText(producto.getDenoProducto());
    	congelado.setSelected(producto.isCongelado());
    	
    	
    	ProductoObservacion observacion = ObservacionDAO.obtenerObservacionPorProducto(producto, sesion);
    	if (observacion != null) {
    		
    		this.observacion.setText(observacion.getObservacion());
    	}else {
    		
    		this.observacion.setText("");
    	}
    	System.out.println("producto nombre: " + producto.getDenoProducto() +"\n Property valor" + denoProductoProperty.get() + "\n valor campo" + denoProducto.getText());
    	}
    	
    	
    	
    	
    	
    	
    	
    	
    	

    }
    
    
    
    
    

	public EventHandler<ActionEvent> getOnShowProduct() {
		return onShowProduct;
	}

	public void setOnShowProduct(EventHandler<ActionEvent> onShowProduct) {
		this.onShowProduct = onShowProduct;
	}

	public EventHandler<ActionEvent> getOndelete() {
		return ondelete;
	}

	public void setOndelete(EventHandler<ActionEvent> ondelete) {
		this.ondelete = ondelete;
	}

	public SimpleStringProperty getObservacionProperty() {
		return observacionProperty;
	}

	public void setObservacionProperty(SimpleStringProperty observacionProperty) {
		this.observacionProperty = observacionProperty;
	}

	public SimpleStringProperty getDenoProductoProperty() {
		return denoProductoProperty;
	}

	public void setDenoProductoProperty(SimpleStringProperty string) {
		this.denoProductoProperty = string;
	}

	public SimpleStringProperty getFamiliaProductoProperty() {
		return familiaProductoProperty;
	}

	public void setFamiliaProductoProperty(SimpleStringProperty familiaProductoProperty) {
		this.familiaProductoProperty = familiaProductoProperty;
	}

	public SimpleBooleanProperty getCongeladoProperty() {
		return congeladoProperty;
	}

	public void setCongeladoProperty(SimpleBooleanProperty congeladoProperty) {
		this.congeladoProperty = congeladoProperty;
	}

	public SimpleDoubleProperty getPrecioProductoProperty() {
		return precioProductoProperty;
	}

	public void setPrecioProductoProperty(SimpleDoubleProperty precioProductoProperty) {
		this.precioProductoProperty = precioProductoProperty;
	}


    
}
