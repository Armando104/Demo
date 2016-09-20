/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static store.User.getMaxID;

/**
 *
 * @author CLAIRE
 */
public class User {

    private int user_id=getMaxID();
    private String username, usernamemsg;
    private String password, passwordmsg;
    private String role,status, rolemsg,msg;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public User() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
 public static int getMaxID() {
        int id = 10190;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from users");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id + 1;
    }
    public boolean insert() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into users values(?,?,?,?)");
            pst.setInt(1, user_id);
             
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setString(4, role);
            

            pst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", usernamemsg=" + usernamemsg + ", password=" + password + ", passwordmsg=" + passwordmsg + ", role=" + role + ", rolemsg=" + rolemsg + '}';
    }

    public static Object[][]view(){
   List<User>l=User.getUserList();
   int no=0;
   Object[][]v=new Object[l.size()][3];
   for(int i=0;i<l.size();i++){
       no++;
       
      v[i][0]=l.get(i).getUsername();
        v[i][1]=l.get(i).getPassword();
        v[i][2]=l.get(i).getRole(); 
        
       // v[i][7]= l.get(i).getQty();
   }
   
   return  v;
   
}

    public void tt(List<String> l) {
    }

    

    public static List<User> getUserList() {
        List<User> l = new ArrayList<User>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from users");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User pr = new User();
                pr.setUser_id(rs.getInt(1));
             
                pr.setUsername(rs.getString(2));
                pr.setPassword(rs.getString(3));
                pr.setRole(rs.getString(4));
                
                 {
                    l.add(pr);
                 }
              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
 public static User getUser(String un) {
        User pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from users where usr_username='" + un + "'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new User();

               pr.setUser_id(rs.getInt(1));
             
                pr.setUsername(rs.getString(2));
                pr.setPassword(rs.getString(3));
                pr.setRole(rs.getString(4));
                 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }
    public static User getUser_ID(String Citizen_id) {
        User pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from users where user_id=" + Citizen_id + "");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new User();

               pr.setUser_id(rs.getInt(1));
             
                pr.setUsername(rs.getString(2));
                pr.setPassword(rs.getString(3));
                pr.setRole(rs.getString(4));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }

 public boolean update() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("update students set user_id=? where stu_reg_number=?");
            pst.setInt(1, user_id);
            pst.setString(2, username);
             
           
            pst.execute();
            return true;
        } catch (Exception ex) {
               ex.printStackTrace();
            return false;
        }

    }
  

  

//    public boolean delete() {
//        try {
//            Connection con = DBClassConnector.getConnection();
//            PreparedStatement pst = con.prepareStatement("delete from citizen where citizen _id=?");
//
//            pst.setInt(1, citizen_id);
//            pst.execute();
//            con.close();
//            return true;
//        } catch (Exception ex) {
//
//            return false;
//        }
//
//    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernamemsg() {
        return usernamemsg;
    }

    public void setUsernamemsg(String usernamemsg) {
        this.usernamemsg = usernamemsg;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordmsg() {
        return passwordmsg;
    }

    public void setPasswordmsg(String passwordmsg) {
        this.passwordmsg = passwordmsg;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRolemsg() {
        return rolemsg;
    }

    public void setRolemsg(String rolemsg) {
        this.rolemsg = rolemsg;
    }
}
