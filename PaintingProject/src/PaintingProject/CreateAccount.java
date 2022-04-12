package PaintingProject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CreateAccount {
   private Scene NewAccountScene;
    CreateAccount(){
        try {
            CreateNewAccount();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
    private void CreateNewAccount()throws IOException{
        Label NewUserText = new Label("Enter new name here");
        Label NewPassText = new Label("Enter new password here");
        TextField NewUserField = new TextField();
        TextField NewPassField = new TextField();
        GridPane NewGrid = new GridPane();
        Button MakeAccount = new Button("Create New Account");
        NewGrid.addColumn(0, NewUserText, NewUserField);
        NewGrid.addColumn(1, NewPassText, NewPassField);
        NewGrid.addColumn(2, MakeAccount);
        NewAccountScene = new Scene(NewGrid,700,400);
        MakeAccount.setOnAction(e->{
            try {
                SearchForNull(NewGrid, NewUserField.getText(), NewPassField.getText());
            } catch (IOException e1) {
                
                e1.printStackTrace();
            }
        });
        


    }
    public Scene getScene(){
        return NewAccountScene;
    } 
   private void SearchForNull(GridPane pane, String Name, String Pass)throws IOException{
if(!(Name.equals(""))&&!(Pass.equals(""))){//first checks to see if the entries are null. We do not want null values.
    if(!CheckForDupe(Name)){
       
        MakeAccount(Name, Pass,pane);
    }else{
        Label DupeText = new Label("That username is taken.");
        pane.add(DupeText, 1, 2);
    }
}else{
    Label NoNameDetected = new Label("Username and password can not be null");//this gets printed out if user tries to enter null entries
    pane.add(NoNameDetected, 0, 2);
}
   }
  
private boolean CheckForDupe(String Name)throws IOException{//checks to see if a username with that name is already named
       boolean dupeflag = false;
       String NextName = "";
       Scanner fileIn = new Scanner(new File("TextFolder/Username.txt"));
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
   private void MakeAccount(String name, String pass,GridPane pane) throws IOException{//when a username and password is not null and a username with that name doesn't already exist, this function creates the account
    ArrayList<String> UserList = new ArrayList<>();
    ArrayList<String> PassList = new ArrayList<>();
    String PName="";
    String PPass="";
    String NextString = "";
    String NewString="";
    Label AccountCreated = new Label("Welcome to the painting software, "+ name);
    Scanner fileIn = new Scanner(new File("TextFolder/UserName.txt"));
    while(fileIn.hasNext()){
NextString = fileIn.nextLine();
UserList.add(NextString);
    }
    fileIn.close();
fileIn = new Scanner(new File("TextFolder/Password.txt"));
while(fileIn.hasNext()){
    NextString = fileIn.nextLine();
    PassList.add(NextString);
}
fileIn.close();

for(int i=0;i<UserList.size();i++){
    if(i==0){
          PName = name + "\n"+ UserList.get(i);//first let's make a setup for the txtfile for username
    }else{
      PName +=  "\n"+UserList.get(i);
    }

    


}
for(int i=0;i<PassList.size();i++){
    if(i==0){
          PPass = pass + "\n"+ PassList.get(i);//next, let's make a setup for password
    }else{
      PPass +=  "\n"+PassList.get(i);
    }



}
Path path = Paths.get("TextFolder/Username.txt");
Files.write(path, PName.getBytes());//this writes the actual file for username

Path path1 = Paths.get("TextFolder/Password.txt");
Files.write(path1, PPass.getBytes());//this writes the actual file for password
Path NewUser = Paths.get("TextFolder/"+name+".txt");//this creates the file that lists are the images the user has (which is none)
Files.write(NewUser, NewString.getBytes());
pane.add(AccountCreated, 0, 3);


}
}