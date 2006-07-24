package org.mbs3.juniuploader.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import org.mbs3.juniuploader.objects.localobjects.LUAFile;
import org.mbs3.juniuploader.objects.localobjects.WAccount;
import org.mbs3.juniuploader.objects.localobjects.WDirectory;
import org.mbs3.juniuploader.objects.remoteobjects.FormPairGroup;
import org.mbs3.juniuploader.objects.remoteobjects.UploadRule;
import org.mbs3.juniuploader.objects.remoteobjects.UploadSite;

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
public class pnlUploadRules extends javax.swing.JPanel {
    
    public static final long serialVersionUID = 1L;

    private JComboBox cmbWoWDirs;
    private JButton btnDelSelRule;
    private JButton btnAddRule;
    private JComboBox cmbFormVarGrp;
    private JLabel lblSelFormGrp;
    private JComboBox cmbLUAFiles;
    private JList lstRules;
    private JScrollPane jScrollPane1;
    private JComboBox cmbUploadSite;
    private JLabel lblSelSite;
    private JLabel lblSelectLuaFile;
    private JComboBox cmbAccts;
    private JLabel lblSelectAnAccount;
    private JLabel lblSelActDir;
    
    /**
    * Auto-generated main method to display this 
    * JPanel inside a new JFrame.
    */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new pnlUploadRules());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    public pnlUploadRules() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            GridBagLayout thisLayout = new GridBagLayout();
            this.setLayout(thisLayout);
            {
                lblSelActDir = new JLabel();
                this.add(lblSelActDir, new GridBagConstraints(4, 1, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                lblSelActDir.setText("Select a World of Warcraft Directory:");
            }
            {
                 
                cmbWoWDirs = new JComboBox();
                cmbWoWDirs.setModel(frmMain.wowDirectories);
                this.add(cmbWoWDirs, new GridBagConstraints(4, 2, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                cmbWoWDirs.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent evt) {
                        cmbWoWDirsItemStateChanged(evt);
                    }
                });
            }
            {
                lblSelectAnAccount = new JLabel();
                this.add(lblSelectAnAccount, new GridBagConstraints(4, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                lblSelectAnAccount.setText("Select a World of Warcraft Account:");
            }
            {
                WDirectory wd = (WDirectory)frmMain.wowDirectories.getSelectedItem();
                if(wd != null)
                    frmMain.accounts = new DefaultComboBoxModel(wd.getAccounts());
                else
                    frmMain.accounts = new DefaultComboBoxModel();
                cmbAccts = new JComboBox();
                this.add(cmbAccts, new GridBagConstraints(4, 5, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                if(frmMain.accounts != null)
                    cmbAccts.setModel(frmMain.accounts);
                cmbAccts.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent evt) {
                        cmbAcctsItemStateChanged(evt);
                    }
                });
            }
            {
                lblSelectLuaFile = new JLabel();
                this.add(lblSelectLuaFile, new GridBagConstraints(4, 7, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                lblSelectLuaFile.setText("Select a Saved Variables LUA File:");
            }
            {
                 
                cmbLUAFiles = new JComboBox();
                WAccount wa = (WAccount)frmMain.accounts.getSelectedItem();
                if(wa != null)
                    frmMain.luaFiles = new DefaultComboBoxModel(wa.getLuaFiles());

                this.add(cmbLUAFiles, new GridBagConstraints(4, 8, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                if(frmMain.luaFiles != null)
                    cmbLUAFiles.setModel(frmMain.luaFiles);
            }
            {
                lblSelFormGrp = new JLabel();
                this.add(lblSelFormGrp, new GridBagConstraints(4, 13, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                lblSelFormGrp.setText("Select a Form Variable Group:");
            }
            {
                cmbFormVarGrp = new JComboBox();
                this.add(cmbFormVarGrp, new GridBagConstraints(4, 14, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                cmbFormVarGrp.setModel(frmMain.formGroups);
            }
            {
                btnAddRule = new JButton();
                this.add(btnAddRule, new GridBagConstraints(4, 16, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                btnAddRule.setText("Add This Rule");
                btnAddRule.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnAddRuleActionPerformed(evt);
                    }
                });
            }
            {
                btnDelSelRule = new JButton();
                this.add(btnDelSelRule, new GridBagConstraints(5, 16, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                btnDelSelRule.setText("Delete Selected Rule");
                btnDelSelRule.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnDelSelRuleActionPerformed(evt);
                    }
                });
            }
            {
                lblSelSite = new JLabel();
                this.add(lblSelSite, new GridBagConstraints(4, 10, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                lblSelSite.setText("Select an Upload Site:");
            }
            {

                cmbUploadSite = new JComboBox();
                this.add(cmbUploadSite, new GridBagConstraints(4, 11, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                cmbUploadSite.setModel(frmMain.uploadLocations);
            }
            {
                jScrollPane1 = new JScrollPane();
                this.add(jScrollPane1, new GridBagConstraints(1, 1, 2, 16, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                {
                    
                    lstRules = new JList();
                    jScrollPane1.setViewportView(lstRules);
                    lstRules.setModel(frmMain.uploadRules);
                    this.lstRules.invalidate();
                }
            }
            thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            thisLayout.rowHeights = new int[] {7, 22, 28, 8, 21, 29, 8, 19, 26, 9, 19, 32, 8, 25, 25, 8, 36, 6};
            thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.1, 0.1, 0.0};
            thisLayout.columnWidths = new int[] {10, 162, 135, 10, 20, 20, 11};
            this.setPreferredSize(new java.awt.Dimension(598, 359));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void cmbWoWDirsItemStateChanged(ItemEvent evt) {
        WDirectory wd = (WDirectory)frmMain.wowDirectories.getSelectedItem();
        if(wd != null)
        {
            frmMain.accounts = new DefaultComboBoxModel(wd.getAccounts());
            this.cmbAccts.setModel(frmMain.accounts);
            this.cmbAccts.invalidate();
        }
    }
    
    private void cmbAcctsItemStateChanged(ItemEvent evt) {
        WAccount wa = (WAccount)frmMain.accounts.getSelectedItem();
        if(wa != null)
        {
            frmMain.luaFiles = new DefaultComboBoxModel(wa.getLuaFiles());
            this.cmbLUAFiles.setModel(frmMain.luaFiles);
            this.cmbAccts.invalidate();
        }

    }
    
    private void btnAddRuleActionPerformed(ActionEvent evt) {
        if(evt.getSource() == this.btnAddRule)
        {
            WDirectory wd = (WDirectory) this.cmbWoWDirs.getSelectedItem();
            WAccount wa = (WAccount) this.cmbAccts.getSelectedItem();
            FormPairGroup fpg = (FormPairGroup) this.cmbFormVarGrp.getSelectedItem();
            UploadSite us = (UploadSite) this.cmbUploadSite.getSelectedItem();
            LUAFile lf = (LUAFile) this.cmbLUAFiles.getSelectedItem();
            
            if(
                    (wd != null) &&
                    (wa != null) &&
                    (us != null) &&
                    (lf != null)
                    )
            {
                UploadRule ur = new UploadRule(lf, wa, wd, us, fpg);
                frmMain.uploadRules.addElement(ur);
                
            }
        }
    }
    
    private void btnDelSelRuleActionPerformed(ActionEvent evt) {
        JButton b = (JButton)evt.getSource();
        if(b == this.btnDelSelRule)
        {
            int sel = this.lstRules.getSelectedIndex();
            if(sel != -1)
            {
                frmMain.uploadRules.removeElementAt(sel);
            }
                
        }
    }

}
