package arrays;

import clases.Empleado;
import conexion.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArregloEmpleadoBD {

    public List<Empleado> listar() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "CALL sp_Listar_Empleado()";
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                Empleado emp = new Empleado(
                    String.valueOf(rs.getInt("codigoEmpleado")),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("dni"),
                    rs.getString("cargo"),
                    rs.getDouble("sueldo")
                );
                lista.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean insertar(Empleado emp) {
        String sql = "CALL sp_Insertar_Empleado(?, ?, ?, ?, ?)";
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, emp.getNombre());
            cs.setString(2, emp.getApellido());
            cs.setString(3, emp.getDni());
            cs.setString(4, emp.getCargo());
            cs.setDouble(5, emp.getSueldo());

            cs.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Empleado emp) {
        String sql = "CALL sp_Editar_Empleado(?, ?, ?, ?, ?, ?)";
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setInt(1, Integer.parseInt(emp.getCodigo()));
            cs.setString(2, emp.getNombre());
            cs.setString(3, emp.getApellido());
            cs.setString(4, emp.getDni());
            cs.setString(5, emp.getCargo());
            cs.setDouble(6, emp.getSueldo());

            cs.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int codigo) {
        String sql = "CALL sp_Eliminar_Empleado(?)";
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setInt(1, codigo);
            cs.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Empleado buscar(int codigo) {
        String sql = "CALL sp_ConsultarCod_Empleado(?)";
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setInt(1, codigo);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                return new Empleado(
                    String.valueOf(rs.getInt("codigoEmpleado")),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("dni"),
                    rs.getString("cargo"),
                    rs.getDouble("sueldo")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

