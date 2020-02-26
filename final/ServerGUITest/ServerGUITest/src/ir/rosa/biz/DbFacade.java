/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.rosa.biz;

import entity.TestClassEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author m.bandpey
 */
public class DbFacade {
    
    private EntityManager em;
    
    public DbFacade(){
        
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("myJPA");
        this.em = emf.createEntityManager();
        
    }
    
    
    
    public void createTestClassEntity(TestClassEntity testclass)
    
    {
        em.getTransaction().begin();
        em.persist(testclass);
        em.getTransaction().commit();
    }
    
    
    
    
}
