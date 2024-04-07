import shapesXd.*;
/**
 * Write a description of class Hebra here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void makeVisible()
    {
        isVisible=true;
        Body= new Line(angle,radio);
        Body.makeVisible();
        
    }
    
    public void makeInvisible()
    {
        Body.makeInvisible();
        isVisible=false;
    }
    
    public double getAngle(){
        return angle;
    }
    
    public void changeAngle(double Angle){
        this.angle=Angle;
    }
    
    public void changeRadio(double radio){
        
        this.radio=radio;
    }
}
