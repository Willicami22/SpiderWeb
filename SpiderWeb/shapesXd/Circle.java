    package shapesXd;

import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle{

    public static final double PI=3.1416;
    
    private int diameter;
    private double xPosition;
    private double yPosition;
    private String color;
    private boolean isVisible;
    
    /**
     * Constructs a Circle object with default parameters.
     * The default diameter is 30, default X position is 20, default Y position is 15,
     * default color is "blue", and the object is initially invisible.
     */
    public Circle(){
        diameter = 5;
        xPosition = 690.0;
        yPosition = 410.0;
        color = "black";
        isVisible = false;
    }

    
    /**
     * Constructs a Circle object with specified parameters.
     * 
     * @param x The X coordinate of the center of the circle.
     * @param y The Y coordinate of the center of the circle.
     * @param vColor The color of the circle.
     * The default diameter is 5, and the object is initially invisible.
     */
    public Circle(double x, double y, String vColor) {
        diameter = 12;
        xPosition = x;
        yPosition = y;
        color = vColor;
        isVisible = false;
    }
    
    
    /**
    * Calculate the perimeter of the circle.
    */
    public double perimeter() {
        return 2 * Math.PI * (diameter/2);
    }
    
    /**
    * Makes the circle jump.
    */
    public void bounce(int times, int height) {
        Random random = new Random();

        for (int i = 0; i < times; i++) {
            
            int jumpHeight = random.nextInt(height) + 1;

            slowMoveVertical(jumpHeight);

            draw();

            slowMoveVertical(-jumpHeight);

            draw();
        }
    }   
    
    /**
     * Calculate the area of the circle.
     */
    public double area() {
        double radius = diameter / 2;

        double area = PI * Math.pow(radius, 2);

        return area;
    }
    
    /**
     * Calculate the duplicate area of the circle.
     */
    public double duplicate() {
        
        double newArea = area() * 2;

        return newArea;
    }
    
    /**
     * Makes the circle visible.
     * This method sets the visibility flag to true and redraws the spider web.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Makes the cirlce invisible.
     * This method erases the spider web and sets the visibility flag to false.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }

    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * Move the circle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the circle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the circle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels.
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically.
     * @param distance the desired distance in pixels.
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the circle horizontally.
     * @param distance the desired distance in pixels.
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the circle vertically.
     * @param distance the desired distance in pixels.
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }
    
    public void slowMove(double distance,double angle){
        double deltax;
        double deltay;
        
        double angle1=angle;
        
        double Xdistance = distance*Math.cos(angle1);
        double Ydistance = distance*Math.sin(angle1);

        deltax=Math.cos(angle1);
        deltay=-Math.sin(angle1); 
        
        if (distance<0){
            distance=-distance;
        }

        for(int i = 0; i < distance; i++){
            xPosition += deltax;
            yPosition += deltay;
            draw();
        }
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Change the color.
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta", "cyan","gray" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    
    /**
     * Changes the position of the circle.
     * 
     * @param newx The new X coordinate of the circle.
     * @param newy The new Y coordinate of the circle.
     */
    public void changePosition (double newx, double newy) {
        erase();
        xPosition = newx;
        yPosition = newy;
        draw();
    }

    
    /**
     * Retrieves the X coordinate of the circle.
     * 
     * @return The X coordinate of the circle.
     */
    public double getX() {
        return xPosition;
    }
    
    
    /**
     * Retrieves the X coordinate of the circle.
     * 
     * @return The X coordinate of the circle.
     */
    public double getY() {
        return yPosition;
    }
    
}
