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
public class Sale {

    private int sale_id,prod_id;
    //private String description;
    private Date selling_date;
    private double qty,received_amount,unitprice;

    
public static Object[][]view(Date d){
   List<Sale>l=Sale.getStudentList();
   int no=0;
   
    Calendar cal=new GregorianCalendar();
         Calendar cal1=new GregorianCalendar();
         cal1.setTime(d);
        String date="";String date1="";
   
   Object[][]v=new Object[l.size()][8];
   for(int i=0;i<l.size();i++){
       
        cal.setTime(l.get(i).getSelling_date());
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
            
             System.out.println("day from search.........."+day+"......."+l.get(i).getSelling_date());
           
            System.out.println("month from search.........."+month+"......."+l.get(i).getSelling_date());
           
            System.out.println("date from search.........."+date+"......."+l.get(i).getSelling_date());
       
  if (date.equalsIgnoreCase(date1)) {
       no++;
       Product pr=Product.getProduct(l.get(i).getProd_id()+"");
       Purchase pu=Purchase.getPurchase1(pr.getProd_id()+"");
      v[i][0]=no;
        v[i][1]=pr.getProdname();
        v[i][2]=l.get(i).getQty();
        v[i][3]= l.get(i).getUnitprice();
        v[i][4]=l.get(i).getReceived_amount();
        v[i][5]=l.get(i).getSelling_date();
         v[i][6]=pu.getPurchasing_unitprice();
         double m=(l.get(i).getReceived_amount())-(pu.getPurchasing_unitprice()*l.get(i).getQty());
         //double n=m;
         if(m<0){
          v[i][7]="("+(m)+")" ; 
         }else{
           v[i][7]=m ;
         }
  }
        
       // v[i][7]= l.get(i).getQty();
   }
   
   return  v;
   
}
  
public static Object[][]view(){
   List<Sale>l=Sale.getStudentList();
   int no=0;
   Object[][]v=new Object[l.size()][8];
   for(int i=0;i<l.size();i++){
       no++;
       Product pr=Product.getProduct(l.get(i).getProd_id()+"");
       Purchase pu=Purchase.getPurchase1(pr.getProd_id()+"");
      v[i][0]=l.get(i).getSale_id();
        v[i][1]=pr.getProdname();
        v[i][2]=l.get(i).getQty();
        v[i][3]= l.get(i).getUnitprice();
        v[i][4]=l.get(i).getReceived_amount();
        v[i][5]=l.get(i).getSelling_date();
         v[i][6]=pu.getPurchasing_unitprice();
         double m=(l.get(i).getReceived_amount())-(pu.getPurchasing_unitprice()*l.get(i).getQty());
         //double n=m;
         if(m<0){
          v[i][7]="("+(m)+")" ; 
         }else{
           v[i][7]=m ;
         }
        
       // v[i][7]= l.get(i).getQty();
   }
   
   return  v;
   
}
public static Object[][]view(Calendar ca){
   List<Sale>l=Sale.getStudentList();
   int no=0;int k=0;
   Object[][]v=new Object[l.size()][8];
     Calendar cal=new GregorianCalendar();
   for(int i=0;i<l.size();i++){
         cal.setTime(l.get(i).getSelling_date());
        
         if(cal.get(Calendar.YEAR)==ca.get(Calendar.YEAR)&&ca.get(Calendar.MONTH)==cal.get(Calendar.MONTH)){
         no++;
       
       Product pr=Product.getProduct(l.get(i).getProd_id()+"");
       Purchase pu=Purchase.getPurchase1(pr.getProd_id()+"");
      v[k][0]=no;
        v[k][1]=pr.getProdname();
        v[k][2]=l.get(i).getQty();
        v[k][3]= l.get(i).getUnitprice();
        v[k][4]=l.get(i).getReceived_amount();
        v[k][5]=l.get(i).getSelling_date();
         v[k][6]=pu.getPurchasing_unitprice();
         double m=(l.get(i).getReceived_amount())-(pu.getPurchasing_unitprice()*l.get(i).getQty());
         //double n=m;
         if(m<0){
          v[k][7]="("+(m)+")" ; 
         }else{
           v[k][7]=m ;
         }
         k++;
        }
       // v[i][7]= l.get(i).getQty();
   }
   
   return  v;
   
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
            if (date.equalsIgnoreCase(date1)) {
                System.out.println("yes.............");
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
            }else{
               System.out.println("day from search........noooooooooo......");
            }
           
            //break;

            // v[i][7]= l.get(i).getQty();
        }

        return v;

    }
    public static Sale getPurchase(String id) {
        Sale pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from sales where sale_id= " + id + "");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new Sale();
                pr.setSale_id(rs.getInt(1));
                pr.setProd_id(rs.getInt(2));
                pr.setQty(rs.getDouble(3));
                pr.setUnitprice(rs.getDouble(4));
                pr.setReceived_amount(rs.getDouble(5));
              
                pr.setSelling_date(rs.getDate(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }
 public static Double getCountSales(String d) {
        double id = 0;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select count(received_amount) from sales where selling_date=" + d + "");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    public boolean updateforsales() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("update purchase set qty=qty-? where prod_id=?");
            pst.setDouble(1, qty);
            pst.setInt(2, prod_id);
         
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println(">>>>Error Hint>> "+e);
            e.printStackTrace();
            return false;
        }
    }
    public boolean update() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("update sales set prod_id=?, qty=?,unitprice=?,received_amount=?,selling_date=? where sale_id=?");
            pst.setDouble(2, qty);
            pst.setInt(1, prod_id);
            pst.setDouble(3, unitprice);
            pst.setDouble(4, received_amount);
            pst.setDate(5, new java.sql.Date(selling_date.getTime()));
            pst.setInt(6, sale_id);
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println(">>>>Error Hint>> "+e);
            e.printStackTrace();
            return false;
        }
    }
    public boolean delete() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("delete from sales where sale_id=?");
            pst.setInt(1, sale_id);
         
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
            PreparedStatement pst = con.prepareStatement("select * from sales");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id + 1;
    }

    public static List<Sale> getStudentList() {
        List<Sale> l = new ArrayList<Sale>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from sales");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Sale pr = new Sale();
                  pr.setSale_id(rs.getInt(1));
                pr.setProd_id(rs.getInt(2));
                pr.setQty(rs.getDouble(3));
                pr.setUnitprice(rs.getDouble(4));
                pr.setReceived_amount(rs.getDouble(5));
              
                pr.setSelling_date(rs.getDate(6));
                 
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
            PreparedStatement pst = con.prepareStatement("insert into sales values(?,?,?,?,?,?)");
            pst.setInt(1, sale_id);
            pst.setInt(2, prod_id);
            //pst.setString(3, description);
            
            pst.setDouble(3, qty);
            pst.setDouble(4, unitprice);
             pst.setDouble(5, received_amount);
            pst.setDate(6, new java.sql.Date(selling_date.getTime()));
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

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public Date getSelling_date() {
        return selling_date;
    }

    public void setSelling_date(Date selling_date) {
        this.selling_date = selling_date;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getReceived_amount() {
        return received_amount;
    }

    public void setReceived_amount(double received_amount) {
        this.received_amount = received_amount;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    
 

    
    
}
