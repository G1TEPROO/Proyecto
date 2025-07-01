package Arrays;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public  Producto ConsultarPro(String nom){
		   Producto producto = null;
		    try {
		        Connection cnx = ConexionDB.getConexión();
		        String sql = "SELECT * FROM product_table WHERE producto LIKE ?";
		        PreparedStatement pst = cnx.prepareStatement(sql);
		        pst.setString(1, "%" + nom + "%"); // búsqueda parcial

		        ResultSet rs = pst.executeQuery();
		        if (rs.next()) {
		            producto = new Producto(
		                rs.getInt(1),
		                rs.getString(2),
		                rs.getDouble(3),
		                rs.getInt(4)
		            );
		        }
		    } catch (Exception e) {
		        e.printStackTrace(); // No dejes catch vacío
		    }
		    return producto;
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

	public void eliminar (int cod) {
		try {
		Connection cnx=ConexionDB.getConexión();
		CallableStatement csta=cnx.prepareCall("{call sp_Eliminar_Producto(?)}");
			csta.setInt(1,cod);
			csta.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR"+e);
		}
		
	
		}
	
	public Producto buscar(int cod) {
	    Producto pro = null;

	    try {
	        String sql = "SELECT * FROM product_table WHERE codigoProducto = ?";
	        PreparedStatement ps = ConexionDB.getConexión().prepareStatement(sql);
	        ps.setInt(1, cod);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            pro = new Producto(
	                rs.getInt(1),
	                rs.getString(2),
	                rs.getDouble(3),
	                rs.getInt(4)
	            );
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return pro; // Si no se encuentra, retorna null
	}
	
	
	public void editar (Producto p) {
		try {
			Connection cnx=ConexionDB.getConexión();
			CallableStatement csta=cnx.prepareCall("{call sp_Editar(?,?,?,?)}");
			csta.setInt(1,p.getCodigoProducto());
			csta.setString(2, p.getProducto());
			csta.setDouble(3,p.getPrecio());
			csta.setInt(4,p.getStock());
				csta.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("ERROR"+e);
			}
			
		
		
		
	}
	public ArrayList<Producto> ConsultarCod(int cod){
		ArrayList<Producto> lista=new ArrayList<Producto>();
		try {
			Connection cnx=ConexionDB.getConexión();
			CallableStatement csta=cnx.prepareCall("{call sp_ConsultarCod_Producto(?)}");
			csta.setInt(1, cod);
			ResultSet rs=csta.executeQuery();
			Producto acce;
			while (rs.next()) {
				acce=new Producto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
				lista.add(acce);
			}
		} catch (Exception e) {
			
		}
		return lista;
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
	public  Producto buscar(int  cod){

		Producto Pro;
		try {
			
			java.sql.Statement sta=ConexionDB.getConexión().createStatement();
			ResultSet rs=sta.executeQuery("select * from product_table where codigoProducto like %"+cod+"%");
		
			while (rs.next()) {
			Pro=new Producto(rs.getInt(1), rs.getString(2),  rs.getDouble(3), rs.getInt(4));
				if (Pro.getCodigoProducto()==cod) {
					return Pro;
					
					
				}
			}	
		} catch(Exception e ) {}
		
		
		return null;
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