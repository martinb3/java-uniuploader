package org.mbs.juniuploader.gui;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.mbs.juniuploader.objects.UAInterface;

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
public class pnlRemoteInterface extends javax.swing.JPanel {
    
    public static final long serialVersionUID = 1L;
    
    private JLabel lblIfUrl;
    private JCheckBox chkOverwriteAddons;
    private JCheckBox chkOverwriteSettings;
    private JCheckBox chkSyncAddons;
    private JCheckBox chkSyncSettings;
    private JTextField tfIfUrl;

    /**
    * Auto-generated main method to display this 
    * JPanel inside a new JFrame.
    */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new pnlRemoteInterface());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    public pnlRemoteInterface() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            GridBagLayout thisLayout = new GridBagLayout();
            this.setLayout(thisLayout);
            {
                lblIfUrl = new JLabel();
                this.add(lblIfUrl, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                lblIfUrl.setText("Interface File URL");
            }
            {
                tfIfUrl = new JTextField();
                this.add(tfIfUrl, new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                tfIfUrl.setText(UAInterface.getInterfaceLocation());
                tfIfUrl.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent evt) {
                        tfIfUrlKeyReleased(evt);
                    }
                });
            }
            {
                chkSyncSettings = new JCheckBox();
                this.add(chkSyncSettings, new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                chkSyncSettings.setText("Check remote settings for conflicts");
                chkSyncSettings.setSelected(UAInterface.isSyncSettings());
                chkSyncSettings.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent evt) {
                        chkSettingsItemStateChanged(evt);
                    }
                });
            }
            {
                chkSyncAddons = new JCheckBox();
                this.add(chkSyncAddons, new GridBagConstraints(1, 6, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                chkSyncAddons.setText("Synchronize my WoW Addons with all WoW Directories");
                chkSyncAddons.setSelected(UAInterface.isSyncAddons());
                chkSyncAddons.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent evt) {
                        chkSyncAddonsItemStateChanged(evt);
                    }
                });
            }
            {
                chkOverwriteSettings = new JCheckBox();
                this.add(chkOverwriteSettings, new GridBagConstraints(1, 5, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                chkOverwriteSettings.setText("Overwrite local settings if conflicts are found");
                chkOverwriteSettings.setSelected(UAInterface.isOverwriteSettings());
                chkOverwriteSettings.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent evt) {
                        chkOverwriteSettingsItemStateChanged(evt);
                    }
                });
            }
            {
                chkOverwriteAddons = new JCheckBox();
                this.add(chkOverwriteAddons, new GridBagConstraints(1, 7, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                chkOverwriteAddons.setText("Overwrite ALL WoW Addons Everywhere with Remote Ones");
                chkOverwriteAddons.setSelected(UAInterface.isOverwriteAddons());
                chkOverwriteAddons.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent evt) {
                        chkOverwriteAddonsItemStateChanged(evt);
                    }
                });
            }
            thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1, 0.1};
            thisLayout.rowHeights = new int[] {25, 33, 29, 30, 29, 30, 26, 26, 20, 20, 20};
            thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
            thisLayout.columnWidths = new int[] {7, 7, 7, 7};
            this.setPreferredSize(new java.awt.Dimension(640, 406));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public JLabel getLblIfUrl() {
        return lblIfUrl;
    }
    
    public JTextField getTfIfUrl() {
        return tfIfUrl;
    }
    
    public JCheckBox getChkSyncAddons() {
        return chkSyncAddons;
    }

    
    public JCheckBox getChkOverwriteSettings() {
        return chkOverwriteSettings;
    }

    
    private void tfIfUrlKeyReleased(KeyEvent evt) {
        if(evt.getSource() == tfIfUrl)
            UAInterface.setInterfaceLocation(tfIfUrl.getText());
    }
    
    private void chkSettingsItemStateChanged(ItemEvent evt) {
        if(evt.getSource() == this.chkSyncSettings)
        {
            UAInterface.setSyncSettings(this.chkSyncSettings.isSelected());
        }
            
    }
    
    private void chkOverwriteSettingsItemStateChanged(ItemEvent evt) {
        if(evt.getSource() == this.chkOverwriteSettings)
        {
            UAInterface.setOverwriteSettings(this.chkOverwriteSettings.isSelected());
        }
    }
    
    private void chkSyncAddonsItemStateChanged(ItemEvent evt) {
        if(evt.getSource() == this.chkSyncAddons)
        {
            UAInterface.setSyncAddons(this.chkSyncAddons.isSelected());
        }
        
    }
    
    private void chkOverwriteAddonsItemStateChanged(ItemEvent evt) {
        if(evt.getSource() == this.chkOverwriteAddons)
        {
            UAInterface.setOverwriteAddons(this.chkOverwriteAddons.isSelected());
        }
    }

}
