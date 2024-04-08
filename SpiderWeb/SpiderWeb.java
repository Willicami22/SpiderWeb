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
     * Write a description of class Telaraña here.
     * 
     * William Hernandez y Nicolas Toro
     * @version 1.0
     */
    public class SpiderWeb
    {
    private final double angle=360;
    private double diameter;
    private int numStrands;
    private Spider spider;
    private ArrayList<Strand > strands; 
    private int xCenter;
    private int yCenter;
    private String Color;
    private Map<String, Spot > mapSpots;
    private Map<String, Bridge > mapBridge;
    private boolean isVisible;
    private boolean lastAction;
    
    public SpiderWeb(int diameter, int numStrands){
        lastAction=false;
        if(diameter > 0 && numStrands > 0){
            this.diameter=diameter*10;
            strands = new ArrayList<>();
            mapSpots= new HashMap<>();
            mapBridge=new HashMap<>();
            spider= new Spider();
            
            this.numStrands=numStrands;
            xCenter=515;
            yCenter=413;
            Color="black"; 
            isVisible=false;
            lastAction=true;
            
            double angle = this.angle/numStrands;
            
            for (int i = 0; i<numStrands; i++) {
                double angle1 =angle*i;
                
                Strand strand = new Strand(angle1,(this.diameter)/2);
                
                strands.add(strand);
                
            }
        }

    }

    public SpiderWeb(int numStrands,int favorite, int bridges[][]){
        lastAction = false;
        if(numStrands > 0 && favorite > 0){
            diameter = 600;
            strands = new ArrayList<>();
            mapSpots= new HashMap<>();
            mapBridge=new HashMap<>();
            spider= new Spider();
            
            this.numStrands=numStrands;
            xCenter=515;
            yCenter=413;
            Color="black";
            isVisible=false;
            lastAction=true;
            
            double angle=this.angle/numStrands;
            
            for (int i = 0; i<numStrands; i++) {
                double angle1 =angle*i;
                
                Strand strand = new Strand(angle1,diameter/2);
                 
                strands.add(strand);
                
            }
            
            ArrayList<String> colors = new ArrayList<>();
            colors.add("red"); colors.add("black"); colors.add("blue"); colors.add("yellow"); colors.add("green");
            colors.add("magenta"); colors.add("pink"); colors.add("orange");colors.add("gray"); colors.add("cyan");
            
            for (int[] b : bridges){
                
    
                Random random = new Random(); int randomIndex = random.nextInt(colors.size()); String color = colors.get(randomIndex);
                colors.remove(randomIndex);
                addBridge(color, b[0],b[1]);
                
            }
            
            Random random = new Random(); int randomIndex = random.nextInt(colors.size()); String color = colors.get(randomIndex);
            colors.remove(randomIndex);
            addSpot(color, favorite);
        }
    }

        public void makeVisible(){
        lastAction=false;
        if(!isVisible){
            for (Strand s : strands) {
                s.makeVisible();

            }
        
            spider.makeVisible();
        
            for(Spot s : mapSpots.values()){
                s.makeVisible();
            }
        
            for(Bridge b : mapBridge.values()){
                b.makeVisible();
            }
            isVisible=true;
            lastAction=true;
        }  
    }
    
    public void makeInvisible(){
        lastAction=false;
        if(isVisible) {
            for (Strand s : strands) {
                s.makeInvisible();

            }
        
            spider.makeInvisible();
        
            for(Spot s : mapSpots.values()){
                s.makeInvisible();
            }
        
            for(Bridge b : mapBridge.values()){
                b.makeInvisible();
            }
            isVisible = false;
            lastAction = true;
        }
        
    }

 
    public void addBridge(String color, int distance, int firstStrand){
        
        lastAction=false;
        if(!mapBridge.containsKey(color) && distance<(diameter/2) && firstStrand>0 && firstStrand<=numStrands){
            
            double angle=this.angle/numStrands;
                
            int secondStrand=firstStrand+1;
            if(firstStrand==numStrands){
                secondStrand=1;
            }
                
            double angle1 = Math.toRadians((firstStrand-1)*angle);
            double angle2 = Math.toRadians((secondStrand-1)*angle);
                
            double xStart= xCenter+(distance*Math.cos(-angle1));
            double yStart= yCenter+(distance*Math.sin(-angle1));
            double xEnd= xCenter+(distance*Math.cos(-angle2));
            double yEnd= yCenter+(distance*Math.sin(-angle2));
            
            
            Bridge bridge = new Bridge(xStart,yStart,xEnd,yEnd, firstStrand,secondStrand,color, distance);
            
            if(isVisible){
                bridge.makeVisible();
            }
            mapBridge.put(color, bridge);
            lastAction=true;
        }
        
    }
    
    
    public void addSpot(String color, int favorite){
        
        lastAction=false;
        if (!mapSpots.containsKey(color) && favorite>0 && favorite<= numStrands){
            double angle=this.angle/numStrands;
            double angle1 = Math.toRadians((favorite-1)*angle);
            
            double x = xCenter+(diameter/2)*Math.cos(-angle1);
            double y = yCenter+(diameter/2)*Math.sin(-angle1);
            
            Spot spot= new Spot(x, y, favorite, color);
            mapSpots.put(color, spot);
            
            if(isVisible){  
                spot.makeVisible();
            }
            lastAction=true;
        }
    }
        
    public void addStrand(){
        
        boolean draw= false;
        if(isVisible){
            makeInvisible();
            draw=true;
        }
        numStrands=numStrands+1;
        double angle=this.angle/numStrands; 
        for (int i = 0; i<(numStrands-1); i++) {
            double angle1 =angle*i;
            strands.get(i).changeAngle(angle1);
        }
            
        Strand strand = new Strand(angle*(numStrands-1),diameter/2);
        strands.add(strand);
            
        for (Spot s : mapSpots.values()){
                
            double angle1 = Math.toRadians(((s.getStrand()-1)*angle));
            
            double x = xCenter+(diameter/2)*Math.cos(-angle1);
            double y = yCenter+(diameter/2)*Math.sin(-angle1);
                
            s.changePosition(x,y);
            }
            
        for (Bridge b : mapBridge.values()){
            int firstStrand=b.getFirstStrand();
            int secondStrand=b.getSecondStrand();
            double distance=b.getDistance();
            
            
            double angle1 = Math.toRadians((firstStrand-1)*angle);
            double angle2 = Math.toRadians((secondStrand-1)*angle);
            
            double xStart= xCenter+(distance*Math.cos(-angle1));
            double yStart= yCenter+(distance*Math.sin(-angle1));
            double xEnd= xCenter+(distance*Math.cos(-angle2));
            double yEnd= yCenter+(distance*Math.sin(-angle2));
                
            b.changePoints( xStart, yStart, xEnd, yEnd);
        }
        
        if(draw){
            makeVisible();
        }
        lastAction=true;
    }
    
    
    public void enlarge(double percentage){
        
        boolean draw= false;
        if(isVisible){
            makeInvisible();
            draw=true;
        }
        
        diameter+= (diameter*(percentage/100));

        for(Strand s : strands){
            s.changeRadio(diameter/2); 
        }
        
        for(Spot m : mapSpots.values()){
            
            int Strand = m.getStrand();
            double angle=this.angle/numStrands;
            double angle1 = Math.toRadians((Strand-1)*angle);
            
            double x = xCenter+(diameter/2)*Math.cos(-angle1);
            double y = yCenter+(diameter/2)*Math.sin(-angle1);
            
            m.changePosition(x,y);
            
        }
        
        if(draw){
            makeVisible();
        }
        
        lastAction=true;
    }
      
    
    public void relocateBridge(String color, double distance){
        lastAction=false;
        if(distance < diameter/2 && mapBridge.containsKey(color)){
            double angle=this.angle/numStrands;
            Bridge bridge=mapBridge.get(color); 
                
            int firstStrand=bridge.getFirstStrand();
            int secondStrand=bridge.getSecondStrand();
                    
            double angle1 = Math.toRadians((firstStrand-1)*angle);
            double angle2 = Math.toRadians((secondStrand-1)*angle);
                    
            double xStart= xCenter+(distance*Math.cos(-angle1));
            double yStart= yCenter+(distance*Math.sin(-angle1));
            double xEnd= xCenter+(distance*Math.cos(-angle2));
            double yEnd= yCenter+(distance*Math.sin(-angle2));
                        
            bridge.changePoints( xStart, yStart, xEnd, yEnd);
            bridge.changeDistance(distance);
            if(isVisible){
                bridge.makeInvisible();
                bridge.makeVisible();
            }
            lastAction=true;
        }
    }
    
        
    public void delBridge(String color){
        lastAction=false;
        if(mapBridge.containsKey(color)){
            Bridge b = mapBridge.remove(color); 
            if(isVisible){
                b.makeInvisible();
                }
            lastAction=true;
        }
    }
    
    public void delSpot(String color){
        lastAction=false;
        if(mapSpots.containsKey(color)){
            Spot s= mapSpots.remove(color); 
            if(isVisible){
                s.makeInvisible();
            }
            lastAction=true;
        }
    }
    
    public void spiderSit(int strand){
        lastAction=false;
        if (strand>0 && strand<=numStrands){ 
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            double angle=this.angle/numStrands;
=======
            spider.makeInvisible();
            double angle=this.angle     /numStrands;
>>>>>>> 37754b2cc5d3cce2d8e21dc7e86ad66f4555264e
=======
            spider.makeInvisible();
            double angle=this.angle     /numStrands;
>>>>>>> 37754b2cc5d3cce2d8e21dc7e86ad66f4555264e
=======
            spider.makeInvisible();
            double angle=this.angle     /numStrands;
>>>>>>> 37754b2cc5d3cce2d8e21dc7e86ad66f4555264e
            double angle1 = Math.toRadians((strand-1)*angle);
        
            spider.returnToCenter();
            spider.locateSpider(angle1);
        
            spider.changeCurrentStrand(strand);
            lastAction=true;    
            
            if(isVisible){
                spider.makeVisible();
            }
        
        }
    }
    
    public void spiderWalk(boolean advance){
            lastAction=false;
            double distance= spider.getDistanceToCenter();
            double angle= this.angle/numStrands;
            if (advance && distance==0){
                int cont=0;
                while(cont<mapBridge.size()){  
                    cont=0;
                    for (Bridge b : mapBridge.values()){
                        
                        int strand=spider.getCurrentStrand();
                        double angle1 = Math.toRadians((strand-1)*angle);
                            
                            if((b.getFirstStrand()==strand || b.getSecondStrand()==strand) && b.getDistance()>distance ){
                                spider.moveSpider(angle1, b.getDistance() - distance);
                                
                                if (b.getFirstStrand()==strand){
                                    
                                    double newX= b.getxEnd();
                                    double newY= b.getyEnd();
                                    spider.transportSpider(Math.toRadians((strand)*angle),newX,newY);
                                    spider.changeCurrentStrand(b.getSecondStrand());
                
                                }
                                else{
                                    double newX= b.getxStart();
                                    double newY= b.getyStart();
                                    spider.transportSpider(Math.toRadians((strand-2)*angle),newX,newY);
                                    spider.changeCurrentStrand(b.getFirstStrand());
                                    
                                }
                                
                                distance = b.getDistance();
                                break;
                                    
                            }
                        cont++;
                        
                    }
                }
                
                spider.locateSpider(Math.toRadians((spider.getCurrentStrand()-1)*angle));
                spider.moveSpider(Math.toRadians((spider.getCurrentStrand()-1)*angle), (diameter/2) - distance);
                spider.changeDistanceToCenter(diameter/2);
                spider.locateSpider(Math.toRadians((spider.getCurrentStrand()-1)*angle)+Math.PI);
                lastAction=true;
                
            }
            else if(!advance && distance == (diameter/2)){
                int cont=0;
                while(cont<mapBridge.size()){  
                    cont=0;
                    for (Bridge b : mapBridge.values()){
                        
                        int strand=spider.getCurrentStrand();
                        double angle1 = Math.toRadians((strand-1)*angle)+Math.PI;
                            
                            if((b.getFirstStrand()==strand || b.getSecondStrand()==strand) && b.getDistance()<distance ){
                                spider.moveSpider(angle1, b.getDistance() - distance);
                                
                                if (b.getFirstStrand()==strand){
                                    
                                    double newX= b.getxEnd();
                                    double newY= b.getyEnd();
                                    spider.transportSpider(Math.toRadians((strand)*angle),newX,newY);
                                    spider.changeCurrentStrand(b.getSecondStrand());
                
                                }
                                else{
                                    double newX= b.getxStart();
                                    double newY= b.getyStart();
                                    spider.transportSpider(Math.toRadians((strand-2)*angle),newX,newY);
                                    spider.changeCurrentStrand(b.getFirstStrand());
                                    
                                }
                                
                                distance = b.getDistance();
                                break;
                                    
                            }
                        cont++;
                        
                    }
                }
                
                spider.locateSpider(Math.toRadians((spider.getCurrentStrand()-1)*angle)+Math.PI);
                spider.moveSpider(Math.toRadians((spider.getCurrentStrand()-1)*angle)+Math.PI, distance);
                spider.returnToCenter();
                spider.locateSpider(Math.toRadians((spider.getCurrentStrand()-1)*angle));
                lastAction=true;
                
            }
            
        }
    
    ///public int[] spiderLasthPath(){
        
        
    ///}
    
    ///public String[] unusedBridge(){
        
    ///}
    
    public String[] bridges(){
        lastAction=false;
        Set<String> keySet = mapBridge.keySet();
        String[] keysArray = keySet.toArray(new String[keySet.size()]);
        if (mapBridge.size()>0){

            lastAction=true;
            
        }
        return keysArray;
        
    }
    
    public int[] bridge(String color){
        int[] strands = new int[2];
        lastAction=false;
        if(mapBridge.containsKey(color)){
            Bridge bridge=mapBridge.get(color);
            int first= bridge.getFirstStrand();
            strands[0]=first;
            int second=bridge.getSecondStrand();
            strands[1]=second;
            lastAction=true;
        }
        return strands;
    }
    
    public String[] spots(){
        lastAction=false;
        Set<String> keySet = mapSpots.keySet();
        String[] keysArray = keySet.toArray(new String[keySet.size()]);
        if(mapSpots.size()>0){
            lastAction=true;
        }
        return keysArray;
        
    }
    
    public int spot(String color){
        lastAction=false;
        if(mapSpots.containsKey(color)){
            Spot spot= mapSpots.get(color);
            lastAction=true;
            return spot.getStrand();
        }
        return -1;
    }
    
    /**
     *Finish the simulator  
     */
    public void finish() {
        lastAction = false;
        makeInvisible();
        lastAction = true;    
        JOptionPane.showMessageDialog(null, "The simulation has ended.");
    }
    
    public boolean ok(){
        return lastAction;
    }
}
