/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andres.controlador;

import com.andres.modelo.ArchivosManager;
import com.andres.modelo.Conexion;
import com.andres.modelo.ConsultasOracle;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Andre
 */
public class MetodosDB {
    
    public static void desconectarDB(){
        //ConsultasOracle.desconexion();
        Conexion.dessconectar();
    }
    
    public static Connection getConnection(){
        try {
            return Conexion.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MetodosDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }
    
    public static ArrayList<String> getTables(){
        return ConsultasOracle.getTables();
    }
    
    public static ArrayList<String> getFunctions(){
        return ConsultasOracle.getFunctions();
    }
    
    public static ArrayList<String> getProcedures(){
        return ConsultasOracle.getProcedures();
    }
    
    public static ArrayList<String> getTriggers(){
        return ConsultasOracle.getTriggers();
    }
    
    public static ArrayList<String> getViews(){
        return ConsultasOracle.getViews();
    }
    
    public static ArrayList<String> getPackages(){
        return ConsultasOracle.getPackages();
    }
    
    public static ArrayList<String> getUsers(){
        return ConsultasOracle.getUsers();
    }
    
    public static void listarDatosTabla(JTable tabla, String sql){
        ConsultasOracle.listarDatosTabla(tabla, sql);
    }
    
    public static String[] getColumnasTab(){
        return ConsultasOracle.getColumnasTab();
    }
    
    public static String getArregloErrores(){
        return ConsultasOracle.getArregloErrores();
    }
    
    public static String getArregloCodigos(){
        return ConsultasOracle.getArregloCodigos();
    }
    
    public static void actualizarDatosTabla(String sql){
        ConsultasOracle.actualizarDatosTabla(sql);
    }
    
    /*public static void listarDiccionarioDatos(ArrayList<String> datos, DefaultMutableTreeNode nodo){
        ConsultasOracle.listarDiccionarioDatos(datos, nodo);
    }*/
    
    public static void listarDiccionarioDatos(ArrayList<String> datos){
        ConsultasOracle.listarDiccionarioDatos(datos);
    }
    
    /*public static void mostrarCodigoDiccionarioDatos(String name, JTextArea texto){
        ConsultasOracle.mostrarCodigoDiccionarioDatos(name, texto);
    }*/
    
    public static void mostrarCodigoDiccionarioDatos(String name){
        ConsultasOracle.mostrarCodigoDiccionarioDatos(name);
    }
    
    public static void setUsuario(String usuario){
        Conexion.setUsuario(usuario);
    }
    
    public static void setContrasena(String contrasena){
        Conexion.setContrasena(contrasena);
    }
    
    public static void setIpServidor(String ipServidor){
        Conexion.setIpServidor(ipServidor);
    }
    
    public static void setPuerto(String puerto){
        Conexion.setPuerto(puerto);
    }
    
    public static void setInstancia(String instancia){
        Conexion.setInstancia(instancia);
    }
    
    public static void setBaseDatos(String baseDatos){
        Conexion.setBaseDatos(baseDatos);
    }
    
    
    public static void setEsOracle(boolean esOracle){
        Conexion.setEsOracle(esOracle);
    }
    
    public static void setEsSQLServer(boolean esSQLServer){
        Conexion.setEsSQLServer(esSQLServer);
    }
    
    public static void crearUsuario(String usuario, String contrasena){
        try {
            ConsultasOracle.crearUsuario(usuario, contrasena);
            //JOptionPane.showMessageDialog(null, "Usuario creado con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static void guardarArchivo(String texto){
        ArchivosManager.guardarArchivo(texto);
    }
    
    public static void cargarArchivo(){
        ArchivosManager.cargarArchivo();
    }
    
    public static String getArregloTextosAM(){
        return ArchivosManager.getArregloTextos();
    }
    
    public static String getArregloErroresAM(){
        return ArchivosManager.getArregloErrores();
    }
    
    public static void getSchema(){
        ConsultasOracle.getSchema();
    }

    
}
