package org.mbs3.juniuploader.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mbs3.juniuploader.objects.localobjects.WDirectory;

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
public class pnlMainMenu extends javax.swing.JPanel {
    
    public static final long serialVersionUID = 1L;
    
    private JButton btnPerfUpload;
    private JButton btnSyncUAInterface;
    private JButton btnExit;
    private JButton btnLaunchWoW;
    private JButton btnEraseSettings;
    private JButton btnChkUploads;

    private static Log log = LogFactory.getLog(pnlMainMenu.class);
    /**
    * Auto-generated main method to display this 
    * JPanel inside a new JFrame.
    */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new pnlMainMenu());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    public pnlMainMenu() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            GridBagLayout thisLayout = new GridBagLayout();
            thisLayout.rowWeights = new double[] {0.0, 0.1, 0.1, 0.1, 0.0};
            thisLayout.rowHeights = new int[] {10, 7, 7, 7, 9};
            thisLayout.columnWeights = new double[] {0.0, 0.1, 0.0, 0.1, 0.0};
            thisLayout.columnWidths = new int[] {12, 7, 10, 7, 11};
            this.setLayout(thisLayout);
            {
                btnPerfUpload = new JButton();
                this.add(btnPerfUpload, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                btnPerfUpload.setText("Perform Upload Rules");
                btnPerfUpload.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnPerfUploadActionPerformed(evt);
                    }
                });
            }
            {
                btnChkUploads = new JButton();
                this.add(btnChkUploads, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                btnChkUploads.setText("Check for Program Updates");
                btnChkUploads.setEnabled(false);
            }
            {
                btnSyncUAInterface = new JButton();
                this.add(btnSyncUAInterface, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                btnSyncUAInterface.setText("Sync with UniAdmin Interface");
                btnSyncUAInterface.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnSyncUAInterfaceActionPerformed(evt);
                    }
                });
            }
            {
                btnEraseSettings = new JButton();
                this.add(btnEraseSettings, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                btnEraseSettings.setText("Erase Saved Settings (causes exit)");
                btnEraseSettings.setEnabled(false);
                btnEraseSettings.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnEraseSettingsActionPerformed(evt);
                    }
                });
                //btnEraseSettings.setEnabled(false);
            }
            {
                btnLaunchWoW = new JButton();
                this.add(btnLaunchWoW, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                btnLaunchWoW.setText("Launch WoW");
                btnLaunchWoW.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnLaunchWoWActionPerformed(evt);
                    }
                });
                //btnLaunchWoW.setEnabled(false);
            }
            {
                btnExit = new JButton();
                this.add(btnExit, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                btnExit.setText("Exit Program");
                btnExit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnExitActionPerformed(evt);
                    }
                });
            }
            this.setPreferredSize(new java.awt.Dimension(645, 376));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public JButton getBtnPerfUpload() {
        return btnPerfUpload;
    }
    
    public JButton getBtnChkUploads() {
        return btnChkUploads;
    }
    
    public JButton getBtnSyncUAInterface() {
        return btnSyncUAInterface;
    }
    
    public JButton getBtnEraseSettings() {
        return btnEraseSettings;
    }
    
    public JButton getBtnLaunchWoW() {
        return btnLaunchWoW;
    }
    
    public JButton getBtnExit() {
        return btnExit;
    }
    
    private void btnPerfUploadActionPerformed(ActionEvent evt) {
        log.trace("pnlMainMenu: btnPerfUploadActionPerformed called");
        JButton btn = (JButton)evt.getSource();
        if(btn == this.btnPerfUpload)
        {
            frmMain.doUpload(btn);
        }
        
    }
    
    private void btnSyncUAInterfaceActionPerformed(ActionEvent evt) {
        log.trace("pnlMainMenu: btnSyncUAInterfaceActionPerformed called");
        JButton btn = (JButton)evt.getSource();
        if(btn == this.btnSyncUAInterface)
        {
            btn.setEnabled(false);
            frmMain.syncInterface(btn);
        }    
    }
        
        private void btnExitActionPerformed(ActionEvent evt) {
            System.exit(0);
        }
        
        private void btnLaunchWoWActionPerformed(ActionEvent evt) {
            log.trace("pnlMainMenu: btnLaunchWoWActionPerformed called");
            JButton btn = (JButton)evt.getSource();
            if(btn == this.btnLaunchWoW)
            {
                WDirectory wd = (WDirectory)frmMain.wowDirectories.getElementAt(0);
                wd.launch();
            }    
        }
        
        private void btnEraseSettingsActionPerformed(ActionEvent evt) {
            System.out
                .println("btnEraseSettings.actionPerformed, event=" + evt);
            //TODO add your code for btnEraseSettings.actionPerformed
        }

}
