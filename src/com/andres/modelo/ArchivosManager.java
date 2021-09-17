/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andres.modelo;

/**
 *
 * @author Andre
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ArchivosManager {

    static ArrayList<String> textos = new ArrayList<>();
    static ArrayList<String> errores = new ArrayList<>();
    
    public static void cargarArchivo() {
        File archivo;
        Scanner lector;
        int respuesta;
        JFileChooser eleccion = new JFileChooser("C:\\Users\\Andre\\OneDrive\\Desktop\\V\\Universidad\\2021 - 1\\Administracion de datos");
        String output = "";

        eleccion.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //eleccion.setFileFilter(new FileNameExtensionFilter(".sql", "archivo sql"));

        respuesta = eleccion.showOpenDialog(null);
        
        textos.add("");
        errores.add("");

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            archivo = eleccion.getSelectedFile();

            try {
                lector = new Scanner(archivo);

                if (archivo.isFile()) {
                    while (lector.hasNextLine()) {
                        output += lector.nextLine() + "\n";
                    }
                } else {
                    //error.setText("Archivo no admitido!");
                    errores.add("Archivo no admitido!");
                }
                //texto.setText(output);
                textos.add(output);
                lector.close();

            } catch (FileNotFoundException ex) {
                //error.setText(ex.getMessage());
                errores.add(ex.getMessage());
                Logger.getLogger(ArchivosManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void guardarArchivo(String texto) {
        Scanner lector;
        int respuesta;
        JFileChooser eleccion = new JFileChooser("C:\\Users\\Andre\\OneDrive\\Desktop\\V\\Universidad\\2021 - 1\\Administracion de datos");
        String output = null;

        eleccion.setDialogTitle("Guardar archivo");
        //eleccion.setFileFilter(new FileNameExtensionFilter(".sql", "archivo sql"));

        textos.add("");
        errores.add("");
        
        respuesta = eleccion.showSaveDialog(null);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            //String contenido = texto.getText();
            String contenido = texto;
            File guardado = eleccion.getSelectedFile();
            try {
                FileWriter escribir = new FileWriter(guardado.getPath());
                escribir.write(contenido);
                escribir.flush();
                escribir.close();
            } catch (Exception e) {
                //error.setText(e.getMessage());
                errores.add(e.getMessage());
            }
        }
    }
    
    /*public static void guardarArchivo(JTextArea texto, JTextArea error) {
        Scanner lector;
        int respuesta;
        JFileChooser eleccion = new JFileChooser("C:\\Users\\Andre\\OneDrive\\Desktop\\V\\Universidad\\2021 - 1\\Administracion de datos");
        String output = null;

        eleccion.setDialogTitle("Guardar archivo");
        eleccion.setFileFilter(new FileNameExtensionFilter(".sql", "archivo sql"));

        respuesta = eleccion.showSaveDialog(null);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            String contenido = texto.getText();
            File guardado = eleccion.getSelectedFile();
            try {
                FileWriter escribir = new FileWriter(guardado.getPath());
                escribir.write(contenido);
                escribir.flush();
                escribir.close();
            } catch (Exception e) {
                error.setText(e.getMessage());
            }
        }
    }*/
    
    public static String getArregloTextos(){
        return textos.get(textos.size() - 1);
    }
    
    public static String getArregloErrores(){
        return errores.get(errores.size() - 1);
    }

}
