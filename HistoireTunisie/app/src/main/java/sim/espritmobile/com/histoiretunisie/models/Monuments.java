package sim.espritmobile.com.histoiretunisie.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by sarra on 11/22/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Monuments {

    private double longitude,lattitude;

    private ArrayList<Details> detailsList=new ArrayList<>();
    private String id,nom,description;
    private String Img;


    public Monuments() {super();
    }


    public Monuments(String id, String nom, String description, String img) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        Img = img;
    }


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public ArrayList<Details> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(ArrayList<Details> detailsList) {
        this.detailsList = detailsList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }
}
