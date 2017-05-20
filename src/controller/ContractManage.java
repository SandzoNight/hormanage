/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import model.DataInsert;
import model.DataQuery;

/**
 *
 * @author Pacharapol
 */
public class ContractManage {
    public static void create(long currentContractNo,LocalDate currentTime, long dormId){
        ResultSet res = DataQuery.query("nextrecordId");
        long nextRoomId = 0;
        try{
            while(res.next()){
                nextRoomId = res.getLong("nextcontractId");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        DataInsert di = new DataInsert();
        di.insertContract(nextRoomId, currentContractNo, currentTime, dormId);
        
    }
    
    public static void delete(long contractId){
        
    }
    
    public static void list(long dormId){
        
    }
}
