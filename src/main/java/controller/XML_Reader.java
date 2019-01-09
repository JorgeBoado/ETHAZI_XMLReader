package controller;

import model.tables.Lodging;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class XML_Reader {

    private ArrayList<Lodging> lodgings;
    private File file;

    public XML_Reader(String file) {
        this.file = new File(file);
        this.lodgings = new ArrayList<>();
    }

    public void readAll() {

        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            doc.getDocumentElement().normalize();

            this.lodgings = new ArrayList<>();

            NodeList nodes = doc.getElementsByTagName("row");
            for (int i = 0; i < nodes.getLength(); i++) {
                Lodging lodging = new Lodging();
                NodeList childrens = nodes.item(i).getChildNodes();

                for (int x = 0; x < childrens.getLength(); x++) {
                    this.insertAlojamiento(lodging, childrens.item(x).getNodeName(), childrens.item(x).getTextContent());
                }
                lodgings.add(lodging);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

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
            case "locality":
                lodging.setLocality(value);
                break;
            case "address":
                lodging.setAddres(value);
                break;
            case "marks":
                lodging.setMarks(value);
                break;
            case "postalcode":
                lodging.setPostalcode(value);
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

    public ArrayList<Lodging> getLodgings() {
        return lodgings;
    }
}
