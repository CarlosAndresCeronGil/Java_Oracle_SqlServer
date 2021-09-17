/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andres.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Andre
 */
public class Conexion {

    private static Connection conexion = null;
    
    private static String usuario;
    private static String contrasena;
    private static String ipServidor;
    private static String puerto;
    private static String instancia;
    private static String baseDatos;
    
    private static boolean esOracle;
    private static boolean esSQLServer;
    
    public static Connection getConnection() throws SQLException {
        if (isEsOracle()) {
            try {
                if (Conexion.conexion == null) {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Conexion.conexion = DriverManager.getConnection("jdbc:oracle:thin:@" + ipServidor + ":" + puerto + ":" + instancia, usuario, contrasena);
                    Conexion.conexion.setAutoCommit(false);
                    return Conexion.conexion;
                }
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                Conexion.conexion.close();
            }
            return Conexion.conexion;
        } else if (isEsSQLServer()) {
            try {
                if (Conexion.conexion == null) {
                    String conexionURL = "jdbc:sqlserver://" + ipServidor +":"+ puerto +";"
                            + "database=" + baseDatos + ";"
                            + "user=" + usuario + ";"
                            + "password=" + contrasena + ";"
                            + "loginTimeout=30;";
                    Conexion.conexion = DriverManager.getConnection(conexionURL);
                    //ConexionSQLServer.conexion.setAutoCommit(false);
                    return Conexion.conexion;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                Conexion.conexion.close();
            }
            return Conexion.conexion;
        }
        return null;
    }

    public static void dessconectar(){
        Conexion.conexion = null;
    }
    
    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        Conexion.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public static void setContrasena(String contrasena) {
        Conexion.contrasena = contrasena;
    }

    public String getIpServidor() {
        return ipServidor;
    }

    public static void setIpServidor(String ipServidor) {
        Conexion.ipServidor = ipServidor;
    }

    public String getPuerto() {
        return puerto;
    }

    public static void setPuerto(String puerto) {
        Conexion.puerto = puerto;
    }

    public String getInstancia() {
        return instancia;
    }

    public static void setInstancia(String instancia) {
        Conexion.instancia = instancia;
    }

    public static String getBaseDatos() {
        return baseDatos;
    }

    public static void setBaseDatos(String baseDatos) {
        Conexion.baseDatos = baseDatos;
    }

    public static boolean isEsOracle() {
        return esOracle;
    }

    public static void setEsOracle(boolean esOracle) {
        Conexion.esOracle = esOracle;
    }

    public static boolean isEsSQLServer() {
        return esSQLServer;
    }

    public static void setEsSQLServer(boolean esSQLServer) {
        Conexion.esSQLServer = esSQLServer;
    }
    
    

}
