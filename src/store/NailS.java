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

/**
 *
 * @author The Brain Key
 */
public class NailS {

    private int prod_id;
    private String prodname, deptidvalue,details, deptidmsg, deptnamesmsg, msg = "Fill All Fields", operator = "save";
    private boolean valid = true;
    private double qty;
    private Date recordingdate;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getRecordingdate() {
        return recordingdate;
    }

    public void setRecordingdate(Date recordingdate) {
        this.recordingdate = recordingdate;
    }

    public static NailS getProduct(String id) {
        NailS pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from nail where prod_id= " + id + "");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new NailS();
               pr.setProd_id(rs.getInt(1));
                pr.setProdname(rs.getString(2));
                pr.setQty(rs.getDouble(3));
                pr.setRecordingdate(rs.getDate(4));
                pr.setDetails(rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

   
    public String getDeptidvalue() {
        return deptidvalue;
    }

    public void setDeptidvalue(String deptidvalue) {
        this.deptidvalue = deptidvalue;
    }

    public String getDeptidmsg() {
        return deptidmsg;
    }

    public void setDeptidmsg(String deptidmsg) {
        this.deptidmsg = deptidmsg;
    }

    public static NailS getProductFromName(String id) {
        NailS pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from nail where prodname= '" + id + "'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new NailS();
                pr.setProd_id(rs.getInt(1));
                pr.setProdname(rs.getString(2));
                pr.setQty(rs.getDouble(3));
                pr.setRecordingdate(rs.getDate(4));
                pr.setDetails(rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }

    public boolean delete() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("delete from nail where prod_id=?");
            pst.setInt(1, prod_id);

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
            PreparedStatement pst = con.prepareStatement("select * from nail");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id + 1;
    }

    @Override
    public String toString() {
        return prodname;
    }
    
     public static Object[][] view_date1_date2(String date1,String date2) {
        List<NailS> l = NailS.getList_Date1_Date2(date1,date2);
        int no = 0;
        Object[][] v = new Object[l.size()][5];
        for (int i = 0; i < l.size(); i++) {
            no++;
            v[i][0] = l.get(i).getProd_id();
            v[i][1] = l.get(i).getProdname();
            v[i][2] = l.get(i).getQty();
            v[i][3] = l.get(i).getRecordingdate();
            v[i][4] = l.get(i).getDetails();
            
        }

        return v;

    }
    
 public static Object[][] view(String date) {
        List<NailS> l = NailS.getList(date);
        int no = 0;
        Object[][] v = new Object[l.size()][5];
        for (int i = 0; i < l.size(); i++) {
            no++;
            v[i][0] = l.get(i).getProd_id();
            v[i][1] = l.get(i).getProdname();
            v[i][2] = l.get(i).getQty();
            v[i][3] = l.get(i).getRecordingdate();
            v[i][4] = l.get(i).getDetails();
            
        }

        return v;

    }
    public static Object[][] view() {
        List<NailS> l = NailS.getDepartmentList();
        int no = 0;
        Object[][] v = new Object[l.size()][5];
        for (int i = 0; i < l.size(); i++) {
            no++;
            v[i][0] = l.get(i).getProd_id();
            v[i][1] = l.get(i).getProdname();
            v[i][2] = l.get(i).getQty();
            v[i][3] = l.get(i).getRecordingdate();
            v[i][4] = l.get(i).getDetails();
            
        }

        return v;

    }
public static List<NailS> getProductForworkshopList() {
        List<NailS> l = new ArrayList<>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from nail where business like 'workshop'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                NailS pr = new NailS();

                pr.setProd_id(rs.getInt(1));
                pr.setProdname(rs.getString(2));
                pr.setQty(rs.getDouble(3));
pr.setRecordingdate(rs.getDate(4));
pr.setDetails(rs.getString(5));
                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
    public static List<NailS> getProductForHardwareList() {
        List<NailS> l = new ArrayList<>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from nail");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                NailS pr = new NailS();

                pr.setProd_id(rs.getInt(1));
                pr.setProdname(rs.getString(2));
                pr.setQty(rs.getDouble(3));
               pr.setRecordingdate(rs.getDate(4));
               pr.setDetails(rs.getString(5));
                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
    public static List<NailS> getDepartmentList() {
        List<NailS> l = new ArrayList<>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from nail order by prodname");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                NailS pr = new NailS();

                pr.setProd_id(rs.getInt(1));
                pr.setProdname(rs.getString(2));
                pr.setQty(rs.getDouble(3));
                pr.setRecordingdate(rs.getDate(4));
                pr.setDetails(rs.getString(5));
                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public static List<NailS> getList_Date1_Date2(String g1,String g2) {
        List<NailS> l = new ArrayList<>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from nail where recording_date between'"+g1+"' and '"+g2+"' order by prodname");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                NailS pr = new NailS();

                pr.setProd_id(rs.getInt(1));
                pr.setProdname(rs.getString(2));
                pr.setQty(rs.getDouble(3));
                pr.setRecordingdate(rs.getDate(4));
                pr.setDetails(rs.getString(5));
                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
    public static List<NailS> getList(String g) {
        List<NailS> l = new ArrayList<>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from nail where recording_date='"+g+"' order by prodname");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                NailS pr = new NailS();

                pr.setProd_id(rs.getInt(1));
                pr.setProdname(rs.getString(2));
                pr.setQty(rs.getDouble(3));
                pr.setRecordingdate(rs.getDate(4));
                pr.setDetails(rs.getString(5));
                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
    
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    
    public boolean update() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("update nail set prodname=?,qty=?,recording_date=?,details=? where prod_id=?");

            //deptid=Integer.parseInt(deptidvalue);
            pst.setInt(5, prod_id);
            pst.setString(1, prodname);
            pst.setDouble(2, qty);
            pst.setDate(3, new java.sql.Date(recordingdate.getTime()));
            pst.setString(4,details);
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("errrrorr........." + e);
            e.printStackTrace();
            return false;
        }
    }

    public boolean insert() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into nail values(?,?,?,?,?)");

            //deptid=Integer.parseInt(deptidvalue);
            pst.setInt(1, prod_id);
            pst.setString(2, prodname);
            pst.setDouble(3, qty);
            pst.setDate(4, new java.sql.Date(recordingdate.getTime()));
            pst.setString(5,details);
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("errrrorr........." + e);
            e.printStackTrace();
            return false;
        }
    }

    public NailS() {
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getDeptnamesmsg() {
        return deptnamesmsg;
    }

    public void setDeptnamesmsg(String deptnamesmsg) {
        this.deptnamesmsg = deptnamesmsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
