package PaintingProject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;

public class Save {
    private Canvas canvas;
    Save(Canvas canvas){
        this.canvas = canvas;
    }
    public void SaveImage(String artName, String UserName)throws IOException{
        
        
        
        WritableImage wim = new WritableImage((int)canvas.getWidth(), (int)canvas.getHeight()); 
        canvas.snapshot(null, wim); //takes a snapshot of the canvas

        
        File file = new File("ArtFolder/"+artName + ".png");//names the image
        ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);

AddImagetoName(artName, UserName);
    }
    private void AddImagetoName(String NameofFile,String UserName)throws IOException{
        ArrayList<String> NameList = new ArrayList<>();
        String NextArtName="";
        boolean dupeflag = true;
        String Timeadder="";
        String FileUp;
        Timeadder = "TextFolder/"+UserName+".txt";
       
        Scanner fileIn = new Scanner(new File(Timeadder));//first we read in the contents of the username's art file names
        while(fileIn.hasNext()){
           NextArtName = fileIn.nextLine();//code that actually reads in the file
           NameList.add(NextArtName);//adds all the contents of the names of the user's art files in an arraylist
            if(NextArtName.equals(NameofFile)){
                System.out.println("Dupe detected!");//if the name of the image is already in that user's txt file, this flag will be true
                dupeflag=false;//prevents duping of txt for better organization.
            }
        }
        NameList.add(NextArtName);
        fileIn.close();
        FileUp = NameofFile;
        if(dupeflag){
        for(int i=0;i<NameList.size()-1;i++){
          
            FileUp = FileUp + "\n"+NameList.get(i);//this code will skip the name of the duplicate (essientially, nothing will happen)
        
        }
    }else{
        for(int i=0;i<NameList.size()-1;i++){
          if(i==0){
                FileUp = NameList.get(i);//and this code will happen if there is no duplicate
          }else{
            FileUp = FileUp + "\n"+NameList.get(i);//dupe or no dupe, we are making a string that will be used as the content of the new txt file
          }
        }
    }
        Path path = Paths.get(Timeadder);
       Files.write(path, FileUp.getBytes());//this writes the actual file
    }

    
}
