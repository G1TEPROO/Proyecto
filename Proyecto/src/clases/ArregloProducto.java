package clases;

import java.util.ArrayList;

public class ArregloProducto {
	
	private ArrayList<Producto> pro;

	public ArregloProducto() {
		pro= new ArrayList<Producto>();
	}
	
	public void Adicionar (String producto, double  precio) {
	   	
		Producto p1 = new Producto(producto, precio);
		Adicionar(p1);
		
	}
	private void Adicionar(Producto p1) {
		pro.add(p1);
	}
	public Producto obtener(int x) {
		return pro.get(x);
		
		
	}
	public int Tamaño() {
		if (pro.size()>0) return pro.size();
		else return 0;
	}

}
