package PaintingProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

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
    private void setExtFilters(FileChooser chooser){//allows filechooser to accept png files
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
    public void UploadImageTime(){
       
        
      
        System.out.println("Found you");
        
        Image image = new Image(file.toURI().toString());//creates an image that is at the path of the file the user picks
      System.out.println("path:"+file.getName());
        System.out.println(image.getHeight());
        File file1 = new File("ArtFolder/"+file.getName());//make a brand new file that will be placed in the ArtFolder instead
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file1);//makes the image at the path that was defined by file1 that has the content of the image object
        } catch (IOException e) {
           
            e.printStackTrace();
        }
           
      GivePermission();

    }
    private void GivePermission(){//first we get the name of the image by chopping off the extensions. Then we check to see if the name of the file is already in the system, then we write permission
        String nameoffile = file.getName();
        nameoffile = nameoffile.replace(".png", "");
        try {
            if(!CheckForDupe(nameoffile)){
                WritePermission(nameoffile);
            }
        } catch (IOException e) {
           
            e.printStackTrace();
        }

    }
    private boolean CheckForDupe(String Name)throws IOException{//checks to see if the 
        boolean dupeflag = false;
        String NextName = "";
        Scanner fileIn = new Scanner(new File("TextFolder/"+user+".txt"));
        while(fileIn.hasNext()){
         NextName = fileIn.nextLine();
         if(Name.equals(NextName)){
             System.out.println("Dupe detected!");
             dupeflag = true;
         }
        }
 fileIn.close();
 
 return dupeflag;
    }
    private void WritePermission(String nameoffile) throws IOException{//adds the name of the file to the user's txtfile to give them permission to open it
        ArrayList<String> ArtList = new ArrayList<>();
        String NextArt= "";
        String PName = "";
        try {
            Scanner fileIn = new Scanner(new File("TextFolder/"+user+".txt"));
            while(fileIn.hasNext()){
                NextArt = fileIn.nextLine();
                ArtList.add(NextArt);
                    }
                    fileIn.close();
                    for(int i=0;i<ArtList.size();i++){
                        if(i==0){
                              PName = nameoffile + "\n"+ ArtList.get(i);//first let's make a setup for the txtfile for username
                        }else{
                          PName +=  "\n"+ArtList.get(i);
                        }
                    }
                    Path path = Paths.get("TextFolder/"+user+".txt");
                        Files.write(path, PName.getBytes());         


        } catch (FileNotFoundException e) {
          
            e.printStackTrace();
        }


    }

    
}
