package arrays;

import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import clases.Boleta;
import conexion.ConexionDB;

public class ArregloBoletaBD {
	public static int insertarBoleta(double total, int codEmpleado) {
        int nuevoCodigo = -1;
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall("{ call sp_Insertar_Boleta(?, ?, ?) }")) {

            cs.setDouble(1, total);
            cs.setInt(2, codEmpleado);
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.execute();

            nuevoCodigo = cs.getInt(3);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nuevoCodigo;
    }

    public static boolean insertarDetalle(int codigoBoleta, Boleta item) {
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall("{ call sp_Insertar_DetalleBoleta(?, ?, ?, ?) }")) {

            cs.setInt(1, codigoBoleta);
            cs.setString(2, item.getProducto());
            cs.setInt(3, item.getCantidad());
            cs.setDouble(4, item.getPrecio());

            cs.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static ArrayList<Object[]> listarBoletasConEmpleado() {
        ArrayList<Object[]> lista = new ArrayList<>();
        try (Connection con = ConexionDB.getConexión();
             PreparedStatement ps = con.prepareStatement(
                 "SELECT b.codigoBoleta, e.dni, b.fecha, b.total " +
                 "FROM Boleta b JOIN Empleado e ON b.codigoEmpleado = e.codigoEmpleado"
             );
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("codigoBoleta"),
                    rs.getString("dni"),
                    rs.getTimestamp("fecha"),
                    rs.getDouble("total")
                };
                lista.add(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
  
    public static ArrayList<Boleta> obtenerDetallesBoleta(int codigoBoleta) {
        ArrayList<Boleta> lista = new ArrayList<>();
        try (Connection con = ConexionDB.getConexión();
             PreparedStatement ps = con.prepareStatement(
                 "SELECT nombreProducto, cantidad, precio FROM DetalleBoleta WHERE codigoBoleta = ?"
             )) {
            ps.setInt(1, codigoBoleta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Boleta(
                    rs.getString("nombreProducto"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
