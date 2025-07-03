package arrays;

import java.util.ArrayList;

import clases.Boleta;

public class ArregloBoleta {
	private ArrayList<Boleta> items;
    private double total;

    public ArregloBoleta() {
        this.items = new ArrayList<>();
        this.total = 0;
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

    public void Limpiar() {
        items.clear();
        total = 0;
    }
}
