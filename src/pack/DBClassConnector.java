/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author The Brain Key
 */
public class DBClassConnector {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //jdbc:mysql://localhost/koryo", "root", ""
            conn = DriverManager.getConnection("jdbc:mysql://localhost/smanzi", "root", "");

        } catch (Exception e) {
            e.printStackTrace();

        }
        return conn;
    }
 

}
