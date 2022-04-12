package PaintingProject;

import javafx.scene.control.Button;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

;
public class List {//this class makes the scene that the ListStage will use. We are using doing 3 stages to make it so you an login to multiple accounts per session
    private Button Uploading;
   private String Name;
    private GridPane ArtGrid = new GridPane();
    private Scene ArtScene;
    private TextField EnteredArt;
    private CheckValidation Checking;
    private Stage stage;
    public List(String paraName,Button paraButton, TextField paraArt, Stage stage)throws IOException{
        Name = paraName;
        Uploading = paraButton;
        EnteredArt = paraArt;
        this.stage = stage;
        LookAtArt();
    }
    private void LookAtArt()throws IOException{
        FileChooser fileChoose = new FileChooser();
        Label Welcome = new Label("Welcome,"+Name);
        Label ArtHere = new Label("Enter your art here:");
        Label NoAccess = new Label("You do not have access");
        Button UploadImageBtn = new Button("Upload new images");
        ArtGrid.add(Welcome, 0, 0);
        ArtLookUp CheckName = new ArtLookUp(Name);
        CheckName.SearchArt(ArtGrid);
        ArtGrid.add(Uploading,2,5);
        ArtGrid.add(ArtHere,2,3);
        ArtGrid.add(EnteredArt, 2, 4);
        ArtGrid.add(UploadImageBtn,2,6);
        EnteredArt.setOnAction(e->{
            try {
                Checking = new CheckValidation(Name,EnteredArt.getText());
                System.out.println("Hey");
            } catch (IOException e1) {
                
                e1.printStackTrace();
            }
            if(!Checking.getValid()){

                ArtGrid.add(NoAccess, 2, 6);
            }
        });
        
        UploadImageBtn.setOnAction(e->{
            UploadImage Uploading = new UploadImage(fileChoose, stage, Name);
            Uploading.UploadImageTime();

        });


        Group ArtLookGroup = new Group(ArtGrid);
        ArtScene = new Scene(ArtLookGroup,800,500);


    }
    public Scene getScene(){
        return ArtScene;
    }
    


}
