/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author ulises
 */
public class baseDatos {
    public static   Connection conn = null;
    public static   String sqlurl = "jdbc:mysql://localhost:3306/";
    public static   String databas = "parqueo?allowMultiQueries=true";
    public static   String driver = "com.mysql.jdbc.Driver";
    public static   String dbuser = "parqueo";
    public static   String dbpass = "r3p34r";
    public static   Statement st;
    
    
    public static void ingresarAutos(String tipo, int posicion){
        /*
        Este metodo ingresa el carro al parqueo, y tambien inicia el registro
        
        */
        // Para capturar el id del vehiculo de la db
        int idVehiculo;
        
        //query
        String consultasqlIn="INSERT INTO vehiculos(tipo,estado,posicion) VALUES('"+tipo+"',1,"+posicion+")";
        
        
        //tiempo
        String Tiempo = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        
        String consultaPos="SELECT idVehiculo FROM vehiculos WHERE posicion="+posicion+" AND estado=1";
        
        
        try{
            Class.forName(driver).newInstance();
            conn = (Connection) DriverManager.getConnection(sqlurl+databas,dbuser,dbpass);
            st = (Statement) conn.createStatement();
            st.executeUpdate(consultasqlIn);
            st.close();
            conn.close();  
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        
        
    }
    
    
    
    
    
    
}
