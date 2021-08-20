package baseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdministradorDeConexiones {
	
	public static Connection obtenerConexion() throws Exception
	{
	 
	        // Establece el nombre del driver a utilizar
	        String dbDriver = "com.mysql.jdbc.Driver";
	    	
	    	// Establece la url y base de datos a utilizar
	        String dbConnString = "jdbc:mysql://localhost/niif16?serverTimezone=UTC";
	    	
	    	//serverTimezone=UTC&
                //zeroDateTimeBehavior=CONVERT_TO_NULL;connectTimeout=120000;socketTimeout=120000;useSSL=false;allowPublicKeyRetrieval=true
                // Establece el usuario de la base de datos
	        String dbUser = "root";
	       
	        // Establece la contrasena de la base de datos
	        String dbPassword = "tar.nuf.roi-2";
	       
	        // Establece el driver de conexion
	        Class.forName(dbDriver).newInstance();
	       
	        // Retorna la conexion
	        Connection conn =  DriverManager.getConnection(dbConnString, dbUser, dbPassword);
	        return conn;
		}
	 


}
