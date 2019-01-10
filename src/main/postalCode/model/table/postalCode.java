package model.table;

public class postalCode {
    private String postalCode;
    private String territory;
    private String municipalityCode;
    private String municipality;

    public postalCode() {

    }

    public postalCode(String postalCode, String territory, String municipalityCode, String municipality) {
        this.postalCode = postalCode;
        this.territory = territory;
        this.municipalityCode = municipalityCode;
        this.municipality = municipality;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getMunicipalityCode() {
        return municipalityCode;
    }

    public void setMunicipalityCode(String municipalityCode) {
        this.municipalityCode = municipalityCode;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    @Override
    public String toString() {
        return "postalCode{" +
                "postalCode='" + postalCode + '\'' +
                ", territory='" + territory + '\'' +
                ", municipalityCode='" + municipalityCode + '\'' +
                ", municipality='" + municipality + '\'' +
                '}';
    }
}
