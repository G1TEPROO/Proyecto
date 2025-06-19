package clases;

import java.util.ArrayList;

public class ArregloProducto {
	private ArrayList<Producto> productos = new ArrayList<>();
	private int contadorCodigo = 1;
	
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
	public Producto Obtener(int indice) {
		return productos.get(indice);
	}

	public Producto Buscar(int codigo) {
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
	public int ActualizarCodigo() {
		return contadorCodigo++;
	}
}