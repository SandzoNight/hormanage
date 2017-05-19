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
public class Renter {
    private long renterId;
    private String renterName;
    private String renterLastName;
    
    public Renter(String renterId,String renterName,String renterLastName){
        this.renterId = Long.parseLong(renterId);
        this.renterName = renterName;
        this.renterLastName = renterLastName;
    }

    public long getRenterId() {
        return renterId;
    }

    public void setRenterId(long renterId) {
        this.renterId = renterId;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public String getRenterLastName() {
        return renterLastName;
    }

    public void setRenterLastName(String renterLastName) {
        this.renterLastName = renterLastName;
    }
    
    public String getFullname(){
        return renterName+" "+renterLastName;
    }
    
    public String toString(){
        return renterName+" "+renterLastName;
    }
}
