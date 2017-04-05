/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import controller.DormManage;


public class TestRegisDorm {
    public static void main(String[] args) {
        System.out.println("TEST INSERT DORM");
        String[] facilityDorm = new String[2], facilityRoom = new String[2];
        facilityDorm[0] = "1";
        facilityDorm[1] = "3";
        
        facilityRoom[0] = "1";
        facilityRoom[1] = "2";
        
        DormManage.add("SITMansion", "Male&Female", "KMUTT", 3, facilityDorm, facilityRoom, 123456789);
        
    }
 
    
}
