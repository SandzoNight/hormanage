/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Pacharapol
 */
public class Dormitory {
    private long dormId;
    private String dormName;
    private double waterRate;
    private double elecRate;
    
    public Dormitory(long dormId,String dormName,double waterRate,double elecRate){
        this.dormId = dormId;
        this.dormName = dormName;
        this.waterRate = waterRate;
        this.elecRate = elecRate;
    }

    public long getDormId() {
        return dormId;
    }

    public String getDormName() {
        return dormName;
    }

    public double getWaterRate() {
        return waterRate;
    }

    public double getElecRate() {
        return elecRate;
    }
    
    
}
