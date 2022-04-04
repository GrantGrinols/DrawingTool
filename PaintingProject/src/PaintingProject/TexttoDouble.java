/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaintingProject;



import javafx.scene.control.TextField;

/**
 *
 * @author Grant
 */
public class TexttoDouble {
    double width;
    double height;
    public TexttoDouble(TextField widthfield, TextField heightfield){//this class translate the strings you'ved entered from the GUI into doubles
       this.width =  Double.parseDouble(widthfield.getText());
       this.height = Double.parseDouble(heightfield.getText());
   }
    public double getwidth(){
        return width;
    }
    public double getheight(){
        return height;
    }
    
    
    
    
}
