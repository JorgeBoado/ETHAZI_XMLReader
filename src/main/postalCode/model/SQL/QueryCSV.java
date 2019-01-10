package model.SQL;

import model.table.postalCode;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryCSV {
    private static QueryCSV ourInstance = new QueryCSV();
    private static ConexionCSV mConexion = ConexionCSV.getInstance();
    private static Connection mConnection= mConexion.conectar();

    public static QueryCSV getInstance() {
        return ourInstance;
    }

    private QueryCSV() {
    }

    public void insertPostalCodes(postalCode postalCode) {
        try {
            PreparedStatement st = mConnection.prepareStatement(
                    "INSERT INTO `postalCode`(postalcode, territory, municipalitycode, municipality) VALUES (?, ?, ?, ?);");
            int i =1;

            //Insert_PostalCode
            st.setString(i++, postalCode.getPostalCode());

            //Insert_Territory
            st.setString(i++, postalCode.getTerritory());

            //Insert_MunicipalityCode
            st.setString(i++, postalCode.getMunicipalityCode());

            //Insert_municipality
            st.setString(i++, postalCode.getMunicipality());

            st.executeUpdate();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}
