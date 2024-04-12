import shapesXd.*;
import java.util.ArrayList;

/**
 * The Spider class represents a spider in the spider web simulation.
 * @author William Hernandez y Nicolas Toro
 * @version 1.0
 */
public class Spider {
    
    private Circle body;
    private Circle face; 
    private Circle leftEye; 
    private Circle rightEye; 
    private boolean isVisible; 
    private ArrayList<Circle> lastPath; 
    private int currentStrand;
    private double distanceToCenter; 
    private double angle; 
    /**
     * Constructor for objects of class Spider.
     */
    public Spider() {
    
        body = new Circle(500, 400, "black"); 
        body.changeSize(30);
        face = new Circle(505, 385, "black"); 
        face.changeSize(20);
        leftEye = new Circle(509, 390, "red"); 
        leftEye.changeSize(3);
        rightEye = new Circle(519, 390, "red"); 
        rightEye.changeSize(3);    
        lastPath = new ArrayList<>();
        currentStrand = 0;
        distanceToCenter = 0;
        angle = 90;
    }
    
    /**
     * Makes the spider visible.
     */
    public void makeVisible(){
        isVisible = true; 
        face.makeVisible();
        body.makeVisible(); 
        rightEye.makeVisible(); 
        leftEye.makeVisible();
    }
    
    /**
     * Makes the spider invisible.
     */
    public void makeInvisible(){
        isVisible = false; 
        face.makeInvisible();
        body.makeInvisible(); 
        rightEye.makeInvisible(); 
        leftEye.makeInvisible();
    }
    
    /**
     * Returns the spider to the center of the web.
     */
    public void returnToCenter(){
        distanceToCenter = 0;
        body.changePosition(500, 400);
    }
    
    /**
     * Locates the spider at a given angle.
     * @param angle The angle at which the spider is to be located.
     */
    public void locateSpider(double angle) {

        double deltaX1 = leftEye.getX() - face.getX();
        double deltaY1 = leftEye.getY() - face.getY();
        double angle1 = Math.atan2(deltaY1, deltaX1) - Math.toRadians(this.angle) + angle; 
        
        double deltaX2 = rightEye.getX() - face.getX();
        double deltaY2 = rightEye.getY() - face.getY();
        double angle2 = -Math.atan2(deltaY2, deltaX2) - Math.toRadians(this.angle) + angle; 
        
        double distance1 = Math.sqrt(181);
        double distance2 = Math.sqrt(461);
        
        this.angle = angle;
        
        face.changePosition(body.getX() + (17 * Math.cos(angle)) + 5, body.getY() + (17 * -Math.sin(angle)) + 5); 
        leftEye.changePosition(face.getX() + distance1 * Math.cos(angle1), face.getY() + distance1 * Math.sin(-angle1)); 
        rightEye.changePosition(face.getX() + distance2 * Math.cos(angle2), face.getY() + distance2 * Math.sin(-angle2));
    }
    
    /**
     * Gets the current strand on which the spider is located.
     * @return The current strand.
     */
    public int getCurrentStrand(){
        return currentStrand;    
    }
    
    /**
     * Gets the distance of the spider to the center of the web.
     * @return The distance to the center.
     */
    public double getDistanceToCenter(){
        return distanceToCenter;
    }
    
    /**
     * Changes the current strand of the spider.
     * @param strand The new current strand.
     */
    public void changeCurrentStrand(int strand){
        currentStrand = strand;
    }
    
    /**
     * Moves the spider along a specified angle and distance.
     * @param angle The angle of movement.
     * @param distance The distance to move.
     */
    public void moveSpider(double angle, double distance){
        double deltax;
        double deltay;
        
        double angle1 = angle;
        
        double Xdistance = distance * Math.cos(angle1);
        double Ydistance = distance * Math.sin(angle1);

        deltax = Math.cos(angle1) * 2;
        deltay = -Math.sin(angle1) * 2; 
        
        if (distance < 0){
            distance = -distance;
        }

        for(int i = 0; i < distance / 2; i++){
            double xPositionFace = deltax + face.getX();
            double yPositionFace = deltay + face.getY();
            double xPositionBody = deltax + body.getX();
            double yPositionBody = deltay + body.getY();
            double xPositionLeftEye = deltax + leftEye.getX();
            double yPositionLeftEye = deltay + leftEye.getY();
            double xPositionRightEye = deltax + rightEye.getX();
            double yPositionRightEye = deltay + rightEye.getY();
            face.changePosition(xPositionFace, yPositionFace);
            body.changePosition(xPositionBody, yPositionBody);
            leftEye.changePosition(xPositionLeftEye, yPositionLeftEye);
            rightEye.changePosition(xPositionRightEye, yPositionRightEye);
            
            Circle path = new Circle(xPositionBody, yPositionBody, "red");
            path.makeVisible();
            lastPath.add(path);
        }
    }
    
    /**
     * Changes the distance of the spider to the center.
     * @param distance The new distance to the center.
     */
    public void changeDistanceToCenter(double distance){
        distanceToCenter= distance;
    }
    
    /**
     * Transports the spider to a given position and angle.
     * @param angle The angle at which to position the spider.
     * @param xPosition The x-coordinate of the spider's new position.
     * @param yPosition The y-coordinate of the spider's new position.
     */
    public void transportSpider(double angle, double xPosition, double yPosition){
        body.changePosition(xPosition, yPosition);
        locateSpider(angle);
    }       
    
    /**
     * Resets the spider's last path.
     */
    public void reestartLastPath(){
        lastPath = new ArrayList<>();
    }
    
    /**
     * Displays the spider's last path.
     */
    public void showLastPath(){
        for(Circle l : lastPath){
            l.makeVisible();
        }
    }
    
    /**
     * Erases the spider's last path.
     */
    public void eraseLastPath(){
        for(Circle l : lastPath){
            l.makeInvisible();
        }
    }
} 
