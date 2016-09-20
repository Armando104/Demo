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
public class Stockout {

    private int id;
    private String product, details, emp;
    private Date recording_date;
    private double qty;

    public String getEmp() {
        return emp;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }

    public static Stockout getPurchase1(String id) {
        Stockout pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from stockout where id= " + id + "");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new Stockout();
                // Stockout pr = new Stockout();
                pr.setId(rs.getInt(1));
                pr.setProduct(rs.getString(2));

                pr.setQty(rs.getDouble(3));
                pr.setDetails(rs.getString(4));

                pr.setRecording_date(rs.getDate(5));
                pr.setEmp(rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }
public static Stockout getPurchase(String id) {
        Stockout pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from stockout where id= " + id + "");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // pr = new Stockout();
                pr = new Stockout();
                pr.setId(rs.getInt(1));
                pr.setProduct(rs.getString(2));

                pr.setQty(rs.getDouble(3));
                pr.setDetails(rs.getString(4));
                //pr.set(rs.getDouble(6));
                pr.setRecording_date(rs.getDate(5));
                pr.setEmp(rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }
    public static Double getStockoutFromHardW(String date) {
        double pr =0.0;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select count(qty) from stockout where recordingdate= '" +date + "'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // pr = new Stockout();
                
                pr=(rs.getDouble(1));
                 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }

    public boolean delete() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("delete from stockout where id=?");
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
            PreparedStatement pst = con.prepareStatement("select * from stockout");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id + 1;
    }
  public static Object[][] view(Date d) {
        List<Stockout> l = Stockout.getStockoutList();
        int no = 0,k=0;
         Calendar cal=new GregorianCalendar();
         Calendar cal1=new GregorianCalendar();
         cal1.setTime(d);
        String date="";String date1="";
        Object[][] v = new Object[l.size()][6];
        for (int i = 0; i < l.size(); i++) {
            
             cal.setTime(l.get(i).getRecording_date());
            String day="",month="";String day1="",month1="";
            if((cal.get(Calendar.MONTH))<10){
            month="0"+(cal.get(Calendar.MONTH)+1);
            }
            else{
              month=""+(cal.get(Calendar.MONTH)+1);
            }
            
            if((cal1.get(Calendar.MONTH)+1)<10){
            month1="0"+(cal1.get(Calendar.MONTH)+1);
            }
            else{
              month1=""+(cal1.get(Calendar.MONTH)+1);
            }
            System.out.println("Month........"+month);
            System.out.println("Month1........"+month1);
            
            
            if((cal.get(Calendar.DAY_OF_MONTH))<10){
            day="0"+(cal.get(Calendar.DAY_OF_MONTH));
            }else{
             day=""+(cal.get(Calendar.DAY_OF_MONTH));
            }
            
            if((cal1.get(Calendar.DAY_OF_MONTH))<10){
            day1="0"+(cal1.get(Calendar.DAY_OF_MONTH));
            }else{
             day1=""+(cal1.get(Calendar.DAY_OF_MONTH));
            }
            
            date=cal.get(Calendar.YEAR)+"-"+month+"-"+day;
            
            date1=cal1.get(Calendar.YEAR)+"-"+month1+"-"+day1;
            
             System.out.println("date from search.........."+date+".......Date from db..."+date1);
            System.out.println("Day........"+day);
            System.out.println("Day1........"+day1);
            
            
            no++;
            if (date.equalsIgnoreCase(date1)){
            v[k][0] = (l.get(i).getId()+ "");;
            v[k][1] = (l.get(i).getEmp() + "");
            v[k][2] = (l.get(i).getProduct() + "");
            v[k][3] = l.get(i).getQty();
            v[k][4] = l.get(i).getDetails();
            v[k][5] = l.get(i).getRecording_date();
            k++;
            }
//        v[i][5]= l.get(i).getPurchasing_unitprice();
//        v[i][6]= l.get(i).getPurchasing_date();
            // v[i][7]= l.get(i).getQty();
        }

        return v;

    } 
    public static Object[][] view(Calendar ca) {
        List<Stockout> l = Stockout.getStockoutList();
        int no = 0;
        Object[][] v = new Object[l.size()][6];
          Calendar cal=new GregorianCalendar();
        for (int i = 0; i < l.size(); i++) {
            cal.setTime(l.get(i).getRecording_date());
        
         if(cal.get(Calendar.YEAR)==ca.get(Calendar.YEAR)&&ca.get(Calendar.MONTH)==cal.get(Calendar.MONTH)){
            no++;
            v[i][0] = (l.get(i).getId()+ "");;
            v[i][1] = (l.get(i).getEmp() + "");
            v[i][2] = (l.get(i).getProduct() + "");
            v[i][3] = l.get(i).getQty();
            v[i][4] = l.get(i).getDetails();
            v[i][5] = l.get(i).getRecording_date();
//        v[i][5]= l.get(i).getPurchasing_unitprice();
//        v[i][6]= l.get(i).getPurchasing_date();
            // v[i][7]= l.get(i).getQty();
         }
        }

        return v;

    }

       public static Object[][] view() {
        List<Stockout> l = Stockout.getStockoutList();
        int no = 0;
        Object[][] v = new Object[l.size()][6];
        for (int i = 0; i < l.size(); i++) {
            no++;
            v[i][0] = (l.get(i).getId()+ "");;
            v[i][1] = (l.get(i).getEmp() + "");
            v[i][2] = (l.get(i).getProduct() + "");
            v[i][3] = l.get(i).getQty();
            v[i][4] = l.get(i).getDetails();
            v[i][5] = l.get(i).getRecording_date();
//        v[i][5]= l.get(i).getPurchasing_unitprice();
//        v[i][6]= l.get(i).getPurchasing_date();
            // v[i][7]= l.get(i).getQty();
        }

        return v;

    }

    public static List<Stockout> getStockoutList() {
        List<Stockout> l = new ArrayList<Stockout>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from stockout");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Stockout pr = new Stockout();
                pr.setId(rs.getInt(1));
                pr.setProduct(rs.getString(2));

                pr.setQty(rs.getDouble(3));
                pr.setDetails(rs.getString(4));
                //pr.set(rs.getDouble(6));
                pr.setRecording_date(rs.getDate(5));
                pr.setEmp(rs.getString(6));
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
            PreparedStatement pst = con.prepareStatement("insert into stockout values(?,?,?,?,?,?)");
            pst.setInt(1, id);
            pst.setString(2, product);
            pst.setDouble(3, qty);

            pst.setString(4, details);

            pst.setDate(5, new java.sql.Date(recording_date.getTime()));
            pst.setString(6, emp);
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
            PreparedStatement pst = con.prepareStatement("update  stockout set product=?,qty=?,details=?,recordingdate=?,emp=? where id=?");
            pst.setInt(6, id);
            pst.setString(1, product);
            pst.setDouble(2, qty);

            pst.setString(3, details);

            pst.setDate(4, new java.sql.Date(recording_date.getTime()));
            pst.setString(5, emp);
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getRecording_date() {
        return recording_date;
    }

    public void setRecording_date(Date recording_date) {
        this.recording_date = recording_date;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }
}
