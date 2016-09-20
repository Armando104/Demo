/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

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
    private String material, emp, client, details;//,operator="Save",deptidvalue,dobvalue,studentidvalue,namesmsg,studentidmsg,deptidmsg,msg,dobmsg;
    private Date recording_date;
    private double amount,qty;

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
            PreparedStatement pst = con.prepareStatement("delete from workshopsales where saleid=?");
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
                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
public static void main(String[]args){
    System.out.println(getStudentList().get(0).getQty());
}
    public static Object[][] view() {
        List<NailSale> l = NailSale.getStudentList();
        int no = 0;
        Object[][] v = new Object[l.size()][7];
        for (int i = 0; i < l.size(); i++) {
            no++;
            v[i][0] = no;
            v[i][1] = l.get(i).getMaterial();
            //v[i][2] = l.get(i).getEmp();
            v[i][2] = l.get(i).getAmount();
            v[i][3] = l.get(i).getRecording_date();
            v[i][4]=l.get(i).getDetails();
            v[i][5]= l.get(i).getQty();
              //v[i][6]= l.get(i).getPurchasing_date();
              v[i][6]= l.get(i).getQty();
        }

        return v;

    }

    public boolean insert() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into nailsale values(?,?,?,?,?,?,?,?)");
            pst.setInt(1, sale_id);
            pst.setString(2, material);
            pst.setString(3, emp);
            pst.setString(4, client);
            pst.setString(5, details);
            pst.setDouble(6, amount);
            pst.setDate(7, new java.sql.Date(recording_date.getTime()));
            pst.setDouble(8, qty);
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
