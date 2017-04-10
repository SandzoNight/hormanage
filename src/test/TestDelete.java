/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import model.DataDelete;
import model.DataInsert;

/**
 *
 * @author fluke
 */
public class TestDelete {
    public static void main(String[] args) {
        System.out.println("######## TestDelete ########");
        int num = DataDelete.delete("dormitory", "dormId", "4");
        System.out.println(num);
        DataDelete.disconnect();
    }
}
