package clases;

public class Producto {
	private String producto;
	private double precio;
	
	public Producto(String producto, double precio) {
		this.producto = producto;
		this.precio = precio;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecioTotal(double precio) {
		this.precio = precio;
	}
}
