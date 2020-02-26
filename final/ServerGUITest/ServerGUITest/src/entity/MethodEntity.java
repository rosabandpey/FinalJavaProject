/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gorji
 */
public class MethodEntity implements java.io.Serializable {

    private int methodSuccessCount;
    private int methodFailedCount;
    private int totalMethod;
    private int PercentageOfSuccess;
    private int PercentageOfFailed;
    public List<MethodsEntity> methodList=new ArrayList<>();
    
    MethodsEntity methodsEntity=null;
    
    

    public int percentage(int value, int total) {
        return ((value * 100) / total);
    }

    public void methodPassed() {
        methodSuccessCount += 1;
    }

    public void methodFailed() {
        methodFailedCount += 1;
    }

    public void methodTotalRun() {
        totalMethod += 1;
    }

    public int getMethodSuccessCount() {
        return methodSuccessCount;
    }

    public void setMethodSuccessCount(int methodSuccessCount) {
        this.methodSuccessCount = methodSuccessCount;
    }

    public int getMethodFailedCount() {
        return methodFailedCount;
    }

    public void setMethodFailedCount(int methodFailedCount) {
        this.methodFailedCount = methodFailedCount;
    }

    public int getTotalMethod() {
        return totalMethod;
    }

    public void setTotalMethod(int totalMethod) {
        this.totalMethod = totalMethod;
    }

    public int getPercentageOfSuccess() {
        return PercentageOfSuccess;
    }

    public void setPercentageOfSuccess(int PercentageOfSuccess) {
        this.PercentageOfSuccess = PercentageOfSuccess;
    }

    public int getPercentageOfFailed() {
        return PercentageOfFailed;
    }

    public void setPercentageOfFailed(int PercentageOfFailed) {
        this.PercentageOfFailed = PercentageOfFailed;
    }
}