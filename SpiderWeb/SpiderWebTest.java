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
    
    @Before
    public void before(){
        SpiderWeb web = new SpiderWeb(10, 5, new int[][]{{100,2}, {200,5}});
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
        
        SpiderWeb web4 = new SpiderWeb(20, 10, new int[][]{{50,3}, {200,20}});
        assertFalse(web4.ok());
        
        SpiderWeb web5 = new SpiderWeb(10, 5, new int[][]{{100,2}, {200,5}});
        assertFalse(web5.ok());
        
        SpiderWeb web6 = new SpiderWeb(10, 5, new int[][]{{100,2}, {200,5}});
        assertFalse(web6.ok());
        
    }
} 
