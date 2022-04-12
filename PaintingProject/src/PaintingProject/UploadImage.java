package PaintingProject;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UploadImage {
    FileChooser Chooser;
    Stage stage;
    String user;
    File file;
    public UploadImage(FileChooser Chooser, Stage stage, String user){
        this.Chooser = Chooser;
        this.stage = stage;
        setExtFilters(Chooser);
        this.user = user;
        file = Chooser.showOpenDialog(stage);


    }
    private void setExtFilters(FileChooser chooser){
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
    public void UploadImageTime(){
       
        
      
        System.out.println("Found you");
        
        Image image = new Image(file.toURI().toString());
      System.out.println("path:"+file.getName());
        System.out.println(image.getHeight());
        File file1 = new File("ArtFolder/"+file.getName());//names the image
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file1);
        } catch (IOException e) {
           
            e.printStackTrace();
        }
           
      GivePermission();

    }
    private void GivePermission(){

    }
}
