/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;

/**
 *
 * @author ulises
 */
public class baseDatos {
    public    Connection conn = null;
    public    String sqlurl = "jdbc:mysql://localhost:3060/";
    public    String driver = "com.mysql.jdbc.Driver";
    public    String dbuser = "parqueo";
    public    String dbpass = "r3p34r";
    public    Statement st;
    
    
    public static ResultSet ingresarAutos(){
        ResultSet rs=null;
        
        
        return rs;
    }
    
    
    
}
