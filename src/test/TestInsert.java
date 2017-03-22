/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import model.DataInsert;

/**
 *
 * @author fluke
 */
public class TestInsert {
    public static void main(String[] args) {
        System.out.println("######## TestInsert ########");
        DataInsert di = new DataInsert();
        di.insert("testinsert", "'Pacharapol','Apitanapat','19','Fluke'");
        di.insert("testinsert", "'Pacharapol','Apitanapat','19','Fluke'");
        di.insert("testinsert", "'Pacharapol','Apitanapat','19','Fluke'");
        di.insert("testinsert", "'Pacharapol','Apitanapat','19','Fluke'");
        di.disconnect();
    }
}
