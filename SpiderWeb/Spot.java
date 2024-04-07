import shapesXd.*;
/**
 * Write a description of class Spot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
     */
    public Spot(double x,double y,int strand, String color)
    {
        this.strand = strand;
        xPosition=x;
        yPosition=y;
        this.color= color;
        isVisible=false;
        

    }
    

    public void makeVisible(){
        isVisible=true;
        Body = new Circle(xPosition,yPosition,color);
        
        Body.makeVisible();
        

    }
    
    public void makeInvisible(){
        Body.makeInvisible();
        isVisible=false;

    }
    
    public void changePosition(double x,double y){
        
        xPosition=x;
        yPosition=y;
        
    }
    
    public int getStrand(){
        return strand;
    }
    
}
