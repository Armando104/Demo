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
public class Expense {

    private int expense_id;
    private String description, business;//,operator="Save",deptidvalue,dobvalue,studentidvalue,namesmsg,studentidmsg,deptidmsg,msg,dobmsg;
    private Date recording_date;
    private double amount;

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public static Expense getExpense(String id) {
        Expense pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from expenses where expense_id= " + id + "");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new Expense();
                pr.setExpense_id(rs.getInt(1));
                pr.setBusiness(rs.getString(2));
                pr.setDescription(rs.getString(3));
                pr.setRecording_date(rs.getDate(5));
                pr.setAmount(rs.getDouble(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }

    public Expense() {
    }

    public boolean delete() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("delete from expenses where expense_id=?");
            pst.setInt(1, expense_id);

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
            PreparedStatement pst = con.prepareStatement("select * from expenses");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id + 1;
    }

    public static List<Expense> getStudentList() {
        List<Expense> l = new ArrayList<Expense>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from expenses");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Expense pr = new Expense();
                pr.setExpense_id(rs.getInt(1));
                pr.setBusiness(rs.getString(2));
                pr.setDescription(rs.getString(3));
                pr.setRecording_date(rs.getDate(5));
                pr.setAmount(rs.getDouble(4));

                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public static List<Expense> getStudentList(String n) {
        List<Expense> l = new ArrayList<Expense>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from expenses where business like '" + n + "'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Expense pr = new Expense();
                pr.setExpense_id(rs.getInt(1));
                pr.setBusiness(rs.getString(2));
                pr.setDescription(rs.getString(3));
                pr.setRecording_date(rs.getDate(5));
                pr.setAmount(rs.getDouble(4));

                l.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
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
  public static Object[][] viewHardware(Date d) {
        List<Expense> l = Expense.getStudentList("hardware");
        int no = 0,k=0;
         Calendar cal=new GregorianCalendar();
         Calendar cal1=new GregorianCalendar();
         cal1.setTime(d);
        String date="";String date1="";
        Object[][] v = new Object[l.size()][5];
        for (int i = 0; i < l.size(); i++) {
             cal.setTime(l.get(i).getRecording_date());
            String day="",month="";String day1="",month1="";
            if (l.get(i).getBusiness().equalsIgnoreCase("hardware")) {
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
                no++;
                v[k][0] = l.get(i).getExpense_id();
                v[k][1] = l.get(i).getBusiness();
                v[k][2] = l.get(i).getDescription();
                v[k][3] = l.get(i).getAmount();
                v[k][4] = l.get(i).getRecording_date();
            }
//        v[i][4]=l.get(i).getSelling_unitprice();
//        v[i][5]= l.get(i).getPurchasing_unitprice();
//        v[i][6]= l.get(i).getPurchasing_date();
                // v[i][7]= l.get(i).getQty();
            }
        }

        return v;

    }
    public static Object[][] viewWorkshop(Date d) {
        List<Expense> l = Expense.getStudentList();
        int no = 0,k=0;
         Calendar cal=new GregorianCalendar();
         Calendar cal1=new GregorianCalendar();
         cal1.setTime(d);
        String date="";String date1="";
        Object[][] v = new Object[l.size()][5];
        for (int i = 0; i < l.size(); i++) {
             cal.setTime(l.get(i).getRecording_date());
            String day="",month="";String day1="",month1="";
            if (l.get(i).getBusiness().equalsIgnoreCase("hardware")) {
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
                no++;
                v[k][0] = l.get(i).getExpense_id();
                v[k][1] = l.get(i).getBusiness();
                v[k][2] = l.get(i).getDescription();
                v[k][3] = l.get(i).getAmount();
                v[k][4] = l.get(i).getRecording_date();
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
    
  public static Object[][] viewHardware(Calendar ca) {
        List<Expense> l = Expense.getStudentList();
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

  public static Object[][] viewWorkshop(Calendar ca) {
        List<Expense> l = Expense.getStudentList("workshop");
        int no = 0,k=0;
          Calendar cal=new GregorianCalendar();
        Object[][] v = new Object[l.size()][5];
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getBusiness().equalsIgnoreCase("workshop")) {
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
  public static Object[][] viewHardware() {
        List<Expense> l = Expense.getStudentList("hardware");
        int no = 0;
        Object[][] v = new Object[l.size()][5];
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getBusiness().equalsIgnoreCase("hardware")) {
                no++;
                v[i][0] = no;
                v[i][1] = l.get(i).getBusiness();
                v[i][2] = l.get(i).getDescription();
                v[i][3] = l.get(i).getAmount();
                v[i][4] = l.get(i).getRecording_date();
//        v[i][4]=l.get(i).getSelling_unitprice();
//        v[i][5]= l.get(i).getPurchasing_unitprice();
//        v[i][6]= l.get(i).getPurchasing_date();
                // v[i][7]= l.get(i).getQty();
            }
        }

        return v;

    }

  
    public static Object[][] viewExpenseNail() {
        List<Expense> l = Expense.getStudentList("nail");
        int no = 0;
        Object[][] v = new Object[l.size()][5];
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getBusiness().equalsIgnoreCase("nail")) {
                no++;
                v[i][0] = no;
                v[i][1] = l.get(i).getBusiness();
                v[i][2] = l.get(i).getDescription();
                v[i][3] = l.get(i).getAmount();
                v[i][4] = l.get(i).getRecording_date();

            }
        }

        return v;

    }
    public static Object[][] viewWorkshop() {
        List<Expense> l = Expense.getStudentList("workshop");
        int no = 0;
        Object[][] v = new Object[l.size()][5];
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getBusiness().equalsIgnoreCase("workshop")) {
                no++;
                v[i][0] =l.get(i).getExpense_id();
                v[i][1] = l.get(i).getBusiness();
                v[i][2] = l.get(i).getDescription();
                v[i][3] = l.get(i).getAmount();
                v[i][4] = l.get(i).getRecording_date();

            }
        }

        return v;

    }

    public static Object[][] view() {
        List<Expense> l = Expense.getStudentList();
        int no = 0;
        Object[][] v = new Object[l.size()][5];
        for (int i = 0; i < l.size(); i++) {
            no++;
            v[i][0] = no;
            v[i][1] = l.get(i).getBusiness();
            v[i][2] = l.get(i).getDescription();
            v[i][3] = l.get(i).getAmount();
            v[i][4] = l.get(i).getRecording_date();
//        v[i][4]=l.get(i).getSelling_unitprice();
//        v[i][5]= l.get(i).getPurchasing_unitprice();
//        v[i][6]= l.get(i).getPurchasing_date();
            // v[i][7]= l.get(i).getQty();
        }

        return v;

    }

    public boolean update() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("update expenses set business=?,description=?,amount=?,recording_date=? where expense_id=?");
            pst.setInt(5, expense_id);
            pst.setString(1, business);
            pst.setString(2, description);
            pst.setDouble(3, amount);
            pst.setDate(4, new java.sql.Date(recording_date.getTime()));
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
            PreparedStatement pst = con.prepareStatement("insert into expenses values(?,?,?,?,?)");
            pst.setInt(1, expense_id);
            pst.setString(2, business);
            pst.setString(3, description);
            pst.setDouble(4, amount);
            pst.setDate(5, new java.sql.Date(recording_date.getTime()));
            pst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(int expense_id) {
        this.expense_id = expense_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
