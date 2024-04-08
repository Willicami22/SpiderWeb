import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Set;
import java.util.HashSet;

/**
 * The test class SpiderWebTest.
 *
 * @author  (William Hernandez and Nicolas Toro)
 * @version (a version number or a date)
 */
public class SpiderWebTest{
    
    private SpiderWeb web;
    private SpiderWeb web0;
    
    @Before
    public void before(){
        web = new SpiderWeb(10, 5, new int[][]{{100,2}, {200,5}});
        
        web0 = new SpiderWeb(60, 10);
    }
    
    @Test
    public void shouldCreateSpiderWeb(){
        SpiderWeb web1 = new SpiderWeb(20, 10);
        assertTrue(web1.ok());
        
        SpiderWeb web2 = new SpiderWeb(30, 5);
        assertTrue(web2.ok());
        
        SpiderWeb web4 = new SpiderWeb(20, 10, new int[][]{{50,3}, {200,20}});
        assertTrue(web4.ok());
        
        SpiderWeb web5 = new SpiderWeb(10, 5, new int[][]{{100,2}, {200,5}});
        assertTrue(web5.ok());
        
    }
    
    @Test
    public void shouldNotCreateSpiderWeb(){
        SpiderWeb web1 = new SpiderWeb(-20, 10);
        assertFalse(web1.ok());
        
        SpiderWeb web2 = new SpiderWeb(30, -5);
        assertFalse(web2.ok());
        
        SpiderWeb web3 = new SpiderWeb(0, 15);
        assertFalse(web3.ok());
        
        SpiderWeb web4 = new SpiderWeb(-20, 10, new int[][]{{50,3}, {200,20}});
        assertFalse(web4.ok());
        
        SpiderWeb web5 = new SpiderWeb(10, -5, new int[][]{{100,2}, {200,5}});
        assertFalse(web5.ok());
        
    }
    
    @Test
    public void shouldMakeVisible(){
        SpiderWeb web1 = new SpiderWeb(20, 10);
        web1.makeVisible();
        assertTrue(web1.ok());
        
        SpiderWeb web2 = new SpiderWeb(40, 5);
        web2.makeVisible();
        assertTrue(web2.ok());
        
        SpiderWeb web3 = new SpiderWeb(20, 10, new int[][]{{50,3}, {200,20}});
        web3.makeVisible();
        assertTrue(web3.ok());
        
        SpiderWeb web4 = new SpiderWeb(10, 5, new int[][]{{100,2}, {200,5}});
        web4.makeVisible();
        assertTrue(web4.ok());
        
    }
    
    @Test
    public void shouldNotMakeVisible(){
        SpiderWeb web1 = new SpiderWeb(20, 10);
        web1.makeVisible();
        web1.makeVisible();
        assertFalse(web1.ok());
        
        SpiderWeb web2 = new SpiderWeb(40, 5);
        web2.makeVisible();
        web2.makeVisible();
        assertFalse(web2.ok());
        
        SpiderWeb web3 = new SpiderWeb(20, 10, new int[][]{{50,3}, {200,20}});
        web3.makeVisible();
        web3.makeVisible();
        assertFalse(web3.ok());
        
        SpiderWeb web4 = new SpiderWeb(10, 5, new int[][]{{100,2}, {200,5}});
        web4.makeVisible();
        web4.makeVisible();
        assertFalse(web4.ok());
        
    }
    
    @Test
    public void shouldMakeInvisible(){
        SpiderWeb web1 = new SpiderWeb(20, 10);
        web1.makeInvisible();
        assertFalse(web1.ok());
        
        SpiderWeb web2 = new SpiderWeb(40, 5);
        web2.makeInvisible();
        assertFalse(web2.ok());
        
        SpiderWeb web3 = new SpiderWeb(20, 10, new int[][]{{50,3}, {200,20}});
        web3.makeInvisible();
        assertFalse(web3.ok());
        
        SpiderWeb web4 = new SpiderWeb(10, 5, new int[][]{{100,2}, {200,5}});
        web4.makeInvisible();
        assertFalse(web4.ok());
    }
    
    @Test
    public void shouldNotMakeInvisible(){
        SpiderWeb web1 = new SpiderWeb(20, 10);
        web1.makeInvisible();
        assertFalse(web1.ok());
        
        SpiderWeb web2 = new SpiderWeb(40, 5);
        web2.makeVisible();
        web2.makeInvisible();
        assertTrue(web2.ok());
        
    }
    
    @Test
    public void shouldAddBridge(){
        web0.addBridge("green", 100, 2);
        assertTrue(web.ok());
        
        web0.addBridge("red", 200, 5);
        assertTrue(web.ok());
        
    }
    
    @Test
    public void shouldNotAddBridge(){
        SpiderWeb web1 = new SpiderWeb(50, 10);
        web1.addBridge("green", 300, 2);
        assertFalse(web1.ok());
        
        SpiderWeb web2 = new SpiderWeb(50, 10);
        web2.addBridge("red", 200, 11);
        assertFalse(web2.ok());
        
    }
    
    @Test
    public void shouldAddSpot(){
        web0.addSpot("green", 3);
        assertTrue(web0.ok());
        
        web0.addSpot("red", 5);
        assertTrue(web.ok());
        
    }
    
    @Test
    public void shouldNotAddSpot(){
        web0.addSpot("green", -3);
        assertFalse(web0.ok());
        
        web.addSpot("red", 5);
        web.addSpot("red", 7);
        assertFalse(web.ok());
        
        web.addSpot("yellow", 15);
        assertFalse(web.ok());
        
    }
    
    @Test
    public void shouldAddStrand(){
        web.addStrand();
        assertTrue(web.ok());
        
        web0.addStrand();
        assertTrue(web0.ok());
    }
    
    @Test
    public void shouldEnlarge(){
        web.enlarge(60);
        assertTrue(web.ok());
        
        web0.enlarge(-10);
        assertTrue(web0.ok());
    }
    
    @Test
    public void shouldRelocateBridge(){
        web0.addBridge("blue", 100, 1);
        web0.relocateBridge("blue", 7);
        assertTrue(web0.ok());
        
        web0.addBridge("yellow", 200, 5);
        web0.relocateBridge("yellow", 10);
        assertTrue(web0.ok());
        
    }
    
    @Test
    public void shouldNotRelocateBridge(){
        web0.relocateBridge("red", 7);
        assertFalse(web0.ok());
        
        web.relocateBridge("red", 150);
        assertFalse(web0.ok());
        
    }
    
    @Test
    public void shouldDelBridge(){
        web0.addBridge("blue", 100, 7);
        web0.delBridge("blue");
        assertTrue(web0.ok());
        
        web.addBridge("red", 100, 7);
        web.delBridge("red");
        assertTrue(web.ok());
    }
    
    @Test
    public void shouldNotDelBridge(){
        web0.delBridge("blue");
        assertFalse(web0.ok());
        
        web0.delBridge("pink");
        assertFalse(web0.ok());
    }
    
    @Test
    public void shouldDelSpot(){
        web0.addSpot("blue", 7);
        web0.delSpot("blue");
        assertTrue(web0.ok());
        
        web.addSpot("red", 10);
        web.delSpot("red");
        assertTrue(web.ok());
    }
    
    @Test
    public void shouldNotDelSpot(){
        web0.delSpot("blue");
        assertFalse(web0.ok());
        
    }
    
    @Test
    public void spiderShouldSit(){
        web.spiderSit(7);
        assertTrue(web.ok());
        
        web0.spiderSit(6);
        assertTrue(web0.ok());
        
    }
    
    @Test
    public void spiderShouldNotSit(){
        web.spiderSit(-1);
        assertFalse(web.ok());
        
        web0.spiderSit(15);
        assertFalse(web0.ok());
        
    }
    
    @Test
    public void shouldReturnBridgeColor(){
        web0.addBridge("red", 100, 7);
        web0.addBridge("blue", 200, 6);
        web0.bridges();
        assertTrue(web0.ok());
        
        web.bridges();
        assertTrue(web.ok());
        
    }
    
    @Test
    public void shouldNotReturnBridgeColor(){
        web0.bridges();
        assertFalse(web0.ok());
        
        SpiderWeb web1 = new SpiderWeb(70, 15);
        web1.bridges();
        assertFalse(web1.ok());
        
    }
    
    @Test
    public void shouldReturnBridgeStrands(){
        web.addBridge("pink", 200, 3);
        web.bridge("pink");
        assertTrue(web.ok());
        
        web0.addBridge("blue", 130, 6);
        web0.bridge("blue");
        assertTrue(web0.ok());
        
    }
    
    @Test
    public void shouldNotReturnBridgeStrands(){
        web0.bridge("pink");
        assertFalse(web0.ok());
        
        web0.addBridge("blue", 130, 6);
        web0.bridge("orange");
        assertFalse(web0.ok());
        
    }
    
    @Test
    public void shouldReturnSpotsColor(){
        web.spots();
        assertTrue(web.ok());
        
        web0.addSpot("green", 7);
        web0.spots();
        assertTrue(web0.ok());
        
    }
    
    @Test
    public void shouldNotReturnSpotsColor(){
        web0.spots();
        assertFalse(web0.ok());
        
        SpiderWeb web1 = new SpiderWeb(20, 20);
        web1.spots();
        assertFalse(web1.ok());
        
    }
    
    @Test
    public void shouldReturnSpotsStrand(){
        web.addSpot("pink", 8);
        web.spot("pink");
        assertTrue(web.ok());
        
        web0.addSpot("blue", 10);
        web0.spot("blue");
        assertTrue(web0.ok());
        
    }
    
    @Test
    public void shouldNotReturnSpotsStrand(){
        web0.spot("blue");
        assertFalse(web0.ok());
        
        SpiderWeb web1 = new SpiderWeb(20, 20);
        web1.spot("orange");
        assertFalse(web1.ok());
        
    }
    
    @Test
    public void spiderShouldWalk(){
        web.spiderSit(2);
        web.spiderWalk(true);
        assertTrue(web.ok());
        
        web0.spiderSit(10);
        web.spiderWalk(true);
        web.spiderWalk(false);
        assertTrue(web.ok());
        
    }
    
    @Test
    public void spiderShouldNotWalk(){
        web0.spiderSit(10);
        web0.spiderWalk(false);
        assertFalse(web0.ok());
        
        web.spiderWalk(true);
        assertFalse(web.ok());
        
    }
    
    @Test
    public void shouldShowSpiderLastPath(){
        web.spiderSit(2);
        web.spiderWalk(true);
        web.spiderLastPath();
        assertTrue(web.ok());
        
        web.spiderSit(9);
        web.spiderWalk(true);
        web.spiderLastPath();
        assertTrue(web.ok());
        
    }
    
    @Test
    public void shouldNotShowSpiderLastPath(){
        
        web.spiderLastPath();
        assertFalse(web.ok());
        
        web0.spiderSit(2);
        web0.spiderLastPath();
        assertFalse(web0.ok());
        
    }
    
    @Test
    public void shouldShowUnusedBridges(){
        web.spiderSit(2);
        web.spiderWalk(true);
        web.unusedBridge();
        assertTrue(web.ok());
        
        web.spiderSit(9);
        web.spiderWalk(true);
        web.spiderWalk(false);
        web.unusedBridge();
        assertTrue(web.ok());
        
    }
    
    @Test
    public void shouldNotShowUnusedBridges(){
        web0.spiderSit(2);
        web0.spiderWalk(true);
        web0.unusedBridge();
        assertFalse(web0.ok());
        
        web.spiderSit(9);
        web.unusedBridge();
        assertFalse(web.ok());
        
    }
} 
