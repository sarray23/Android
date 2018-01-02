package sim.espritmobile.com.histoiretunisie.Experience;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class ItemObject {

    private String name,id,image;
    private int photo;

    public ItemObject(){super();}




    public ItemObject(String id,String name,String  image) {

        this.id=id;
        this.name = name;
       image=image;
    }
    public ItemObject(String id,String name) {

        super();
        this.id=id;
        this.name = name;

    }



    public String getName() {
        return name;
    }
    public String getId(){return id;}
    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
    public String getImage(){return image;}

    public void setImage(String image){this.image=image;}
    public void  setId(String id){this.id=id;}
}
