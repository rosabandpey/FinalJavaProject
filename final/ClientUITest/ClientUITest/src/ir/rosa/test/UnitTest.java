/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.rosa.test;


import java.awt.List;
import java.lang.reflect.Method;
import java.util.ArrayList;
import junit.framework.TestResult;
import org.junit.Assert;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *
 * @author m.bandpey
 */
public class UnitTest extends TestResult {

    ArrayList list;
    static int a,b;
    static int sum;
    int my=4;
    int yours=5;
    int h;

    @Before
    public void initialize() {
        a=2;
        b=2;
    }

    public static int sum(int a,int b){
        
        sum = a + b;
        return sum;
    }
    
    
    @Test
    public void testMethod() {
        
        if (a==0 & b==0)
        {
            fail("failed a and b must not be null");
        }
        try {
            assertEquals("true",4, sum(a,b));
            System.out.println("Equal");
        } catch (AssertionError e) {
            System.out.println("Not Equal");
            throw e;
        }
    }
    
    @Test
    public void testfailed() {
        
        try {
            assertEquals("true",3, sum(a,b));
        } catch (AssertionError e) {
            System.out.println("Not Equal");
            throw e;
        }
    }

    @Test
    public void testfailed2() {
        
        
            assertTrue(my==yours);
            System.out.println(my +"is not equal to"+"  "+yours);
          
    }
    
    
    public void add() {
         h=5;
         System.out.println("h value is    "+h);
    }
    
    public void delete(){
        list.add(a);
    }

}
