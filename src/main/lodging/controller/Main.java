package controller;

import java.awt.*;

import model.SQL.Conexion;
import view.Window;
/**
 * Clase de Inicio.
 *
 * @author Jorge
 * @version 1.01
 * @since 2018-12-29
 */
public class Main {
    private static Conexion mConexion=Conexion.getInstance();
    /**
     * Lanzador de la aplicacion.
     * Genera la instancia de una ventana.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Window frame = Window.getInstance();
                frame.getFrame().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        mConexion.desconectar();
    }
}
