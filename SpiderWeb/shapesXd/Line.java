package shapesXd;


/**
 * Write a description of class Line here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Color;
import java.awt.geom.Line2D;

public class Line {
    private double xStart; 
    private double yStart;
    private double xEnd;
    private double yEnd;
    private double length;  
    private String Color; 
    private boolean isVisible; 
    
    public Line(double angle, double large){
        length= large;
        angle = Math.toRadians(angle);
        xStart = 515;
        yStart = 413;
        xEnd = xStart + (length * Math.cos(angle));
        yEnd = yStart + (length * Math.sin(angle));
        Color = "black";
        isVisible = false;
    }
    
    public Line(double xStart,double yStart, double xEnd, double yEnd, String color){
        length= Math.sqrt(Math.pow((xEnd-xStart),2)+Math.pow((yEnd-yStart),2));
        this.xStart = xStart;
        this.yStart = yStart;
        this.yEnd = yEnd;
        this.xEnd = xEnd;
        Color = color;
        isVisible = false;
    }
    
    private void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            
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
    
}

