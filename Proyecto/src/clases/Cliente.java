package clases;
public class Cliente {
	private int codigo;
    private String nombre;
    private int dni;
    private int telefono;

    public Cliente() {}
    
    public Cliente(String nombre, int dni, int telefono) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
    }
    
    public Cliente(int codigo, String nombre, int dni, int telefono) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre + " - " + dni;
    }
  }
