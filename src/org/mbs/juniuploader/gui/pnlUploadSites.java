package org.mbs.juniuploader.gui;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mbs.juniuploader.objects.remoteobjects.UploadSite;

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
public class pnlUploadSites extends javax.swing.JPanel {
    
    public static final long serialVersionUID = 1L;
    
    private JList lstUploadSites;
    private JButton btnAddSite;
    private JButton btnRemoveSite;
    private JScrollPane jScrollPane1;
    private static Log log = LogFactory.getLog(pnlUploadSites.class);

    /**
    * Auto-generated main method to display this 
    * JPanel inside a new JFrame.
    */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new pnlUploadSites());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    public pnlUploadSites() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            GridBagLayout thisLayout = new GridBagLayout();
            this.setLayout(thisLayout);
            {
                btnAddSite = new JButton();
                this.add(btnAddSite, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                btnAddSite.setText("Add Site to List");
                btnAddSite.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnAddSiteActionPerformed(evt);
                    }
                });
            }
            {
                btnRemoveSite = new JButton();
                this.add(btnRemoveSite, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                btnRemoveSite.setText("Remove Selected Site");
                btnRemoveSite.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnRemoveSiteActionPerformed(evt);
                    }
                });
            }
            {
                jScrollPane1 = new JScrollPane();
                this.add(jScrollPane1, new GridBagConstraints(1, 1, 1, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                jScrollPane1.setPreferredSize(new java.awt.Dimension(367, 362));
                {
                    lstUploadSites = new JList();
                    jScrollPane1.setViewportView(lstUploadSites);
                    lstUploadSites.setModel(frmMain.uploadLocations);
                    lstUploadSites.setBorder(BorderFactory
                        .createEtchedBorder(BevelBorder.LOWERED));
                }
            }
            thisLayout.rowWeights = new double[] {0.0, 0.1, 0.1, 0.0, 0.1, 0.0};
            thisLayout.rowHeights = new int[] {9, 20, 7, 8, 7, 9};
            thisLayout.columnWeights = new double[] {0.0, 0.1, 0.0, 0.0, 0.0};
            thisLayout.columnWidths = new int[] {11, 20, 9, 217, 11};
            this.setPreferredSize(new java.awt.Dimension(614, 382));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public JList getLstUploadSites() {
        return lstUploadSites;
    }
    
    public JButton getBtnAddSite() {
        return btnAddSite;
    }
    
    public JButton getBtnRemoveSite() {
        return btnRemoveSite;
    }
    
    private void btnRemoveSiteActionPerformed(ActionEvent evt) {
        JButton b = (JButton)evt.getSource();
        if(b == this.btnRemoveSite)
        {
            int sel = this.lstUploadSites.getSelectedIndex();
            if(sel != -1)
            {
                String url = ((UploadSite)this.lstUploadSites.getSelectedValue()).getUrl();
                frmMain.removeUploadLocation(url);
            }
                
        }
    }
    
    private void btnAddSiteActionPerformed(ActionEvent evt) {
        if(evt.getSource() == this.btnAddSite)
        {
            String returnVal = JOptionPane.showInputDialog(this,"What is the URL of this site:");

            if (returnVal != null && !returnVal.equals("")) {
                    frmMain.addUploadLocation(returnVal);
            } else {
                log.trace("New site addition cancelled by user.");
            }

        }
    }

}
