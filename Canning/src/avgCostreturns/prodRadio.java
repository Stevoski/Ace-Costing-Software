/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avgCostreturns;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static newpackage1.NewJFrame.Choose1;
import static newpackage1.NewJFrame.Choose2;
import static newpackage1.NewJFrame.jLabel156;
import static newpackage1.NewJFrame.jTableavg;
import static newpackage1.NewJFrame.jTextField20;
import static newpackage1.NewJFrame.jTextLogin;
import newpackage1.db_Connection;

/**
 *
 * @author Stevoski
 */
public class prodRadio {
   
     public static void prodradioAdmin() throws HeadlessException {
 String pck = jTextLogin.getText();
//        String aciu = jLabel156.getText();
//          String avgdisp1 = jLabel141.getText();
        String dateaverage1 = ((JTextField) Choose1.getDateEditor().getUiComponent()).getText();
        String dateaverage2 = ((JTextField) Choose2.getDateEditor().getUiComponent()).getText();
        String avgdisp = jTextField20.getText();     
        try {
            String clik = "SELECT \n"
                    + "        `new_view`.`product` AS `product`,\n"
                    + "        `new_view`.`factory` AS `factory`,\n"
                    + "        `new_view`.`kgsperpc` AS `kgsperpc`,\n"
                    + "       `new_view`.`IngCost`+`new_view`.`rawcost` AS `rawcost`,\n"
                    + "	`new_view`.`LabelCost`+`new_view`.`CanCost` AS `cancost`,\n"
                    + "        `new_view`.`briqCost` + `new_view`.`fuelCost` AS `fuelcost`,\n"
                    + "        `new_view`.`eleCost`  AS `eleCost`,\n"
                    + "	`new_view`.`dieselCost` AS `dieselCost`,\n"
                    + "	`new_view`.`manCost` AS `manCost`,\n"
                    + "	`new_view`.`wasteCost` AS `wastecost`,\n"
                    + "     `new_view`.`Cperunit` AS `AveragePrice`,\n"
 + "     `new_view`.`standard_cost` AS `standard_cost`,\n"
                     + "     round(`new_view`.`deviation_from_averagecost`,2) AS `deviation`,\n"
                    + "     `new_view`.`date` AS `date`,  \n"
                    + "     `new_view`.`quantity` AS `quantity`"
                    + " FROM `new_view`"
                    + " WHERE new_view.product='" + avgdisp + "' and new_view.date between ? and ? ";

            try {
                
                PreparedStatement psy = db_Connection.getInstance().prepareStatement(clik);
                psy.setString(1, dateaverage1);
                psy.setString(2, dateaverage2);
                ResultSet rsPadd = psy.executeQuery();

                while (rsPadd.next()) {
                    jTableavg.setModel(rsToTableModels.aboveMarginModel.buildTableModel(rsPadd));
//                    model.addRow(new Object[]{rsPadd.getString("product"), rsPadd.getString("factory"), rsPadd.getDouble("rawcost"), rsPadd.getDouble("cancost"), rsPadd.getDouble("fuelcost"), rsPadd.getDouble("elecost"), rsPadd.getDouble("dieselcost"), rsPadd.getDouble("mancost"), rsPadd.getDouble("wastecost"), rsPadd.getDouble("averageprice"), rsPadd.getString("date"), rsPadd.getDouble("quantity")});
                }
                psy.close();
          rsPadd.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error connection");
                e.printStackTrace();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error connection");
            e.printStackTrace();
        }
    }

    
    public static void prodradioUser() throws HeadlessException {
        String pck = jTextLogin.getText();
        String aciu = jLabel156.getText();
//         String avgdisp1 = jLabel141.getText();
        String dateaverage1 = ((JTextField) Choose1.getDateEditor().getUiComponent()).getText();
        String dateaverage2 = ((JTextField) Choose2.getDateEditor().getUiComponent()).getText();
        String avgdisp = jTextField20.getText();
        try {
            String clik = "SELECT * FROM `new_view` WHERE new_view.product='" + avgdisp + "' and new_view.factory='" + pck + "' ";

            try {
               ;
                try (PreparedStatement psy = db_Connection.getInstance().prepareStatement(clik)) {
                    psy.setString(1, dateaverage2);
                    psy.setString(2, dateaverage1);
                    ResultSet  rsPadd = psy.executeQuery();
                    while (rsPadd.next()) {
                        jTableavg.setModel(rsToTableModels.aboveMarginModel.buildTableModel(rsPadd));
//                    model.addRow(new Object[]{rsPadd.getString("product"), rsPadd.getString("factory"), rsPadd.getDouble("rawcost"), rsPadd.getDouble("cancost"), rsPadd.getDouble("fuelcost"), rsPadd.getDouble("elecost"), rsPadd.getDouble("dieselcost"), rsPadd.getDouble("mancost"), rsPadd.getDouble("wastecost"), rsPadd.getDouble("averageprice"), rsPadd.getString("date"), rsPadd.getDouble("quantity")});
                    }
                    rsPadd.close();
          psy.close();
                }
          
                      } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error connection");
                e.printStackTrace();
            }
//            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error connection");
            e.printStackTrace();
        }
    }  
}
