package dad.productos;

import org.hibernate.Session;

import aed.hibernate.Familia;
import aed.hibernate.HibernateUtil;
import aed.hibernate.Producto;
import aed.hibernate.ProductoObservacion;
import aed.hibernate.Stock;
import aed.hibernate.Tienda;
import aed.productos.dao.FamiliaDAO;
import aed.productos.dao.ObservacionDAO;
import aed.productos.dao.ProductoDAO;
import aed.productos.dao.StockDAO;
import aed.productos.dao.TiendaDAO;
import javafx.application.Application;

public class Main {

	public static void main(String[] args) {
		
		
		
		
		Session sesion = HibernateUtil.getSessionFactory().openSession(); // crea la sesion


			// Añadiendo Valores por defecto
			Familia cereales = FamiliaDAO.addFamilia("Cereales", sesion);
			Familia Bebidas = FamiliaDAO.addFamilia("Bebidas", sesion);
			Familia Congelados = FamiliaDAO.addFamilia("Congelados", sesion);
			Familia Frutas = FamiliaDAO.addFamilia("Consumibles", sesion);

			Tienda Mercadona = TiendaDAO.addTienda("Mercadona", "38380", sesion);
			Tienda Hiperdino = TiendaDAO.addTienda("Hiperdino", "38380", sesion);
			Tienda Spar = TiendaDAO.addTienda("Spar", "38380", sesion);
			Tienda Alteza = TiendaDAO.addTienda("Alteza", "38380", sesion);

			Producto manzanas = ProductoDAO.addProducto("Manzanas", 20.00, Frutas, false, sesion);
			ProductoObservacion observacionmanzans = ObservacionDAO.addObservacion(manzanas,
					"Manzanas de muy buena calidad", sesion);
			
			Producto Naranjas = ProductoDAO.addProducto("Naranjas", 20.00, Frutas, false, sesion);
			ProductoObservacion observacionNaranjas = ObservacionDAO.addObservacion(manzanas,
					"naranjas de muy buena calidad", sesion);
			
			
			Producto Chocapicks = ProductoDAO.addProducto("Chocapicks", 20.00, cereales, false, sesion);

			

			Stock primero = StockDAO.addStock(manzanas, Alteza, 1000, sesion);
			Stock segundo = StockDAO.addStock(manzanas, Spar, 300, sesion);
			Stock tercero = StockDAO.addStock(manzanas, Mercadona, 400, sesion);
		
		ProductoDAO.getProductos();

		
		
		
		Application.launch(ProductosApp.class, args);
	}

}
