package arrays;

import clases.Empleado;
import java.util.ArrayList;

public class ArregloEmpleado {
	private ArrayList<Empleado> empleados;

	public ArregloEmpleado() {
		empleados = new ArrayList<>();
	}

	public void agregar(Empleado e) {
		empleados.add(e);
	}

	public Empleado buscar(String codigo) {
		for (Empleado e : empleados) {
			if (e.getCodigo().equalsIgnoreCase(codigo)) {
				return e;
			}
		}
		return null;
	}

	public boolean eliminar(String codigo) {
		Empleado e = buscar(codigo);
		if (e != null) {
			empleados.remove(e);
			return true;
		}
		return false;
	}

	public int tamaño() {
		return empleados.size();
	}

	public Empleado obtener(int i) {
		return empleados.get(i);
	}
}