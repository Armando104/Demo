/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author CLAIRE
 */
public class User {
   
    private String username,usernamemsg;
    private String password,passwordmsg;
    private String role,rolemsg; 

    public User() {
    }
public boolean insert() {
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into user values(?,?,?)");
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, role);
             
            
            pst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
public void tt(List<String> l){

}
public static void main(String args[]){
    System.out.println(User.getUser("admin").getUsername());

}
 public static List<User> getUserList() {
        List<User> l = new ArrayList<User>();
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from user");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User pr = new User();
                pr.setUsername(rs.getString(1));
                pr.setPassword(rs.getString(2));
                pr.setRole(rs.getString(3));
                
                l.add(pr);
            }
        } catch (Exception e) {
        }
        return l;
    }
public static User getUser(String Citizen_id) {
       User pr = null;
        try {
            Connection con = DBClassConnector.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from user where username='" + Citizen_id + "'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pr = new User();
                  
                pr.setUsername(rs.getString(1));
                pr.setPassword(rs.getString(2));
                pr.setRole(rs.getString(3));
             }
        } catch (Exception e) {
        }
        return pr;
    }
 
 
// public boolean update() {
//        try {
//            Connection con = DBClassConnector.getConnection();
//            PreparedStatement pst = con.prepareStatement("update citizen set citizen_names=?, tel=?,address=? where citizen_id=?");
//            pst.setInt(6, citizen_id);
//            pst.setString(1, citizen_names);
//            pst.setString(2, tel);
//            pst.setString(3,address);
//           
//            pst.execute();
//            return true;
//        } catch (Exception ex) {
//                JOptionPane.showMessageDialog(null,ex);
//            return false;
//        }
//
//    }
//
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
