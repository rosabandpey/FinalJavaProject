/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.rosa.biz;

import com.google.gson.Gson;
import db.DaoFactory;
import db.Property;
import db.TestClassDAOInterface;
import entity.MethodEntity;
import entity.MethodList;
import entity.MethodsEntity;
import entity.TestClassEntity;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import static org.eclipse.persistence.jpa.jpql.parser.Expression.CURRENT_TIMESTAMP;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import reflection.ClassTestReflection;
import org.junit.*;

/**
 *
 * @author m.bandpey
 */
public class ClientHandeler extends Thread {

    Socket socket = null;
    public final String PATH = "src\\ir\\rosa\\test\\";
    String fileName = null;
    int len;
    InputStream in = null;
    OutputStream out = null;
    byte[] block = new byte[1024];
    TestClassEntity testclass = new TestClassEntity();
    Property pm = new Property();
    TestClassDAOInterface TCI = DaoFactory.daoFactory("mysql");
    ClassTestReflection reflection = new ClassTestReflection();
    Result result;
    MethodEntity methodentity = new MethodEntity();
    MethodsEntity methodsentity = new MethodsEntity();
    MethodList methodList = new MethodList();
    Socket sockReverse = null;
    JSONArray list;
    File fileReader;

    ClientHandeler(Socket socket) {
        this.socket = socket;
    }

    public void saveClassFromClient() {

        // UUID uuid = UUID.randomUUID();
        // fileName = MessageFormat.format("TestClass{0}.class", uuid);
        try {
            InputStream in2 = socket.getInputStream();
            OutputStream out2 = new FileOutputStream(PATH + "FileEntity.json");

            while ((len = in2.read(block)) != -1) {
                out2.write(block, 0, len);
            }
            in2.close();
            out2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("File Recieved Successfully");

    }

    public void doDeserialize() {

        try {
            FileReader reader = new FileReader(PATH + "FileEntity.json");
            JSONParser jsonparser = new JSONParser();
            JSONObject jsonobject = (JSONObject) jsonparser.parse(reader);
            list = (JSONArray) jsonobject.get("files");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("The  " + i + " element of the array: " + list.get(i));
                String fr = jsonobject.get("path").toString() + jsonobject.get("fileName").toString();
                System.out.println(fr);
                fileReader = new File(fr);

            }
            Iterator i = list.iterator();

            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                System.out.println("fileName  " + jsonobject.get("fileName") + "  " + "path   " + jsonobject.get("path"));
                testclass.setClassName(jsonobject.get("fileName").toString());
                testclass.setClassPath(jsonobject.get("path").toString());
                testclass.setClientIP(socket.getInetAddress().toString());
                java.util.Date now = new Date();
                testclass.setClientDate(now);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* public void SaveResultAsTextFile() {

        try {
            OutputStream out = new FileOutputStream(PATH + "account.txt");
            PrintStream print = new PrintStream(out);

            for (int i = 0; i < methodList.methodList.size(); i++) {
                print.println(methodList.methodList.get(i).getNameMethod());
                print.println(methodList.methodList.get(i).isPass());
                print.println(methodList.methodList.get(i).getReportMethod());
            }
            out.close();
            print.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

 /* public void seralize() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PATH + "methods.obj"));
            out.writeObject(methodList);
            out.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }*/
    public void saveAsJson() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(methodList);
        try {
            OutputStream out = new FileOutputStream(PATH + "methods.json");
            PrintStream p = new PrintStream(out);
            p.print(jsonString);
            p.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendResuletToClient() {

        try {
            sockReverse = new Socket("127.0.0.1", 8087);
            in = (new FileInputStream(PATH + "methods.json"));
            out = (sockReverse.getOutputStream());
            int len;
            while ((len = in.read(block)) != -1) {
                out.write(block, 0, len);

            }
            in.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                sockReverse.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void run() {

        try {

            saveClassFromClient();
            doDeserialize();
            pm.loadProperty();
            //String url = Property.getMap("url");
            //String un = Property.getMap("un");
            //String pw = Property.getMap("pw");
            //TCI.saveInDB(testclass, url, un, pw);
            DbFacade dbf = new DbFacade();
            dbf.createTestClassEntity(testclass);

            reflection.classLoad(fileReader);
            reflection.methodBefore(methodentity, methodList);
            reflection.methodTest(methodentity, methodList);
            reflection.methodAfter(methodentity, methodList);
            Thread.sleep(5000);
            //reflection.resuletTest();

            System.out.println("getMethodFailedCount" + "   " + methodentity.getMethodFailedCount());
            System.out.println("getMethodSuccessCount" + "   " + methodentity.getMethodSuccessCount());
            System.out.println("getMethodTotalRun" + "   " + methodentity.getTotalMethod());
            methodentity.setPercentageOfSuccess(methodentity.percentage(methodentity.getMethodSuccessCount(), methodentity.getTotalMethod()));
            System.out.println("PercentageOfSuccess" + "  " + methodentity.getPercentageOfSuccess());
            methodentity.setPercentageOfFailed(methodentity.percentage(methodentity.getMethodFailedCount(), methodentity.getTotalMethod()));
            System.out.println("PercentageOfFailed" + "  " + methodentity.getPercentageOfFailed());
            for (int i = 0; i < methodList.methodList.size(); i++) {
                System.out.println(methodList.methodList.get(i).getNameMethod() + methodList.methodList.get(i).isPass());

                System.out.println(methodList.methodList.get(i).getReportMethod());

            }
            // SaveResultAsTextFile();
            //seralize();
            saveAsJson();
            sendResuletToClient();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
