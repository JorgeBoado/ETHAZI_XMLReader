package model.SQL;

import model.tables.Lodging;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Query {
    private static Query ourInstance = new Query();
    private static Conexion mConexion = Conexion.getInstance();
    private static Connection mConnection = mConexion.conectar();
    private int errorNumber;

    public static Query getInstance() {
        return ourInstance;
    }

    private Query() {
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public void insertLodging(Lodging lodging) {


        try {
            PreparedStatement st = mConnection.prepareStatement(
                    "INSERT INTO `lodging` " +
                            "(signatura, `name`, description, `type`, phone, locality, address, marks, postalcode, municipalitycode, `coordinates`," +
                            " category, turismemail, web, capacity, friendlyurl, physicalurl, zipfile) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE " +
                            "signatura = ?, `name` = ?, description = ?, `type` = ?, phone = ?, locality = ?, address = ?, marks = ?," +
                            " postalcode = ?, municipalitycode = ?, `coordinates` = ?, category = ?, turismemail = ?, web = ?, capacity = ?, friendlyurl = ?, physicalurl = ?, zipfile = ?");

            int i = 1;
            int x = i + 18;

            //Insert_Signatura
            st.setString(i++, lodging.getSignatura());
            //Update_Signatura
            st.setString(x++, lodging.getSignatura());

            //Insert_Name
            st.setString(i++, lodging.getName());
            //Update_Name
            st.setString(x++, lodging.getName());

            //Insert_Description
            st.setString(i++, lodging.getDescription());
            //Update_Description
            st.setString(x++, lodging.getDescription());

            //Insert_Type
            st.setString(i++, lodging.getType());
            //Update_Type
            st.setString(x++, lodging.getType());

            //Insert_Phone
            st.setString(i++, lodging.getPhone());
            //Update_Phone
            st.setString(x++, lodging.getPhone());

            //Insert_Locality
            st.setString(i++, lodging.getLocality());
            //Update_Locality
            st.setString(x++, lodging.getLocality());

            //Insert_Address
            st.setString(i++, lodging.getAddres());
            //Update_Address
            st.setString(x++, lodging.getAddres());

            //Insert_Marks
            st.setString(i++, lodging.getMarks());
            //Update_Marks
            st.setString(x++, lodging.getMarks());

            //Insert_PostalCode
            st.setString(i++, lodging.getPostalcode());
            //Update_PostalCode
            st.setString(x++, lodging.getPostalcode());

            //Insert_MunicipalityCode
            st.setString(i++, lodging.getMunicipalitycode());
            //Update_MunicipalityCode
            st.setString(x++, lodging.getMunicipalitycode());

            //Insert_Coordinates
            st.setString(i++, lodging.getCoordinates());
            //Update_Coordinates
            st.setString(x++, lodging.getCoordinates());

            //Insert_Category
            st.setString(i++, lodging.getCategory());
            //Update_Category
            st.setString(x++, lodging.getCategory());

            //Insert_TurismEmail
            st.setString(i++, lodging.getTurismemail());
            //Update_TurismEmail
            st.setString(x++, lodging.getTurismemail());

            //Insert_Web
            st.setString(i++, lodging.getWeb());
            //Update_Web
            st.setString(x++, lodging.getWeb());

            //Insert_Capacity
            st.setInt(i++, lodging.getCapacity());
            //Update_Capacity
            st.setInt(x++, lodging.getCapacity());

            //Insert_FriendlyURL
            st.setString(i++, lodging.getFriendlyurl());
            //Update_FriendlyURL
            st.setString(x++, lodging.getFriendlyurl());

            //Insert_PhysicalURL
            st.setString(i++, lodging.getPhysicalurl());
            //Update_PhysicalURL
            st.setString(x++, lodging.getPhysicalurl());

            //Insert_ZipFile
            st.setString(i, lodging.getZipfile());
            //Update_ZipFile
            st.setString(x, lodging.getZipfile());

            st.execute();

        } catch (SQLException e) {
            errorNumber += 1;
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Error: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);

        }
    }
}

