package com.inventory.management.inventory.management;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author gaura
 */

import java.sql.*;
import java.util.*;
import org.sqlite.JDBC;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.sql.*;
import java.util.Date;  // For Date handling
import java.text.SimpleDateFormat;  // For formatting Date to string
import com.toedter.calendar.JDateChooser;  // JDateChooser for date selection
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.util.Date;  // For handling Date objects
import java.text.SimpleDateFormat;  // For formatting Date to a specific pattern
// For autocomplete in JComboBox


public class entry extends javax.swing.JFrame {

    /**
     * Creates new form entry
     */
    public entry() {
        initComponents();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        empEnt = new javax.swing.JLabel();
        empName = new javax.swing.JComboBox<>();
        try{
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:inven.db");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from emp");
            while(rs.next()){
                String s = rs.getString("empName")+" "+rs.getString("empID");
                empName.addItem(s);
            }
            con.close();
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("Error is "+e.getMessage());
        }
        AutoCompleteDecorator.decorate(empName);
        itemName = new javax.swing.JComboBox<>();
        try{
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:inven.db");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from items");
            while(rs.next()){
                itemName.addItem(rs.getString("itemName"));
                System.out.println(rs.getString("itemName"));
            }
            con.close();
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("Error is "+e.getMessage());
        }

        AutoCompleteDecorator.decorate(itemName);
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        quan = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        totalTA = new javax.swing.JTextArea();
        addBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        empEnt.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        empEnt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empEnt.setText("Employee Entry");
        getContentPane().add(empEnt);
        empEnt.setBounds(0, 0, 650, 70);

        empName.setEditable(true);
        empName.setToolTipText("");
        getContentPane().add(empName);
        empName.setBounds(10, 100, 200, 22);

        itemName.setEditable(true);
        itemName.setToolTipText("");
        getContentPane().add(itemName);
        itemName.setBounds(10, 170, 200, 22);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(0, 70, 790, 10);

        jLabel1.setText("Quantity");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(380, 150, 100, 16);

        jLabel2.setText("Employee Name");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 80, 100, 16);

        jLabel3.setText("Item Name");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 150, 100, 16);
        getContentPane().add(quan);
        quan.setBounds(380, 170, 90, 22);

        totalTA.setColumns(20);
        totalTA.setRows(5);
        jScrollPane1.setViewportView(totalTA);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(60, 240, 470, 180);

        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        getContentPane().add(addBtn);
        addBtn.setBounds(260, 210, 75, 23);

        jLabel4.setText("Date");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(380, 80, 100, 20);
        getContentPane().add(jDateChooser);
        jDateChooser.setBounds(350, 100, 160, 40);

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(530, 80, 60, 23);

        setSize(new java.awt.Dimension(614, 457));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
        // Get the selected item and quantity
    String emp = (String) empName.getSelectedItem();
    String item = (String) itemName.getSelectedItem();
    int qn = (int) quan.getValue();
    
    // Split the employee name to get empName and empID
    String[] empDetails = emp.split(" ");
    String empName = empDetails[0];
    String empID = empDetails[1];
    
    // Get the selected date
    Date selectedDate = jDateChooser.getDate();
    
    // Format the date to "yyyy-MM-dd"
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String entryDate = formatter.format(selectedDate);

    // Insert data into the database
    try {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:inven.db");
        String query = "INSERT INTO entry (empName, empID, itemName, quantity, entryDate) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, empName);
        pst.setString(2, empID);
        pst.setString(3, item);
        pst.setInt(4, qn);
        pst.setString(5, entryDate);  // Insert the formatted date

        int result = pst.executeUpdate();
        
        if (result > 0) {
            totalTA.append("\nEntry added: " + item + " : " + qn + " on " + entryDate);
        } else {
            totalTA.append("\nFailed to add entry.");
        }

        pst.close();
        con.close();
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("Error: " + e.getMessage());
        totalTA.append("\nError: " + e.getMessage());
    }

        
        
    }//GEN-LAST:event_addBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new AdminMenu().setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(entry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(entry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(entry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(entry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new entry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JLabel empEnt;
    private javax.swing.JComboBox<String> empName;
    private javax.swing.JComboBox<String> itemName;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner quan;
    private javax.swing.JTextArea totalTA;
    // End of variables declaration//GEN-END:variables
}
