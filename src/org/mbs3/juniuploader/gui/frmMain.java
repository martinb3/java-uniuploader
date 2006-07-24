package org.mbs3.juniuploader.gui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mbs3.juniuploader.StatusThread;
import org.mbs3.juniuploader.jUniUploader;
import org.mbs3.juniuploader.objects.UAInterface;
import org.mbs3.juniuploader.objects.localobjects.WDirectory;
import org.mbs3.juniuploader.objects.remoteobjects.FormPairGroup;
import org.mbs3.juniuploader.objects.remoteobjects.UploadRule;
import org.mbs3.juniuploader.objects.remoteobjects.UploadSite;
import org.mbs3.juniuploader.util.*;


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
public class frmMain extends javax.swing.JFrame {

    public static final long serialVersionUID = 1L;
    
    private JMenuItem helpMenuItem;
    private JMenu jMenu5;
    private JMenuItem deleteMenuItem;
    private JSeparator jSeparator1;
    private JMenuItem pasteMenuItem;
    private pnlFormVariables pnlFormVariables1;
    private pnlUploadSites pnlUploadSites1;
    private pnlRemoteInterface pnlRemoteInterface1;
    private pnlWoWDirectories pnlWoWDirectories1;
    private pnlMainMenu pnlMainMenu1;
    private pnlDebug pnlDebug1;
    private JTabbedPane jTabbedPane1;
    private JMenuItem copyMenuItem;
    private JMenuItem cutMenuItem;
    private JMenu jMenu4;
    private JMenuItem exitMenuItem;
    private JSeparator jSeparator2;
    private pnlUploadRules pnlUploadRules1;
    private JMenuItem closeFileMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem openFileMenuItem;
    private JMenuItem newFileMenuItem;
    private JMenu jMenu3;
    private JMenuBar jMenuBar1;
    
    public static Preferences prefs = Prefs.getPrefs();
    private static Log log = LogFactory.getLog(frmMain.class);
    private pnlAbout pnlAbout1;
    private pnlSettings pnlLoggingOptions1;
    public static JLabel lblStatus;
    public static DefaultComboBoxModel wowDirectories;
    public static DefaultComboBoxModel uploadLocations;
    public static DefaultComboBoxModel uploadRules;
    public static DefaultComboBoxModel formGroups;

    public static DefaultComboBoxModel formPairValues;

    public static DefaultComboBoxModel accounts;
    public static DefaultComboBoxModel luaFiles;
    
    public static FileMonitor fileMon;
    
    public frmMain() {
        super();
        log.debug("Main form constructor, configuring Look&Feel and initializing Uploader object");
        
        frmMain.retrievePreferences();
        try {
            UIManager.setLookAndFeel(System.getProperty(Prefs.class.getName() + ".lookandfeelclassname"));
          } catch (Exception ex) {
              log.error("Failed to set the look and feel to the saved preference, this may be fatal.", ex);
          }
          initGUI();
          
          fileMon = FileMonitor.getInstance();
          log.debug("GUI created, ready to perform any and all operations");
    }
    
    public static void retrievePreferences()
    {
        
        Vector wd = WDirectory.retrievePreferences(); 
        if(!(wd.size() > 0))
            wd = WDirectory.rescanDirectories();

        Vector us = UploadSite.retrievePreferences();

        Vector fpg = FormPairGroup.retrievePreferences();

        Vector ur = new Vector();
        UploadRule.retrievePreferences(ur, us, fpg, wd);

        frmMain.uploadLocations = new DefaultComboBoxModel(us);
        frmMain.uploadRules = new DefaultComboBoxModel(ur);
        
        frmMain.formGroups = new DefaultComboBoxModel(fpg);
        formGroups.addElement(new FormPairGroup("none"));

        uploadLocations = new DefaultComboBoxModel(us);
        wowDirectories = new DefaultComboBoxModel(wd);
        

    }
    
    public static void storePreferences()
    {
        // fixed after refactor
        WDirectory.storePreferences(Util.DCBMtoVector(wowDirectories));
        
        // fixed after refactor
        UploadSite.storePreferences(Util.DCBMtoVector(uploadLocations));
        
        // fixed after refactor
        FormPairGroup.storePreferences(Util.DCBMtoVector(formGroups));
        
        // fixed?
        UploadRule.storePreferences(Util.DCBMtoVector(uploadRules));

    }
    
    private void initGUI() {
        try {
            BorderLayout thisLayout = new BorderLayout();
            getContentPane().setLayout(thisLayout);
            this.setTitle("jUniUploader " + jUniUploader.VERSION);
            {
                lblStatus = new JLabel();
                getContentPane().add(lblStatus, BorderLayout.SOUTH);
                lblStatus.setText("Lots of status text will go here");
                lblStatus.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            }
            {
                jTabbedPane1 = new JTabbedPane();
                getContentPane().add(jTabbedPane1, BorderLayout.CENTER);
                jTabbedPane1.setPreferredSize(new java.awt.Dimension(665, 402));
                {
                    pnlMainMenu1 = new pnlMainMenu();
                    jTabbedPane1.addTab(
                        "Main Menu",
                        null,
                        getPnlMainMenu1(),
                        null);
                }
                {
                    pnlWoWDirectories1 = new pnlWoWDirectories();
                    jTabbedPane1.addTab(
                        "WoW Directories",
                        null,
                        getPnlWoWDirectories1(),
                        null);
                }
                {
                    pnlRemoteInterface1 = new pnlRemoteInterface();
                    jTabbedPane1.addTab(
                        "UniAdmin Interface",
                        null,
                        getPnlRemoteInterface1(),
                        null);
                }
                {
                    pnlUploadSites1 = new pnlUploadSites();
                    jTabbedPane1.addTab(
                        "Upload Sites",
                        null,
                        getPnlUploadSites1(),
                        null);
                }
                {
                    pnlFormVariables1 = new pnlFormVariables();
                    jTabbedPane1.addTab(
                        "Form Variables",
                        null,
                        getPnlFormVariables1(),
                        null);
                }
                {
                    pnlUploadRules1 = new pnlUploadRules();
                    jTabbedPane1.addTab(
                        "Upload Rules",
                        null,
                        getPnlUploadRules1(),
                        null);
                }
                {
                    pnlDebug1 = new pnlDebug();
                    jTabbedPane1.addTab("Debug Log", null, pnlDebug1, null);
                }
                {
                    pnlLoggingOptions1 = new pnlSettings();
                    jTabbedPane1.addTab(
                        "Application Settings",
                        null,
                        getPnlLoggingOptions1(),
                        null);
                }
                {
                    pnlAbout1 = new pnlAbout();
                    jTabbedPane1.addTab("About", null, getPnlAbout1(), null);
                }
            }
            this.setSize(673, 456);

            jMenuBar1 = new JMenuBar();
            setJMenuBar(jMenuBar1);
               
            jMenu3 = new JMenu();
            jMenuBar1.add(jMenu3);
            jMenu3.setText("File");

            newFileMenuItem = new JMenuItem();
            jMenu3.add(newFileMenuItem);
            newFileMenuItem.setText("New");

            openFileMenuItem = new JMenuItem();
            jMenu3.add(openFileMenuItem);
            openFileMenuItem.setText("Open");

            saveMenuItem = new JMenuItem();
            jMenu3.add(saveMenuItem);
            saveMenuItem.setText("Save");
        
            saveAsMenuItem = new JMenuItem();
            jMenu3.add(saveAsMenuItem);
            saveAsMenuItem.setText("Save As ...");

            closeFileMenuItem = new JMenuItem();
            jMenu3.add(closeFileMenuItem);
            closeFileMenuItem.setText("Close");

            jSeparator2 = new JSeparator();
            jMenu3.add(jSeparator2);

            exitMenuItem = new JMenuItem();
            jMenu3.add(exitMenuItem);
            exitMenuItem.setText("Exit");
            exitMenuItem.addActionListener(new ActionListener() {
            
                public void actionPerformed(ActionEvent evt) {
                    exitMenuItemActionPerformed(evt);
                }
                });

            jMenu4 = new JMenu();
            jMenuBar1.add(jMenu4);
            jMenu4.setText("Edit");
            jMenu4.setEnabled(false);

            cutMenuItem = new JMenuItem();
            jMenu4.add(cutMenuItem);
            cutMenuItem.setText("Cut");

            copyMenuItem = new JMenuItem();
            jMenu4.add(copyMenuItem);
            copyMenuItem.setText("Copy");

            pasteMenuItem = new JMenuItem();
            jMenu4.add(pasteMenuItem);
            pasteMenuItem.setText("Paste");

            jSeparator1 = new JSeparator();
            jMenu4.add(jSeparator1);
        
            deleteMenuItem = new JMenuItem();
            jMenu4.add(deleteMenuItem);
            deleteMenuItem.setText("Delete");

            jMenu5 = new JMenu();
            jMenuBar1.add(jMenu5);
            jMenu5.setText("Help");

            helpMenuItem = new JMenuItem();
            jMenu5.add(helpMenuItem);
            helpMenuItem.setText("Help");
        } catch (Exception ex) {
            log.error("Error", ex);
        }
    }
    
    public pnlDebug getPnlDebug1() {
        return pnlDebug1;
    }
    
    private void exitMenuItemActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    
    public pnlMainMenu getPnlMainMenu1() {
        return pnlMainMenu1;
    }
    
    public pnlWoWDirectories getPnlWoWDirectories1() {
        return pnlWoWDirectories1;
    }
    
    public pnlRemoteInterface getPnlRemoteInterface1() {
        return pnlRemoteInterface1;
    }
    
    public pnlUploadSites getPnlUploadSites1() {
        return pnlUploadSites1;
    }
    
    public pnlFormVariables getPnlFormVariables1() {
        return pnlFormVariables1;
    }
    
    public pnlUploadRules getPnlUploadRules1() {
        return pnlUploadRules1;
    }
    
    public pnlSettings getPnlLoggingOptions1() {
        return pnlLoggingOptions1;
    }
    
    public pnlAbout getPnlAbout1() {
        return pnlAbout1;
    }

    public static void removeWDirectory(WDirectory dir)
    {
        Vector toRemove = new Vector();
        
        for(int i = 0; i < wowDirectories.getSize(); i++)
        {
            WDirectory wd = (WDirectory)wowDirectories.getElementAt(i);
            if(dir.equals(wd))
            {
                log.trace("removeWDirectory() removed " + dir);
                toRemove.add(wd);
            }
        }
        for(int i = 0; i < toRemove.size(); i++)
            wowDirectories.removeElement(toRemove.get(i));
    }

    public static void doAllUploads(final JButton btn)
    {
        log.trace("RemoteSystem: doAllUpload() called");
        org.mbs3.juniuploader.StatusThread.addMessage("Spinning off upload threads for every rule");

        
        //final Vector urs = uploadRules;
        Runnable go = new Runnable() {
            public void run() {

                if(btn.isEnabled())
                    btn.setEnabled(false);
                
                for(int i = 0; i < uploadRules.getSize(); i++)
                {
                    final UploadRule ur = (UploadRule)uploadRules.getElementAt(i);
                    log.trace("doAllUpload() calling upload on " + ur.getUploadSite().getUrl());
            
                    if(ur.isValid())
                    {
                        org.mbs3.juniuploader.StatusThread.addMessage("Uploading selected file to " + ur.getUploadSite().getUrl());
                        Util.postFileUpload(ur);
                        org.mbs3.juniuploader.StatusThread.addMessage("Completed an upload to " + ur.getUploadSite().getUrl());
                    }
                }
                
                org.mbs3.juniuploader.StatusThread.addMessage("All uploads threads completed!");
                if(!btn.isEnabled())
                    btn.setEnabled(true);
            }
        };
        Thread t = new Thread(go);
        t.start();

        log.trace("doAllUpload() completed");
    }
    
    public UploadSite getUploadLocation(String url)
    // TODO: Make sure app is aware this can return null
    {
        log.trace("RemoteSystem: getUploadLocation(String) called for " + url);
        for(int i = 0; i < uploadLocations.getSize(); i++)
        {
            UploadSite ptr = (UploadSite)uploadLocations.getElementAt(i);
            log.trace("RemoteSystem: getUploadLocation(String) comparing " + ptr.getUrl());
            if(url.equals(ptr.getUrl()))
            {
                return ptr;
            }
                
        }
        log.trace("RemoteSystem: getUploadLocation(String) couldn't find " + url);
        return null;
    
    }
    public UploadSite [] getUploadLocations()
    {
        UploadSite ret[] = new UploadSite[uploadLocations.getSize()];
        for(int i = 0; i<uploadLocations.getSize(); i++)
            ret[i] = (UploadSite)uploadLocations.getElementAt(i);
        return ret;
    }
    
    public static void addUploadLocation(String url)
    {
        Preferences sites = prefs.node(prefs.absolutePath() + "/sites");
        for(int i = 0; i < uploadLocations.getSize(); i++)
        {
            UploadSite ptr = (UploadSite)uploadLocations.getElementAt(i);
            if(url.equals(ptr.getUrl()))
            {
                return;
            }
        }
        sites.put(url, url);
        UploadSite toAdd = new UploadSite(url);
        uploadLocations.addElement(toAdd);
    }
    
    public static void removeUploadLocation(String url)
    // TODO: Make this preferences aware
    {
        Preferences sites = prefs.node(prefs.absolutePath() + "/sites");
        for(int i = 0; i < uploadLocations.getSize(); i++)
        {
            UploadSite ptr = (UploadSite)uploadLocations.getElementAt(i);
            if(url.equals(ptr.getUrl()))
            {
                uploadLocations.removeElementAt(i); i--;
                sites.remove(url);
            }
        }
    }
    
    public static void syncInterface(final JButton btn)
    {
        log.debug("syncInterface() called");
        StatusThread.addMessage("Synchronizing with remote UniAdmin interface");
        
        btn.setEnabled(false);

        Runnable go = new Runnable() {
                public void run() {
                    
                    UAInterface.remotePrefstoLocal();
                    UAInterface.syncImages();
                    for(int i = 0; i < wowDirectories.getSize(); i++)
                    {
                        WDirectory currentWowDir = (WDirectory)wowDirectories.getElementAt(i);
                        UAInterface.compareAddonsLocalRemote(currentWowDir);
                    }
                    btn.setEnabled(true);
                }
            };
            Thread t = new Thread(go);
            t.start();
        
        log.debug("syncInterface() completed");
        StatusThread.addMessage("Synchronizing with remote UniAdmin interface completed");
    }
    
    public static void doUpload(JButton btn)
    {
        log.info("Begining to upload LUA files to requested locations");
        doAllUploads(btn);
        log.debug("Returned from doing all uploads");
    }
}
