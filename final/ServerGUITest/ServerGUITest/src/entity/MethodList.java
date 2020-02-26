/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author m.bandpey
 */
@XmlRootElement
public class MethodList implements java.io.Serializable{
    
    
  private static final long serialVersionUID = 1L;
    
  public  List<MethodsEntity> methodList =new ArrayList<>();
    
  
    
    
}
