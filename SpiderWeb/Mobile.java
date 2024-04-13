
/**
 * Write a description of class Mobile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mobile extends Bridge
{

    private boolean crossed;
    /**
     * Constructor for objects of class Mobile
     */
    public Mobile(double xStart, double yStart, double xEnd, double yEnd, int firstStrand, int secondStrand, String color, double distance)
    {
        super( xStart, yStart, xEnd, yEnd, firstStrand, secondStrand,color, distance);
        crossed=false;
    }
    
    public boolean getCrossed(){
        return crossed;
    }
    
    public void moved(int numStrands){
        firstStrand+=1;
        if(firstStrand>numStrands){
            firstStrand=1;
        }
        secondStrand+=1;
        if(secondStrand>numStrands){
            secondStrand=1;
        }
        distance+=50;
        double angle=Math.toRadians(360/numStrands);
        double angle1=angle*(firstStrand-1);
        double angle2=angle*(secondStrand-1);
        double xStart = 515 + (distance * Math.cos(-angle1));
        double yStart = 413 + (distance * Math.sin(-angle1));
        double xEnd = 515 + (distance * Math.cos(-angle2));
        double yEnd = 413 + (distance * Math.sin(-angle2));
        changePoints(xStart,yStart,xEnd,yEnd);
        if(isVisible){
            makeInvisible();
            makeVisible();
        }
        crossed=true;
    }

}
