/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaintingProject;





import javafx.application.Application;

import java.io.IOException;
import javafx.scene.Scene;

import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;



/**
 *
 * @author Grant
 */
public class PaintingProject  extends Application {//this page should be the first thing the user sees. Planning to have some sort of login first, but createcanvas will be first for now
   CreateCanvas newCanvas;
   Button MakeCanvaswithArt = new Button("Make Canvas with art");
   Button LoginAttempt = new Button("Log in");
   TextField enteredusername = new TextField();
    TextField enteredpassword = new TextField();
    TextField enteredArt = new TextField();
    List CreateList;
    
    @Override
    public void start(Stage primaryStage) throws IOException{
        try {
            Stage CanvasStage = new Stage();
           Stage ListStage = new Stage();
           
            TextField Textheight = new TextField();
            TextField TextWidth = new TextField();
            Label HeightDescribe = new Label("Please enter width");
            Label WidthDescribe = new Label("Please enter height");//from 38 to 76 it's a whole lot of visuals and giving it life.
            
           
            Button B = new Button("Make Canvas");
            GridPane root = new GridPane();//hint: you can add buttons to represent pics
            StackPane pane = new StackPane();
            root.addColumn(0, HeightDescribe,Textheight);
            root.addColumn(1, WidthDescribe, TextWidth);
            root.addColumn(2, B);
              Group StartingGroup = new Group(pane);
            Scene StartingScene = new Scene(StartingGroup,600,200);
         
               pane.getChildren().add(root);
           
            primaryStage.setScene(StartingScene);
            primaryStage.setTitle("Time to draw");
            primaryStage.show();
           

          enteredusername = new TextField();
          enteredpassword = new TextField();
          
          Label Username = new Label("Username");
          Label Password = new Label("Password");
          LoginAttempt = new Button("Log in");
         
          
         
          root.add(Username,0,2);
          root.add(enteredusername,0,3);
          root.add(Password,1,2);
          root.add(enteredpassword,1,3);
          root.add(LoginAttempt,2,3);
            
          
            LoginAttempt.setOnAction(e->{//when you click this button, logincheck to see if you've entered correctly
            boolean loginattempt = false;
             try {
             
              loginattempt =LoginCheck(enteredusername,enteredpassword);
              
            } catch (IOException e1) {
             
              e1.printStackTrace();
            }
            if(loginattempt){
              try {
                CreateList(enteredusername, MakeCanvaswithArt, root, enteredArt);
              } catch (IOException e1) {
                
                e1.printStackTrace();
              }
             ListStage.setScene(CreateList.getScene());
             ListStage.setTitle("Here is your result");
             ListStage.show();
            }
            }
            );

            B.setOnAction(e->{
            try {
              SetupCanvas(Textheight,TextWidth);
            } catch (IOException e1) {
              
              e1.printStackTrace();
            }
            
            //when you click this button, this sets up the actual canvas based on what you wrote in
            CanvasStage.setScene(newCanvas.getScene());
            CanvasStage.setTitle("A new Canvas!");
            CanvasStage.show();
            }
            );
           
            MakeCanvaswithArt.setOnAction(e->{
              try {
                if(Validate(enteredusername,enteredArt)){
                SetupCanvaswithArt(enteredArt);
                System.out.println("Uploading Art");
                CanvasStage.setScene(newCanvas.getScene());
                CanvasStage.setTitle("Canvas with Art");
                CanvasStage.show();

                }
                
              } catch (IOException e1) {
             
                e1.printStackTrace();
              }
              
              //when you click this button, whatever art you entered in enteredArt will be passed to SetUpCanvas
           
            }
            );
            
              
   
 
        }catch(Exception e){
            e.printStackTrace();
        }
        
       
        
  
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    public void SetupCanvas(TextField fieldwidth, TextField fieldheight) throws IOException{//makes a blank canvas, no artID will be used here
       
       TexttoDouble Translation = new TexttoDouble(fieldwidth, fieldheight);
       newCanvas = new CreateCanvas(Translation.getwidth(),Translation.getheight(),"Nothing","NoName");
       
    }
   
    public boolean LoginCheck(TextField user, TextField pass) throws IOException{

CheckUserPass Checking = new CheckUserPass(user.getText(),pass.getText());//Checking automatically checks if the password/username matches with those in the txt files

return Checking.getAnswer();//checking.getAnswer is the automatic result of the above
    }
    public void CreateList(TextField user, Button UploadTime,GridPane Grid, TextField EnterArt)throws IOException{
      
        CreateList = new List(user.getText(), UploadTime, enteredArt);//each time createlist is triggered, a NEW CreateList is made.
      }
      
  
    
public void SetupCanvaswithArt(TextField ArtID)throws IOException{//width and height parameters are irrelevent here. If ARTID is not nothing, that ART's width and height will be used instead
   newCanvas  = new CreateCanvas(100,500,ArtID.getText(),enteredusername.getText());
}
private boolean Validate(TextField username, TextField enteredArt)throws IOException{
  
  CheckValidation Legal = new CheckValidation(username.getText(),enteredArt.getText());
  return Legal.getValid();
}
}

