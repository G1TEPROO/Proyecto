package clases;

public class Boleta {
	private int codigoBoleta;
	private String producto;
    private int cantidad;
    private double precio;
  
    public Boleta() {
	}
    
    public Boleta(String producto, int cantidad, double precio) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    public Boleta(int codigoBoleta, String producto, int cantidad, double precio) {
        this.codigoBoleta = codigoBoleta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getCodigoBoleta() {
        return codigoBoleta;
    }

    public void setCodigoBoleta(int codigoBoleta) {
        this.codigoBoleta = codigoBoleta;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}