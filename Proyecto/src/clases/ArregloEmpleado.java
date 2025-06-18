package clases;

import java.util.ArrayList;

public class ArregloEmpleado {
    private ArrayList<Empleado> lista;

    public ArregloEmpleado() {
        lista = new ArrayList<>();
        
    }

    public void agregar(Empleado e) {
        lista.add(e);
    }

    public Empleado buscar(String codigo) {
        for (Empleado e : lista) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                return e;
            }
        }
        return null;
    }

    public boolean eliminar(String codigo) {
        Empleado e = buscar(codigo);
        if (e != null) {
            lista.remove(e);
            return true;
        }
        return false;
    }

    public int tamaño() {
        return lista.size();
    }

    public Empleado obtener(int i) {
        return lista.get(i);
    }

    public ArrayList<Empleado> listar() {
        return lista;
    }
}
