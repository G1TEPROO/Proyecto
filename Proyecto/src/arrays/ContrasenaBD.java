package arrays;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import conexion.ConexionDB;

public class ContrasenaBD {
	public static String obtenerClave() {
		String clave = "pan123";
        String sql = "CALL sp_Obtener_Clave()";

        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            if (rs.next()) {
                clave = rs.getString("clave");
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clave;
    }

    public static boolean actualizarClave(String nuevaClave) {
        String sql = "CALL sp_Actualizar_Clave(?)";

        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, nuevaClave);
            cs.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
