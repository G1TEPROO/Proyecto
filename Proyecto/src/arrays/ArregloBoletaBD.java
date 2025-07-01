package arrays;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import clases.Boleta;
import conexion.ConexionDB;

public class ArregloBoletaBD {
	/*public int guardarBoleta(ArregloBoleta ab) {
        int idGenerado = -1;
        try (Connection cnx = ConexionDB.getConexión()) {

            String sqlBoleta = "INSERT INTO Boleta (fecha, total) VALUES (NOW(), ?)";
            PreparedStatement psBoleta = cnx.prepareStatement(sqlBoleta, Statement.RETURN_GENERATED_KEYS);
            psBoleta.setDouble(1, ab.getTotal());
            psBoleta.executeUpdate();

            ResultSet rs = psBoleta.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }

            // Insertar detalle
            String sqlDetalle = "INSERT INTO DetalleBoleta (codigoBoleta, producto, cantidad, precio) VALUES (?, ?, ?, ?)";
            PreparedStatement psDetalle = cnx.prepareStatement(sqlDetalle);
            for (Boleta item : ab.getItems()) {
                psDetalle.setInt(1, idGenerado);
                psDetalle.setString(2, item.getProducto());
                psDetalle.setInt(3, item.getCantidad());
                psDetalle.setDouble(4, item.getPrecio());
                psDetalle.addBatch();
            }
            psDetalle.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idGenerado;
    }*/
}
