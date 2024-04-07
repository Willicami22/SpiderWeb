
/**
 * Write a description of class Line here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Color;
import java.awt.geom.Line2D;

public class Linea {
    private double xStart; 
    private double yStart; 
    private double length; 
    private double angle; 
    private String Color; 
    private boolean isVisible; 
        
    public Linea(double angulo, double largo){
        length= largo;
        angle=Math.toRadians(angulo);
        xStart = 150;
        yStart = 150;
        Color = "negro";
        isVisible = false;
    }
    
    private void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            double xEnd = xStart + length * Math.cos(angle);
            double yEnd = yStart + length * Math.sin(angle);
            
            canvas.draw(this, Color, new Line2D.Double(xStart, yStart, xEnd, yEnd));
            canvas.wait(10);
        }
    }
    
        public void makeVisible(){
        isVisible = true;
        draw();
    }
    

    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    private double obtenerXFinal(){
        double xEnd = xStart + length * Math.cos(angle);
        return xEnd;
    }
    
    private double obtenerYFinal(){
        double yEnd = yStart + length * Math.sin(angle);
        return yEnd;
    }
}

