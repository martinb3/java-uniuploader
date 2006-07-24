package org.mbs3.juniuploader.gui;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mbs3.juniuploader.objects.remoteobjects.FormPair;
import org.mbs3.juniuploader.objects.remoteobjects.FormPairGroup;
import org.mbs3.juniuploader.util.CheckSummer;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class pnlFormVariables extends javax.swing.JPanel {
    
    public static final long serialVersionUID = 1L;
    
    private JList lstFormPairValues;
    private JButton btnAddPair;
    private JButton btnRemPair;
    
    private JButton btnEditPair;
    private JButton btnRemSelGrp;
    private JButton btnAddGroup;
    private JComboBox cmbFormGroups;
    private static Log log = LogFactory.getLog(pnlFormVariables.class);
    
    public pnlFormVariables() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            GridBagLayout thisLayout = new GridBagLayout();
            this.setLayout(thisLayout);
            {
                btnAddPair = new JButton();
                this.add(btnAddPair, new GridBagConstraints(3, 3, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                btnAddPair.setText("Add a New Pair");
                btnAddPair.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnAddPairActionPerformed(evt);
                    }
                });
            }
            {
                btnRemPair = new JButton();
                this.add(btnRemPair, new GridBagConstraints(3, 6, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                btnRemPair.setText("Remove Selected Pair");
                btnRemPair.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnRemPairActionPerformed(evt);
                    }
                });
            }
            {
                
                cmbFormGroups = new JComboBox();
                this.add(cmbFormGroups, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                cmbFormGroups.setModel(frmMain.formGroups);
                cmbFormGroups.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent evt) {
                        cmbFormGroupsItemStateChanged(evt);
                    }
                });
            }
            {

                DefaultComboBoxModel groups = frmMain.formGroups;
                FormPairGroup fpg = (FormPairGroup)groups.getSelectedItem();
                DefaultComboBoxModel dcbmFormPairs;
                if(fpg != null)
                    dcbmFormPairs = new DefaultComboBoxModel(fpg.getPairs());
                else
                    dcbmFormPairs = new DefaultComboBoxModel();
                
                lstFormPairValues = new JList();
                lstFormPairValues.setModel(dcbmFormPairs);

                this.add(lstFormPairValues, new GridBagConstraints(1, 3, 1, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                lstFormPairValues.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
            }

            {
                btnAddGroup = new JButton();
                this.add(btnAddGroup, new GridBagConstraints(
                    3,
                    1,
                    1,
                    1,
                    0.0,
                    0.0,
                    GridBagConstraints.CENTER,
                    GridBagConstraints.NONE,
                    new Insets(0, 0, 0, 0),
                    0,
                    0));
                btnAddGroup.setText("Add a Group");
                btnAddGroup.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnAddGroupActionPerformed(evt);
                    }
                });
            }
            {
                btnRemSelGrp = new JButton();
                this.add(btnRemSelGrp, new GridBagConstraints(
                    5,
                    1,
                    1,
                    1,
                    0.0,
                    0.0,
                    GridBagConstraints.CENTER,
                    GridBagConstraints.NONE,
                    new Insets(0, 0, 0, 0),
                    0,
                    0));
                btnRemSelGrp.setText("Remove This Group");
                btnRemSelGrp.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnRemSelGrpActionPerformed(evt);
                    }
                });
            }
            {
                btnEditPair = new JButton();
                this.add(btnEditPair, new GridBagConstraints(3, 4, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                btnEditPair.setText("Edit This Pair");
                btnEditPair.setEnabled(false);
            }
            thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.1, 0.1, 0.0, 0.1, 0.0};
            thisLayout.rowHeights = new int[] {8, 24, 9, 20, 7, 8, 7, 9};
            thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            thisLayout.columnWidths = new int[] {11, 335, 9, 97, 10, 119, 10};
            this.setPreferredSize(new java.awt.Dimension(614, 382));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public JList getLstFormPairValues() {
        return lstFormPairValues;
    }
    
    public JButton getBtnAddPair() {
        return btnAddPair;
    }
    
    public JButton getBtnRemPair() {
        return btnRemPair;
    }
    
    private void btnAddGroupActionPerformed(ActionEvent evt) {
        if(evt.getSource() == this.btnAddGroup)
        {
            String returnVal = JOptionPane.showInputDialog(this,"What is the name of the new group:");

            if (returnVal != null && !returnVal.equals("")) {
                    FormPairGroup fpg = new FormPairGroup(returnVal);
                    
                    frmMain.formGroups.addElement(fpg);
                    log.trace("btnAddGroupActionPerformed() added group " + fpg.getName());
                    log.trace(frmMain.formGroups.getSize() + " items now in groups model");
            } else {
                log.trace("New form group cancelled by user.");
            }

        }
    }
    
    private void btnRemSelGrpActionPerformed(ActionEvent evt) {
        JButton b = (JButton)evt.getSource();
        if(b == this.btnRemSelGrp)
        {
            
            if(this.cmbFormGroups.getSelectedItem() != null)
            {
                frmMain.formGroups.removeElement(this.cmbFormGroups.getSelectedItem());
            }
                
        }

    }
    
    private void cmbFormGroupsItemStateChanged(ItemEvent evt) {
       if(evt.getSource() == this.cmbFormGroups)
       {
           int sel = this.cmbFormGroups.getSelectedIndex();
           if(sel != -1)
           {
               FormPairGroup fpg = (FormPairGroup)frmMain.formGroups.getElementAt(sel);
               frmMain.formPairValues = new DefaultComboBoxModel(fpg.getPairs());
               this.lstFormPairValues.setModel(frmMain.formPairValues);
               this.lstFormPairValues.invalidate();
           }
           else
           {
               frmMain.formPairValues = new DefaultComboBoxModel();
               this.lstFormPairValues.setModel(frmMain.formPairValues);
               this.lstFormPairValues.invalidate();
           }
       }
    }
    
    private void btnAddPairActionPerformed(ActionEvent evt) {
        if(evt.getSource() == this.btnAddPair)
        {
            String key = JOptionPane.showInputDialog(this,"What is the field name:");
            String val = JOptionPane.showInputDialog(this,"What is the field value:");
            int enc = JOptionPane.showConfirmDialog(this, "Would you like this variable sent and stored with encryption?", "Encrypt?", JOptionPane.YES_NO_OPTION);

            if (key != null && !key.equals("") && val != null && !val.equals("")) {
                
                // send roster_update_password securely
                if(enc == 0)
                {
                    val = CheckSummer.createChecksumFromString(val);
                }
                
                FormPair fp = new FormPair(key,val); 
                int sel = this.cmbFormGroups.getSelectedIndex();
                if(sel != -1)
                {
                    frmMain.formPairValues.addElement(fp);
                 }
            } else {
                log.trace("New site addition cancelled by user.");
            }

        }
    }
    
    private void btnRemPairActionPerformed(ActionEvent evt) {
        JButton b = (JButton)evt.getSource();
        if(b == this.btnRemPair)
        {
            //java.util.Vector groups = frmMain.getUploader().getRemoteSystem().getUploadSites();
            FormPairGroup fpg = (FormPairGroup)frmMain.formGroups.getSelectedItem();
            FormPair fp = (FormPair)this.lstFormPairValues.getSelectedValue();
            if(fpg != null && fp != null)
            {
                        frmMain.formPairValues.removeElement(fp);

            }
                
        }
    }

}
