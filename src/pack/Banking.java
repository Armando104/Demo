/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author The Brain Key
 */
public class Banking {

    private int id;
    private String  emp;
    private Date recording_date;
    private double amount;

    

//    public static Banking getPurchase1(String id) {
//        Banking pr = null;
//        try {
//            Connection con = DBClassConnector.getConnection();
//            PreparedStatement pst = con.prepareStatement("select * from payment where = " + id + "");
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                pr = new Banking();
//                pr.setPayment_id(rs.getInt(1));
//                pr.setPay(rs.getString(2));
//                pr.setEmp(rs.getString(3));
//                pr.setRecording_date(rs.getDate(5));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return pr;
//    }

//    public static Banking getPurchase(String id) {
//        Banking pr = null;
//        try {
//            Connection con = DBClassConnector.getConnection();
//            PreparedStatement pst = con.prepareStatement("select * from banking where id= " + id + "");
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                pr = new Banking();
//                 pr.setPayment_id(rs.getInt(1));
//                pr.setPay(rs.getString(2));
//                pr.setEmp(rs.getString(3));
//              
//                pr.setRecording_date(rs.getDate(5));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return pr;
//    }

//    public boolean delete() {
//        try {
//            Connection con = DBClassConnector.getConnection();
//            PreparedStatement pst = con.prepareStatement("delete from purchase where purchase_id=?");
//            pst.setInt(1, purchase_id);
//         
//            pst.execute();
//            return true;
//        } catch (Exception e) {
//            System.out.println(">>>>Error Hint>> "+e);
//            e.printStackTrace();
//            return false;
//        }
//    }
     

    public static int getMaxID() {
        int id = 0;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from banking");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id + 1;
    }
public static Object[][]view(){
   List<Banking>l=Banking.getPurchaseList();
   int no=0;
   Object[][]v=new Object[l.size()][4];
   for(int i=0;i<l.size();i++){
       no++;
      v[i][0]=no;
        v[i][1]=(l.get(i).getEmp())+"";
        v[i][2]=l.get(i).getRecording_date();
        v[i][3]= l.get(i).getAmount();
        //v[i][4]=l.get(i).getRecording_date();
         
       // v[i][7]= l.get(i).getQty();
   }
   
   return  v;
   
}
    public static List<Banking> getPurchaseList() {
        List<Banking> l = new ArrayList<Banking>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from banking");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Banking pr = new Banking();
                  pr.setId(rs.getInt(1));
                pr.setAmount(rs.getDouble(2));
                pr.setEmp(rs.getString(3));
              
                pr.setRecording_date(rs.getDate(4));
                 
                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public boolean insert() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into banking values(?,?,?,?)");
            pst.setInt(1, id);
            
            pst.setString(3, emp);
            
            pst.setDouble(2, amount);
            
            pst.setDate(4, new java.sql.Date(recording_date.getTime()));
            pst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmp() {
        return emp;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }

    public Date getRecording_date() {
        return recording_date;
    }

    public void setRecording_date(Date recording_date) {
        this.recording_date = recording_date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

     
 
 
 

    
    
}
