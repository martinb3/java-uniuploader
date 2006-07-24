package org.mbs.juniuploader.gui;


import java.awt.FileDialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.border.BevelBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mbs.juniuploader.jUniUploader;
import org.mbs.juniuploader.objects.localobjects.WDirectory;
import org.mbs.juniuploader.util.Util;
import org.mbs.juniuploader.gui.frmMain;

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
public class pnlWoWDirectories extends javax.swing.JPanel {
    
    public static final long serialVersionUID = 1L;
    
    private JList lstWoWDirs;
    private JButton btnAddDirectory;
    private JButton btnRemoveSelected;
    
    private static Log log = LogFactory.getLog(frmMain.class);

    public pnlWoWDirectories() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            GridBagLayout thisLayout = new GridBagLayout();
            this.setLayout(thisLayout);
            {
                /*dlmWoWDirs = new DefaultListModel();
                Vector dirs = frmMain.getUploader().getLocalSystem().getWowDirectories();
                Iterator i =  dirs.iterator();
                while(i.hasNext())
                    dlmWoWDirs.addElement(i.next());
                    */
                
                lstWoWDirs = new JList(); //dlmWoWDirs);
                lstWoWDirs.setModel(frmMain.wowDirectories);
                this.add(lstWoWDirs, new GridBagConstraints(1, 1, 1, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                lstWoWDirs.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
            }
            {
                btnAddDirectory = new JButton();
                this.add(btnAddDirectory, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                btnAddDirectory.setText("Add Directory to List");
                btnAddDirectory.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnAddDirectoryActionPerformed(evt);
                    }
                });
            }
            {
                btnRemoveSelected = new JButton();
                this.add(btnRemoveSelected, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                btnRemoveSelected.setText("Remove Selected Directory");
                btnRemoveSelected.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnRemoveSelectedActionPerformed(evt);
                    }
                });
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
    
    public JList getLstWoWDirs() {
        return lstWoWDirs;
    }
    
    public JButton getBtnAddDirectory() {
        return btnAddDirectory;
    }
    
    public JButton getBtnRemoveSelected() {
        return btnRemoveSelected;
    }
    
    private void btnRemoveSelectedActionPerformed(ActionEvent evt) {
        JButton b = (JButton)evt.getSource();
        if(b == this.btnRemoveSelected)
        {
            int sel = this.lstWoWDirs.getSelectedIndex();
            if(sel != -1)
            {
                frmMain.removeWDirectory((WDirectory)frmMain.wowDirectories.getElementAt(sel));
            }
                
        }
    }
    
    private void btnAddDirectoryActionPerformed(ActionEvent evt) {
        if(evt.getSource() == this.btnAddDirectory)
        {
            boolean failed = false;
            boolean mac = Util.isMac();
            if(mac)
            {
                java.awt.Frame f = jUniUploader.inst;
                FileDialog fd = new FileDialog(f, "Select a World of Warcraft Directory", FileDialog.LOAD);
                try {
                    fd.show();
                    
                    String fileName = fd.getFile();
                    String rootDir = fd.getDirectory();
                    String completePath = (rootDir == null ? "" : rootDir) + (fileName == null ? "" : fileName);
                    log.debug("Adding OS X style " + completePath);
                    if(completePath != null)
                    {
                        File file = new File(completePath);
                        if(file != null && file.exists() && file.isDirectory())
                        {
                            WDirectory wd = new WDirectory(file);
                            frmMain.wowDirectories.addElement(wd);
                        }
                        
                    }
                    
                } catch (Exception ex) {
                    failed = true;
                    log.warn("Failed trying to display a FileDialog, falling back to JFileChooser", ex);
                }
                
            }
            if(!mac || failed)
            {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = fc.showOpenDialog(this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    if(file != null && file.exists() && file.isDirectory())
                    {
                        WDirectory wd = new WDirectory(file);
                        frmMain.wowDirectories.addElement(wd);
                    }
                    
                } else {
                    log.trace("Open command cancelled by user.");
                }

            }
        }
    }

}
