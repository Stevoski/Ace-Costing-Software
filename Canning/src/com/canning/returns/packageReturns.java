 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.canning.returns;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import newpackage1.NewJFrame;
import static newpackage1.NewJFrame.jComboBox3;
import static newpackage1.NewJFrame.jTextLogin;
import static newpackage1.NewJFrame.jTextStatus;
import newpackage1.db_Connection;

/**
 *
 * @author Stevoski
 */
public class packageReturns {
    public static ArrayList<String> getPackage() throws InstantiationException {
        String grootpc= jTextStatus.getText();
        String grutpc = jTextLogin.getText();
          String qrduie=(String)jComboBox3.getSelectedItem();
        ArrayList<String> packageList = new ArrayList<>();
        ResultSet trm;
        String kruda;
       
        switch (grootpc) {
            case "USER":
                String sI ="select * from package where factory=?";
                try (PreparedStatement oloki = db_Connection.getInstance().prepareStatement(sI)) {
                    oloki.setString(1, grutpc);
                    trm = oloki.executeQuery();
                    while (trm.next()) {
                        kruda = trm.getString("can_name");
                        packageList.add(kruda);
                    }
                } catch (SQLException e) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
            case "ADMINISTRATOR":
                String sIs ="select * from package where factory=?";
                try (PreparedStatement oloki = db_Connection.getInstance().prepareStatement(sIs)) {
                    oloki.setString(1, qrduie);
                    trm = oloki.executeQuery();
                    while (trm.next()) {
                        kruda = trm.getString("can_name");
                        packageList.add(kruda);
                    }
                } catch (SQLException e) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, e);
                }
            default:

                break;
        }
        return packageList;
    }
    public static ArrayList<String> getmainPackage() throws InstantiationException {
        String grootpc= jTextStatus.getText();
        String grutpc = jTextLogin.getText();
        ArrayList<String> packageList = new ArrayList<>();
        ResultSet trm;
        String kruda;        
        switch (grootpc) {
            case "USER":
                String sI ="select * from package where factory=?";
                try (PreparedStatement oloki = db_Connection.getInstance().prepareStatement(sI)) {
                    oloki.setString(1, grutpc);
                    trm = oloki.executeQuery();
                    while (trm.next()) {
                        kruda = trm.getString("main_package");
                        packageList.add(kruda);
                    }
                } catch (SQLException e) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
            case "ADMINISTRATOR":
                String kroodE = "select distinct(package) from main_package WHERE category='package'";
                try (PreparedStatement oloki = db_Connection.getInstance().prepareStatement(kroodE)) {
                    trm = oloki.executeQuery(kroodE);
                    while (trm.next()) {
                        kruda = trm.getString("package");
                        packageList.add(kruda);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Invalid User. Please contact your system administrator for more information!");
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, e);
                    break;
                }
                 case "CENTRAL":
                String kroodS = "select distinct(package) from main_package WHERE category='package'";
                try (PreparedStatement oloki = db_Connection.getInstance().prepareStatement(kroodS)) {
                    trm = oloki.executeQuery(kroodS);
                    while (trm.next()) {
                        kruda = trm.getString("package");
                        packageList.add(kruda);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Invalid User. Please contact your system administrator for more information!");
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, e);
                    break;
                }
            default:

                break;
        }
        return packageList;
    }
}
