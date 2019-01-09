package model.tables;


import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;

public class Lodging {
    private String signatura = "";
    private String name = "";
    private String description = "";
    private String type = "";
    private String phone = "";
    private String locality = "";
    private String addres = "";
    private String marks = "";
    private String postalcode = "";
    private String coordinates = "";
    private String category = "";
    private String turismemail = "";
    private String web = "";
    private int capacity;
    private String friendlyurl = "";
    private String physicalurl = "";
    private String zipfile = "";

    public Lodging() {
    }

    public Lodging( String signatura, String name, String description, String type, String phone, String locality, String addres, String marks, String postalcode, String coordinates, String category, String turismemail, String web, int capacity, String friendlyurl, String physicalurl, String zipfile) {
        this.signatura = signatura;
        this.name = name;
        this.description = description;
        this.type = type;
        this.phone = phone;
        this.locality = locality;
        this.addres = addres;
        this.marks = marks;
        this.postalcode = postalcode;
        this.coordinates = coordinates;
        this.category = category;
        this.turismemail = turismemail;
        this.web = web;
        this.capacity = capacity;
        this.friendlyurl = friendlyurl;
        this.physicalurl = physicalurl;
        this.zipfile = zipfile;
    }

    public String getSignatura() {
        return signatura;
    }

    public void setSignatura(String signatura) {
        this.signatura = signatura;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = descCleaner(description);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTurismemail() {
        return turismemail;
    }

    public void setTurismemail(String turismemail) {
        this.turismemail = turismemail;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getFriendlyurl() {
        return friendlyurl;
    }

    public void setFriendlyurl(String friendlyurl) {
        this.friendlyurl = friendlyurl;
    }

    public String getPhysicalurl() {
        return physicalurl;
    }

    public void setPhysicalurl(String physicalurl) {
        this.physicalurl = physicalurl;
    }

    public String getZipfile() {
        return zipfile;
    }

    public void setZipfile(String zipfile) {
        this.zipfile = zipfile;
    }

    public static String descCleaner(String text){
        return Jsoup.parse(StringEscapeUtils.unescapeHtml4(text)).text();
    }
}