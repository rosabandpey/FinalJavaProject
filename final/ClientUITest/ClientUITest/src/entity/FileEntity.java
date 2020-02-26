/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author m.bandpey
 */
public class FileEntity implements Serializable {
    
    private ArrayList<File> files = new ArrayList<File>();
    private String fileName;
    private String path;

    public ArrayList<File> getFiles() {
        return files;
    }

    public void addFiles(File file) {
        this.files.add(file);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
    
    
}
