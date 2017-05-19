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
    
    public RoomType(long typeId,String typeName){
        this.typeId = typeId;
        this.typeName = typeName;
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
