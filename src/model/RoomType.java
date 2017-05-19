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
public class RoomType {
    private long typeId;
    private String typeName;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public RoomType(long typeId,String typeName,double price){
        this.typeId = typeId;
        this.typeName = typeName;
        this.price = price;
    }

    public long getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }
    
    public String toString(){
        return typeName;
    }
}
