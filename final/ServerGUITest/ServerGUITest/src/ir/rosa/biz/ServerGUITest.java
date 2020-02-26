/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.rosa.biz;

import db.DaoFactory;
import db.DaoMysqlImpl;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author m.bandpey
 */
public class ServerGUITest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
   
        System.out.println("server is waitig");
        ServerSocket listener=new ServerSocket(8085);
        
        while (true)
        {
            Socket socket=listener.accept();
            System.out.println("Server is Listening");
            new  ClientHandeler(socket).start();
        }
        
    }
    
}
