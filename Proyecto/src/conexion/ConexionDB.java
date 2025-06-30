package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {
	public static Connection getConexión() {
		Connection cnx=null;
		try {
			//Adjuntar driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver correcto");
			//conexión BD
			cnx=DriverManager.getConnection
					("jdbc:mysql://localhost:3306/Panaderia","root","root");
			System.out.println("conexión correcta");
		} catch (Exception e) {
			System.out.println("Error: "+e);
		}
		return cnx;
	}
	
	public static void main(String[] args) {
	getConexión();	

	}
}
