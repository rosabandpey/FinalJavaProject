/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Gorji
 */
public class Property {
    
   static HashMap<String,String> map=new HashMap<String,String>();
    
    public void loadProperty(){
        String file="src//db//config.properties";
        try {
        BufferedReader reader=new BufferedReader(new FileReader(file));
        String line;
        while((line=reader.readLine())!=null)
        {
            Scanner in=new Scanner(line).useDelimiter("=");
            String key=in.next();
            String value=in.next();
            map.put(key,value);
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static String getMap(String key)
    {
        return (String) map.get(key);
    }
    
}
