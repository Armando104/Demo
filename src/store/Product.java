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
public class Product {

    private int prod_id;
    private String prodname, business, deptidvalue, deptidmsg, deptnamesmsg, msg = "Fill All Fields", operator = "save";
    private boolean valid = true;

    public static Product getProduct(String id) {
        Product pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from product where prod_id= " + id + "");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new Product();
                pr.setProd_id(rs.getInt(1));
                pr.setProdname(rs.getString(2));
                pr.setBusiness(rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
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

    public static Product getProductFromName(String id) {
        Product pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from product where prodname= '" + id + "'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new Product();
                pr.setProd_id(rs.getInt(1));
                pr.setProdname(rs.getString(2));
                pr.setBusiness(rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }
    
    public static Product getProductFromName_and_business(String id,String bus) {
        Product pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from product where prodname= '" + id + "' and business='"+bus+"'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new Product();
                pr.setProd_id(rs.getInt(1));
                pr.setProdname(rs.getString(2));
                pr.setBusiness(rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }

    public boolean delete() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("delete from product where prod_id=?");
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
            PreparedStatement pst = con.prepareStatement("select * from product");
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

    public static Object[][] view() {
        List<Product> l = Product.getDepartmentList();
        int no = 1;
        Object[][] v = new Object[l.size()][4];
        for (int i = 0; i < l.size(); i++) {
            
            v[i][0] = no;
            v[i][1] = l.get(i).getProdname();
            v[i][2] = l.get(i).getBusiness();
            v[i][3] = l.get(i).getProd_id();
            no++;
        }

        return v;

    }
    public static Object[][] viewSearch(String n) {
        List<Product> l = Product.getDepartmentList();
        int no = 1,k=0;
        
        Object[][] v = new Object[l.size()][4];
        for (int i = 0; i < l.size(); i++) {
           
            if(l.get(i).getProdname().contains(n)){
              v[k][0] = no;
            v[k][1] = l.get(i).getProdname();
            v[k][2] = l.get(i).getBusiness();
            v[k][3] = l.get(i).getProd_id();
            k++;
             no++;
            }
            
        }

        return v;

    }
public static List<Product> getProductForworkshopList() {
        List<Product> l = new ArrayList<>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from product where business like 'workshop' order by prodname");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Product pr = new Product();

                pr.setProd_id(rs.getInt(1));
                pr.setProdname(rs.getString(2));
                pr.setBusiness(rs.getString(3));

                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

public static List<Product> getProductForNailList_Sales() {
        List<Product> l = new ArrayList<>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from product where business like 'nail' order by prodname");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Product pr = new Product();

                pr.setProd_id(rs.getInt(1));
                pr.setProdname(rs.getString(2));
                pr.setBusiness(rs.getString(3));
                 Nail_Store ns=Nail_Store.getPurchase_From_Prod_ID(pr.getProd_id()+"");
                 if(ns!=null){
                    if(ns.getQty()>0){
                     l.add(pr);
                    }
                 }
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }


public static List<Product> getProductForNailList() {
        List<Product> l = new ArrayList<>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from product where business like 'nail' order by prodname");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Product pr = new Product();

                pr.setProd_id(rs.getInt(1));
                pr.setProdname(rs.getString(2));
                pr.setBusiness(rs.getString(3));

                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
    public static List<Product> getProductForHardwareList() {
        List<Product> l = new ArrayList<>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from product  where business like 'hardware' order by prodname");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Product pr = new Product();

                pr.setProd_id(rs.getInt(1));
                pr.setProdname(rs.getString(2));
                pr.setBusiness(rs.getString(3));

                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
    public static List<Product> getDepartmentList() {
        List<Product> l = new ArrayList<>();
        try {
            Connection con = DBClassConnector1.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from product order by prodname");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Product pr = new Product();
                pr.setProd_id(rs.getInt(1));
                pr.setProdname(rs.getString(2));
                pr.setBusiness(rs.getString(3));

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

    public boolean insert() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into product values(?,?,?)");

            //deptid=Integer.parseInt(deptidvalue);
            pst.setInt(1, prod_id);
            pst.setString(2, prodname);
            pst.setString(3, business);
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("errrrorr........." + e);
            e.printStackTrace();
            return false;
        }
    }
    
 public boolean UpdateProduct() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("update product set prodname=?,business=? where prod_id=?");

            //deptid=Integer.parseInt(deptidvalue);
            pst.setInt(3, prod_id);
            pst.setString(1, prodname);
            pst.setString(2, business);
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("errrrorr........." + e);
            e.printStackTrace();
            return false;
        }
    }
    public Product() {
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
