/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import entity.TestClassEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Gorji
 */
public class DaoMysqlImpl implements TestClassDAOInterface{
    
        public  void saveInDB (TestClassEntity testClass,String url,String un,String pw){
    
            
            Connection con=null;
            PreparedStatement stm=null;
            String sql="INSERT INTO classtest (classname,classpath,clientIP,clientDate) Values (?,?,?,CURRENT_TIMESTAMP)";
            try{
            con=DriverManager.getConnection(url, un, pw);
            stm=con.prepareStatement(sql);
            stm.setString(1, testClass.getClassName());
            stm.setString(2, testClass.getClassPath());
            stm.setString(3, testClass.getClientIP().toString());
            stm.executeUpdate();
            System.out.println("File Saved Successfully");
            testClass.getClassPath();
            
            }catch(Exception e)
            {
                e.printStackTrace();
            }finally{
            try{
            con.close();
            stm.close();}catch(SQLException e){
                e.printStackTrace();
            }
        }
        
}

    
    
}
