import shapesXd.*;

/**
 * The Bridge class represents a bridge connecting two strands in the spider web simulation.
 * @author William Hernandez y Nicolas Toro
 * @version 1.0
 */

public class Bridge {
    
    public Line body; 
    private int firstStrand; 
    private int secondStrand; 
    private boolean isVisible;
    private String color; 
    private double xStart; 
    private double yStart; 
    private double xEnd; 
    private double yEnd; 
    private double distance; 
    private double length; 
    private double angle;
    
    /**
     * Constructor for objects of class Bridge.
     */
    public Bridge(double xStart, double yStart, double xEnd, double yEnd, int firstStrand, int secondStrand, String color, double distance) {
        body = new Line(xStart, yStart, xEnd, yEnd, color); 
        this.firstStrand = firstStrand;
        this.secondStrand = secondStrand;
        isVisible = false;
        this.color = color;
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.distance = distance;
        length = Math.sqrt((xEnd - xStart) * (xEnd - xStart) + (yEnd - yStart) * (yEnd - yStart));
        angle = Math.atan(yStart - yEnd / xStart - xEnd);
    }
    
    /**
     * Makes the bridge visible.
     */
    public void makeVisible() {
        isVisible = true;
        body = new Line(xStart, yStart, xEnd, yEnd, color);
        body.makeVisible();
    }
    
    /**
     * Makes the bridge invisible.
     */
    public void makeInvisible() {
        body.makeInvisible();
        isVisible = false;
    }
    
    /**
     * Gets the index of the first strand connected by the bridge.
     * @return The index of the first strand.
     */
    public int getFirstStrand() {
        return firstStrand;
    }
    
    /**
     * Gets the index of the second strand connected by the bridge.
     * @return The index of the second strand.
     */
    public int getSecondStrand() {
        return secondStrand;
    }
    
    /**
     * Gets the distance between the two ends of the bridge.
     * @return The distance of the bridge.
     */
    public double getDistance() {
        return distance;
    }
    
    /**
     * Changes the points of the bridge to new coordinates.
     * @param xStart The x-coordinate of the new starting point.
     * @param yStart The y-coordinate of the new starting point.
     * @param xEnd The x-coordinate of the new ending point.
     * @param yEnd The y-coordinate of the new ending point.
     */
    public void changePoints(double xStart, double yStart, double xEnd, double yEnd) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }
    
    /**
     * Changes the distance of the bridge.
     * @param distance The new distance of the bridge.
     */
    public void changeDistance(double distance) {
        this.distance = distance;
    }
    
    
    /**
     * Gets the length of the bridge.
     * @return The length of the bridge.
     */
    public double getLength() {
        return length;
    }
    
    /**
     * Gets the angle of the bridge.
     * @return The angle of the bridge.
     */
    public double getAngle() {
        return angle;
    }
    
    /**
     * Gets the x-coordinate of the starting point of the bridge.
     * @return The x-coordinate of the starting point.
     */
    public double getStartX() {
        return xStart;
    }
    
    /**
     * Gets the y-coordinate of the starting point of the bridge.
     * @return The y-coordinate of the starting point.
     */
    public double getStartY() {
        return yStart;
    }
    
    /**
     * Gets the x-coordinate of the ending point of the bridge.
     * @return The x-coordinate of the ending point.
     */
    public double getEndX() {
        return xEnd;
    }
    
    /**
     * Gets the y-coordinate of the ending point of the bridge.
     * @return The y-coordinate of the ending point.
     */
    public double getEndY() {
        return yEnd;
    }
    
    /**
     * Gets the color of the bridge.
     * @return The color of the bridge.
     */
    public String getColor() {
        return color;
    }

}

