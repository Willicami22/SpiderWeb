import shapesXd.*;
/**
 * The `Strand` class represents a line segment that connects two points in a spider web simulation.
 * It is used to simulate the strands of the web.
 * This class provides methods to control the visibility, angle, and length of the strand.
 * 
 * @author William Hernandez y Nicolas Toro
 * @version 1.0
 */
public class Strand
{

    private Line Body;
    private boolean isVisible;
    private double angle;
    private double radio;
    

    /**
     * Constructor for objects of class Strand
     */
    public Strand(double angle, double radio)
    {
        this.angle = angle;
        this.radio = radio;

        isVisible=false;
    }

    /**
     * Makes the strand visible.
     * 
     * @param  None
     * @return Void
     */
    public void makeVisible()
    {
        isVisible=true;
        Body= new Line(angle,radio);
        Body.makeVisible();
        
    }
    
    /**
     * Makes the strand invisible.
     * 
     * @param  None
     * @return Void
     */
    public void makeInvisible()
    {
        Body.makeInvisible();
        isVisible=false;
    }
    
    /**
     * Gets the angle of the strand.
     * 
     * @param  None
     * @return The angle of the strand
     */
    public double getAngle(){
        return angle;
    }
    
    /**
     * Changes the angle of the strand.
     * 
     * @param  Angle the new angle of the strand
     * @return Void
     */
    public void changeAngle(double Angle){
        this.angle=Angle;
    }
    
    /**
     * Changes the length of the strand.
     * 
     * @param  radio the new length of the strand
     * @return Void
     */
    public void changeRadio(double radio){
        
        this.radio=radio;
    }
}

