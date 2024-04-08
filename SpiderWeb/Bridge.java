import shapesXd.*;
/**
 * Write a description of class Puente here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bridge
{
    public Line Body;
    private int firstStrand;
    private int secondStrand;
    private boolean isVisible;
    private String color;
    private double xStart;
    private double yStart;
    private double xEnd;
    private double yEnd;
    private double distance;
    private double large;
    private double angle;

    /**
     * Constructor for objects of class Bridge
     */
    public Bridge(double xStart,double yStart,double xEnd,double yEnd,int firstStrand,int secondStrand, String color,double distance)
    {
        Body= new Line(xStart,yStart,xEnd,yEnd,color);
        this.firstStrand= firstStrand;
        this.secondStrand=secondStrand;
        isVisible=false;
        this.color=color;
        this.xStart=xStart;
        this.yStart=yStart;
        this.xEnd=xEnd;
        this.yEnd=yEnd;
        this.distance=distance;
        large = Math.sqrt((xEnd-xStart)*(xEnd-xStart) + (yEnd-yStart)*(yEnd-yStart));
        angle=Math.atan(yStart - yEnd/xStart - xEnd);

    
    }
    
    public void makeVisible(){
        isVisible=true;
        Body= new Line(xStart,yStart,xEnd,yEnd,color);
        Body.makeVisible();
        

    }
    
    public void makeInvisible(){
        Body.makeInvisible();
        isVisible=false;
    }
    
    public int getFirstStrand(){
        return firstStrand;
    }
    
    public int getSecondStrand(){
        return secondStrand;
    }
    
    public double getDistance(){
        return distance;
    }
    ;
    public void changePoints(double xStart,double yStart,double xEnd,double yEnd){
        this.xStart=xStart;
        this.yStart=yStart;
        this.xEnd=xEnd;
        this.yEnd=yEnd;
    }
    
    public void changeDistance(double distance){
        this.distance=distance;
    }
    
    public double getLarge(){
        return large;
    }
    
    public double getAngle(){
        return angle;
    }
    
    public double getxStart(){
        return xStart;
    
    }
    
    public double getyStart(){
        return yStart;
    
    }
    
    public double getxEnd(){
        return xEnd;
    
    }
    
    public double getyEnd(){
        return yEnd;
    
    }
    
    public String getColor(){
        return color;
    }
}
