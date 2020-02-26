/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.net.InetAddress;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;

/**
 *
 * @author Gorji
 */
@Entity
@Table(name="classtest")
public class TestClassEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="classname")
    private String className;
    @Column(name="classpath")
    private String classPath;
    @Column(name="clientIP")
    private String clientIP;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="clientDate")
    private java.util.Date clientDate;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public java.util.Date getClientDate() {
        return clientDate;
    }

    public void setClientDate(java.util.Date clientDate) {
        this.clientDate = clientDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
