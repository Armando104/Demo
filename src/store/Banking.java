/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

import pack.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author The Brain Key
 */
public class Banking {

    private int id;
    private String  details,emp,banking_type;
    private Date recording_date;
    private double amount;

    public String getBanking_type() {
        return banking_type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setBanking_type(String banking_type) {
        this.banking_type = banking_type;
    }

    

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

    public boolean delete() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("delete from banking where id=?");
            pst.setInt(1, id);
         
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println(">>>>Error Hint>> "+e);
            e.printStackTrace();
            return false;
        }
    }
     

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
   Object[][]v=new Object[l.size()][6];
   for(int i=0;i<l.size();i++){
       no++;
      v[i][0]=l.get(i).getId();
        v[i][1]=(l.get(i).getEmp())+"";
        v[i][2]=l.get(i).getRecording_date();
        v[i][3]= l.get(i).getAmount();
        v[i][4]=l.get(i).getBanking_type();
         
       v[i][5]= l.get(i).getDetails();
   }
   
   return  v;
   
}

public static Object[][]view_search_by_date(String h){
   List<Banking>l=Banking.getBankings(h);
   if(l.size()==0){
       JOptionPane.showMessageDialog(null,"Sorry No Data Found","Message", JOptionPane.ERROR_MESSAGE);
   }
    System.out.println("DATE: "+h);
   int no=0;
   Object[][]v=new Object[l.size()][6];
   for(int i=0;i<l.size();i++){
       no++;
      v[i][0]=l.get(i).getId();
        v[i][1]=(l.get(i).getEmp())+"";
        v[i][2]=l.get(i).getRecording_date();
        v[i][3]= l.get(i).getAmount();
        v[i][4]=l.get(i).getBanking_type();
         
       v[i][5]= l.get(i).getDetails();
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
                 pr.setBanking_type(rs.getString(5));
                 pr.setDetails(rs.getString(6));
                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
public static List<Banking> getBankings(String d) {
        List<Banking> l = new ArrayList<Banking>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from banking where recordingdate='"+d+"'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Banking pr = new Banking();
                  pr.setId(rs.getInt(1));
                pr.setAmount(rs.getDouble(2));
                pr.setEmp(rs.getString(3));
              
                pr.setRecording_date(rs.getDate(4));
                 pr.setBanking_type(rs.getString(5));
                 pr.setDetails(rs.getString(6));
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
            PreparedStatement pst = con.prepareStatement("insert into banking values(?,?,?,?,?,?)");
            pst.setInt(1, id);
            
            pst.setString(3, emp);
            
            pst.setDouble(2, amount);
            
            pst.setDate(4, new java.sql.Date(recording_date.getTime()));
            pst.setString(5,banking_type);
            pst.setString(6, details);
            pst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("update banking set amount=?,emp=?,recordingdate=?,banking_type=?,details=? where id=?");
            pst.setInt(6, id);
            
            pst.setString(2, emp);
            
            pst.setDouble(1, amount);
            
            pst.setDate(3, new java.sql.Date(recording_date.getTime()));
            pst.setString(4,banking_type);
            pst.setString(5, details);
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
