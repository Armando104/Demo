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
public class NailSale {

    private int sale_id;
    private String material, emp, slip1, slip2, client, details;//,operator="Save",deptidvalue,dobvalue,studentidvalue,namesmsg,studentidmsg,deptidmsg,msg,dobmsg;
    private Date recording_date;
    private double amount, qty;

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public static NailSale getNailSale(String id) {
        NailSale pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from nailsale where saleid= " + id + "");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new NailSale();
                pr.setSale_id(rs.getInt(1));
                pr.setMaterial(rs.getString(2));
                pr.setEmp(rs.getString(3));
                pr.setClient(rs.getString(4));
                pr.setDetails(rs.getString(5));

                pr.setAmount(rs.getDouble(6));
                pr.setRecording_date(rs.getDate(7));
                pr.setQty(rs.getDouble(8));
                pr.setSlip1(rs.getString(9));
                pr.setSlip2(rs.getString(10));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }

    public NailSale() {
    }

    public boolean delete() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("delete from nailsale where saleid=?");
            pst.setInt(1, sale_id);

            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println(">>>>Error Hint>> " + e);
            e.printStackTrace();
            return false;
        }
    }

    public static Double getCountExpense(String d) {
        double id = 0;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select count(amount) from expenses where recording_date=" + d + "");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public static int getMaxID() {
        int id = 0;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from nailsale");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id + 1;
    }
 public static List<NailSale> getList(String dd) {
        List<NailSale> l = new ArrayList<NailSale>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from nailsale where recordingdate='"+dd+"'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                NailSale pr = new NailSale();
                pr.setSale_id(rs.getInt(1));
                pr.setMaterial(rs.getString(2));
                pr.setEmp(rs.getString(3));
                pr.setClient(rs.getString(4));
                pr.setDetails(rs.getString(5));

                pr.setAmount(rs.getDouble(6));
                pr.setRecording_date(rs.getDate(7));
                pr.setQty(rs.getDouble(8));
                pr.setSlip1(rs.getString(9));
                pr.setSlip2(rs.getString(10));

                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
 
 public static List<NailSale> getList_Date1_Date2(String d1,String d2) {
        List<NailSale> l = new ArrayList<NailSale>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from nailsale where recordingdate between'"+d1+"' and '"+d2+"' ");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                NailSale pr = new NailSale();
                pr.setSale_id(rs.getInt(1));
                pr.setMaterial(rs.getString(2));
                pr.setEmp(rs.getString(3));
                pr.setClient(rs.getString(4));
                pr.setDetails(rs.getString(5));

                pr.setAmount(rs.getDouble(6));
                pr.setRecording_date(rs.getDate(7));
                pr.setQty(rs.getDouble(8));
                pr.setSlip1(rs.getString(9));
                pr.setSlip2(rs.getString(10));

                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
 
    public static List<NailSale> getStudentList() {
        List<NailSale> l = new ArrayList<NailSale>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from nailsale");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                NailSale pr = new NailSale();
                pr.setSale_id(rs.getInt(1));
                pr.setMaterial(rs.getString(2));
                pr.setEmp(rs.getString(3));
                pr.setClient(rs.getString(4));
                pr.setDetails(rs.getString(5));

                pr.setAmount(rs.getDouble(6));
                pr.setRecording_date(rs.getDate(7));
                pr.setQty(rs.getDouble(8));
                pr.setSlip1(rs.getString(9));
                pr.setSlip2(rs.getString(10));

                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(getStudentList().get(0).getQty());
    }

    public static Object[][] view() {
        List<NailSale> l = NailSale.getStudentList();
        int no = 0;
        Object[][] v = new Object[l.size()][11];
        for (int i = 0; i < l.size(); i++) {
            no++;
            v[i][0] = no;
            Product pr = Product.getProductFromName(l.get(i).getMaterial());
            System.out.println("Prod:" + pr);
            Nail_Store ns = Nail_Store.getPurchase_From_Prod_ID(pr.getProd_id() + "");
            System.out.println("Store:" + ns);
            v[i][1] = l.get(i).getMaterial();
            v[i][2] = l.get(i).getQty()+" Bags";
            double tot = ns.getPurchasing_unitprice() * l.get(i).getQty();
            v[i][3] = tot+" Rfw";
            v[i][4] = l.get(i).getAmount()+" Rfw";
            double rem = (l.get(i).getQty() * ns.getPurchasing_unitprice()) - l.get(i).getAmount();
            if (rem < 0) {
                v[i][5] = "(+" + rem * -1 + ") Rfw";
            } else {
                v[i][5] = "(-" + rem + ") Rfw";
            }

            v[i][6] = l.get(i).getRecording_date();
            v[i][7] = l.get(i).getDetails();
            v[i][8] = l.get(i).getSlip1();
            v[i][9] = l.get(i).getSlip2();
            v[i][10] = l.get(i).getClient();
        }


        return v;

    }
public static Object[][] view_search_by_date(String n) {
        List<NailSale> l = NailSale.getList(n);
        int no = 0;
        Object[][] v = new Object[l.size()][11];
        for (int i = 0; i < l.size(); i++) {
            no++;
            v[i][0] = no;
            Product pr = Product.getProductFromName(l.get(i).getMaterial());
            
            Nail_Store ns = Nail_Store.getPurchase_From_Prod_ID(pr.getProd_id() + "");
         
            v[i][1] = l.get(i).getMaterial();
            v[i][2] = l.get(i).getQty()+" Bags";
            double tot = ns.getPurchasing_unitprice() * l.get(i).getQty();
            v[i][3] = tot+" Rfw";
            v[i][4] = l.get(i).getAmount()+" Rfw";
            double rem = (l.get(i).getQty() * ns.getPurchasing_unitprice()) - l.get(i).getAmount();
            if (rem < 0) {
                v[i][5] = "(+" + rem * -1 + ") Rfw";
            } else {
                v[i][5] = "(-" + rem + ") Rfw";
            }

            v[i][6] = l.get(i).getRecording_date();
            v[i][7] = l.get(i).getDetails();
            v[i][8] = l.get(i).getSlip1();
            v[i][9] = l.get(i).getSlip2();
            v[i][10] = l.get(i).getClient();
        }


        return v;

    }


public static Object[][] view_search_by_date1_date2(String d1,String d2) {
        List<NailSale> l = NailSale.getList_Date1_Date2(d1, d2);
        int no = 0;
        Object[][] v = new Object[l.size()][11];
        for (int i = 0; i < l.size(); i++) {
            no++;
            v[i][0] = no;
            Product pr = Product.getProductFromName(l.get(i).getMaterial());
            
            Nail_Store ns = Nail_Store.getPurchase_From_Prod_ID(pr.getProd_id() + "");
         
            v[i][1] = l.get(i).getMaterial();
            v[i][2] = l.get(i).getQty()+" Bags";
            double tot = ns.getPurchasing_unitprice() * l.get(i).getQty();
            v[i][3] = tot+" Rfw";
            v[i][4] = l.get(i).getAmount()+" Rfw";
            double rem = (l.get(i).getQty() * ns.getPurchasing_unitprice()) - l.get(i).getAmount();
            if (rem < 0) {
                v[i][5] = "(+" + rem * -1 + ") Rfw";
            } else {
                v[i][5] = "(-" + rem + ") Rfw";
            }

            v[i][6] = l.get(i).getRecording_date();
            v[i][7] = l.get(i).getDetails();
            v[i][8] = l.get(i).getSlip1();
            v[i][9] = l.get(i).getSlip2();
            v[i][10] = l.get(i).getClient();
        }


        return v;

    }

    public String getSlip1() {
        return slip1;
    }

    public void setSlip1(String slip1) {
        this.slip1 = slip1;
    }

    public String getSlip2() {
        return slip2;
    }

    public void setSlip2(String slip2) {
        this.slip2 = slip2;
    }
 public boolean upate() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("update nailsale set material=?,emp=?,client=?,details=?,amount=?,recordingdate=?,qty=?,slip1=?,slip2=? where saleid=?");
            pst.setInt(10, sale_id);
            pst.setString(1, material);
            pst.setString(2, emp);
            pst.setString(3, client);
            pst.setString(4, details);
            pst.setDouble(5, amount);
            pst.setDate(6, new java.sql.Date(recording_date.getTime()));
            pst.setDouble(7, qty);
            pst.setString(8, slip1);
            pst.setString(9, slip2);
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
            PreparedStatement pst = con.prepareStatement("insert into nailsale values(?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, sale_id);
            pst.setString(2, material);
            pst.setString(3, emp);
            pst.setString(4, client);
            pst.setString(5, details);
            pst.setDouble(6, amount);
            pst.setDate(7, new java.sql.Date(recording_date.getTime()));
            pst.setDouble(8, qty);
            pst.setString(9, slip1);
            pst.setString(10, slip2);
            pst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getSale_id() {
        return sale_id;
    }

    public void setSale_id(int sale_id) {
        this.sale_id = sale_id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getEmp() {
        return emp;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
