
package PaintingProject;

import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.paint.Color;

/**
 *
 * @author Dondre
 */
public class ChangeBrushSize {
    
    public ChangeBrushSize(GraphicsContext gc, double size){
      
        gc.setLineWidth(size);
    }

}
