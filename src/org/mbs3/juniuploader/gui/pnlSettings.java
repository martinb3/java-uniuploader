package org.mbs3.juniuploader.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Iterator;
import javax.swing.BorderFactory;

import javax.swing.DefaultButtonModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JFileChooser;
import javax.swing.LookAndFeel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.mbs.juniuploader.objects.RemoteSystem;
import org.mbs3.juniuploader.util.Prefs;
import org.mbs3.juniuploader.util.Util;

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
public class pnlSettings extends javax.swing.JPanel {
    
    public static final long serialVersionUID = 1L;
    private static Log log = LogFactory.getLog(pnlSettings.class);
    
    private JLabel lblGUI;
    private JLabel lblObj;
    private JRadioButton btnMainError;
    private JTextPane txtWarn;
    private JToggleButton btnExport;
    private JLabel lblUserAgent;
    private JTextField tfUserAgent;
    private JComboBox cmbLF;
    private JLabel lblDeLF;
    private JLabel lblLogLevel;
    private JRadioButton btnHTTPTrace;
    private JRadioButton btnHTTPDebug;
    private JRadioButton btnHTTPInfo;
    private JRadioButton btnHTTPWarn;
    private JRadioButton btnHTTPError;
    private JRadioButton btnHTTPFatal;
    private JRadioButton btnObjTrace;
    private JRadioButton btnObjDebug;
    private JRadioButton btnObjInfo;
    private JRadioButton btnObjWarn;
    private JRadioButton btnObjError;
    private JRadioButton btnObjFatal;
    private JRadioButton btnGUITrace;
    private JRadioButton btnGUIDebug;
    private JRadioButton btnGUIInfo;
    private JRadioButton btnGUIWarn;
    private JRadioButton btnGUIError;
    private JRadioButton btnGUIFatal;
    private ButtonGroup grpHTTP;
    private ButtonGroup grpObjects;
    private ButtonGroup grpGUI;
    private JRadioButton btnMainTrace;
    private JRadioButton btnMainDebug;
    private JRadioButton btnMainInfo;
    private JRadioButton btnMainWarn;
    private JRadioButton btnMainFatal;
    private ButtonGroup grpMain;
    private JLabel lblTrace;
    private JLabel lblDebug;
    private JLabel lblInfo;
    private JLabel lblWarn;
    private JLabel lblError;
    private JLabel lblFatal;
    private JLabel lblLObj;
    private JLabel lblMain;

    public pnlSettings() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            GridBagLayout thisLayout = new GridBagLayout();
            thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            thisLayout.rowHeights = new int[] {11, 20, 18, 22, 22, 23, 23, 20, 18, 23, 22, 24, 6, 26, 4, 30, 8};
            thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1, 0.1, 0.1, 0.0, 0.1, 0.0};
            thisLayout.columnWidths = new int[] {10, 106, 90, 20, 20, 7, 100, 20, 7};
            this.setLayout(thisLayout);
            this.setPreferredSize(new java.awt.Dimension(659, 349));
            {
                {
                    grpMain = new ButtonGroup();
                }
                {
                    grpGUI = new ButtonGroup();
                }
                {
                    grpObjects = new ButtonGroup();
                }
                {
                    grpHTTP = new ButtonGroup();
                }
                {
                }
                {
                }
                lblGUI = new JLabel();
                this.add(lblGUI, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                lblGUI.setText("Graphical Interface");
            }
            {
                lblMain = new JLabel();
                this.add(lblMain, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                lblMain.setText("Main Program");
            }
            {
                lblObj = new JLabel();
                this.add(lblObj, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                lblObj.setText("Object Messages");
            }
            {
                lblLObj = new JLabel();
                this.add(lblLObj, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                lblLObj.setText("HTTP Messages");
            }
            {
                lblFatal = new JLabel();
                this.add(lblFatal, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                lblFatal.setText("Fatal Messages");
            }
            {
                lblError = new JLabel();
                this.add(lblError, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                lblError.setText("Errors");
            }
            {
                lblWarn = new JLabel();
                this.add(lblWarn, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                lblWarn.setText("Warnings");
            }
            {
                lblInfo = new JLabel();
                this.add(lblInfo, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                lblInfo.setText("Info Messages");
            }
            {
                lblDebug = new JLabel();
                this.add(lblDebug, new GridBagConstraints(6, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                lblDebug.setText("Debug Messages");
            }
            {
                lblTrace = new JLabel();
                this.add(lblTrace, new GridBagConstraints(7, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                lblTrace.setText("Trace Execution");
            }
            {
                btnMainFatal = new JRadioButton();
                this.add(btnMainFatal, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
            }
            grpMain.add(btnMainFatal);
            btnMainFatal.setActionCommand("fatal");
            btnMainFatal.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    logLevelBtnAction(evt);
                }
            });
            {
                btnMainError = new JRadioButton();
                this.add(btnMainError, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpMain.add(btnMainError);
                btnMainError.setActionCommand("error");
                btnMainError.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnMainWarn = new JRadioButton();
                this.add(btnMainWarn, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpMain.add(btnMainWarn);
                btnMainWarn.setActionCommand("warn");
                btnMainWarn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnMainInfo = new JRadioButton();
                this.add(btnMainInfo, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpMain.add(btnMainInfo);
                btnMainInfo.setActionCommand("info");
                btnMainInfo.setSelected(true);
                btnMainInfo.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnMainDebug = new JRadioButton();
                this.add(btnMainDebug, new GridBagConstraints(6, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpMain.add(btnMainDebug);
                btnMainDebug.setActionCommand("debug");
                btnMainDebug.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnMainTrace = new JRadioButton();
                this.add(btnMainTrace, new GridBagConstraints(7, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpMain.add(btnMainTrace);
                btnMainTrace.setActionCommand("trace");
                btnMainTrace.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnGUIFatal = new JRadioButton();
                this.add(btnGUIFatal, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpGUI.add(btnGUIFatal);
                btnGUIFatal.setActionCommand("fatal");
                btnGUIFatal.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnGUIError = new JRadioButton();
                this.add(btnGUIError, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpGUI.add(btnGUIError);
                btnGUIError.setActionCommand("error");
                btnGUIError.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnGUIWarn = new JRadioButton();
                this.add(btnGUIWarn, new GridBagConstraints(4, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpGUI.add(btnGUIWarn);
                btnGUIWarn.setActionCommand("warn");
                btnGUIWarn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnGUIInfo = new JRadioButton();
                this.add(btnGUIInfo, new GridBagConstraints(5, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpGUI.add(btnGUIInfo);
                btnGUIInfo.setActionCommand("info");
                btnGUIInfo.setSelected(true);
                btnGUIInfo.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnGUIDebug = new JRadioButton();
                this.add(btnGUIDebug, new GridBagConstraints(6, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpGUI.add(btnGUIDebug);
                btnGUIDebug.setActionCommand("debug");
                btnGUIDebug.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnGUITrace = new JRadioButton();
                this.add(btnGUITrace, new GridBagConstraints(7, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpGUI.add(btnGUITrace);
                btnGUITrace.setActionCommand("trace");
                btnGUITrace.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnObjFatal = new JRadioButton();
                this.add(btnObjFatal, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpObjects.add(btnObjFatal);
                btnObjFatal.setActionCommand("fatal");
                btnObjFatal.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnObjError = new JRadioButton();
                this.add(btnObjError, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpObjects.add(btnObjError);
                btnObjError.setActionCommand("error");
                btnObjError.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnObjWarn = new JRadioButton();
                this.add(btnObjWarn, new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpObjects.add(btnObjWarn);
                btnObjWarn.setActionCommand("warn");
                btnObjWarn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnObjInfo = new JRadioButton();
                this.add(btnObjInfo, new GridBagConstraints(5, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpObjects.add(btnObjInfo);
                btnObjInfo.setActionCommand("info");
                btnObjInfo.setSelected(true);
                btnObjInfo.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnObjDebug = new JRadioButton();
                this.add(btnObjDebug, new GridBagConstraints(6, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpObjects.add(btnObjDebug);
                btnObjDebug.setActionCommand("debug");
                btnObjDebug.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnObjTrace = new JRadioButton();
                this.add(btnObjTrace, new GridBagConstraints(7, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpObjects.add(btnObjTrace);
                btnObjTrace.setActionCommand("trace");
                btnObjTrace.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnHTTPFatal = new JRadioButton();
                this.add(btnHTTPFatal, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpHTTP.add(btnHTTPFatal);
                btnHTTPFatal.setActionCommand("fatal");
                btnHTTPFatal.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnHTTPError = new JRadioButton();
                this.add(btnHTTPError, new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpHTTP.add(btnHTTPError);
                btnHTTPError.setActionCommand("error");
                btnHTTPError.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnHTTPWarn = new JRadioButton();
                this.add(btnHTTPWarn, new GridBagConstraints(4, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpHTTP.add(btnHTTPWarn);
                btnHTTPWarn.setActionCommand("warn");
                btnHTTPWarn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnHTTPInfo = new JRadioButton();
                this.add(btnHTTPInfo, new GridBagConstraints(5, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpHTTP.add(btnHTTPInfo);
                btnHTTPInfo.setActionCommand("info");
                btnHTTPInfo.setSelected(true);
                btnHTTPInfo.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnHTTPDebug = new JRadioButton();
                this.add(btnHTTPDebug, new GridBagConstraints(6, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpHTTP.add(btnHTTPDebug);
                btnHTTPDebug.setActionCommand("debug");
                btnHTTPDebug.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                btnHTTPTrace = new JRadioButton();
                this.add(btnHTTPTrace, new GridBagConstraints(7, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                grpHTTP.add(btnHTTPTrace);
                btnHTTPTrace.setActionCommand("trace");
                btnHTTPTrace.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        logLevelBtnAction(evt);
                    }
                });
            }
            {
                lblLogLevel = new JLabel();
                this.add(lblLogLevel, new GridBagConstraints(1, 1, 7, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                lblLogLevel
                    .setText("Set the level of logging for different parts of this application:");
            }
            {
                lblDeLF = new JLabel();
                this.add(lblDeLF, new GridBagConstraints(1, 8, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                lblDeLF
                    .setText("Set the look and feel for the GUI of this application:");
            }
            {
                LookAndFeelInfo [] lfs = UIManager.getInstalledLookAndFeels();
                LookAndFeel lf = UIManager.getLookAndFeel();
                DefaultComboBoxModel cmbLFModel = new DefaultComboBoxModel();
                for(int i = 0; i < lfs.length ; i++)
                {
                    String n = lfs[i].getName();
                    cmbLFModel.addElement(n);
                    if(lf.getName().equals(lfs[i].getName()))
                        cmbLFModel.setSelectedItem(n);
                }
                cmbLF = new JComboBox();
                this.add(cmbLF, new GridBagConstraints(1, 9, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                cmbLF.setModel(cmbLFModel);
                cmbLF.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent evt) {
                        cmbLFItemStateChanged(evt);
                    }
                });
            }
            {
                tfUserAgent = new JTextField(Util.getUserAgent());
                this.add(tfUserAgent, new GridBagConstraints(1, 11, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                tfUserAgent.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent evt) {
                        tfUserAgentKeyReleased(evt);
                    }
                });
            }
            {
                lblUserAgent = new JLabel();
                this.add(lblUserAgent, new GridBagConstraints(1, 10, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                lblUserAgent.setText("User Agent");
            }
            {
                btnExport = new JToggleButton();
                this.add(btnExport, new GridBagConstraints(1, 15, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                btnExport.setText("Export All Settings and Data to File");
                btnExport.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        btnExportActionPerformed(evt);
                    }
                });
            }
            {
                txtWarn = new JTextPane();
                this.add(txtWarn, new GridBagConstraints(4, 15, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                txtWarn.setText("Note: This includes rules, form variables, sites, and ANYTHING else this program can remember from run to run.");
                txtWarn.setEditable(false);
                txtWarn.setBackground(new java.awt.Color(255,255,255));
                txtWarn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void cmbLFItemStateChanged(ItemEvent evt) {
        int sel = cmbLF.getSelectedIndex(); 
        if(sel != -1)
        {
            // we have selected a new item
            LookAndFeelInfo [] lfs = UIManager.getInstalledLookAndFeels();
            try {
                String lfClass = lfs[sel].getClassName();
                UIManager.setLookAndFeel(lfClass);
                System.setProperty(Prefs.class.getName() + ".lookandfeelclassname", lfClass);
                SwingUtilities.updateComponentTreeUI(this.getParent());
            } catch (Exception ex)
            {
                log.error("Error setting a new Look and Feel", ex);
            }
        }
    }
    
    private void logLevelBtnAction(ActionEvent evt) {
        JRadioButton jbr = (JRadioButton)evt.getSource();
        DefaultButtonModel bm = (DefaultButtonModel)jbr.getModel();
        ButtonGroup bg = bm.getGroup();

        String pkg = "org.mbs.juniuploader";
        String logClass = pkg + ".util.GUILog";
        
        java.util.Vector toMark = new java.util.Vector();
        String level = jbr.getActionCommand();
        
        log.info("Changing logging level to " + level + " for selected components");
        if(bg == grpMain)
        {
            log.debug("grpMain setting up level " + level);
            String prefix = pkg + "";
            toMark.add(new String(prefix + ".ClassRunner")); 
            toMark.add(new String(prefix + ".ShutdownThread"));
            toMark.add(new String(prefix + ".StatusThread"));
            toMark.add(new String(prefix + ".Uploader"));
            
            toMark.add(new String(prefix + ".util.Prefs"));
            toMark.add(new String(prefix + ".util.Util"));

        }
        else if(bg == grpGUI)
        {
            log.debug("grpGUI setting up level " + level);
            String prefix = pkg + ".gui";
            toMark.add(new String(prefix + ".frmMain"));
            toMark.add(new String(prefix + ".pnlDebug"));
            toMark.add(new String(prefix + ".pnlFormVariables"));
            toMark.add(new String(prefix + ".pnlMainMenu"));
            toMark.add(new String(prefix + ".pnlRemoteInterface"));
            toMark.add(new String(prefix + ".pnlSettings"));
            toMark.add(new String(prefix + ".pnlUploadRules"));
            toMark.add(new String(prefix + ".pnlUploadSites"));
            toMark.add(new String(prefix + ".pnlWoWDirectories"));

        }
        else if(bg == grpObjects)
        {
            log.debug("grpObjects setting up level " + level);
            String prefix = pkg + ".objects";
            toMark.add(new String(prefix + ".Addon"));
            toMark.add(new String(prefix + ".AddonFile"));
            toMark.add(new String(prefix + ".LocalSystem"));
            toMark.add(new String(prefix + ".RemoteSystem"));
            
            prefix = pkg + ".objects.localobjects";
            toMark.add(new String(prefix + ".LUAFile"));
            toMark.add(new String(prefix + ".WAccount"));
            toMark.add(new String(prefix + ".WDirectory"));
            
            prefix = pkg + ".objects.remoteobjects";
            toMark.add(new String(prefix + ".FormPair"));
            toMark.add(new String(prefix + ".FormPairGroup"));
            toMark.add(new String(prefix + ".UploadRule"));
            toMark.add(new String(prefix + ".UploadSite"));
            
            prefix = pkg + ".objects.util";
            toMark.add(new String(prefix + ".CheckSummer"));
            toMark.add(new String(prefix + ".MyVector"));
            toMark.add(new String(prefix + ".UUIDGen"));
        }
        else if(bg == grpHTTP)
        {
            log.debug("grpHTTP setting up level " + level);
            toMark.add(new String("httpclient.wire.header"));
            toMark.add(new String("org.apache.commons.httpclient"));
        }
        else
        {
            log.info("Unknown button group!!");
        }
        
        // now that we know what properties to set, let's set them
        Iterator i = toMark.iterator();
        while(i.hasNext())
        {
            String name = (String)i.next();
            System.setProperty(logClass + "." + name, level);
            log.trace("Configuring " + logClass + "." + name + " to " + level);
        }
    }
    
    private void tfUserAgentKeyReleased(KeyEvent evt) {
        if(evt.getSource() == tfUserAgent)
            Util.setUserAgent(tfUserAgent.getText());
    }
    
    private void btnExportActionPerformed(ActionEvent evt) {
        final pnlSettings thisFrame = this;
        if(evt.getSource() == this.btnExport)
        {
                    JFileChooser fc = new JFileChooser();
                    //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    int returnVal = fc.showSaveDialog(thisFrame);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        final File file = fc.getSelectedFile();
                
                        try {
                            if(file != null && file.createNewFile())
                            {
                                log.debug("Writing all possible options to a file");
                                frmMain.storePreferences();
                                Prefs.flush();
                                Prefs.exportFile(file);
                            } else {
                                log.info("File chosen already exists, will not overwrite it");
                            }
                        } catch (Exception ex)
                        {
                            log.error("Failed while trying to write to file", ex);
                        }
                
                    } else {
                        log.trace("Open command cancelled by user.");
                    }
                
            
        }

    }
    
    
}
