package sim.espritmobile.com.histoiretunisie.Experience;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by sarra on 25/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class Experience {

   private String name,id,image,description;


    Experience(){super();}

    Experience(String id,String name,String Image,String Description)
    {this.id=id;
        this.name=name;
        image=Image;
        description=Description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String Image) {image = Image;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
