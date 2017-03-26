/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.RoomManage;

/**
 *
 * @author fluke
 */
public class TestDeleteRoom {
    public static void main(String[] args) {
        System.out.println("######## TestDeleteRoom ########");
        RoomManage.delete("10");
    }
}
