/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author POOM
 */
public class Session {
    private static String _userId;
    private static String _dormId="0";
    
    public Session(String userId){
        _userId = userId;
    }
    
    public static void setDormId(String dormId){
        _dormId = dormId;
    }
}
