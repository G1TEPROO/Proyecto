package clases;

import java.util.ArrayList;

public class ArregloProducto {
	private ArrayList<Producto> productos = new ArrayList<>();
	
	public void Agregar(Producto p) {
		productos.add(p);
	}
	public boolean Eliminar(int codigo) {
		for (Producto p : productos) {
	        if (p.getCodigoProducto() == codigo) {
	            productos.remove(p);
	            return true;
	        }
	    }
		return false;
	}
	public Producto Obtener(int codigo) {
		for (Producto p : productos) {
	        if (p.getCodigoProducto() == codigo) {
	            return p;
	        }
		}
	    return null;
	}
	public int Tamano() {
		return productos.size();
	}
}
/*{
new Producto(1, "Pan Frances", 0.20),
new Producto(2, "Pan Yema", 0.40),
new Producto(3, "Pan Ciabatta", 0.30),
new Producto(4, "Paquete de Jamonada", 5.20),
new Producto(5, "Paquete de Queso", 6.50)
};*/