package arrays;
import clases.Cliente;
import conexion.ConexionDB;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ArregloClienteBD {

    public ArrayList<Cliente> listar() {
    	ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "CALL sp_Listar_Cliente()";
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente(
                    rs.getInt("codigoCliente"),
                    rs.getString("nombre"),
                    rs.getInt("dni"),
                    rs.getInt("telefono")
                );
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public boolean insertar(Cliente c) {
       
    	
    	String sql = "CALL sp_Insertar_Cliente(?, ?, ?)";
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, c.getNombre());
            cs.setInt(2, c.getDni());
            cs.setInt(3, c.getTelefono());
            cs.execute();
            return true;
        } catch (Exception e) {
           

    	}
            return false;
            
        
    }
    
    
    public boolean Verificar_dni(int dni) {
    	
    	    String sql = "SELECT * FROM Cliente WHERE dni = ?";
    	    try (Connection cn = ConexionDB.getConexión();
    	         PreparedStatement ps = cn.prepareStatement(sql)) {
    	        
    	        ps.setInt(1, dni);
    	        ResultSet rs = ps.executeQuery();
    	        
    	        return rs.next(); // Devuelve true si se encontró al menos un resultado
    	        
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        return false;
    	    }
    	
    }
    public boolean Verificar_telefono(String telefono) {
    	
	    String sql = "SELECT * FROM Cliente WHERE telefono = ?";
	    try (Connection cn = ConexionDB.getConexión();
	         PreparedStatement ps = cn.prepareStatement(sql)) {
	        
	        ps.setString(1, telefono);
	        ResultSet rs = ps.executeQuery();
	        
	        return rs.next(); // Devuelve true si se encontró al menos un resultado
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	
}
    
    
    
    
    
    public boolean editar(Cliente c) {
        String sql = "CALL sp_Editar_Cliente(?, ?, ?, ?)";
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql)) {
            cs.setInt(1,(c.getDni()));
            cs.setString(2, c.getNombre());
            cs.setInt(3, c.getDni());
            cs.setInt(4, c.getTelefono());
            cs.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String dni) {
        String sql = "CALL sp_Eliminar_Cliente(?)";
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, dni);
            cs.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cliente buscar(String dni) {
    	String sql = "CALL sp_ConsultarCod_Cliente(?)";
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, dni);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("codigoCliente"),
                    rs.getString("nombre"),
                    rs.getInt("dni"),
                    rs.getInt("telefono")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean eliminar(int dni) {
        String sql = "CALL sp_Eliminar_Cliente(?)";
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setInt(1, dni);
            cs.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean editarPorCodigo(Cliente c) {
        String sql = "CALL sp_Editar_Cliente_PorCodigo(?, ?, ?, ?)";
        try (Connection cn = ConexionDB.getConexión();
             CallableStatement cs = cn.prepareCall(sql)) {
            cs.setInt(1, c.getCodigo());
            cs.setString(2, c.getNombre());
            cs.setInt(3, c.getDni());
            cs.setInt(4, c.getTelefono());
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
