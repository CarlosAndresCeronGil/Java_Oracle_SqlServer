/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andres.vista;

import com.andres.controlador.MetodosDB;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Andre
 */
public class Principal extends javax.swing.JFrame {

    DefaultMutableTreeNode tablas_raiz;
    DefaultMutableTreeNode funciones_raiz;
    DefaultMutableTreeNode paquetes_raiz;
    DefaultMutableTreeNode procedimientos_raiz;
    DefaultMutableTreeNode triggers_raiz;
    DefaultMutableTreeNode vistas_raiz;
    DefaultMutableTreeNode roles_raiz;
    DefaultMutableTreeNode usuarios_raiz;
    
    DefaultTableModel model;

    public Principal() {
        initComponents();
    }
    
    public JFrame getFrame(){
        return this;
    }

    public boolean comprobarSentencia(String sentencia, JTextArea areaTexto) {
        return Pattern.compile(Pattern.quote(sentencia), Pattern.CASE_INSENSITIVE).matcher(areaTexto.getText()).find();
    }
    
    public void recorrerArbol(ArrayList<String> datos, DefaultMutableTreeNode nodo){
        for(String dato: datos){
            DefaultMutableTreeNode aux = new DefaultMutableTreeNode();
            aux.setUserObject(dato);
            nodo.add(aux);
        }
    }

    public void llenarArbol() {
        tablas_raiz.removeAllChildren();
        funciones_raiz.removeAllChildren();
        paquetes_raiz.removeAllChildren();
        procedimientos_raiz.removeAllChildren();
        triggers_raiz.removeAllChildren();
        vistas_raiz.removeAllChildren();
        roles_raiz.removeAllChildren();
        usuarios_raiz.removeAllChildren();
        /*MetodosDB.listarDiccionarioDatos(MetodosDB.getTables(), tablas_raiz);
        MetodosDB.listarDiccionarioDatos(MetodosDB.getFunctions(), funciones_raiz);
        MetodosDB.listarDiccionarioDatos(MetodosDB.getPackages(), paquetes_raiz);
        MetodosDB.listarDiccionarioDatos(MetodosDB.getProcedures(), procedimientos_raiz);
        MetodosDB.listarDiccionarioDatos(MetodosDB.getTriggers(), triggers_raiz);
        MetodosDB.listarDiccionarioDatos(MetodosDB.getViews(), vistas_raiz);
        MetodosDB.listarDiccionarioDatos(MetodosDB.getUsers(), usuarios_raiz);*/
        recorrerArbol(MetodosDB.getTables(), tablas_raiz);
        recorrerArbol(MetodosDB.getFunctions(), funciones_raiz);
        recorrerArbol(MetodosDB.getPackages(), paquetes_raiz);
        recorrerArbol(MetodosDB.getProcedures(), procedimientos_raiz);
        recorrerArbol(MetodosDB.getTriggers(), triggers_raiz);
        recorrerArbol(MetodosDB.getViews(), vistas_raiz);
        recorrerArbol(MetodosDB.getUsers(), usuarios_raiz);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comandosTabbedPanel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sentenciasSQLTextArea = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        plsqlTextArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ddlTextArea = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        resultadoTable = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        erroresTexttArea = new javax.swing.JTextArea();
        cambiarUsuarioButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Archivos");

        tablas_raiz = new DefaultMutableTreeNode("Tablas");
        funciones_raiz = new DefaultMutableTreeNode("Funciones");
        paquetes_raiz = new DefaultMutableTreeNode("Paquetes");
        procedimientos_raiz = new DefaultMutableTreeNode("Procedimientos");
        triggers_raiz = new DefaultMutableTreeNode("Triggers");
        vistas_raiz = new DefaultMutableTreeNode("Vistas");
        roles_raiz = new DefaultMutableTreeNode("Roles");
        usuarios_raiz = new DefaultMutableTreeNode("Usuarios");

        raiz.add(tablas_raiz);
        raiz.add(funciones_raiz);
        raiz.add(paquetes_raiz);
        raiz.add(procedimientos_raiz);
        raiz.add(triggers_raiz);
        raiz.add(usuarios_raiz);

        llenarArbol();
        arbolElementos = new javax.swing.JTree(raiz);
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sentenciasSQLTextArea.setColumns(20);
        sentenciasSQLTextArea.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        sentenciasSQLTextArea.setRows(5);
        sentenciasSQLTextArea.setText("select * from cargos");
        jScrollPane2.setViewportView(sentenciasSQLTextArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addContainerGap())
        );

        comandosTabbedPanel.addTab("SQL", jPanel1);

        plsqlTextArea.setColumns(20);
        plsqlTextArea.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        plsqlTextArea.setRows(5);
        jScrollPane3.setViewportView(plsqlTextArea);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addContainerGap())
        );

        comandosTabbedPanel.addTab("PLSQL - TSQL - Otros", jPanel2);

        ddlTextArea.setColumns(20);
        ddlTextArea.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        ddlTextArea.setRows(5);
        jScrollPane4.setViewportView(ddlTextArea);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addContainerGap())
        );

        comandosTabbedPanel.addTab("DDL", jPanel3);

        resultadoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(resultadoTable);

        erroresTexttArea.setColumns(20);
        erroresTexttArea.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        erroresTexttArea.setForeground(java.awt.Color.red);
        erroresTexttArea.setLineWrap(true);
        erroresTexttArea.setRows(5);
        erroresTexttArea.setEditable(false);
        jScrollPane6.setViewportView(erroresTexttArea);

        cambiarUsuarioButton.setText("Nuevo usuario");
        cambiarUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarUsuarioButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Guardar archivo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cargar archivo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Ejecutar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        arbolElementos.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                arbolElementosValueChanged(evt);
            }
        });
        jScrollPane7.setViewportView(arbolElementos);

        jButton1.setText("Cambiar usuario");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setText("Refrescar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Limpiar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Copiar base datos");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cambiarUsuarioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(29, 29, 29)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jScrollPane5))
                        .addComponent(comandosTabbedPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7)))
                .addContainerGap())
            .addComponent(jScrollPane6)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1)
                            .addComponent(cambiarUsuarioButton)
                            .addComponent(jButton3)
                            .addComponent(jButton5)
                            .addComponent(jButton6)
                            .addComponent(jButton4)
                            .addComponent(jButton7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(comandosTabbedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cambiarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarUsuarioButtonActionPerformed
        // TODO add your handling code here:
        //ConsultasOracle.desconexion();
        CrearUsuario crearUsuario = new CrearUsuario();
        crearUsuario.setLocationRelativeTo(null);
        crearUsuario.setVisible(true);
    }//GEN-LAST:event_cambiarUsuarioButtonActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        switch (comandosTabbedPanel.getSelectedIndex()) {
            case 0:
                erroresTexttArea.setText("");
                if (comprobarSentencia("select", sentenciasSQLTextArea)) {
                    MetodosDB.listarDatosTabla(resultadoTable, sentenciasSQLTextArea.getText());
                    //model.addRow(MetodosDB.listarDatosTabla(sentenciasSQLTextArea.getText()));
                    erroresTexttArea.setText(MetodosDB.getArregloErrores());
                } else if (comprobarSentencia("insert", sentenciasSQLTextArea) || comprobarSentencia("update", sentenciasSQLTextArea) || comprobarSentencia("delete", sentenciasSQLTextArea)) {
                    MetodosDB.actualizarDatosTabla(sentenciasSQLTextArea.getText());
                    erroresTexttArea.setText(MetodosDB.getArregloErrores());
                } else if (comprobarSentencia("create", sentenciasSQLTextArea) || comprobarSentencia("alter", sentenciasSQLTextArea) || comprobarSentencia("drop", sentenciasSQLTextArea)) {
                    erroresTexttArea.setText("Sentencia con contenido DDL, favor dirigirse a la correspondiente ??rea");
                } else if (comprobarSentencia("function", sentenciasSQLTextArea) || comprobarSentencia("procedure", sentenciasSQLTextArea) || comprobarSentencia("trigger", sentenciasSQLTextArea)) {
                    erroresTexttArea.setText("Sentencia con contenido PLSQL, favor dirigirse a la correspondiente ??rea");
                } else {
                    erroresTexttArea.setText("Sentencia sql no aceptada");
                }
                break;
            case 1:
                erroresTexttArea.setText("");
                if (comprobarSentencia("function", plsqlTextArea) || comprobarSentencia("procedure", plsqlTextArea) || comprobarSentencia("trigger", plsqlTextArea) || comprobarSentencia("package", plsqlTextArea)) {
                    MetodosDB.actualizarDatosTabla(plsqlTextArea.getText());
                    erroresTexttArea.setText(MetodosDB.getArregloErrores());
                } else if (comprobarSentencia("select", plsqlTextArea) || comprobarSentencia("insert", plsqlTextArea) || comprobarSentencia("update", plsqlTextArea) || comprobarSentencia("delete", plsqlTextArea)) {
                    erroresTexttArea.setText("Sentencia con contenido SQL, favor dirigirse a la correspondiente ??reaa");
                } else if (comprobarSentencia("create", plsqlTextArea) || comprobarSentencia("drop", plsqlTextArea)) {
                    erroresTexttArea.setText("Sentencia con contenido DDL, favor dirigirse a la correspondiente ??rea");
                } else {
                    erroresTexttArea.setText("Sentencia sql no aceptada");
                }
                break;
            case 2:
                erroresTexttArea.setText("");
                if (comprobarSentencia("create", ddlTextArea)) {
                    MetodosDB.actualizarDatosTabla(ddlTextArea.getText());
                    erroresTexttArea.setText(MetodosDB.getArregloErrores());
                } else if (comprobarSentencia("drop", ddlTextArea)) {
                    MetodosDB.actualizarDatosTabla(ddlTextArea.getText());
                    erroresTexttArea.setText(MetodosDB.getArregloErrores());
                } else if (comprobarSentencia("alter", ddlTextArea)) {
                    MetodosDB.actualizarDatosTabla(ddlTextArea.getText());
                    erroresTexttArea.setText(MetodosDB.getArregloErrores());
                } else if (comprobarSentencia("select", ddlTextArea) || comprobarSentencia("insert", ddlTextArea) || comprobarSentencia("update", ddlTextArea) || comprobarSentencia("delete", ddlTextArea)) {
                    erroresTexttArea.setText("Sentencia con contenido SQL, favor dirigirse a la correspondiente ??reaa");
                } else if (comprobarSentencia("function", ddlTextArea) || comprobarSentencia("procedure", ddlTextArea) || comprobarSentencia("trigger", ddlTextArea)) {
                    erroresTexttArea.setText("Sentencia con contenido PLSQL, favor dirigirse a la correspondiente ??rea");
                } else {
                    erroresTexttArea.setText("Sentencia DDl no aceptada");
                }
                break;
            default:
                break;
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    public boolean identificarNodo(String nodo, String nombre) {
        return arbolElementos.getSelectionPath().toString().equals("[Archivos, " + nodo + ", " + nombre + "]");
    }

    private void arbolElementosValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_arbolElementosValueChanged
        // TODO add your handling code here:
        try {
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) arbolElementos.getLastSelectedPathComponent();
            //System.out.println("NODO: " + nodo.getUserObject());
            //System.out.println("path: " + arbolElementos.getSelectionPath());
            String nombre = (String) nodo.getUserObject();
            
            if (identificarNodo("Tablas", nombre)) {
                MetodosDB.listarDatosTabla(resultadoTable, "Select * from " + nombre);
                //model = new DefaultTableModel(null, MetodosDB.getColumnasTab());
                //model.addRow(MetodosDB.listarDatosTabla("Select * from " + nombre));
                //resultadoTable.setModel(model);
            } else if (identificarNodo("Funciones", nombre) || identificarNodo("Procedimientos", nombre) || identificarNodo("Triggers", nombre) || identificarNodo("Paquetes", nombre)) {
                comandosTabbedPanel.setSelectedIndex(1);
                //MetodosDB.mostrarCodigoDiccionarioDatos(nombre, plsqlTextArea);
                MetodosDB.mostrarCodigoDiccionarioDatos(nombre);
                plsqlTextArea.setText(MetodosDB.getArregloCodigos());
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }

    }//GEN-LAST:event_arbolElementosValueChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        switch (comandosTabbedPanel.getSelectedIndex()) {
            case 0:
                //MetodosDB.guardarArchivo(sentenciasSQLTextArea, erroresTexttArea);
                MetodosDB.guardarArchivo(sentenciasSQLTextArea.getText());
                erroresTexttArea.setText(MetodosDB.getArregloErroresAM());
                break;
            case 1:
                //MetodosDB.guardarArchivo(plsqlTextArea, erroresTexttArea);
                MetodosDB.guardarArchivo(plsqlTextArea.getText());
                erroresTexttArea.setText(MetodosDB.getArregloErroresAM());
                break;
            case 2:
                //MetodosDB.guardarArchivo(ddlTextArea, erroresTexttArea);
                MetodosDB.guardarArchivo(ddlTextArea.getText());
                erroresTexttArea.setText(MetodosDB.getArregloErroresAM());
                break;
            default:
                break;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        switch (comandosTabbedPanel.getSelectedIndex()) {
            case 0:
                //MetodosDB.cargarArchivo(sentenciasSQLTextArea, erroresTexttArea);
                sentenciasSQLTextArea.setText("");
                MetodosDB.cargarArchivo();
                sentenciasSQLTextArea.setText(MetodosDB.getArregloTextosAM());
                erroresTexttArea.setText(MetodosDB.getArregloErroresAM());
                break;
            case 1:
                //MetodosDB.cargarArchivo(plsqlTextArea, erroresTexttArea);
                plsqlTextArea.setText("");
                MetodosDB.cargarArchivo();
                plsqlTextArea.setText(MetodosDB.getArregloTextosAM());
                erroresTexttArea.setText(MetodosDB.getArregloErroresAM());
                break;
            case 2:
                //MetodosDB.cargarArchivo(ddlTextArea, erroresTexttArea);
                ddlTextArea.setText("");
                MetodosDB.cargarArchivo();
                ddlTextArea.setText(MetodosDB.getArregloTextosAM());
                erroresTexttArea.setText(MetodosDB.getArregloErroresAM());
                break;
            default:
                break;
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        CambiarUsuario cambiarUsuario = new CambiarUsuario();
        cambiarUsuario.setResizable(false);
        cambiarUsuario.setLocationRelativeTo(null);
        cambiarUsuario.setVisible(true);
        CambiarUsuario.setFrame(this);
    }//GEN-LAST:event_jButton1ActionPerformed

    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        //initComponents();
        //SwingUtilities.updateComponentTreeUI(this);
        //this.invalidate();
        //this.validate();
        //this.repaint();
        llenarArbol();
        DefaultTreeModel model = (DefaultTreeModel) arbolElementos.getModel();
        model.reload();
        //llenarArchivos();
        //MetodosDB.desconectarDB();
        //MetodosDB.getConnection();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        sentenciasSQLTextArea.setText("");
        plsqlTextArea.setText("");
        ddlTextArea.setText("");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        //MetodosDB.getSchema(sentenciasSQLTextArea);
        MetodosDB.getSchema();
        sentenciasSQLTextArea.setText(MetodosDB.getArregloCodigos());
        erroresTexttArea.setText(MetodosDB.getArregloErrores());
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbolElementos;
    private javax.swing.JButton cambiarUsuarioButton;
    private javax.swing.JTabbedPane comandosTabbedPanel;
    private javax.swing.JTextArea ddlTextArea;
    private javax.swing.JTextArea erroresTexttArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea plsqlTextArea;
    private javax.swing.JTable resultadoTable;
    private javax.swing.JTextArea sentenciasSQLTextArea;
    // End of variables declaration//GEN-END:variables

}
