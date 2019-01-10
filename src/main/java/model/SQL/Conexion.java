package model.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    // Creacion de la instancia.
    private static Conexion mInstance;

    //Conexion
    private Connection mConexion = null;
    private Properties mPropiedades;

    //Constantes de Inicio de sesion
    /*private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/";*/
    private static final String DATABASE_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mariadb://kasserver.synology.me:3307/";
    private static final String DATABASE_NAME = "reto_gp4";
    private static final String USERNAME = "gp4";
    private static final String PASSWORD = "MmlYOc8DvJXQns7D";
    private static final String MAX_POOL = "250";

    // El constructor privado no permite que se genere un constructor por defecto.
    // (con mismo modificador de acceso que la definicion de la clase)

    private Conexion() {

    }

    public static Conexion getInstance() {
        if (mInstance == null) {
            mInstance = new Conexion();
        }
        return mInstance;
    }

    private Properties getProperties() {
        if (mPropiedades == null) {
            mPropiedades = new Properties();
            mPropiedades.setProperty("user", USERNAME);
            mPropiedades.setProperty("password", PASSWORD);
            mPropiedades.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return mPropiedades;
    }

    //Conexion a la base de datos
    public Connection conectar() {
        if (mConexion == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                mConexion = DriverManager.getConnection(DATABASE_URL + DATABASE_NAME, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("No se ha podido conectar a la base de datos.");
                e.printStackTrace();
            }
        }
        return mConexion;
    }

    //Desconexion de la base de datos
    public void desconectar() {
        if (mConexion != null) {
            try {
                mConexion.close();
                mConexion = null;
            } catch (SQLException e) {
                System.err.println("No se ha podido desconectar de la base de datos.");
                e.printStackTrace();
            }
        }
    }
}
