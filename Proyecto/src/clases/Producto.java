package clases;

public class Producto {
	private int codigoProducto;
	private String producto;
	private double precio;
	private int stock;
	
	public Producto(int codigoProducto, String producto, double precio,  int stock) {
		this.codigoProducto = codigoProducto;
		this.producto = producto;
		this.precio = precio;
		this.stock = stock;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}
