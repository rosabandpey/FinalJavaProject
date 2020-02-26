/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import entity.TestClassEntity;

/**
 *
 * @author Gorji
 */
public interface TestClassDAOInterface {
    
    public  void saveInDB(TestClassEntity testClass,String url,String un,String pw);
    
    
}
