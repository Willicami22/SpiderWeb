import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Set;
import java.util.HashSet;

/**
 * The test class SpiderATest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SpiderATest
{
    
    private SpiderWeb spiderweb;
    private SpiderWeb web;
    
    @Before
    public void Before(){
         // Se crea una telaraña con 10 hebras y de 300 de radio
        spiderweb = new SpiderWeb(60, 10);
        
        // Se crea una telaraña con 7 hebras, spot en 6 y 5 puentes
        web= new SpiderWeb(7,6,new int[][]{{50,1},{100,3},{150,3},{200,7},{250,5}});

        
    }
    
    @Test
    public void spiderwebMustWork() {
        // Se hace visible para comprobar su funcionamiento
        spiderweb.makeVisible();
        assertTrue(spiderweb.ok());
        
        // Se agrega una hebra a la spiderWeb
        spiderweb.addStrand();
        assertTrue(spiderweb.ok());
        
        // Se intenta caminar a la araña, debería fallar porque no se ha sentado
        spiderweb.spiderWalk(true);
        assertFalse(spiderweb.ok());
        
        // Se crea un puente y un spot normales que deberían funcionar
        spiderweb.addBridge("green", 200, 2);
        assertTrue(spiderweb.ok());
        spiderweb.addSpot("orange", 4);
        assertTrue(spiderweb.ok());
        
        // Se crean puentes y spots que deberían fallar 
        spiderweb.addBridge("blue", 200, 2); // Ya existe un puente en la posición
        assertFalse(spiderweb.ok());

        spiderweb.addBridge("green", 270, 6); // Ya existe un puente de ese color
        assertFalse(spiderweb.ok());
        
        spiderweb.addBridge("orange", 200, 3); // No pueden existir dos puentes consecutivos
        assertFalse(spiderweb.ok());
        
        spiderweb.addSpot("orange", 5); // No pueden existir dos spots del mismo color
        assertFalse(spiderweb.ok());
        
        spiderweb.addSpot("green", 4);
        assertFalse(spiderweb.ok()); // No se puede agregar donde ya hay un spot
        
        spiderweb.addBridge("cyan", 350, 7); // No se pueden crear puentes por encima de la spiderWeb
        assertFalse(spiderweb.ok());
        
        spiderweb.addBridge("cyan", 350, 7); // No se pueden crear puentes en hebras que no existen
        assertFalse(spiderweb.ok());
        
        spiderweb.addSpot("gray", 12); // No se pueden crear spots en hebras que no existen
        assertFalse(spiderweb.ok());
    
        
        //Deberia alargarse
        spiderweb.enlarge(30);
        assertTrue(spiderweb.ok());
        
        //Deberia Mostar los puentes
        assertNotNull(spiderweb.bridges());
        
        //deberia Mostrar los spots
        assertNotNull(spiderweb.spots());
        
        //Deberia mostrar los puentes no usados
        
        assertNotNull(spiderweb.unusedBridge());
        
        //Deberia mostrar el puente
        
        assertNotNull(spiderweb.bridge("green"));
        
        //deberia mostrar el spot
        
        assertNotNull(spiderweb.spot("orange"));
        
        //Deberia terminar la simulacion
        
        spiderweb.finish();
        assertTrue(spiderweb.ok());

        
    }
    
    @Test
    public void theSpiderMustWalkCorrectly(){
        // Se hace visible para comprobar su funcionamiento
        web.makeVisible();
        assertTrue(web.ok());
        
        //No deberia caminar si la araña no esta sentada en una hebra
        web.spiderWalk(true);
        assertFalse(web.ok());
        
        //Se deberia sentar correctamente
        web.spiderSit(4);
        assertTrue(web.ok());
        
        // No se deberia sentar por que la hebra no existe
        web.spiderSit(8);
        assertFalse(web.ok());  
        
        //La araña deberia caminar y quedarse en la hebra 4
        
        web.spiderWalk(true);
        assertTrue(web.ok());
        
        //La araña no puede volver a caminar hacia adelante
        
        web.spiderWalk(true);
        assertFalse(web.ok());
        
        //La araña se devuelve y queda en el centro
        
        web.spiderWalk(false);
        assertTrue(web.ok());
        
        //La araña debera sentarse en la hebra 5,caminar y llegar al spot
        
        web.spiderSit(5);  //Se sienta
        assertTrue(web.ok());
        
        web.spiderWalk(true); //camina hasta el spot
        assertTrue(web.ok());
        
        //Se intenta sentar a la araña a una hebra que no existe
        
        web.spiderSit(8);
        assertFalse(web.ok());
        
        //Se sienta la araña en cualquier hebra
        
        web.spiderSit(2);
        assertTrue(web.ok());
        
        //intenta caminar para atras
        
        web.spiderWalk(false);
        assertFalse(web.ok());
        
        //Se hace invisivle
        web.makeInvisible();
        assertTrue(web.ok());
    }
    
    @Test
    public void spiderwebMustWorkWithNewTypes() {
        // Se hace visible para comprobar su funcionamiento
        spiderweb.makeVisible();
        assertTrue(spiderweb.ok());
        
        
        // Se intenta caminar a la araña, debería fallar porque no se ha sentado
        spiderweb.spiderWalk(true);
        assertFalse(spiderweb.ok());
        
        //Se sienta a la araña en la hebra 1
        spiderweb.spiderSit(1);
        assertTrue(spiderweb.ok());
        
        // Se crea un puente tipo weak en la 1, un puente transformer en la 2
        spiderweb.addBridge("weak","green", 100, 1);
        assertTrue(spiderweb.ok());
        spiderweb.addBridge("mobile","orange", 120,2);
        assertTrue(spiderweb.ok());
        
        //Se hace caminar la araña
        spiderweb.spiderWalk(true);
        assertTrue(spiderweb.ok());
        
        //Se comprueban los efectos de que halla caminado sobre estos
        assertEquals(spiderweb.bridges().length,1);
        assertEquals(spiderweb.bridge("orange")[0],3);
        assertEquals(spiderweb.bridge("orange")[1],4);
        
        //Se vuelve a sentar en 4
        spiderweb.spiderSit(4);
        assertTrue(spiderweb.ok());
        
        //Se crea un puente en 4 transformer, se elimina para comprobar el spot
        
        spiderweb.addBridge("transformer","red", 100, 4);
        assertTrue(spiderweb.ok());
        spiderweb.delBridge("red");
        assertEquals(spiderweb.spots().length,1);// Se comprueba que los spots no sean vacio
        
        //Se crea un puente en 4 fixed para comprobar que no se elimina
        
        spiderweb.addBridge("fixed","blue", 200, 4);
        assertTrue(spiderweb.ok());
        spiderweb.delBridge("blue");
        assertEquals(spiderweb.bridges().length,2);
        
        
        //Se termina la simulacion
        spiderweb.finish();
        assertTrue(spiderweb.ok());

        
    }
}

