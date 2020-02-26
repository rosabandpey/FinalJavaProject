/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reflection;

import static com.oracle.jrockit.jfr.ContentType.StackTrace;
import entity.MethodEntity;
import entity.MethodList;
import entity.MethodsEntity;
import entity.TestClassEntity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.annotation.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import ir.rosa.test.NewClass;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import junit.framework.TestSuite ;
/**
 *
 * @author Gorji
 */
public class ClassTestReflection {

    public final String DIRECTORY = "src\\ir\\rosa\\test\\";
    Class<?> myClass = null;
    Object obj=null;
    Result result;
   // public List<MethodsEntity> methodList=new ArrayList<>();
    boolean passed;
    StringWriter sw=new StringWriter();
    String stacktrace;
    
   
    public void methodBefore(MethodEntity methodentity,MethodList methodList) {

        Method[] methods = myClass.getDeclaredMethods();
        
        for (Method m : methods) {

            Annotation annotations = m.getAnnotation(Before.class);
            if (annotations != null) {
                try {
                   
                    m.setAccessible(true);
                    m.invoke(obj);
                    methodentity.methodPassed();
                    passed=true;
                    
                } catch (Exception e) {
                    
                    e.printStackTrace(new PrintWriter(sw));
                    stacktrace=sw.toString();
                    methodentity.methodFailed();
                    passed=false;
                }
                methodList.methodList.add(new MethodsEntity(m.getName(), passed,stacktrace));
                
                methodentity.methodTotalRun();
            }
            }
        }
    

    public void methodAfter(MethodEntity methodentity,MethodList methodList) {

         Method[] methods = myClass.getDeclaredMethods();

        for (Method m : methods) {

            Annotation annotations = m.getAnnotation(After.class);
            
            if (annotations != null) {
                try {
               
                    m.setAccessible(true);
                    m.invoke(obj);
                    methodentity.methodPassed();
                    passed=true;
                   
                } catch (Exception e) {
                    
                    e.printStackTrace(new PrintWriter(sw));
                    stacktrace=sw.toString();
                    passed=false;
                    methodentity.methodFailed();
                }
                methodList.methodList.add(new MethodsEntity(m.getName(), passed,stacktrace));
                methodentity.methodTotalRun();
            }
            }
        }
    

    public void methodTest( MethodEntity methodentity,MethodList methodList) {

        Method[] methods = myClass.getDeclaredMethods();

        for (Method m : methods) {

            Annotation annotations = m.getAnnotation(Test.class);
           
            if (annotations != null) {
                try {
                
                    m.setAccessible(true);
                    m.invoke(obj);
                    
                } catch (Exception e) {
                    
                    e.printStackTrace(new PrintWriter(sw));
                    stacktrace=sw.toString();
                }
                
                    if (annotations == m.getAnnotation(Test.class))
            {
                Request request = Request.method(myClass, m.getName());
                Result result = new JUnitCore().run(request);
                
                if (result.wasSuccessful()){
                     passed=true;
                     methodentity.methodPassed();
                }
                else {
                    passed=false;
                    methodentity.methodFailed();
                }
                methodList.methodList.add(new MethodsEntity(m.getName(), passed,stacktrace));
                methodentity.methodTotalRun();
            }
            }

        }
    }

    public void resuletTest(){
        
        result=JUnitCore.runClasses(myClass);
        for (Failure failure : result.getFailures()) {							
         System.out.println(failure.toString());					
      }		
        int getCount=result.getRunCount();
        int getFailre=result.getFailureCount();
        System.out.println("RunCount" +"  "+getCount);
        System.out.println("FailureCount"+"  "+getFailre);
        
    }
    
    
    public void classLoad(File className) throws Exception {

        int len = 0;
        FileInputStream in = new FileInputStream(className);
        FileOutputStream out = new FileOutputStream(DIRECTORY + "UnitTest.class");
        while ((len = in.read()) != -1) {
            out.write(len);
        }
        in.close();
        out.close();
        File myfile = new File(DIRECTORY + "UnitTest.class");
        URLClassLoader classLoader = new URLClassLoader(new URL[]{myfile.toURI().toURL()}, Thread.currentThread().getContextClassLoader());
        Thread.sleep(10000);
        myClass = Class.forName("ir.rosa.test.UnitTest", true, classLoader);
        obj=myClass.newInstance();

    }

    public static void main(String[] args) {

    }
}
