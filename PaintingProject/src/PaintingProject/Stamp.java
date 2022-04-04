
package PaintingProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.io.IOException;
import javafx.scene.image.Image;


/**
 *
 * @author Jerrod
 */
public class Stamp {
    
    String stampName;
    Image stampImage;
    
    public Stamp(String goku) {
        stampName = goku;
        try {
            stampImage = new Image(new FileInputStream("ArtFolder/" + stampName + ".png"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Stamp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
//    public String getStampName(ChoiceBox<String> goku){
//        stampName = goku.getValue();
//        return stampName;
//    }
//    
    public Image getPng(){
        return stampImage;
    }
}
