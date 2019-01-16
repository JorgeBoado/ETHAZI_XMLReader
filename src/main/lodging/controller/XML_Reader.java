package controller;

import model.tables.Lodging;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Esta clase es la encargada de leer el XML y generar y llenar los Objeros de alojamientos.
 *
 * @author Jorge
 * @version 1.2
 * @since 2019-01-05
 */

public class XML_Reader {

    private ArrayList<Lodging> lodgings;
    private File file;

    /**
     * Asocia la variable file con la ruta del aarchivo que recibe y genera un numero ArrayList
     * de alojamientos.
     *
     * @param file Ruta del archivo que se va a querer leer.
     */

    public XML_Reader(String file) {
        this.file = new File(file);
        this.lodgings = new ArrayList<>();
    }

    /**
     * Lee el  XML y lo separa en nodos.
     * Llena el ArrayList con los alojamientos.
     * Los alojamientos que no tengan los datos correctos se omitiran.
     */
    public void readAll() {

        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            doc.getDocumentElement().normalize();

            lodgings = new ArrayList<>();

            NodeList nodes = doc.getElementsByTagName("row");
            for (int i = 0; i < nodes.getLength(); i++) {
                Lodging lodging = new Lodging();
                NodeList childrens = nodes.item(i).getChildNodes();

                for (int x = 0; x < childrens.getLength(); x++) {
                    insertAlojamiento(lodging, childrens.item(x).getNodeName(), childrens.item(x).getTextContent());
                }
                if (!lodging.getFriendlyurl().startsWith("E")) {
                    lodgings.add(lodging);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Localiza las etiquetas en la String que recibe y segun la etiqueta que encuentre va asignando
     * los atributos al objeto de apartamentos que se ha mandado.
     *
     * @param lodging  El objeto de apartamento mandado.
     * @param nodeName Es la String que recibe para identificar que atributo es.
     * @param value    String con el contenido del atributo del objeto.
     */
    private void insertAlojamiento(Lodging lodging, String nodeName, String value) {

        switch (nodeName) {
            case "signatura":
                lodging.setSignatura(value);
                break;
            case "documentname":
                lodging.setName(value);
                break;
            case "turismdescription":
                lodging.setDescription(value);
                break;
            case "lodgingtype":
                lodging.setType(value);
                break;
            case "phone":
                lodging.setPhone(value.replaceAll("\\s+", ""));
                break;
            case "address":
                lodging.setAddress(value);
                break;
            case "marks":
                lodging.setMarks(value);
                break;
            case "postalcode":
                lodging.setPostalcode(value);
                break;
            case "municipalitycode":
                lodging.setMunicipalitycode(value);
                break;
            case "latitudelongitude":
                lodging.setCoordinates(value);
                break;
            case "category":
                lodging.setCategory(value);
                break;
            case "tourismemail":
                lodging.setTurismemail(value);
                break;
            case "web":
                lodging.setWeb(value);
                break;
            case "capacity":
                lodging.setCapacity(Integer.valueOf(value));
                break;
            case "friendlyurl":
                lodging.setFriendlyurl(value);
                break;
            case "physicalurl":
                lodging.setPhysicalurl(value);
                break;
            case "zipfile":
                lodging.setZipfile(value);
                break;
        }
    }

    /**
     * Devuelve un ArrayList.
     *
     * @return Devuelve el ArrayList de apartamentos.
     */
    public ArrayList<Lodging> getLodgings() {
        return lodgings;
    }
}
