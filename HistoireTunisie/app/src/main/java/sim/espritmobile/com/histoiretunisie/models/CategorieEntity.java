package sim.espritmobile.com.histoiretunisie.models;

/**
 * Created by sarra on 08/11/2016.
 */

public class CategorieEntity {
private String titre;
    private int img;

    public CategorieEntity(String titre, int img) {
        super();
        this.titre = titre;
        this.img = img;
    }
    public CategorieEntity() {
        super();

    }

    public String getTitre() {
        return titre;
    }

    public int getImg() {
        return img;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
