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
import java.sql.SQLException;
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
    
    
    
    public static void ingresarAutos(String tipo, int posicion)throws SQLException {
        /*
        Este metodo ingresa el carro al parqueo, y tambien inicia el registro
        
        */
        
        Statement st;
        
        //query
        String consultasqlIn="INSERT INTO vehiculos(tipo,estado,posicion) VALUES('"+tipo+"',1,"+posicion+")";
        
        
        //tiempo
       // String Tiempo = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        
        //String consultaPos="SELECT idVehiculo FROM vehiculos WHERE posicion="+posicion+" AND estado=1";
        
        
        try{
            Class.forName(driver).newInstance();
            conn = (Connection) DriverManager.getConnection(sqlurl+databas,dbuser,dbpass);
            st = (Statement) conn.createStatement();
            st.executeUpdate(consultasqlIn);
            JOptionPane.showMessageDialog(null,"Agregado");
           
        }catch(Exception e){
             
        }
    }
    
    
    public static void eliminarVehiculos(String tipo, int posicion){
        Statement st;
       
        String eliminarv="UPDATE vehiculos set estado=0 WHERE tipo='"+tipo+"' AND posicion="+posicion+" AND estado=1";
        //JOptionPane.showMessageDialog(null, eliminarv);
        try{
            Class.forName(driver).newInstance();
            conn = (Connection) DriverManager.getConnection(sqlurl+databas,dbuser,dbpass);
            st = (Statement) conn.createStatement();
            st.executeUpdate(eliminarv);
            JOptionPane.showMessageDialog(null, "Eliminado");
            
           
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }
        
    }
    /*
    public static void agregarRegistro(String tipo, int posicion)throws SQLException {
        
    
    
    }*/
    
    
    public static ResultSet llenarTabla(String tipo) throws SQLException{
        String querya="SELECT idVehiculo,tipo,posicion FROM vehiculos WHERE tipo='"+tipo+"' AND estado=1";
        Statement st;
        ResultSet result=null;
    
        try{
            Class.forName(driver).newInstance();
            conn = (Connection) DriverManager.getConnection(sqlurl+databas,dbuser,dbpass);
            st = (Statement) conn.createStatement();
            result = st.executeQuery(querya);
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return result;
        
        
    }
    
    
    /*public static int conseguirID(String buscarId){
           int idVehiculo=-1;
           ResultSet idrs;
           Statement st;
            
           try{
               Class.forName(driver).newInstance();
               conn = (Connection) DriverManager.getConnection(sqlurl+databas,dbuser,dbpass);
               st = (Statement) conn.createStatement();
               idrs = st.executeQuery(buscarId);
               
               if(idrs.next()){
                   idVehiculo=idrs.getInt("idVehiculo");
               }
               
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            
            return idVehiculo;
    }*/
    
    
    public static boolean espaciosOcupados(String tipo){
           int lleno=0;
           boolean blleno=false;
           ResultSet resules;
           Statement st;
           String buscarLleno="SELECT count(idVehiculo) as ocupados FROM vehiculos WHERE estado=1 AND tipo='"+tipo+"'"; 
           
           try{
               Class.forName(driver).newInstance();
               conn = (Connection) DriverManager.getConnection(sqlurl+databas,dbuser,dbpass);
               st = (Statement) conn.createStatement();
               resules = st.executeQuery(buscarLleno);
               
               if(resules.next()){
                   lleno=resules.getInt("ocupados");
                   
                   if(lleno>4){
                       blleno=true;
                   }
               }
               
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            
            return blleno;
    }
    
    public static boolean chequearPosicion(String tipo, int posicion){
        boolean ocupado=false;
        ResultSet resulpos;
           Statement st;
           String buscarLleno="SELECT idVehiculo  FROM vehiculos WHERE estado=1 AND tipo='"+tipo+"' AND posicion="+posicion+"";
           try{
               Class.forName(driver).newInstance();
               conn = (Connection) DriverManager.getConnection(sqlurl+databas,dbuser,dbpass);
               st = (Statement) conn.createStatement();
               resulpos = st.executeQuery(buscarLleno);
               
               if(resulpos.next()){
                  ocupado=true;
               }
               
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
           return ocupado;
            
    }
    
}
