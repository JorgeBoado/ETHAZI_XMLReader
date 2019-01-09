package controller;

import java.awt.*;

import model.SQL.Conexion;
import view.Window;

public class Main {
    private static Conexion mConexion=Conexion.getInstance();
    //TODO Comentar con JavaDoc
    /**
     * Launch the application.
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
