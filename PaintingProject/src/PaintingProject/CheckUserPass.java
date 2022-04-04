package PaintingProject;

import java.util.*;
import java.io.*;
public class CheckUserPass {
    String CheckUsername;
    String CheckPassword;
    boolean Response = true;
    boolean FoundUserName = false;
    boolean FoundPassword = false;
    int index;
    int passindex;
    public CheckUserPass(String User, String Pass)throws IOException{//when you enter a username and password, this class checks to see if there's a match
        CheckUsername = User;//converts to regular Strings
        CheckPassword = Pass;
       FoundUserName =SearchUserName();//checks to see if the username matches
       if(FoundUserName){
          FoundPassword = SearchPassword();//then sees to see if the password matches
          if(FoundPassword){
              Response = true;//if and only if the above two gets set to true, will you get a true response
          }else{
              Response = false;
          }
       }else{
           Response = false;
       }
    }
    public boolean getAnswer(){
        return Response;
    }
private boolean SearchUserName()throws IOException{
    
    String NextUserName ="";
    Scanner fileIn = new Scanner(new File("TextFolder/Username.txt"));//grabs the contents of username.txt. Depending on what this is running on,y ou may want to change the location of the txt
    while(fileIn.hasNext()){
        index++;//gets the position of where you found the username (might be pointless, but doesn't hurt)
NextUserName = fileIn.nextLine();
if(NextUserName.equals(CheckUsername)){
    passindex = index;
    fileIn.close();
    System.out.println("UserName is true");
    return true;
}
    }
    fileIn.close();
    return false;
}
private boolean SearchPassword()throws IOException{
    String NextPassword="";
    Scanner fileIn = new Scanner(new File("TextFolder/Password.txt"));
    while(fileIn.hasNext()){
        NextPassword = fileIn.nextLine();
        if((NextPassword.equals(CheckPassword))&&(passindex==index)){
            fileIn.close();
            System.out.println("password is true");
            return true;
        }
    }
fileIn.close();
return false;
}
}