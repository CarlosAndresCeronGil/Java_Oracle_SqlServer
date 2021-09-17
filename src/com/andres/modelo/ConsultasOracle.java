/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andres.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Andre
 */
public class ConsultasOracle {
    
    static ArrayList<String> errores = new ArrayList<>();
    static ArrayList<String> codigos = new ArrayList<>();
    static ArrayList<String> tablas = new ArrayList<>();
    static ArrayList<String> columnas = new ArrayList<>();
    static String[] columnasTab;
    
    public static void desconexion() {
        try {
            Connection conn = Conexion.getConnection();
            conn.close();
            conn = null;
            System.out.println("Desconexion exitosa");
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }
    
    public static ArrayList<String> getData(String data) {
        Statement st = null;
        ResultSet result = null;
        ArrayList<String> procedimientos = new ArrayList<String>();
        
        try {
            Connection conn = Conexion.getConnection();
            
            String sql_procedures = data;
            st = conn.createStatement();
            result = st.executeQuery(sql_procedures);
            
            while (result.next()) {
                procedimientos.add(result.getString(1));
            }
        } catch (Exception e) {
        }
        return procedimientos;
    }
    
    public static ArrayList<String> getData2(String data) {
        Statement st = null;
        ResultSet result = null;
        ArrayList<String> procedimientos = new ArrayList<String>();
        
        try {
            Connection conn = Conexion.getConnection();
            
            String sql_procedures = data;
            st = conn.createStatement();
            result = st.executeQuery(sql_procedures);
            
            while (result.next()) {
                procedimientos.add(result.getString(2));
            }
        } catch (Exception e) {
        }
        return procedimientos;
    }
    
    public static ArrayList<String> getTables() {
        if (Conexion.isEsOracle()) {
            return getData("select * from USER_TABLES");
        } else if (Conexion.isEsSQLServer()) {
            //System.out.println("SI ENTRO AL GET TABLES");
            return getData("SELECT * FROM SYSOBJECTS WHERE xtype = 'U'");
        }
        return getData("");
    }
    
    public static ArrayList<String> getFunctions() {
        if (Conexion.isEsOracle()) {
            return getData("select name from user_dependencies where type='FUNCTION' group by name");
        } else if (Conexion.isEsSQLServer()) {
            return getData("Select [NAME] from sysobjects where type = 'FN' and category = 0");
        }
        return getData("");
    }
    
    public static ArrayList<String> getProcedures() {
        if (Conexion.isEsOracle()) {
            return getData("select * from user_dependencies where type='PROCEDURE'");
        } else if (Conexion.isEsSQLServer()) {
            return getData("Select [NAME] from sysobjects where type = 'P' and category = 0");
        }
        return getData("");
    }
    
    public static ArrayList<String> getTriggers() {
        if (Conexion.isEsOracle()) {
            return getData("select * from USER_TRIGGERS");
        } else if (Conexion.isEsSQLServer()) {
            return getData("Select * from sysobjects where type = 'TR'");
        }
        return getData("");
    }
    
    public static ArrayList<String> getViews() {
        if (Conexion.isEsOracle()) {
            return getData("select * from user_dependencies where type='PROCEDURE'");
        } else if (Conexion.isEsSQLServer()) {
            return getData("Select [NAME] from sysobjects where type = 'V' and category = 0");
        }
        return getData("");
    }
    
    public static ArrayList<String> getPackages() {
        return getData("select * from user_dependencies where type = 'PACKAGE BODY' and referenced_type = 'TABLE'");
    }
    
    public static ArrayList<String> getUsers() {
        if (Conexion.isEsOracle()) {
            return getData("select * from dba_users where account_status = 'OPEN' order by created");
        } else if (Conexion.isEsSQLServer()) {
            return getData("SELECT * FROM master.sys.sql_logins where is_disabled = 0");
        }
        return getData("");
    }
    
    public static void listarDatosTabla(JTable tabla, String sql) {
        DefaultTableModel model;
        Statement st = null;
        ResultSet result = null;
        String[] filas;
        String[] columnas;
        try {
            Connection conn = Conexion.getConnection();
            st = conn.createStatement();
            result = st.executeQuery(sql);
            
            int NUMERO_COLUMNAS = result.getMetaData().getColumnCount();
            //System.out.println("------NUMERO DE COLUMNAS------ " + NUMERO_COLUMNAS);

            filas = new String[NUMERO_COLUMNAS];
            columnas = new String[NUMERO_COLUMNAS];
            
            for (int i = 0; i < NUMERO_COLUMNAS; i++) {
                columnas[i] = result.getMetaData().getColumnName(i + 1);
                //System.out.println("  " + columnas[i]);
            }
            
            model = new DefaultTableModel(null, columnas);
            
            while (result.next()) {
                for (int i = 1; i <= NUMERO_COLUMNAS; i++) {
                    filas[i - 1] = result.getString(i);
                }
                model.addRow(filas);
            }
            tabla.setModel(model);
            errores.add("");
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            errores.add(e.getMessage());
        }
    }
    
    /*public static String[] listarDatosTabla(String sql) {
        DefaultTableModel model;
        Statement st = null;
        ResultSet result = null;
        String[] filas;
        String[] columnas;
        try {
            Connection conn = Conexion.getConnection();
            st = conn.createStatement();
            result = st.executeQuery(sql);
            
            int NUMERO_COLUMNAS = result.getMetaData().getColumnCount();
            //System.out.println("------NUMERO DE COLUMNAS------ " + NUMERO_COLUMNAS);

            filas = new String[NUMERO_COLUMNAS];
            columnas = new String[NUMERO_COLUMNAS];
            
            for (int i = 0; i < NUMERO_COLUMNAS; i++) {
                columnas[i] = result.getMetaData().getColumnName(i + 1);
                //System.out.println("  " + columnas[i]);
            }
            
            columnasTab = columnas;
            
            //model = new DefaultTableModel(null, columnas);
            
            while (result.next()) {
                for (int i = 1; i <= NUMERO_COLUMNAS; i++) {
                    filas[i - 1] = result.getString(i);
                }
                //model.addRow(filas);
            }
            //tabla.setModel(model);
            errores.add("");
            return filas;
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            errores.add(e.getMessage());
        }
        return null;
    }*/
    
    public static void actualizarDatosTabla(String sql) {
        PreparedStatement pst = null;
        errores.add("");
        try {
            Connection conn = Conexion.getConnection();
            
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            errores.add(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        errores.add("Actualización exitosa");
    }
    
    public static void ejecutarQuery(String sql) {
        PreparedStatement pst = null;
        try {
            Connection conn = Conexion.getConnection();
            
            pst = conn.prepareStatement(sql);
            pst.execute();
            //conn.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static void crearUsuario(String usuario, String contrasena) {
        if (Conexion.isEsOracle()) {
            crearUsuarioOracle(usuario, contrasena);
        } else if (Conexion.isEsSQLServer()) {
            crearUsuarioSQLServer(usuario, contrasena);
        }
    }
    
    public static void crearUsuarioOracle(String usuario, String contrasena) {
        try {
            ejecutarQuery("alter session set \"_ORACLE_SCRIPT\" = true");
            ejecutarQuery("create user " + usuario + " identified by " + contrasena);
            ejecutarQuery("grant connect, RESOURCE, dba to " + usuario + " with admin option");
            JOptionPane.showMessageDialog(null, "Usuario creado con exito!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static void crearUsuarioSQLServer(String usuario, String contrasena) {
        ejecutarQuery("create login " + usuario + " with password = '" + contrasena + "'");
        ejecutarQuery("ALTER SERVER ROLE  [dbcreator]  ADD MEMBER [" + usuario + "]");
    }
    
    /*public static void listarDiccionarioDatos(ArrayList<String> datos, DefaultMutableTreeNode nodo) {
        for (String dato : datos) {
            DefaultMutableTreeNode aux = new DefaultMutableTreeNode();
            aux.setUserObject(dato);
            nodo.add(aux);
        }
    }*/
    
    public static ArrayList<String> listarDiccionarioDatos(ArrayList<String> datos) {
        return datos;
    }
    
    public static int numero_filas(String sql) {
        int contador = 0;
        Statement st = null;
        ResultSet result = null;
        try {
            Connection conn = Conexion.getConnection();
            st = conn.createStatement();
            result = st.executeQuery(sql);
            while (result.next()) {
                contador++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return contador;
    }
    
    public static void mostrarCodigoDiccionarioDatos(String name) {
        codigos.add("");
        Statement st = null;
        ResultSet result = null;
        int NUM_FILAS;
        
        try {
            Connection conn = Conexion.getConnection();
            st = conn.createStatement();
            if (Conexion.isEsOracle()) {
                result = st.executeQuery("select TEXT from all_source where owner = 'ANDRES' and name='" + name + "'");
                NUM_FILAS = numero_filas("select TEXT from all_source where owner = 'ANDRES' and name='" + name + "'");
            } else if (Conexion.isEsSQLServer()) {
                result = st.executeQuery("SELECT OBJECT_DEFINITION (OBJECT_ID('" + name + "'))");
                NUM_FILAS = numero_filas("SELECT OBJECT_DEFINITION (OBJECT_ID('" + name + "'))");
            } else {
                NUM_FILAS = 0;
            }
            
            //System.out.println("NUMERO DE FILAS :" + NUM_FILAS);
            String output = "";
            for (int i = 1; i <= NUM_FILAS; i++) {
                while (result.next()) {
                    //System.out.println("" + result.getString(i));
                    output += result.getString(1);
                }
            }
            codigos.add(output);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    /*public static void mostrarCodigoDiccionarioDatos(String name, JTextArea texto) {
        texto.setText("");
        Statement st = null;
        ResultSet result = null;
        int NUM_FILAS;
        
        try {
            Connection conn = Conexion.getConnection();
            st = conn.createStatement();
            if (Conexion.isEsOracle()) {
                result = st.executeQuery("select TEXT from all_source where owner = 'ANDRES' and name='" + name + "'");
                NUM_FILAS = numero_filas("select TEXT from all_source where owner = 'ANDRES' and name='" + name + "'");
            } else if (Conexion.isEsSQLServer()) {
                result = st.executeQuery("SELECT OBJECT_DEFINITION (OBJECT_ID('" + name + "'))");
                NUM_FILAS = numero_filas("SELECT OBJECT_DEFINITION (OBJECT_ID('" + name + "'))");
            } else {
                NUM_FILAS = 0;
            }
            
            System.out.println("NUMERO DE FILAS :" + NUM_FILAS);
            String output = "";
            for (int i = 1; i <= NUM_FILAS; i++) {
                while (result.next()) {
                    //System.out.println("" + result.getString(i));
                    output += result.getString(1);
                }
            }
            texto.setText(output);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }*/
    
    /*public static void getTablesSchemaSQLServer(JTextArea areaTexto) {
        Statement st = null;
        ResultSet result = null;
        String aux = "";
        ArrayList<String> arregloTablas = getData("select TABLE_NAME from INFORMATION_SCHEMA.TABLES");
        areaTexto.setText("");
        errores.add("");
        try {
            Connection conn = Conexion.getConnection();
            st = conn.createStatement();
            String textoCompleto = "";
            for (int i = 0; i < arregloTablas.size(); i++) {
                aux = "DECLARE @table_name SYSNAME\n"
                        + "SELECT @table_name = 'dbo." + arregloTablas.get(i) + "'\n"
                        + "\n"
                        + "DECLARE \n"
                        + "      @object_name SYSNAME\n"
                        + "    , @object_id INT\n"
                        + "\n"
                        + "SELECT \n"
                        + "      @object_name = '[' + s.name + '].[' + o.name + ']'\n"
                        + "    , @object_id = o.[object_id]\n"
                        + "FROM sys.objects o WITH (NOWAIT)\n"
                        + "JOIN sys.schemas s WITH (NOWAIT) ON o.[schema_id] = s.[schema_id]\n"
                        + "WHERE s.name + '.' + o.name = @table_name\n"
                        + "    AND o.[type] = 'U'\n"
                        + "    AND o.is_ms_shipped = 0\n"
                        + "\n"
                        + "DECLARE @SQL NVARCHAR(MAX) = ''\n"
                        + "\n"
                        + ";WITH index_column AS \n"
                        + "(\n"
                        + "    SELECT \n"
                        + "          ic.[object_id]\n"
                        + "        , ic.index_id\n"
                        + "        , ic.is_descending_key\n"
                        + "        , ic.is_included_column\n"
                        + "        , c.name\n"
                        + "    FROM sys.index_columns ic WITH (NOWAIT)\n"
                        + "    JOIN sys.columns c WITH (NOWAIT) ON ic.[object_id] = c.[object_id] AND ic.column_id = c.column_id\n"
                        + "    WHERE ic.[object_id] = @object_id\n"
                        + "),\n"
                        + "fk_columns AS \n"
                        + "(\n"
                        + "     SELECT \n"
                        + "          k.constraint_object_id\n"
                        + "        , cname = c.name\n"
                        + "        , rcname = rc.name\n"
                        + "    FROM sys.foreign_key_columns k WITH (NOWAIT)\n"
                        + "    JOIN sys.columns rc WITH (NOWAIT) ON rc.[object_id] = k.referenced_object_id AND rc.column_id = k.referenced_column_id \n"
                        + "    JOIN sys.columns c WITH (NOWAIT) ON c.[object_id] = k.parent_object_id AND c.column_id = k.parent_column_id\n"
                        + "    WHERE k.parent_object_id = @object_id\n"
                        + ")\n"
                        + "SELECT @SQL = 'CREATE TABLE ' + @object_name + CHAR(13) + '(' + CHAR(13) + STUFF((\n"
                        + "    SELECT CHAR(9) + ', [' + c.name + '] ' + \n"
                        + "        CASE WHEN c.is_computed = 1\n"
                        + "            THEN 'AS ' + cc.[definition] \n"
                        + "            ELSE UPPER(tp.name) + \n"
                        + "                CASE WHEN tp.name IN ('varchar', 'char', 'varbinary', 'binary', 'text')\n"
                        + "                       THEN '(' + CASE WHEN c.max_length = -1 THEN 'MAX' ELSE CAST(c.max_length AS VARCHAR(5)) END + ')'\n"
                        + "                     WHEN tp.name IN ('nvarchar', 'nchar', 'ntext')\n"
                        + "                       THEN '(' + CASE WHEN c.max_length = -1 THEN 'MAX' ELSE CAST(c.max_length / 2 AS VARCHAR(5)) END + ')'\n"
                        + "                     WHEN tp.name IN ('datetime2', 'time2', 'datetimeoffset') \n"
                        + "                       THEN '(' + CAST(c.scale AS VARCHAR(5)) + ')'\n"
                        + "                     WHEN tp.name = 'decimal' \n"
                        + "                       THEN '(' + CAST(c.[precision] AS VARCHAR(5)) + ',' + CAST(c.scale AS VARCHAR(5)) + ')'\n"
                        + "                    ELSE ''\n"
                        + "                END +\n"
                        + "                CASE WHEN c.collation_name IS NOT NULL THEN ' COLLATE ' + c.collation_name ELSE '' END +\n"
                        + "                CASE WHEN c.is_nullable = 1 THEN ' NULL' ELSE ' NOT NULL' END +\n"
                        + "                CASE WHEN dc.[definition] IS NOT NULL THEN ' DEFAULT' + dc.[definition] ELSE '' END + \n"
                        + "                CASE WHEN ic.is_identity = 1 THEN ' IDENTITY(' + CAST(ISNULL(ic.seed_value, '0') AS CHAR(1)) + ',' + CAST(ISNULL(ic.increment_value, '1') AS CHAR(1)) + ')' ELSE '' END \n"
                        + "        END + CHAR(13)\n"
                        + "    FROM sys.columns c WITH (NOWAIT)\n"
                        + "    JOIN sys.types tp WITH (NOWAIT) ON c.user_type_id = tp.user_type_id\n"
                        + "    LEFT JOIN sys.computed_columns cc WITH (NOWAIT) ON c.[object_id] = cc.[object_id] AND c.column_id = cc.column_id\n"
                        + "    LEFT JOIN sys.default_constraints dc WITH (NOWAIT) ON c.default_object_id != 0 AND c.[object_id] = dc.parent_object_id AND c.column_id = dc.parent_column_id\n"
                        + "    LEFT JOIN sys.identity_columns ic WITH (NOWAIT) ON c.is_identity = 1 AND c.[object_id] = ic.[object_id] AND c.column_id = ic.column_id\n"
                        + "    WHERE c.[object_id] = @object_id\n"
                        + "    ORDER BY c.column_id\n"
                        + "    FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 2, CHAR(9) + ' ')\n"
                        + "    + ISNULL((SELECT CHAR(9) + ', CONSTRAINT [' + k.name + '] PRIMARY KEY (' + \n"
                        + "                    (SELECT STUFF((\n"
                        + "                         SELECT ', [' + c.name + '] ' + CASE WHEN ic.is_descending_key = 1 THEN 'DESC' ELSE 'ASC' END\n"
                        + "                         FROM sys.index_columns ic WITH (NOWAIT)\n"
                        + "                         JOIN sys.columns c WITH (NOWAIT) ON c.[object_id] = ic.[object_id] AND c.column_id = ic.column_id\n"
                        + "                         WHERE ic.is_included_column = 0\n"
                        + "                             AND ic.[object_id] = k.parent_object_id \n"
                        + "                             AND ic.index_id = k.unique_index_id     \n"
                        + "                         FOR XML PATH(N''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 2, ''))\n"
                        + "            + ')' + CHAR(13)\n"
                        + "            FROM sys.key_constraints k WITH (NOWAIT)\n"
                        + "            WHERE k.parent_object_id = @object_id \n"
                        + "                AND k.[type] = 'PK'), '') + ')'  + CHAR(13)\n"
                        + "    + ISNULL((SELECT (\n"
                        + "        SELECT CHAR(13) +\n"
                        + "             'ALTER TABLE ' + @object_name + ' WITH' \n"
                        + "            + CASE WHEN fk.is_not_trusted = 1 \n"
                        + "                THEN ' NOCHECK' \n"
                        + "                ELSE ' CHECK' \n"
                        + "              END + \n"
                        + "              ' ADD CONSTRAINT [' + fk.name  + '] FOREIGN KEY(' \n"
                        + "              + STUFF((\n"
                        + "                SELECT ', [' + k.cname + ']'\n"
                        + "                FROM fk_columns k\n"
                        + "                WHERE k.constraint_object_id = fk.[object_id]\n"
                        + "                FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 2, '')\n"
                        + "               + ')' +\n"
                        + "              ' REFERENCES [' + SCHEMA_NAME(ro.[schema_id]) + '].[' + ro.name + '] ('\n"
                        + "              + STUFF((\n"
                        + "                SELECT ', [' + k.rcname + ']'\n"
                        + "                FROM fk_columns k\n"
                        + "                WHERE k.constraint_object_id = fk.[object_id]\n"
                        + "                FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 2, '')\n"
                        + "               + ')'\n"
                        + "            + CASE \n"
                        + "                WHEN fk.delete_referential_action = 1 THEN ' ON DELETE CASCADE' \n"
                        + "                WHEN fk.delete_referential_action = 2 THEN ' ON DELETE SET NULL'\n"
                        + "                WHEN fk.delete_referential_action = 3 THEN ' ON DELETE SET DEFAULT' \n"
                        + "                ELSE '' \n"
                        + "              END\n"
                        + "            + CASE \n"
                        + "                WHEN fk.update_referential_action = 1 THEN ' ON UPDATE CASCADE'\n"
                        + "                WHEN fk.update_referential_action = 2 THEN ' ON UPDATE SET NULL'\n"
                        + "                WHEN fk.update_referential_action = 3 THEN ' ON UPDATE SET DEFAULT'  \n"
                        + "                ELSE '' \n"
                        + "              END \n"
                        + "            + CHAR(13) + 'ALTER TABLE ' + @object_name + ' CHECK CONSTRAINT [' + fk.name  + ']' + CHAR(13)\n"
                        + "        FROM sys.foreign_keys fk WITH (NOWAIT)\n"
                        + "        JOIN sys.objects ro WITH (NOWAIT) ON ro.[object_id] = fk.referenced_object_id\n"
                        + "        WHERE fk.parent_object_id = @object_id\n"
                        + "        FOR XML PATH(N''), TYPE).value('.', 'NVARCHAR(MAX)')), '')\n"
                        + "    + ISNULL(((SELECT\n"
                        + "         CHAR(13) + 'CREATE' + CASE WHEN i.is_unique = 1 THEN ' UNIQUE' ELSE '' END \n"
                        + "                + ' NONCLUSTERED INDEX [' + i.name + '] ON ' + @object_name + ' (' +\n"
                        + "                STUFF((\n"
                        + "                SELECT ', [' + c.name + ']' + CASE WHEN c.is_descending_key = 1 THEN ' DESC' ELSE ' ASC' END\n"
                        + "                FROM index_column c\n"
                        + "                WHERE c.is_included_column = 0\n"
                        + "                    AND c.index_id = i.index_id\n"
                        + "                FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 2, '') + ')'  \n"
                        + "                + ISNULL(CHAR(13) + 'INCLUDE (' + \n"
                        + "                    STUFF((\n"
                        + "                    SELECT ', [' + c.name + ']'\n"
                        + "                    FROM index_column c\n"
                        + "                    WHERE c.is_included_column = 1\n"
                        + "                        AND c.index_id = i.index_id\n"
                        + "                    FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 2, '') + ')', '')  + CHAR(13)\n"
                        + "        FROM sys.indexes i WITH (NOWAIT)\n"
                        + "        WHERE i.[object_id] = @object_id\n"
                        + "            AND i.is_primary_key = 0\n"
                        + "            AND i.[type] = 2\n"
                        + "        FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)')\n"
                        + "    ), '')\n"
                        + "\n"
                        + "select @SQL";
                result = st.executeQuery(aux);
                
                while (result.next()) {
                    textoCompleto += result.getString(1) + "\n";
                }
            }
            textoCompleto += getProgrammabilitySchemaSQLServer(areaTexto);
            areaTexto.setText(textoCompleto);
        } catch (Exception e) {
            errores.add(e.getMessage());
            System.out.println("" + e.getMessage());
        }
    }*/
    
    public static void getTablesSchemaSQLServer() {
        Statement st = null;
        ResultSet result = null;
        String aux = "";
        ArrayList<String> arregloTablas = getData("select TABLE_NAME from INFORMATION_SCHEMA.TABLES");
        codigos.add("");
        errores.add("");
        try {
            Connection conn = Conexion.getConnection();
            st = conn.createStatement();
            String textoCompleto = "";
            for (int i = 0; i < arregloTablas.size(); i++) {
                aux = "DECLARE @table_name SYSNAME\n"
                        + "SELECT @table_name = 'dbo." + arregloTablas.get(i) + "'\n"
                        + "\n"
                        + "DECLARE \n"
                        + "      @object_name SYSNAME\n"
                        + "    , @object_id INT\n"
                        + "\n"
                        + "SELECT \n"
                        + "      @object_name = '[' + s.name + '].[' + o.name + ']'\n"
                        + "    , @object_id = o.[object_id]\n"
                        + "FROM sys.objects o WITH (NOWAIT)\n"
                        + "JOIN sys.schemas s WITH (NOWAIT) ON o.[schema_id] = s.[schema_id]\n"
                        + "WHERE s.name + '.' + o.name = @table_name\n"
                        + "    AND o.[type] = 'U'\n"
                        + "    AND o.is_ms_shipped = 0\n"
                        + "\n"
                        + "DECLARE @SQL NVARCHAR(MAX) = ''\n"
                        + "\n"
                        + ";WITH index_column AS \n"
                        + "(\n"
                        + "    SELECT \n"
                        + "          ic.[object_id]\n"
                        + "        , ic.index_id\n"
                        + "        , ic.is_descending_key\n"
                        + "        , ic.is_included_column\n"
                        + "        , c.name\n"
                        + "    FROM sys.index_columns ic WITH (NOWAIT)\n"
                        + "    JOIN sys.columns c WITH (NOWAIT) ON ic.[object_id] = c.[object_id] AND ic.column_id = c.column_id\n"
                        + "    WHERE ic.[object_id] = @object_id\n"
                        + "),\n"
                        + "fk_columns AS \n"
                        + "(\n"
                        + "     SELECT \n"
                        + "          k.constraint_object_id\n"
                        + "        , cname = c.name\n"
                        + "        , rcname = rc.name\n"
                        + "    FROM sys.foreign_key_columns k WITH (NOWAIT)\n"
                        + "    JOIN sys.columns rc WITH (NOWAIT) ON rc.[object_id] = k.referenced_object_id AND rc.column_id = k.referenced_column_id \n"
                        + "    JOIN sys.columns c WITH (NOWAIT) ON c.[object_id] = k.parent_object_id AND c.column_id = k.parent_column_id\n"
                        + "    WHERE k.parent_object_id = @object_id\n"
                        + ")\n"
                        + "SELECT @SQL = 'CREATE TABLE ' + @object_name + CHAR(13) + '(' + CHAR(13) + STUFF((\n"
                        + "    SELECT CHAR(9) + ', [' + c.name + '] ' + \n"
                        + "        CASE WHEN c.is_computed = 1\n"
                        + "            THEN 'AS ' + cc.[definition] \n"
                        + "            ELSE UPPER(tp.name) + \n"
                        + "                CASE WHEN tp.name IN ('varchar', 'char', 'varbinary', 'binary', 'text')\n"
                        + "                       THEN '(' + CASE WHEN c.max_length = -1 THEN 'MAX' ELSE CAST(c.max_length AS VARCHAR(5)) END + ')'\n"
                        + "                     WHEN tp.name IN ('nvarchar', 'nchar', 'ntext')\n"
                        + "                       THEN '(' + CASE WHEN c.max_length = -1 THEN 'MAX' ELSE CAST(c.max_length / 2 AS VARCHAR(5)) END + ')'\n"
                        + "                     WHEN tp.name IN ('datetime2', 'time2', 'datetimeoffset') \n"
                        + "                       THEN '(' + CAST(c.scale AS VARCHAR(5)) + ')'\n"
                        + "                     WHEN tp.name = 'decimal' \n"
                        + "                       THEN '(' + CAST(c.[precision] AS VARCHAR(5)) + ',' + CAST(c.scale AS VARCHAR(5)) + ')'\n"
                        + "                    ELSE ''\n"
                        + "                END +\n"
                        + "                CASE WHEN c.collation_name IS NOT NULL THEN ' COLLATE ' + c.collation_name ELSE '' END +\n"
                        + "                CASE WHEN c.is_nullable = 1 THEN ' NULL' ELSE ' NOT NULL' END +\n"
                        + "                CASE WHEN dc.[definition] IS NOT NULL THEN ' DEFAULT' + dc.[definition] ELSE '' END + \n"
                        + "                CASE WHEN ic.is_identity = 1 THEN ' IDENTITY(' + CAST(ISNULL(ic.seed_value, '0') AS CHAR(1)) + ',' + CAST(ISNULL(ic.increment_value, '1') AS CHAR(1)) + ')' ELSE '' END \n"
                        + "        END + CHAR(13)\n"
                        + "    FROM sys.columns c WITH (NOWAIT)\n"
                        + "    JOIN sys.types tp WITH (NOWAIT) ON c.user_type_id = tp.user_type_id\n"
                        + "    LEFT JOIN sys.computed_columns cc WITH (NOWAIT) ON c.[object_id] = cc.[object_id] AND c.column_id = cc.column_id\n"
                        + "    LEFT JOIN sys.default_constraints dc WITH (NOWAIT) ON c.default_object_id != 0 AND c.[object_id] = dc.parent_object_id AND c.column_id = dc.parent_column_id\n"
                        + "    LEFT JOIN sys.identity_columns ic WITH (NOWAIT) ON c.is_identity = 1 AND c.[object_id] = ic.[object_id] AND c.column_id = ic.column_id\n"
                        + "    WHERE c.[object_id] = @object_id\n"
                        + "    ORDER BY c.column_id\n"
                        + "    FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 2, CHAR(9) + ' ')\n"
                        + "    + ISNULL((SELECT CHAR(9) + ', CONSTRAINT [' + k.name + '] PRIMARY KEY (' + \n"
                        + "                    (SELECT STUFF((\n"
                        + "                         SELECT ', [' + c.name + '] ' + CASE WHEN ic.is_descending_key = 1 THEN 'DESC' ELSE 'ASC' END\n"
                        + "                         FROM sys.index_columns ic WITH (NOWAIT)\n"
                        + "                         JOIN sys.columns c WITH (NOWAIT) ON c.[object_id] = ic.[object_id] AND c.column_id = ic.column_id\n"
                        + "                         WHERE ic.is_included_column = 0\n"
                        + "                             AND ic.[object_id] = k.parent_object_id \n"
                        + "                             AND ic.index_id = k.unique_index_id     \n"
                        + "                         FOR XML PATH(N''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 2, ''))\n"
                        + "            + ')' + CHAR(13)\n"
                        + "            FROM sys.key_constraints k WITH (NOWAIT)\n"
                        + "            WHERE k.parent_object_id = @object_id \n"
                        + "                AND k.[type] = 'PK'), '') + ')'  + CHAR(13)\n"
                        + "    + ISNULL((SELECT (\n"
                        + "        SELECT CHAR(13) +\n"
                        + "             'ALTER TABLE ' + @object_name + ' WITH' \n"
                        + "            + CASE WHEN fk.is_not_trusted = 1 \n"
                        + "                THEN ' NOCHECK' \n"
                        + "                ELSE ' CHECK' \n"
                        + "              END + \n"
                        + "              ' ADD CONSTRAINT [' + fk.name  + '] FOREIGN KEY(' \n"
                        + "              + STUFF((\n"
                        + "                SELECT ', [' + k.cname + ']'\n"
                        + "                FROM fk_columns k\n"
                        + "                WHERE k.constraint_object_id = fk.[object_id]\n"
                        + "                FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 2, '')\n"
                        + "               + ')' +\n"
                        + "              ' REFERENCES [' + SCHEMA_NAME(ro.[schema_id]) + '].[' + ro.name + '] ('\n"
                        + "              + STUFF((\n"
                        + "                SELECT ', [' + k.rcname + ']'\n"
                        + "                FROM fk_columns k\n"
                        + "                WHERE k.constraint_object_id = fk.[object_id]\n"
                        + "                FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 2, '')\n"
                        + "               + ')'\n"
                        + "            + CASE \n"
                        + "                WHEN fk.delete_referential_action = 1 THEN ' ON DELETE CASCADE' \n"
                        + "                WHEN fk.delete_referential_action = 2 THEN ' ON DELETE SET NULL'\n"
                        + "                WHEN fk.delete_referential_action = 3 THEN ' ON DELETE SET DEFAULT' \n"
                        + "                ELSE '' \n"
                        + "              END\n"
                        + "            + CASE \n"
                        + "                WHEN fk.update_referential_action = 1 THEN ' ON UPDATE CASCADE'\n"
                        + "                WHEN fk.update_referential_action = 2 THEN ' ON UPDATE SET NULL'\n"
                        + "                WHEN fk.update_referential_action = 3 THEN ' ON UPDATE SET DEFAULT'  \n"
                        + "                ELSE '' \n"
                        + "              END \n"
                        + "            + CHAR(13) + 'ALTER TABLE ' + @object_name + ' CHECK CONSTRAINT [' + fk.name  + ']' + CHAR(13)\n"
                        + "        FROM sys.foreign_keys fk WITH (NOWAIT)\n"
                        + "        JOIN sys.objects ro WITH (NOWAIT) ON ro.[object_id] = fk.referenced_object_id\n"
                        + "        WHERE fk.parent_object_id = @object_id\n"
                        + "        FOR XML PATH(N''), TYPE).value('.', 'NVARCHAR(MAX)')), '')\n"
                        + "    + ISNULL(((SELECT\n"
                        + "         CHAR(13) + 'CREATE' + CASE WHEN i.is_unique = 1 THEN ' UNIQUE' ELSE '' END \n"
                        + "                + ' NONCLUSTERED INDEX [' + i.name + '] ON ' + @object_name + ' (' +\n"
                        + "                STUFF((\n"
                        + "                SELECT ', [' + c.name + ']' + CASE WHEN c.is_descending_key = 1 THEN ' DESC' ELSE ' ASC' END\n"
                        + "                FROM index_column c\n"
                        + "                WHERE c.is_included_column = 0\n"
                        + "                    AND c.index_id = i.index_id\n"
                        + "                FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 2, '') + ')'  \n"
                        + "                + ISNULL(CHAR(13) + 'INCLUDE (' + \n"
                        + "                    STUFF((\n"
                        + "                    SELECT ', [' + c.name + ']'\n"
                        + "                    FROM index_column c\n"
                        + "                    WHERE c.is_included_column = 1\n"
                        + "                        AND c.index_id = i.index_id\n"
                        + "                    FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 2, '') + ')', '')  + CHAR(13)\n"
                        + "        FROM sys.indexes i WITH (NOWAIT)\n"
                        + "        WHERE i.[object_id] = @object_id\n"
                        + "            AND i.is_primary_key = 0\n"
                        + "            AND i.[type] = 2\n"
                        + "        FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)')\n"
                        + "    ), '')\n"
                        + "\n"
                        + "select @SQL";
                result = st.executeQuery(aux);
                
                while (result.next()) {
                    textoCompleto += result.getString(1) + "\n";
                }
            }
            textoCompleto += getProgrammabilitySchemaSQLServer();
            codigos.add(textoCompleto);
        } catch (Exception e) {
            errores.add(e.getMessage());
            System.out.println("" + e.getMessage());
        }
    }
    
    public static String getProgrammabilitySchemaSQLServer() {
        Statement st = null;
        ResultSet result = null;
        ArrayList<String> programmabilty = getData("Select [name] from sysobjects where type = 'P'\n"
                + "OR type ='FN' OR type = 'V'\n"
                + "OR type = 'TR' order by crdate");
        errores.add("");
        try {
            Connection conn = Conexion.getConnection();
            st = conn.createStatement();
            String textoCompleto = "";
            for (int i = 0; i < programmabilty.size(); i++) {
                result = st.executeQuery("sp_helptext " + programmabilty.get(i));
                while (result.next()) {
                    textoCompleto += result.getString(1);
                }
            }
            //areaTexto.setText(textoCompleto);
            return textoCompleto;
        } catch (Exception e) {
            errores.add(e.getMessage());
        }
        return "";
    }
    
    /*public static void getSchemaOracle(JTextArea areaTexto) {
        Statement st = null;
        ResultSet result = null;
        String aux = "";
        ArrayList<String> objectName = getData("select object_name, object_type from user_objects where object_type not in ('PACKAGE BODY') order by created");
        ArrayList<String> objectType = getData2("select object_name, object_type from user_objects where object_type not in ('PACKAGE BODY') order by created");
        areaTexto.setText("");
        errores.add("");
        try {
            Connection conn = Conexion.getConnection();
            st = conn.createStatement();
            String textoCompleto = "";
            areaTexto.setText(" " + objectName);
            for (int i = 0; i < objectName.size(); i++) {
                result = st.executeQuery("select dbms_metadata.get_ddl('" + objectType.get(i) + "','" + objectName.get(i) + "','" + Conexion.getUsuario().toUpperCase() + "') \n"
                        + "  from dual");
                System.out.println("" + objectName.get(i));
                System.out.println("" + objectType.get(i));
                while (result.next()) {
                    textoCompleto += result.getString(1);
                }
            }
            //System.out.println("" + Conexion.getUsuario().toUpperCase());
            areaTexto.setText(textoCompleto);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            errores.add(e.getMessage());
        }
    }*/
    
    public static void getSchemaOracle() {
        Statement st = null;
        ResultSet result = null;
        String aux = "";
        ArrayList<String> objectName = getData("select object_name, object_type from user_objects where object_type not in ('PACKAGE BODY') order by created");
        ArrayList<String> objectType = getData2("select object_name, object_type from user_objects where object_type not in ('PACKAGE BODY') order by created");
        codigos.add("");
        errores.add("");
        try {
            Connection conn = Conexion.getConnection();
            st = conn.createStatement();
            String textoCompleto = "";
            codigos.add(" " + objectName);
            for (int i = 0; i < objectName.size(); i++) {
                result = st.executeQuery("select dbms_metadata.get_ddl('" + objectType.get(i) + "','" + objectName.get(i) + "','" + Conexion.getUsuario().toUpperCase() + "') \n"
                        + "  from dual");
                System.out.println("" + objectName.get(i));
                System.out.println("" + objectType.get(i));
                while (result.next()) {
                    textoCompleto += result.getString(1);
                }
            }
            codigos.add(textoCompleto);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            errores.add(e.getMessage());
        }
    }
    
    public static void getSchema() {
        if (Conexion.isEsOracle()) {
            getSchemaOracle();
        } else if (Conexion.isEsSQLServer()) {
            getTablesSchemaSQLServer();
        }
    }
    
    /*public static void getSchema(JTextArea areaTexto) {
        if (Conexion.isEsOracle(areaTexto)) {
            getSchemaOracle();
        } else if (Conexion.isEsSQLServer(areaTexto)) {
            getTablesSchemaSQLServer();
        }
    }*/
    
    
    public static String getArregloErrores() {
        return errores.get(errores.size() - 1);
    }
    
    public static String getArregloCodigos() {
        return codigos.get(codigos.size() - 1);
    }
    
    public static String[] getColumnasTab(){
        return columnasTab;
    }
    
}
