import shapesXd.*;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 * The SpiderWeb class models a spider web which includes strands, spots, bridges, and a spider.
 * The web can be visualized and manipulated through various methods provided.
 * This class is a part of a larger simulation that may involve graphical interaction with a user.
 *
 * Authors: William Hernandez y Nicolas Toro
 * Version: 1.0
 */
public class SpiderWeb {

    private final double angle = 360;
    private double diameter;
    private int numStrands;
    private Spider spider;
    private ArrayList<Strand> strands;
    private int xCenter;
    private int yCenter;
    private String Color;
    private Map<String, Spot> mapSpots;
    private Map<String, Bridge> mapBridge;
    private boolean isVisible;
    private boolean lastAction;
    private ArrayList<Integer> lastPath;
    private ArrayList<String> unusedBridge;
    
    /**
     * Constructor for creating a SpiderWeb with specified diameter and number of strands.
     */
    public SpiderWeb(int diameter, int numStrands) {
        lastAction = false;
        if (diameter > 0 && numStrands > 0) {
            this.diameter = diameter * 10;
            strands = new ArrayList<>();
            mapSpots = new HashMap<>();
            mapBridge = new HashMap<>();
            spider = new Spider();
            lastPath = new ArrayList<>();
            unusedBridge = new ArrayList<>(mapBridge.keySet());
            
            this.numStrands = numStrands;
            xCenter = 515;
            yCenter = 413;
            Color = "black";
            isVisible = false;
            lastAction = true;
            
            double angleIncrement = this.angle / numStrands;
            
            for (int i = 0; i < numStrands; i++) {
                double angle1 = angleIncrement * i;
                Strand strand = new Strand(angle1, (this.diameter) / 2);
                strands.add(strand);
            }
        }
    }

    /**
     * Overloaded constructor that also initializes bridges between strands based on input arrays,
     * and marks a favorite spot with a unique color.
     */
    public SpiderWeb(int numStrands, int favorite, int[][] bridges) {
        lastAction = false;
        if (numStrands > 0 && favorite > 0) {
            diameter = 600;
            strands = new ArrayList<>();
            mapSpots = new HashMap<>();
            mapBridge = new HashMap<>();
            spider = new Spider();
            lastPath = new ArrayList<>();
            unusedBridge = new ArrayList<>(mapBridge.keySet());

            this.numStrands = numStrands;
            xCenter = 515;
            yCenter = 413;
            Color = "black";
            isVisible = false;
            lastAction = true;
            double angleIncrement = this.angle / numStrands;
            for (int i = 0; i < numStrands; i++) {
                double angle1 = angleIncrement * i;
                Strand strand = new Strand(angle1, diameter / 2);
                strands.add(strand);
            }
                
            ArrayList<String> colors = new ArrayList<>(Arrays.asList("red", "black", "blue", "yellow", "green", "magenta", "pink", "orange", "gray", "cyan"));
            
            for (int[] b : bridges) {
                Random random = new Random();
                int randomIndex = random.nextInt(colors.size());
                String color = colors.get(randomIndex);
                colors.remove(randomIndex);
                addBridge(color, b[0], b[1]);
            }
            
            Random random = new Random();
            int randomIndex = random.nextInt(colors.size());
            String color = colors.get(randomIndex);
            colors.remove(randomIndex);
            addSpot(color, favorite);
        }
    }

    /**
     * Makes the SpiderWeb visible if it is not already.
     */
    public void makeVisible() {
        lastAction = false;
        if (!isVisible) {
            for (Strand s : strands) {
                s.makeVisible();
            }
            if(spider.getIsAlive()){
                spider.makeVisible();
            }
            for (Spot s : mapSpots.values()) {
                s.makeVisible();
            }
            for (Bridge b : mapBridge.values()) {
                b.makeVisible();
            }
            isVisible = true;
            lastAction = true;
        }
    }

    /**
     * Makes the SpiderWeb invisible if it is currently visible.
     */
    public void makeInvisible() {
        lastAction = false;
        if (isVisible) {
            for (Strand s : strands) {
                s.makeInvisible();
            }
            spider.makeInvisible();
            spider.eraseLastPath();
            for (Spot s : mapSpots.values()) {
                s.makeInvisible();
            }
            for (Bridge b : mapBridge.values()) {
                b.makeInvisible();
            }
            isVisible = false;
            lastAction = true;
        }
    }
    
 
    /**
     * Adds a bridge between two consecutive strands of the spider web. 
     *
     * This method creates a bridge, represented as a connection between two strands
     * at a specified distance from the center of the web. The bridge is added only if
     * no other bridge with the same color already exists, the specified distance is less than
     * half of the web's diameter, and the strand indices are within the valid range. The bridge
     * connects the specified strand and the next strand in the sequence, wrapping around to the
     * first strand if the specified strand is the last one.
     *
     * @param color The color of the bridge, used as a unique identifier.
     * @param distance The radial distance of the bridge from the center of the web.
     * @param firstStrand The index of the first strand; the bridge will connect this strand
     *                    to the next one in the circular arrangement of strands.
     */
    public void addBridge(String color, int distance, int firstStrand){
        lastAction = false;
        boolean comprobe = comprobeAddBridge(color,distance,firstStrand);
        if(comprobe){
            double angle = this.angle / numStrands;
            
            int secondStrand = firstStrand + 1;
            if(firstStrand == numStrands){
                secondStrand = 1;
            }
            
            double angle1 = Math.toRadians((firstStrand - 1) * angle);
            double angle2 = Math.toRadians((secondStrand - 1) * angle);
            
            double xStart = xCenter + (distance * Math.cos(-angle1));
            double yStart = yCenter + (distance * Math.sin(-angle1));
            double xEnd = xCenter + (distance * Math.cos(-angle2));
            double yEnd = yCenter + (distance * Math.sin(-angle2));
            
            Bridge bridge = new Bridge(xStart, yStart, xEnd, yEnd, firstStrand, secondStrand, color, distance);
            
            if(isVisible){
                bridge.makeVisible();
            }
            mapBridge.put(color, bridge);
            lastAction = true;
        }
    }
    
    private boolean comprobeAddBridge(String color, int distance, int firstStrand){
        boolean comprobe=true;
        int secondStrand = firstStrand + 1;
        if(firstStrand == numStrands){
            secondStrand = 1;
        }
        int previousStrand= firstStrand-1;
        if(firstStrand == 1){
            previousStrand  = numStrands;
        }
        if(mapBridge.containsKey(color) || distance > (diameter / 2) || distance<=0 || firstStrand <= 0 || firstStrand > numStrands){
            comprobe=false;
        }
        
        for(Bridge b : mapBridge.values()){
            if(b.getFirstStrand()==firstStrand && b.getDistance()==distance){
                comprobe=false;
            }
            
            if(b.getFirstStrand()==previousStrand && b.getDistance()==distance){
                comprobe=false;
            }
            
            if(b.getFirstStrand()==secondStrand && b.getDistance()==distance){
                comprobe=false;
            }
            
        }
        return comprobe;
    }
    
    /**
     * Adds a spot to the spider web at the specified strand.
     *
     * This method places a new spot on the web, identified by a unique color. The spot
     * is positioned on the specified strand (referred to as "favorite"). A spot can only
     * be added if there isn't already a spot with the same color, and the specified strand
     * index must be valid within the current number of strands in the web.
     *
     * @param color The color of the spot, which acts as a unique identifier.
     * @param favorite The index of the strand where the spot will be located.
     */
    public void addSpot(String color, int favorite){
        lastAction = false; 
        boolean comprobe=comprobeAddSpot(color,favorite); 
        if (comprobe){
            
            double angle = this.angle / numStrands;
            double angle1 = Math.toRadians((favorite - 1) * angle);
        
            double x = xCenter + (diameter / 2) * Math.cos(-angle1);
            double y = yCenter + (diameter / 2) * Math.sin(-angle1);
            
            Spot spot = new Spot(x, y, favorite, color);
            mapSpots.put(color, spot);
            
            if (isVisible) {  
                spot.makeVisible();
            }
            lastAction = true;  
        }
    }
    
    private boolean comprobeAddSpot(String color, int favorite){
        boolean comprobe=true; 
        if (mapSpots.containsKey(color) || favorite <= 0 || favorite > numStrands){
            comprobe=false;
        }
        
        for (Spot s : mapSpots.values()){
            if(s.getStrand()==favorite){
                comprobe=false;
            }
        }
        
        return comprobe;
    }
    
    public void addSpot(String type,String color, int favorite){
        lastAction = false; 
        boolean comprobe=comprobeAddSpot(color,favorite);
        if (comprobe){
            
            double angle = this.angle / numStrands;
            double angle1 = Math.toRadians((favorite - 1) * angle);
        
            double x = xCenter + (diameter / 2) * Math.cos(-angle1);
            double y = yCenter + (diameter / 2) * Math.sin(-angle1);
            
            Spot spot;
            if (type.equals("bouncy")){
                spot = new Bouncy(x, y, favorite, color);
            }
            else if(type.equals("reverse")){
                spot = new Reverse(x, y, favorite, color);
            }
            else if(type.equals("killer")){
                spot = new Killer(x, y, favorite, color);
            }
            else{ 
                spot = new Spot(x, y, favorite, color);
            }
            
            mapSpots.put(color, spot);
            
            if (isVisible) {  
                spot.makeVisible();
            }
            lastAction = true;  
        }
    }
    
    public void addBridge(String type,String color, int distance, int firstStrand){
        lastAction = false;
        boolean comprobe = comprobeAddBridge(color,distance,firstStrand);
        if(comprobe){
            double angle = this.angle / numStrands;
            int secondStrand = firstStrand + 1;
            if(firstStrand == numStrands){
                secondStrand = 1;
                }   
            double angle1 = Math.toRadians((firstStrand - 1) * angle);
            double angle2 = Math.toRadians((secondStrand - 1) * angle);
                
            double xStart = xCenter + (distance * Math.cos(-angle1));
            double yStart = yCenter + (distance * Math.sin(-angle1));
            double xEnd = xCenter + (distance * Math.cos(-angle2));
            double yEnd = yCenter + (distance * Math.sin(-angle2));
            Bridge bridge;
            
            if(type=="fixed"){
                bridge = new Fixed(xStart, yStart, xEnd, yEnd, firstStrand, secondStrand, color, distance);
            }
            else if(type=="weak"){
                bridge = new Weak(xStart, yStart, xEnd, yEnd, firstStrand, secondStrand, color, distance);
            }
            else if(type=="mobile"){
                bridge = new Mobile(xStart, yStart, xEnd, yEnd, firstStrand, secondStrand, color, distance);
            }
            else if(type=="transformer"){
                bridge = new Transformer(xStart, yStart, xEnd, yEnd, firstStrand, secondStrand, color, distance);
            }
            else{
                bridge = new Bridge(xStart, yStart, xEnd, yEnd, firstStrand, secondStrand, color, distance);
            }
            
            if(isVisible){
                bridge.makeVisible();
            }
            mapBridge.put(color, bridge);
            lastAction = true;
        }
    }


    
        
    /**
     * Adds an additional strand to the spider web.
     *
     * This method increments the total number of strands in the web and recalculates
     * the angular position for each existing strand, spot, and bridge to evenly distribute
     * them around the center point. If the web is visible when this method is called, it
     * will temporarily be made invisible to update the layout and then made visible again
     * to reflect the changes. This approach ensures that the web maintains a balanced
     * appearance with evenly spaced strands.
     */
    public void addStrand(){
        boolean draw = false;          
        if(isVisible){
            makeInvisible();
            draw = true;
        }
        numStrands += 1;
        double angle = this.angle / numStrands;
        for (int i = 0; i < (numStrands - 1); i++) {
            double angle1 = angle * i;
            strands.get(i).changeAngle(angle1);
        }
        Strand strand = new Strand(angle * (numStrands - 1), diameter / 2);
        strands.add(strand);
        for (Spot s : mapSpots.values()){
            double angle1 = Math.toRadians(((s.getStrand() - 1) * angle));
            double x = xCenter + (diameter / 2) * Math.cos(-angle1);
            double y = yCenter + (diameter / 2) * Math.sin(-angle1);
            s.changePosition(x, y);
        }
        
        for (Bridge b : mapBridge.values()){
            int firstStrand = b.getFirstStrand();
            int secondStrand = b.getSecondStrand();
            double distance = b.getDistance();
            double angle1 = Math.toRadians((firstStrand - 1) * angle);
            double angle2 = Math.toRadians((secondStrand - 1) * angle); 
            double xStart = xCenter + (distance * Math.cos(-angle1));
            double yStart = yCenter + (distance * Math.sin(-angle1));
            double xEnd = xCenter + (distance * Math.cos(-angle2));
            double yEnd = yCenter + (distance * Math.sin(-angle2));
                    
            b.changePoints(xStart, yStart, xEnd, yEnd);
        }
        if(draw){
            makeVisible();
        }
        lastAction = true;  
    }

    /**
     * Enlarges the spider web by a specified percentage.
     *
     * This method increases the diameter of the web according to the provided percentage.
     * As a result, the radius of each strand and the positions of all spots are recalculated
     * to fit the new size. If the web is visible when this method is invoked, it will temporarily
     * be made invisible during the update process and then made visible again once adjustments
     * are complete. This ensures that the changes are properly applied and visible to the user.
     *
     * @param percentage The percentage by which to increase the web's diameter. The value
     *                   should represent a positive percentage (e.g., 10 for 10% increase).
     */
    public void enlarge(double percentage){
        boolean draw = false;  
    
        if(isVisible){
            makeInvisible();
            draw = true;
        }
        diameter += (diameter * (percentage / 100));
    
        for(Strand s : strands){
            s.changeRadio(diameter / 2); 
        }
        
        for(Spot m : mapSpots.values()){
            int strand = m.getStrand(); // 
            double angle = this.angle / numStrands; 
            double angle1 = Math.toRadians((strand - 1) * angle); 
            
            double x = xCenter + (diameter / 2) * Math.cos(-angle1);
            double y = yCenter + (diameter / 2) * Math.sin(-angle1);
            
            m.changePosition(x, y); 
        }
        
        if(draw){
            makeVisible();
        }
        
        lastAction = true; 
    }

      
    
    /**
     * Relocates a bridge on the spider web to a new distance from the center.
     *
     * This method updates the position of a specified bridge identified by its color.
     * The new position is determined by a new distance from the web's center, provided
     * the distance is less than half the diameter of the web and the bridge exists.
     * If the bridge is currently visible, it will be briefly made invisible and then
     * visible again to reflect the change visually.
     *
     * @param color    The color identifier of the bridge to be relocated.
     * @param distance The new distance from the center to which the bridge will be moved.
     *                 This distance must be less than half the diameter of the web.
     */
    public void relocateBridge(String color, double distance){
        lastAction = false; 
        if(distance < diameter / 2 && mapBridge.containsKey(color)){
            double angle = this.angle / numStrands; 
            Bridge bridge = mapBridge.get(color);   
    
            int firstStrand = bridge.getFirstStrand();
            int secondStrand = bridge.getSecondStrand();
            
            double angle1 = Math.toRadians((firstStrand - 1) * angle);
            double angle2 = Math.toRadians((secondStrand - 1) * angle);
            
            double xStart = xCenter + (distance * Math.cos(-angle1));
            double yStart = yCenter + (distance * Math.sin(-angle1));
            double xEnd = xCenter + (distance * Math.cos(-angle2));
            double yEnd = yCenter + (distance * Math.sin(-angle2));
            
            bridge.changePoints(xStart, yStart, xEnd, yEnd);
            bridge.changeDistance(distance);
    
            if(isVisible){
                bridge.makeInvisible();
                bridge.makeVisible();
            }
            
            lastAction = true;  
        }
    }

    
     /**
     * Deletes a bridge from the spider web based on its color identifier.
     *
     * This method removes the bridge with the specified color from the spider web.
     * If the bridge is currently visible, it will be made invisible upon deletion.
     *
     * @param color The color identifier of the bridge to be deleted.
     */
    public void delBridge(String color){
        lastAction = false;
        if(mapBridge.containsKey(color)){
            Bridge b = mapBridge.get(color);
            if(b  instanceof Transformer){
                mapBridge.remove(color);
                int firstStrand=b.getFirstStrand();
                boolean thereIsSpot=false;
                for(Spot s : mapSpots.values()){
                    if(s.getStrand()==firstStrand){
                        thereIsSpot=true;
                    }
                }
                if(isVisible){
                    b.makeInvisible();
                }
                
                if (!thereIsSpot){
                    addSpot(color,firstStrand);
                }  
                lastAction = true;
                }
            else if(!(b instanceof Fixed)){
                mapBridge.remove(color);
                if(isVisible){
                    b.makeInvisible();
                }
                lastAction = true;
            }
        }
    }
    
    /**
     * Deletes a spot from the spider web based on its color identifier.
     *
     * This method removes the spot with the specified color from the spider web.
     * If the spot is currently visible, it will be made invisible upon deletion.
     *
     * @param color The color identifier of the spot to be deleted.
     */
    public void delSpot(String color){
        lastAction = false; 

        if(mapSpots.containsKey(color)){
            Spot s = mapSpots.remove(color); 
            
            if(isVisible){
                s.makeInvisible();
            }
            
            lastAction = true;
        }
    }

    
    /**
     * Moves the spider to sit on a specific strand of the spider web.
     *
     * This method relocates the spider to sit on the specified strand of the spider web.
     * If the provided strand index is valid within the current number of strands, the spider
     * will erase its last path, become invisible, return to the center of the web, and then
     * move to sit on the specified strand. The spider's current strand attribute is updated
     * accordingly. If the spider web is visible, the spider will become visible after the move.
     *
     * @param strand The index of the strand where the spider will sit.
     */
    public void spiderSit(int strand){
        lastAction = false; 
        
        if (strand > 0 && strand <= numStrands){ 
            spider.eraseLastPath(); 
            spider.makeInvisible();
            unusedBridge = new ArrayList<>(mapBridge.keySet());
            
            double angle = this.angle / numStrands;
            double angle1 = Math.toRadians((strand - 1) * angle);
            
            spider.returnToCenter(); 
            spider.locateSpider(angle1); 
            
            spider.changeCurrentStrand(strand); 
            lastAction = true; 
            
            if(isVisible){
                spider.makeVisible();
            }
        }
    }

    
    /**
     * Initiates the spider's movement on the spider web, either advancing or retracting.
     *
     * This method controls the spider's movement on the spider web, allowing it to either
     * advance towards the outer edge of the web or retract towards the center. The spider
     * will erase its last path and then proceed with the movement based on the specified
     * boolean parameter 'advance'. If 'advance' is true and the spider is at the center
     * of the web, it will move towards the outer edge. If 'advance' is false and the spider
     * is at the outer edge, it will retract towards the center. After the movement, the spider's
     * position and distance to the center are updated accordingly. The spider's movement is
     * tracked, and the last action flag is set to true upon successful execution.
     *
     * @param advance A boolean value indicating whether the spider should advance (true) or retract (false).
     */
    public void spiderWalk(boolean advance){
        lastAction = false; 
        
        double distance = spider.getDistanceToCenter(); 
        double angle = this.angle / numStrands; 
        
        spider.eraseLastPath();
        
        if (advance && distance == 0 && spider.getCurrentStrand() != 0){
            moveSpider(advance); 
            distance = spider.getDistanceToCenter(); 
            lastPath.add(spider.getCurrentStrand()); 
            
            spider.locateSpider(Math.toRadians((spider.getCurrentStrand() - 1) * angle));
            spider.moveSpider(Math.toRadians((spider.getCurrentStrand() - 1) * angle), (diameter / 2) - distance);
            spider.changeDistanceToCenter(diameter / 2); 
            
            spider.locateSpider(Math.toRadians((spider.getCurrentStrand() - 1) * angle) + Math.PI);

            comprobeSpot();
            
            lastAction = true; 
        }
        else if(!advance && distance == (diameter / 2) && spider.getCurrentStrand() != 0 && spider.getIsAlive()){
            moveSpider(advance);
            distance = spider.getDistanceToCenter(); 
            lastPath.add(spider.getCurrentStrand()); 
            
            spider.locateSpider(Math.toRadians((spider.getCurrentStrand() - 1) * angle) + Math.PI);
            spider.moveSpider(Math.toRadians((spider.getCurrentStrand() - 1) * angle) + Math.PI, distance);
            spider.returnToCenter(); 
            
            spider.locateSpider(Math.toRadians((spider.getCurrentStrand() - 1) * angle));
            
            lastAction = true; 
        }
    }
    
    /**
     * Moves the spider on the spider web based on the specified direction (advance or retract).
     *
     * This method controls the spider's movement on the spider web by iterating through
     * available bridges and moving the spider along them in the specified direction until
     * it reaches the desired position. The spider's movement is updated, and the last path
     * and unused bridge lists are adjusted accordingly. If a bridge connects the spider's
     * current strand to another strand, the spider is transported to the other strand's position.
     *
     * @param advance A boolean value indicating whether the spider should advance (true) or retract (false).
     */
    private void moveSpider(boolean advance) { 
        double anglePerStrand = this.angle / numStrands;  
        double angleAdjustment = advance ? 0 : Math.PI; 
        int cont=0;
        lastPath = new ArrayList<>();
        unusedBridge = new ArrayList<>(mapBridge.keySet());
        spider.reestartLastPath(); 
        
        while (cont==0){
            Bridge closestBridge = null;
            double minDistance = Double.MAX_VALUE; 
            boolean beforeBridge = false;
            int currentStrand = spider.getCurrentStrand(); 
            double currentDistance = spider.getDistanceToCenter();
            for (String s : unusedBridge) {
                if (mapBridge.get(s).getFirstStrand() == currentStrand || mapBridge.get(s).getSecondStrand() == currentStrand) {
                    double bridgeDistance = mapBridge.get(s).getDistance(); 
                    if(advance){
                        if(mapBridge.get(s).getDistance() > currentDistance){
                            beforeBridge = true;
                            if (Math.abs(bridgeDistance - currentDistance) < minDistance) {
                                minDistance = Math.abs(bridgeDistance - currentDistance);
                                closestBridge = mapBridge.get(s);
                            }
                        }
                    }
                    else if (!advance) {
                        if(mapBridge.get(s).getDistance() < currentDistance){
                            beforeBridge = true;
                            if (Math.abs(bridgeDistance - currentDistance) < minDistance) {
                                minDistance = Math.abs(bridgeDistance - currentDistance);
                                closestBridge = mapBridge.get(s);
                            }
                        }
                    } 
                    
                }
            }
            
            if (closestBridge != null && beforeBridge) {
                double angleToMove = Math.toRadians((currentStrand - 1) * anglePerStrand) + angleAdjustment;
                spider.moveSpider(angleToMove, minDistance); 
                lastPath.add(currentStrand);
                unusedBridge.remove(String.valueOf(closestBridge.getColor()));
        
                if (closestBridge.getFirstStrand() == currentStrand) {
                    spider.transportSpider(Math.toRadians(currentStrand * anglePerStrand)+angleAdjustment, closestBridge.getEndX(), closestBridge.getEndY());
                    spider.changeCurrentStrand(closestBridge.getSecondStrand());
                } else {
                    spider.transportSpider(Math.toRadians((currentStrand - 2) * anglePerStrand)+angleAdjustment, closestBridge.getStartX(), closestBridge.getStartY());
                    spider.changeCurrentStrand(closestBridge.getFirstStrand());
                }
                
                spider.changeDistanceToCenter(closestBridge.getDistance()); 
                
                comprobeBridge(closestBridge);
            }
            
            else if(closestBridge==null){
                cont=1;
            }
        }
    }

    
    private void comprobeBridge(Bridge bridge){
        if (bridge instanceof Weak){
            String color=bridge.getColor();
            delBridge(color);
        }
        else if(bridge instanceof Mobile){
            Mobile mobileBridge = (Mobile) bridge;
            double distance=mobileBridge.getDistance();
            if (((distance+50)<=(diameter/2)) && !(mobileBridge.getCrossed())){
                mobileBridge.moved(numStrands);
                unusedBridge.add(mobileBridge.getColor());
            }
        }
    }
    
    private void comprobeSpot(){
        int cont=0;
        while(cont<mapSpots.size()){
            int strand=spider.getCurrentStrand();
            cont=0;
            for (Spot s : mapSpots.values()){
                cont+=1;
                if (strand==s.getStrand() && (s instanceof Killer)){
                    spider.death();
                    cont=mapSpots.size();
                    break;
                }
                else if(strand==s.getStrand() && (s instanceof Bouncy)){
                    spider.jump(numStrands);
                    break;
                }
                else if(strand==s.getStrand() && (s instanceof Reverse)){
                    spiderWalk(false);
                    cont=mapSpots.size();
                    break;
                }
            }
        
        }
    }
    
    
    /**
     * Retrieves the spider's last path as an array of integers.
     *
     * This method retrieves the spider's last path, which consists of the sequence of strands
     * the spider traversed during its previous movement. It returns an array containing the
     * sequence of strands in the spider's last path. If the spider's last path is not empty,
     * it displays the path, copies the sequence of strands into an array, and sets the last
     * action flag to true. Otherwise, it sets the last action flag to false.
     *
     * @return An array of integers representing the spider's last path.
     */
    public int[] spiderLastPath(){
        int[] last = new int[lastPath.size()]; 
        lastAction = false; 
        
        if(lastPath.size() > 0){ 
            spider.showLastPath(); 
            
            for (int i = 0; i < lastPath.size(); i++) {
                last[i] = lastPath.get(i);
            }
            lastAction = true; 
        }
        return last; 
    }
    
    /**
     * Retrieves the unused bridges as an array of strings.
     *
     * This method retrieves the unused bridges on the spider web and returns them as
     * an array of strings. If there are unused bridges, it copies their colors into
     * the array and sets the last action flag to true. Otherwise, it sets the last
     * action flag to false.
     *
     * @return An array of strings representing the unused bridges on the spider web.
     */
    public String[] unusedBridge(){
        String[] unused = new String[unusedBridge.size()]; 
        lastAction = false; 
        
        if(unusedBridge.size() > 0){ 
            for (int i = 0; i < unusedBridge.size(); i++) {
                unused[i] = unusedBridge.get(i);
            }
            lastAction = true; 
        }
        return unused; 
    }

    
    /**
     * Retrieves an array of strings representing the colors of all bridges on the spider web.
     *
     * This method retrieves the colors of all bridges present on the spider web and returns
     * them as an array of strings. It retrieves the set of keys (bridge colors) from the
     * map of bridges, converts it into an array, and sets the last action flag to true if
     * there are bridges available. Otherwise, it sets the last action flag to false.
     *
     * @return An array of strings representing the colors of all bridges on the spider web.
     */
    public String[] bridges(){
        lastAction = false; 
        Set<String> keySet = mapBridge.keySet(); 
        String[] keysArray = keySet.toArray(new String[keySet.size()]);
        if (mapBridge.size() > 0){ 
            lastAction = true; 
        }
        return keysArray; 
    }
    
    /**
     * Retrieves an array of integers representing the strands connected by a specific bridge.
     *
     * This method retrieves the strands connected by the bridge with the specified color
     * and returns them as an array of integers. If the bridge exists in the map, it retrieves
     * the first and second strands connected by the bridge and sets the last action flag to true.
     * Otherwise, it sets the last action flag to false.
     *
     * @param color The color of the bridge for which strands are to be retrieved.
     * @return An array of integers representing the strands connected by the specified bridge.
     */
    public int[] bridge(String color){
        int[] strands = new int[2]; 
        lastAction = false; 
        if(mapBridge.containsKey(color)){ 
            Bridge bridge = mapBridge.get(color); 
            int first = bridge.getFirstStrand(); 
            strands[0] = first;
            int second = bridge.getSecondStrand(); 
            strands[1] = second;
            lastAction = true; 
        }
        return strands;
    }

    
    /**
     * Retrieves an array of strings representing the colors of all spots on the spider web.
     *
     * This method retrieves the colors of all spots present on the spider web and returns
     * them as an array of strings. It retrieves the set of keys (spot colors) from the
     * map of spots, converts it into an array, and sets the last action flag to true if
     * there are spots available. Otherwise, it sets the last action flag to false.
     *
     * @return An array of strings representing the colors of all spots on the spider web.
     */
    public String[] spots(){
        lastAction = false;
        Set<String> keySet = mapSpots.keySet(); 
        String[] keysArray = keySet.toArray(new String[keySet.size()]); 
        if(mapSpots.size() > 0){ 
            lastAction = true; 
        }
        return keysArray; 
    }
    
    /**
     * Retrieves the strand number of the spot with the specified color.
     *
     * This method retrieves the strand number of the spot with the specified color
     * and returns it as an integer. If the spot exists in the map, it retrieves
     * its strand number and sets the last action flag to true. Otherwise, it sets
     * the last action flag to false and returns -1.
     *
     * @param color The color of the spot for which the strand number is to be retrieved.
     * @return The strand number of the spot with the specified color, or -1 if not found.
     */
    public int spot(String color){
        lastAction = false; 
        if(mapSpots.containsKey(color)){
            Spot spot = mapSpots.get(color); 
            lastAction = true;
            return spot.getStrand(); 
        }
        return -1; 
    }
    
    /**
     * Finish the simulation.
     * 
     * This method finishes the simulation by making all elements invisible and
     * displaying a message indicating that the simulation has ended. It sets the
     * last action flag to true after completing the operation.
     */
    public void finish() {
        lastAction = false; 
        makeInvisible(); 
        lastAction = true; 
        JOptionPane.showMessageDialog(null, "The simulation has ended.");
    }
    
    /**
     * Checks if the last action was successful.
     *
     * This method returns a boolean indicating whether the last action performed
     * by the simulator was successful or not.
     *
     * @return True if the last action was successful, false otherwise.
     */
    public boolean ok(){
        return lastAction;
    }

}
