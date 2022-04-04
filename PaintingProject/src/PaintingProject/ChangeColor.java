
package PaintingProject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Dondre
 */
public class ChangeColor {
    private GraphicsContext GC;
    public ChangeColor(GraphicsContext gc){
    GC = gc;
    }
    public void SetColor(Color color){
        GC.setStroke(color);
    }
}
