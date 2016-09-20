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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author The Brain Key
 */
public class Closing {

    private int id,prod_id;
    private String close_type, close_time;//,operator="Save",deptidvalue,dobvalue,studentidvalue,namesmsg,studentidmsg,deptidmsg,msg,dobmsg;
    private Date close_date;
    private double qty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public String getClose_type() {
        return close_type;
    }

    public void setClose_type(String close_type) {
        this.close_type = close_type;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public Date getClose_date() {
        return close_date;
    }

    public void setClose_date(Date close_date) {
        this.close_date = close_date;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }
 
    public static Closing getClosing_From_Prod_ID_and_ClosingType(String prod_id,String type,String date) {
        Closing pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from closing where prod_id= " + prod_id + " and close_type='"+type+"' and close_date='"+date+"'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new Closing();
                pr.setId(rs.getInt(1));
                pr.setProd_id(rs.getInt(2));
                pr.setClose_type(rs.getString(3));
                pr.setQty(rs.getDouble(4));
                pr.setClose_date(rs.getDate(5));
                pr.setClose_time(rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }

    public static Closing getClosing_From_ID(String id) {
        Closing pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from closing where id= " + id + "");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new Closing();
                pr.setId(rs.getInt(1));
                pr.setProd_id(rs.getInt(2));
                pr.setClose_type(rs.getString(3));
                pr.setQty(rs.getDouble(4));
                pr.setClose_date(rs.getDate(5));
                pr.setClose_time(rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }

    public Closing() {
    }

    public boolean delete() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("delete from closing where id=?");
            pst.setInt(1, id);

            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println(">>>>Error Hint>> " + e);
            e.printStackTrace();
            return false;
        }
    }

   
    public static int getMaxID() {
        int id = 0;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from closing");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id + 1;
    }

    public static List<Closing> getList_From_Date1_Date2(String date1,String date2) {
        List<Closing> l = new ArrayList<Closing>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from closing where close_date between '"+date1+"' and '"+date2+"' and close_type='opening' order by prod_id");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Closing pr = new Closing();
                 pr.setId(rs.getInt(1));
                pr.setProd_id(rs.getInt(2));
                pr.setClose_type(rs.getString(3));
                pr.setQty(rs.getDouble(4));
                pr.setClose_date(rs.getDate(5));
                pr.setClose_time(rs.getString(6));

                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
       
    
       public static List<Closing> getList_From_Date(String d) {
        List<Closing> l = new ArrayList<Closing>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from closing where close_date='"+d+"' and close_type='opening' order by prod_id");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Closing pr = new Closing();
                 pr.setId(rs.getInt(1));
                pr.setProd_id(rs.getInt(2));
                pr.setClose_type(rs.getString(3));
                pr.setQty(rs.getDouble(4));
                pr.setClose_date(rs.getDate(5));
                pr.setClose_time(rs.getString(6));

                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
       
    public static List<Closing> getList() {
        List<Closing> l = new ArrayList<Closing>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from closing where close_type='opening'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Closing pr = new Closing();
                 pr.setId(rs.getInt(1));
                pr.setProd_id(rs.getInt(2));
                pr.setClose_type(rs.getString(3));
                pr.setQty(rs.getDouble(4));
                pr.setClose_date(rs.getDate(5));
                pr.setClose_time(rs.getString(6));

                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
    
    
public static Object[][] view() {
        List<Closing> l = Closing.getList();
        int no = 0,k=0;
        
        Object[][] v = new Object[l.size()][6];
        for (int i = 0; i < l.size(); i++) {
           
            no++;
            Product pr=Product.getProduct(l.get(i).getProd_id()+"");
          {
            v[k][0] = (l.get(i).getId()+ "");
            v[k][2] = pr.getProdname();
            //v[k][3] = (l.get(i).getClose_type() + "");
            v[k][3] = l.get(i).getQty();
            v[k][1] = l.get(i).getClose_date();
            Closing cl=Closing.getClosing_From_Prod_ID_and_ClosingType(l.get(i).getProd_id()+"", "closing",l.get(i).getClose_date()+"");
            if(cl!=null){
             v[k][4] = cl.getQty();
            }else{
             v[k][4] = "Closing Not Found";
            }
           
            k++;
            }
//        v[i][5]= l.get(i).getPurchasing_unitprice();
//        v[i][6]= l.get(i).getPurchasing_date();
            // v[i][7]= l.get(i).getQty();
        }

        return v;

    } 
 public static Object[][] view_from_date(String h) {
        List<Closing> l = Closing.getList_From_Date(h);
        int no = 0,k=0;
        
        Object[][] v = new Object[l.size()][6];
        for (int i = 0; i < l.size(); i++) {
           
            no++;
            Product pr=Product.getProduct(l.get(i).getProd_id()+"");
         {
            v[k][0] = (l.get(i).getId()+ "");
            v[k][2] = pr.getProdname();
            //v[k][3] = (l.get(i).getClose_type() + "");
            v[k][3] = l.get(i).getQty();
            v[k][1] = l.get(i).getClose_date();
            Closing cl=Closing.getClosing_From_Prod_ID_and_ClosingType(l.get(i).getProd_id()+"", "closing",l.get(i).getClose_date()+"");
            if(cl!=null){
             v[k][4] = cl.getQty();
            }else{
             v[k][4] = "Closing Not Found";
            }
           
            k++;
            }
//        v[i][5]= l.get(i).getPurchasing_unitprice();
//        v[i][6]= l.get(i).getPurchasing_date();
            // v[i][7]= l.get(i).getQty();
        }

        return v;

    } 
public static Object[][] view_from_date1_date2(String h1,String h3) {
        List<Closing> l = Closing.getList_From_Date1_Date2(h1,h3);
        int no = 0,k=0;
        
        Object[][] v = new Object[l.size()][6];
        for (int i = 0; i < l.size(); i++) {
           
            no++;
            Product pr=Product.getProduct(l.get(i).getProd_id()+"");
         {
            v[k][0] = (l.get(i).getId()+ "");
            v[k][2] = pr.getProdname();
            //v[k][3] = (l.get(i).getClose_type() + "");
            v[k][3] = l.get(i).getQty();
            v[k][1] = l.get(i).getClose_date();
            Closing cl=Closing.getClosing_From_Prod_ID_and_ClosingType(l.get(i).getProd_id()+"", "closing",l.get(i).getClose_date()+"");
            if(cl!=null){
             v[k][4] = cl.getQty();
            }else{
             v[k][4] = "Closing Not Found";
            }
           
            k++;
            }
//        v[i][5]= l.get(i).getPurchasing_unitprice();
//        v[i][6]= l.get(i).getPurchasing_date();
            // v[i][7]= l.get(i).getQty();
        }

        return v;

    } 
 
    public boolean update() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("update closing set prod_id=?,close_type=?,qty=?,close_date=? where id=?");
            pst.setInt(5, id);
            pst.setInt(1, prod_id);
            pst.setString(2, close_type);
            pst.setDouble(3, qty);
            pst.setDate(4, new java.sql.Date(close_date.getTime()));
            pst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean insert() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into closing values(?,?,?,?,?,?)");
            pst.setInt(1, getMaxID());
            pst.setInt(2, prod_id);
            pst.setString(3, close_type);
            pst.setDouble(4, qty);
            pst.setDate(5, new java.sql.Date(close_date.getTime()));
            pst.setString(6, close_time);
            pst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

   
    
}
