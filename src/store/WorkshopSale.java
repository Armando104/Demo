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
public class WorkshopSale {

    private int sale_id;
    private String material, emp, client, details;//,operator="Save",deptidvalue,dobvalue,studentidvalue,namesmsg,studentidmsg,deptidmsg,msg,dobmsg;
    private Date recording_date;
    private double amount;

    public static WorkshopSale getExpense(String id) {
        WorkshopSale pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from expenses where expense_id= " + id + "");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new WorkshopSale();
                pr.setSale_id(rs.getInt(1));
                pr.setMaterial(rs.getString(2));
                pr.setEmp(rs.getString(3));
                pr.setClient(rs.getString(4));
                pr.setDetails(rs.getString(5));

                pr.setAmount(rs.getDouble(6));
                pr.setRecording_date(rs.getDate(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }

    public WorkshopSale() {
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
            PreparedStatement pst = con.prepareStatement("select * from workshopsales");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id + 1;
    }

    public static List<WorkshopSale> getStudentList() {
        List<WorkshopSale> l = new ArrayList<WorkshopSale>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from workshopsales");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                WorkshopSale pr = new WorkshopSale();
                pr.setSale_id(rs.getInt(1));
                pr.setMaterial(rs.getString(2));
                pr.setEmp(rs.getString(3));
                pr.setClient(rs.getString(4));
                pr.setDetails(rs.getString(5));

                pr.setAmount(rs.getDouble(6));
                pr.setRecording_date(rs.getDate(7));
                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
 

    public static Object[][] view(Date d) {
        List<WorkshopSale> l = WorkshopSale.getStudentList();
        int no = 0,k=0;
        Calendar cal = new GregorianCalendar();
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(d);
        String date = "";
        String date1 = "";
        Object[][] v = new Object[l.size()][7];
        for (int i = 0; i < l.size(); i++) {
            cal.setTime(l.get(i).getRecording_date());
            String day = "", month = "";
            String day1 = "", month1 = "";

            if ((cal.get(Calendar.MONTH)) < 10) {
                month = "0" + (cal.get(Calendar.MONTH) + 1);
            } else {
                month = "" + (cal.get(Calendar.MONTH) + 1);
            }

            if ((cal1.get(Calendar.MONTH) + 1) < 10) {
                month1 = "0" + (cal1.get(Calendar.MONTH) + 1);
            } else {
                month1 = "" + (cal1.get(Calendar.MONTH) + 1);
            }
            System.out.println("Month........" + month);
            System.out.println("Month1........" + month1);


            if ((cal.get(Calendar.DAY_OF_MONTH)) < 10) {
                day = "0" + (cal.get(Calendar.DAY_OF_MONTH));
            } else {
                day = "" + (cal.get(Calendar.DAY_OF_MONTH));
            }

            if ((cal1.get(Calendar.DAY_OF_MONTH)) < 10) {
                day1 = "0" + (cal1.get(Calendar.DAY_OF_MONTH));
            } else {
                day1 = "" + (cal1.get(Calendar.DAY_OF_MONTH));
            }

            date = cal.get(Calendar.YEAR) + "-" + month + "-" + day;

            date1 = cal1.get(Calendar.YEAR) + "-" + month1 + "-" + day1;

            System.out.println("date from search.........." + date + ".......Date from db..." + date1);
            System.out.println("Day........" + day);
            System.out.println("Day1........" + day1);


            no++;
            if (date.equalsIgnoreCase(date1)) {
                no++;
                v[k][0] =l.get(i).getSale_id() ;
                v[k][1] = l.get(i).getMaterial();
                v[k][2] = l.get(i).getEmp();
                v[k][3] = l.get(i).getAmount();
                v[k][4] = l.get(i).getRecording_date();
                  v[k][5] = l.get(i).getClient();
            v[k][6] =l.get(i).getDetails() ;
            k++;
                //v[i][6]= l.get(i).getPurchasing_date();
            } //v[i][7]= l.get(i).getQty();
        }

        return v;

    }
public static Object[][] viewHardware(Calendar ca) {
        List<Expense> l = Expense.getStudentList("hardware");
        int no = 0,k=0;
          Calendar cal=new GregorianCalendar();
        Object[][] v = new Object[l.size()][5];
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getBusiness().equalsIgnoreCase("hardware")) {
                  cal.setTime(l.get(i).getRecording_date());
        
         if(cal.get(Calendar.YEAR)==ca.get(Calendar.YEAR)&&ca.get(Calendar.MONTH)==cal.get(Calendar.MONTH)){
                no++;
                v[k][0] = no;
                v[k][1] = l.get(i).getBusiness();
                v[k][2] = l.get(i).getDescription();
                v[k][3] = l.get(i).getAmount();
                v[k][4] = l.get(i).getRecording_date();
                k++;
         }
//        v[i][4]=l.get(i).getSelling_unitprice();
//        v[i][5]= l.get(i).getPurchasing_unitprice();
//        v[i][6]= l.get(i).getPurchasing_date();
                // v[i][7]= l.get(i).getQty();
            }
        }

        return v;

    }
    public static Object[][] view(Calendar ca) {
        List<WorkshopSale> l = WorkshopSale.getStudentList();
        int no = 0,k=0;
        Object[][] v = new Object[l.size()][7];
           Calendar cal=new GregorianCalendar();
        for (int i = 0; i < l.size(); i++) {
            
                  cal.setTime(l.get(i).getRecording_date());
        
         if(cal.get(Calendar.YEAR)==ca.get(Calendar.YEAR)&&ca.get(Calendar.MONTH)==cal.get(Calendar.MONTH)){
            no++;
            v[k][0] =l.get(i).getSale_id();
            v[k][1] = l.get(i).getMaterial();
            v[k][2] = l.get(i).getEmp();
            v[k][3] = l.get(i).getAmount();
            v[k][4] = l.get(i).getRecording_date();
            v[k][5] = l.get(i).getClient();
            v[k][6] =l.get(i).getDetails() ;
            k++;
            //v[i][6]= l.get(i).getPurchasing_date();
            //v[i][7]= l.get(i).getQty();
        }
     
    }

        return v;

    }

    public static Object[][] view() {
        List<WorkshopSale> l = WorkshopSale.getStudentList();
        int no = 0;
        Object[][] v = new Object[l.size()][7];
        for (int i = 0; i < l.size(); i++) {
            no++;
            v[i][0] = l.get(i).getSale_id();
            v[i][1] = l.get(i).getMaterial();
            v[i][2] = l.get(i).getEmp();
            v[i][3] = l.get(i).getAmount();
            v[i][4] = l.get(i).getRecording_date();
              v[i][5] = l.get(i).getClient();
            v[i][6] =l.get(i).getDetails() ;
            //v[i][6]= l.get(i).getPurchasing_date();
            //v[i][7]= l.get(i).getQty();
        }

        return v;

    }

    public boolean insert() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into workshopsales values(?,?,?,?,?,?,?)");
            pst.setInt(1, sale_id);
            pst.setString(2, material);
            pst.setString(3, emp);
            pst.setString(4, client);
            pst.setString(5, details);
            pst.setDouble(6, amount);
            pst.setDate(7, new java.sql.Date(recording_date.getTime()));
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
            PreparedStatement pst = con.prepareStatement("update workshopsales set material=?,emp=?,client=?,details=?,amount=?,recordingdate=? where saleid=?");
            pst.setInt(7, sale_id);
            pst.setString(1, material);
            pst.setString(2, emp);
            pst.setString(3, client);
            pst.setString(4, details);
            pst.setDouble(5, amount);
            pst.setDate(6, new java.sql.Date(recording_date.getTime()));
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
