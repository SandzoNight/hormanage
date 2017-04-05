/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.DormManage;

/**
 *
 * @author fluke
 */
public class TestRemoveDorm {
    public static void main(String[] args) {
        System.out.println("TEST REMOVE DORM");
        DormManage.remove("5", 123456789);
        
    }
}
