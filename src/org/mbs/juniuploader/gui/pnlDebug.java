package org.mbs.juniuploader.gui;
import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mbs.juniuploader.util.GUILog;

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
public class pnlDebug extends javax.swing.JPanel {
    
    public static final long serialVersionUID = 1L;
    
    private JScrollPane jScrollPane1;
    private JTextArea taDebug;
    private static Log log = LogFactory.getLog(pnlDebug.class);

    public pnlDebug() {
        super();
        initGUI();
        GUILog.setJTextArea(taDebug);
        GUILog.flush();
        log.trace("Turning on graphical reporting, hopefully you are reading this in the debug panel");
    }
    
    private void initGUI() {
        try {
            BorderLayout thisLayout = new BorderLayout();
            this.setLayout(thisLayout);
            {
                jScrollPane1 = new JScrollPane();
                this.add(getJScrollPane1x(), BorderLayout.CENTER);
                {
                    taDebug = new JTextArea();
                    jScrollPane1.setViewportView(getTaDebugx());
                    taDebug.setText("");
                    taDebug.setAutoscrolls(false);
                }
            }
            this.setPreferredSize(new java.awt.Dimension(650, 371));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public JScrollPane getJScrollPane1() {
        return jScrollPane1;
    }
    
    public JTextArea getTaDebug() {
        return taDebug;
    }
    
    public JScrollPane getJScrollPane1x() {
        return jScrollPane1;
    }
    
    public JTextArea getTaDebugx() {
        return taDebug;
    }

}
