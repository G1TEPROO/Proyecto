package arrays;

import java.util.ArrayList;

import clases.Empleado;

public class ArregloEmpleado {
    private ArrayList<Empleado> lista;

    public ArregloEmpleado() {
        lista = new ArrayList<>();
    }

    public void agregar(Empleado emp) {
        lista.add(emp);
    }

    public boolean eliminar(String codigo) {
        for (Empleado emp : lista) {
            if (emp.getCodigo().equalsIgnoreCase(codigo)) {
                lista.remove(emp);
                return true;
            }
        }
        return false;
    }

    public Empleado buscar(String codigo) {
        for (Empleado emp : lista) {
            if (emp.getCodigo().equalsIgnoreCase(codigo)) {
                return emp;
            }
        }
        return null;
    }

    public int tamaño() {
        return lista.size();
    }

    public Empleado obtener(int index) {
        if (index >= 0 && index < lista.size()) {
            return lista.get(index);
        }
        return null;
    }
}
