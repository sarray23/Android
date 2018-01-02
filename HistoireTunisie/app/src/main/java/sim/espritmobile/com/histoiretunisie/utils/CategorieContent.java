package sim.espritmobile.com.histoiretunisie.utils;



import java.util.ArrayList;
import sim.espritmobile.com.histoiretunisie.R;
import sim.espritmobile.com.histoiretunisie.models.CategorieEntity;

/**
 * Created by sarra on 04/10/2016.
 */

public class CategorieContent {

    public static String[] names={"Musée","Monument","Site archéologique","Personnage historique"};
    public static Integer[] pictures={R.drawable.bardo,R.drawable.photo1,R.drawable.photo3,R.drawable.hannibal};


    public static ArrayList<CategorieEntity> getContent()
    {
     ArrayList<CategorieEntity> categorie=new ArrayList<>();
        for(int i=0;i<=3;i++)
        {
            categorie.add(new CategorieEntity(names[i],pictures[i]));

        }

return categorie;


    }




}
