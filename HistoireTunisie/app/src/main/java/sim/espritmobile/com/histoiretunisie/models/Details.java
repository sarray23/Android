package sim.espritmobile.com.histoiretunisie.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by sarra on 11/23/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Details {
    String description;
    String titre;
    String img;
    String periode;


    public Details() {super();
    }


    public Details(String description, String titre, String img, String periode) {
        this.description = description;
        this.titre = titre;
        this.img = img;
        this.periode = periode;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }
}
