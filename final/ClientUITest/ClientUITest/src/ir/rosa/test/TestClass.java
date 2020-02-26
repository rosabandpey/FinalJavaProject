package ir.rosa.test;

import java.util.*;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TestClass {

    private List list;    
    private int a=0;

    @Before
    public void initialize() {
        list = new ArrayList();
        a=2;
    }
    
    @Test
    public void size() {
       assertTrue(list.size() == 0);
    }
    
    @Test
    public void add() {
        list.add("Name");
    }
}