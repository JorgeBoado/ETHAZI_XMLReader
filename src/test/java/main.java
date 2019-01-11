public class main {
    public static void main(String[] args) {
        Conexion mConexion=Conexion.getInstance();
        mConexion.conectar();
        mConexion.desconectar();
    }
}
