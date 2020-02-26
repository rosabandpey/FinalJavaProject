/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author m.bandpey
 */
public class MethodsEntity   {
    
    
   
    private String nameMethod;
    private Boolean pass;
    private String reportStackTrace;
    
    
    public MethodsEntity(){
        
    }

    public MethodsEntity(String nameMethod,Boolean pass,String reportStackTrace){
         
       this.nameMethod=nameMethod;
       this.pass=pass;
       this.reportStackTrace=reportStackTrace;
         
    }
  
    
    public String getNameMethod() {
        return nameMethod;
    }

    public void setNameMethod(String nameMethod) {
        this.nameMethod = nameMethod;
    }

    public Boolean isPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public String getReportMethod() {
        return reportStackTrace;
    }

    public void setReportMethod(String reportStackTrace) {
        this.reportStackTrace = reportStackTrace;
    }
    
    
    
    
    
    
}
