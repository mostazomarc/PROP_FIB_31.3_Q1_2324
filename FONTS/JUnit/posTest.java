package JUnit;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


import Domini.*;


import java.util.*;


public class posTest {
    @Test
    public void comprobar_constructora(){
        pos p = new pos(0,1);
        assertEquals(0, p.x);
        assertEquals(1, p.y);
    }
}
