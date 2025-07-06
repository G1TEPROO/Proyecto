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
	public static int insertarBoleta(double total, int codEmpleado, int codCliente) {
		int nuevoCodigo = -1;
	    try (Connection cn = ConexionDB.getConexión();
	         CallableStatement cs = cn.prepareCall("{ call sp_Insertar_Boleta(?, ?, ?, ?) }")) {

	        cs.setDouble(1, total);
	        cs.setInt(2, codEmpleado);
	        cs.setInt(3, codCliente);
	        cs.registerOutParameter(4, java.sql.Types.INTEGER);
	        cs.execute();

	        nuevoCodigo = cs.getInt(4);

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
        String sql = "CALL sp_Listar_Boletas()";

        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                int codigoBoleta = rs.getInt("codigoBoleta");
                String empleado = rs.getString("empleado");
                String cliente = rs.getString("cliente");
                String fecha = rs.getString("fecha");
                double total = rs.getDouble("total");

                lista.add(new Object[]{codigoBoleta, empleado, cliente, fecha, total});
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
    
    public static ArrayList<Object[]> listarBoletasPorDniEmpleado(String dniEmpleado) {
        ArrayList<Object[]> lista = new ArrayList<>();
        String sql = "CALL sp_Listar_Boletas_PorDNI(?)";

        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, dniEmpleado);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                int codigoBoleta = rs.getInt("codigoBoleta");
                String empleado = rs.getString("empleado");
                String cliente = rs.getString("cliente");
                String fecha = rs.getString("fecha");
                double total = rs.getDouble("total");

                lista.add(new Object[]{codigoBoleta, empleado, cliente, fecha, total});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public static ArrayList<Object[]> listarBoletasPorDniCliente(int dniCliente) {
        ArrayList<Object[]> lista = new ArrayList<>();
        String sql = "CALL sp_Listar_Boletas_PorDNI_Cliente(?)";

        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setInt(1, dniCliente);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                int codigoBoleta = rs.getInt("codigoBoleta");
                String empleado = rs.getString("empleado");
                String cliente = rs.getString("cliente");
                String fecha = rs.getString("fecha");
                double total = rs.getDouble("total");

                lista.add(new Object[]{codigoBoleta, empleado, cliente, fecha, total});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public static Object[] obtenerResumenDiario() {
        Object[] resumen = null;
        String sql = "{ call sp_Resumen_Diario() }";

        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            if (rs.next()) {
                String fecha = rs.getString("fecha");
                int cantidad = rs.getInt("cantidad_boletas");
                double ingresos = rs.getDouble("ingresos");

                resumen = new Object[] { fecha, cantidad, ingresos };
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resumen;
    }
    
    public static ArrayList<Object[]> obtenerResumenMensual() {
        ArrayList<Object[]> lista = new ArrayList<>();
        String sql = "{ call sp_Resumen_Mensual() }";

        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                String fecha = rs.getString("fecha");
                int cantidad = rs.getInt("cantidad_boletas");
                double ingresos = rs.getDouble("ingresos");

                lista.add(new Object[]{fecha, cantidad, ingresos});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public static Object[] obtenerProductoMasVendidoDiario() {
        Object[] resultado = null;
        String sql = "{ call sp_Producto_Mas_Vendido_Diario() }";

        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            if (rs.next()) {
                String nombre = rs.getString("nombreProducto");
                int cantidad = rs.getInt("total_vendido");
                resultado = new Object[] { nombre, cantidad };
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public static Object[] obtenerProductoMasVendidoMensual() {
        Object[] resultado = null;
        String sql = "{ call sp_Producto_Mas_Vendido_Mensual() }";

        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            if (rs.next()) {
                String nombre = rs.getString("nombreProducto");
                int cantidad = rs.getInt("total_vendido");
                resultado = new Object[] { nombre, cantidad };
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }
    
    public static Object[] obtenerProductoMenosVendidoDiario() {
        Object[] resultado = null;
        String sql = "{ call sp_Producto_Menos_Vendido_Diario() }";

        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            if (rs.next()) {
                String nombre = rs.getString("nombreProducto");
                int cantidad = rs.getInt("total_vendido");
                resultado = new Object[] { nombre, cantidad };
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public static Object[] obtenerProductoMenosVendidoMensual() {
        Object[] resultado = null;
        String sql = "{ call sp_Producto_Menos_Vendido_Mensual() }";

        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            if (rs.next()) {
                String nombre = rs.getString("nombreProducto");
                int cantidad = rs.getInt("total_vendido");
                resultado = new Object[] { nombre, cantidad };
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }
    public static Object[] obtenerClienteMasGastoDiario() {
        Object[] resultado = null;
        String sql = "{ call sp_Cliente_Mas_Gasto_Diario() }";
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                resultado = new Object[] {
                    rs.getString("nombre"),
                    rs.getDouble("total_gastado")
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public static Object[] obtenerClienteMasGastoMensual() {
        Object[] resultado = null;
        String sql = "{ call sp_Cliente_Mas_Gasto_Mensual() }";
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                resultado = new Object[] {
                    rs.getString("nombre"),
                    rs.getDouble("total_gastado")
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public static Object[] obtenerMejorEmpleadoDiario() {
        Object[] resultado = null;
        String sql = "{ call sp_Mejor_Empleado_Diario() }";
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                resultado = new Object[] {
                    rs.getString("empleado"),
                    rs.getInt("cantidad_boletas")
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public static Object[] obtenerMejorEmpleadoMensual() {
        Object[] resultado = null;
        String sql = "{ call sp_Mejor_Empleado_Mensual() }";
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                resultado = new Object[] {
                    rs.getString("empleado"),
                    rs.getInt("cantidad_boletas")
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
