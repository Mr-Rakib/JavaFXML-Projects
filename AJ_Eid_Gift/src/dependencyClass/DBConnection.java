/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependencyClass;

import aj_eid_gift.FXMLDocumentController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAKIB
 */
public class DBConnection {

    private String USERNAME;
    private String PASSWORD;
    private String HOSTNAME;
    private String DBNAME;
    private String DBURL;

    public DBConnection() {
    }
    
    
    public Connection  startConnect(){
    USERNAME  = "root";
    PASSWORD  = "";
    HOSTNAME  = "localhost";
    DBNAME  = "projectDB";
     Connection conn = null;
    
    
     DBURL = "jdbc:mysql://" + HOSTNAME + "/" + DBNAME;

        try {
            conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;

    }
}

    

