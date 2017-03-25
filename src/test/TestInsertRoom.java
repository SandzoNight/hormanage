/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.RoomCreateRoom;
import model.DataInsert;

/**
 *
 * @author fluke
 */
public class TestInsertRoom {
    public static void main(String[] args) {
        RoomCreateRoom.create("1", "2002", "123456789", "1");
    }
}
