package controller;

import model.SQL.ConexionCSV;
import model.table.postalCode;
import model.SQL.QueryCSV;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class MainCSV {
    public static ArrayList<postalCode> postalCodes = new ArrayList<>();
    private static ConexionCSV mConexion=ConexionCSV.getInstance();
    public static void main(String[] args) {
        mConexion.conectar();
        String path;
        JFileChooser j = new JFileChooser();
        j.showOpenDialog(j);
        path = j.getSelectedFile().getAbsolutePath();
        System.out.println("Leyendo fichero de texto...");
        try {
            BufferedReader ficheroEntrada = new BufferedReader(new FileReader(new File(path)));
            String linea;
            while ((linea = ficheroEntrada.readLine()) != null) {
                pasarAObjeto(linea);
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        for(int x=1; x < postalCodes.size();x++){
            QueryCSV.getInstance().insertPostalCodes(postalCodes.get(x));
        }
        mConexion.desconectar();
    }

    public static void pasarAObjeto(String linea) {
        String[] registro;
        postalCode a = new postalCode();

        registro = linea.split(";");

        switch (registro[0]) {
            case "01":
                a.setTerritory("Alava");
                break;
            case "20":
                a.setTerritory("Guipuzcoa");
                break;
            case "48":
                a.setTerritory("Vizcaya");
                break;
        }
        a.setMunicipalityCode(registro[1]);
        a.setPostalCode(registro[2]);
        a.setMunicipality(registro[3]);

        postalCodes.add(a);
    }
}
