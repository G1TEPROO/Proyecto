package clases;

public class Empleado {
	private String codigo;
    private String nombre;
    private String apellido;
    private String dni;
    private String cargo;
    private double sueldo;

    public Empleado(String codigo, String nombre, String apellido, String dni, String cargo, double sueldo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.cargo = cargo;
        this.sueldo = sueldo;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public double getSueldo() { return sueldo; }
    public void setSueldo(double sueldo) { this.sueldo = sueldo; }

    public String toString() {
        return codigo + " - " + nombre + " " + apellido + " - DNI: " + dni + " - " + cargo + " - S/ " + sueldo;
    }
}
