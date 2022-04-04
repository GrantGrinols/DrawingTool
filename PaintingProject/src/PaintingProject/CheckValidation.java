package PaintingProject;
import java.io.*;
import java.util.*;
import java.io.File;
public class CheckValidation {
private String User;
private String Art;
private boolean Valid = false;
    CheckValidation(String paraUser, String paraArt)throws IOException{
User = paraUser;
Art = paraArt;
LookatValid();
    }
    public boolean getValid(){
        return Valid;
    }
    public void LookatValid() throws IOException{
        String NextArt = "";
        Scanner fileIn = new Scanner(new File("TextFolder/"+User+".txt"));
        while(fileIn.hasNext()){
            NextArt = fileIn.nextLine();
            if(NextArt.equals(Art)){
                Valid = true;
                break;
            }
        }
        fileIn.close();
    }
}
