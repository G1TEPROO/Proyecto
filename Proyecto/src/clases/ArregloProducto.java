package clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import clases.Producto;
import conexion.ConexionDB;


public class ArregloProducto {
	private int contadorCodigo = 4;
	public  ArrayList<Producto> listarPro(){
		ArrayList<Producto>lista = new ArrayList<Producto>();
try {
	CallableStatement csta=ConexionDB.getConexión().prepareCall("{call sp_Listar_Producto()}");
	ResultSet rs= csta.executeQuery();
	Producto acce;
	while (rs.next()) {
		acce=new Producto(rs.getInt(1), rs.getString(2),  rs.getDouble(3), rs.getInt(4));
		lista.add(acce);
	}	
}catch(Exception e) {
				}	
		
		return lista;
	
	}
	
	public  ArrayList<Producto> ConsultarPro(String nom){
		ArrayList<Producto>lista = new ArrayList<Producto>();
		try {
			
			java.sql.Statement sta=ConexionDB.getConexión().createStatement();
			ResultSet rs=sta.executeQuery("select * from product_table where codigoProducto like %"+nom+"%");
			Producto Pro;
			while (rs.next()) {
				Pro=new Producto(rs.getInt(1), rs.getString(2),  rs.getDouble(3), rs.getInt(4));
				lista.add(Pro);
			}	
		} catch(Exception e ) {}
		
		
		return lista;
	}
	
	
	public void Agregar(Producto p) {
		try {
			Connection cnx=ConexionDB.getConexión();
			CallableStatement csta=cnx.prepareCall("{CALL sp_Insertar_Producto(?,?,?)}");
			csta.setString(1, p.getProducto());
			csta.setDouble(2,p.getPrecio());
			csta.setInt(3,p.getStock());
			csta.executeUpdate();
		} catch (Exception e) {
			System.out.println("ERROR"+e);
		}
	}


}
	
	
	/*public boolean Eliminar(int codigo) {
		for (Producto p : productos) {
	        if (p.getCodigoProducto() == codigo) {
	            productos.remove(p);
	            return true;
	        }
	    }
		return false;
	}
	public Producto Obtener(int indice) {
		return productos.get(indice);
	}

	public Producto Buscar(int codigo) {
		for (Producto p : productos) {
			if (p.getCodigoProducto() == codigo) {
				return p;
			}
		}
		return null;
	}
	
	
}*/