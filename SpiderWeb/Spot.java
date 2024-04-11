import shapesXd.*;

/**
 * The `Spot` class represents a colored spot positioned on a specific strand of a spider web simulation.
 * It is used to mark specific points on the web.
 * This class provides methods to control the visibility and position of the spot.
 * 
 * @author William Hernandez y Nicolas Toro
 * @version 1.0
 */
public class Spot
{
    
    private String color;
    private int strand; 
    private Circle Body;
    private boolean isVisible;
    private double xPosition;
    private double yPosition;
    

    /**
     * Constructor for objects of class Spot
     * 
     * @param x The x-coordinate of the spot
     * @param y The y-coordinate of the spot
     * @param strand The strand on which the spot is located
     * @param color The color of the spot
     */
    public Spot(double x, double y, int strand, String color)
    {
        this.strand = strand;
        xPosition = x;
        yPosition = y;
        this.color = color;
        isVisible = false;
    }
    

    /**
     * Makes the spot visible.
     * 
     * @param  None
     * @return Void
     */
    public void makeVisible(){
        isVisible = true;
        Body = new Circle(xPosition, yPosition, color);
        Body.makeVisible();
    }
    
    /**
     * Makes the spot invisible.
     * 
     * @param  None
     * @return Void
     */
    public void makeInvisible(){
        Body.makeInvisible();
        isVisible = false;
    }
    
    /**
     * Changes the position of the spot.
     * 
     * @param x The new x-coordinate of the spot
     * @param y The new y-coordinate of the spot
     * @return Void
     */
    public void changePosition(double x, double y){
        xPosition = x;
        yPosition = y;
    }
    
    /**
     * Gets the strand on which the spot is located.
     * 
     * @param  None
     * @return The strand on which the spot is located
     */
    public int getStrand(){
        return strand;
    }
    
}
