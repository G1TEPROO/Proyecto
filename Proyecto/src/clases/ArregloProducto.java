package clases;

public class ArregloProducto {
	
	Producto[] productos = {
		    new Producto("Pan Frances", 0.20),
		    new Producto("Pan Yema", 0.40),
		    new Producto("Pan Ciabatta", 0.30),
		    new Producto("Paquete de Jamonada", 5.20),
		    new Producto("Paquete de Queso", 6.50)
		};
	
	public Producto Obtener(int x) {
		return productos[x];
	}
	public int Tamano() {
		return productos.length;
	}
}
