package arrays;

import java.util.ArrayList;

import clases.Boleta;

public class ArregloBoleta {
	private static int contador = 1;
    private String codigo;
    private ArrayList<Boleta> items;
    private double total;
    
	public ArregloBoleta() {
		super();
		this.codigo = "B" + contador++;
		this.items = new ArrayList<>();
		this.total = 0;
	}

	public String getCodigo() {
		return codigo;
	}

	public ArrayList<Boleta> getItems() {
		return items;
	}

	public double getTotal() {
		return total;
	}
	    
	public void AgregarItem(Boleta item) {
		items.add(item);
		total += item.getPrecio();
	}
}
