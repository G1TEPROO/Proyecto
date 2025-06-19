package clases;

public class Producto {
	private int codigoProducto;
	private String producto;
	private double precio;
	
	public Producto(int codigoProducto, String producto, double precio) {
		this.codigoProducto = codigoProducto;
		this.producto = producto;
		this.precio = precio;
	}

	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
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

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
