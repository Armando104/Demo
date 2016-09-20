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
public class Purchase {

    private int purchase_id, prod_id;
    private String description;
    private Date purchasing_date;
    private double qty, selling_unitprice, purchasing_unitprice;

    public static Purchase getPurchase1(String id) {
        Purchase pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from purchase where prod_id= " + id + "");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new Purchase();
                pr.setPurchase_id(rs.getInt(1));
                pr.setProd_id(rs.getInt(2));
                pr.setDescription(rs.getString(3));
                pr.setQty(rs.getDouble(4));
                pr.setSelling_unitprice(rs.getDouble(5));
                pr.setPurchasing_unitprice(rs.getDouble(6));
                pr.setPurchasing_date(rs.getDate(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }

    public static Purchase getPurchase(String id) {
        Purchase pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from purchase where purchase_id= " + id + "");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new Purchase();
                pr.setPurchase_id(rs.getInt(1));
                pr.setProd_id(rs.getInt(5));
                pr.setDescription(rs.getString(2));
                pr.setQty(rs.getDouble(3));
                pr.setSelling_unitprice(rs.getDouble(4));
                pr.setPurchasing_unitprice(rs.getDouble(6));
                pr.setPurchasing_date(rs.getDate(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }

    public boolean delete() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("delete from purchase where purchase_id=?");
            pst.setInt(1, purchase_id);

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
            PreparedStatement pst = con.prepareStatement("select * from purchase");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id + 1;
    }

    public static Object[][] view() {
        List<Purchase> l = Purchase.getPurchaseList();
        int no = 0;
        Object[][] v = new Object[l.size()][7];
        for (int i = 0; i < l.size(); i++) {
            no++;
            v[i][0] = l.get(i).getPurchase_id();
            Product pr = Product.getProduct(l.get(i).getProd_id() + "");
            if (pr != null) {
                v[i][1] = pr.getProdname();

            }
            v[i][2] = l.get(i).getDescription();
            v[i][3] = l.get(i).getQty();
            v[i][4] = l.get(i).getSelling_unitprice();
            v[i][5] = l.get(i).getPurchasing_unitprice();
            v[i][6] = l.get(i).getPurchasing_date();
            // v[i][7]= l.get(i).getQty();
        }

        return v;

    }

    public static Object[][] view(Calendar c) {
        List<Purchase> l = Purchase.getPurchaseList();
        int no = 0;
        Object[][] v = new Object[l.size()][7];
        Calendar cal=new GregorianCalendar();
        int k=0;
        for (int i = 0; i < l.size(); i++) {
            no++;
            cal.setTime(l.get(i).getPurchasing_date());
            if(cal.get(Calendar.YEAR)==c.get(Calendar.YEAR)&&c.get(Calendar.MONTH)==cal.get(Calendar.MONTH)){
              
            v[k][0] = l.get(i).getPurchase_id();
            Product pr = Product.getProduct(l.get(i).getProd_id() + "");
            if (pr != null) {
                v[k][1] = pr.getProdname();

            }
            v[k][2] = l.get(i).getDescription();
            v[k][3] = l.get(i).getQty();
            v[k][4] = l.get(i).getSelling_unitprice();
            v[k][5] = l.get(i).getPurchasing_unitprice();
            v[k][6] = l.get(i).getPurchasing_date();
            }
            // v[i][7]= l.get(i).getQty();
        }

        return v;

    }

    public static Object[][] viewSearch(Date d) {
        List<Purchase> l = Purchase.getPurchaseList();
        int no = 0, k = 0;
        Calendar cal=new GregorianCalendar();
         Calendar cal1=new GregorianCalendar();
         cal1.setTime(d);
        String date="";String date1="";
        Object[][] v = new Object[l.size()][7];
        for (int i = 0; i < l.size(); i++) {
            cal.setTime(l.get(i).getPurchasing_date());
            String day="",month="";String day1="",month1="";
            if((cal.get(Calendar.MONTH)+1)<10){
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
            
             System.out.println("day from search.........."+day+"......."+l.get(i).getPurchasing_date());
           
            System.out.println("month from search.........."+month+"......."+l.get(i).getPurchasing_date());
           
            System.out.println("date from search.........."+date+"......."+l.get(i).getPurchasing_date());
            if (date.equalsIgnoreCase(date1)) {
                no++;
                v[k][0] = l.get(i).getPurchase_id();
                Product pr = Product.getProduct(l.get(i).getProd_id() + "");
                if (pr != null) {
                    v[k][1] = pr.getProdname();

                }
                v[k][2] = l.get(i).getDescription();
                v[k][3] = l.get(i).getQty();
                v[k][4] = l.get(i).getSelling_unitprice();
                v[k][5] = l.get(i).getPurchasing_unitprice();
                v[k][6] = l.get(i).getPurchasing_date();
                
            k++;
            }
           
            //break;

            // v[i][7]= l.get(i).getQty();
        }

        return v;

    }

    public static List<Purchase> getPurchaseList() {
        List<Purchase> l = new ArrayList<Purchase>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from purchase");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Purchase pr = new Purchase();
                pr.setPurchase_id(rs.getInt(1));
                pr.setProd_id(rs.getInt(2));
                pr.setDescription(rs.getString(3));
                pr.setQty(rs.getDouble(4));
                pr.setSelling_unitprice(rs.getDouble(5));
                pr.setPurchasing_unitprice(rs.getDouble(6));
                pr.setPurchasing_date(rs.getDate(7));

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
            PreparedStatement pst = con.prepareStatement("insert into purchase values(?,?,?,?,?,?,?)");
            pst.setInt(1, purchase_id);
            pst.setInt(2, prod_id);
            pst.setString(3, description);

            pst.setDouble(4, qty);
            pst.setDouble(5, selling_unitprice);
            pst.setDouble(6, purchasing_unitprice);
            pst.setDate(7, new java.sql.Date(purchasing_date.getTime()));
            pst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean UpdatePurchase() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("update purchase set prod_id=?,description=?,qty=?,selling_unitprice=?,purchasing_date=?,purchasing_date=? where purchase_id=?");
            pst.setInt(7, purchase_id);
            pst.setInt(1, prod_id);
            pst.setString(2, description);

            pst.setDouble(3, qty);
            pst.setDouble(4, selling_unitprice);
            pst.setDouble(5, purchasing_unitprice);
            pst.setDate(6, new java.sql.Date(purchasing_date.getTime()));
            pst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPurchasing_date() {
        return purchasing_date;
    }

    public void setPurchasing_date(Date purchasing_date) {
        this.purchasing_date = purchasing_date;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getSelling_unitprice() {
        return selling_unitprice;
    }

    public void setSelling_unitprice(double selling_unitprice) {
        this.selling_unitprice = selling_unitprice;
    }

    public double getPurchasing_unitprice() {
        return purchasing_unitprice;
    }

    public void setPurchasing_unitprice(double purchasing_unitprice) {
        this.purchasing_unitprice = purchasing_unitprice;
    }
}
