import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        
        web0 = new SpiderWeb(600, 10);
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
    
} 
