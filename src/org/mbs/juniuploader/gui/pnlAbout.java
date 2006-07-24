package org.mbs.juniuploader.gui;


//import java.io.File;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mbs.juniuploader.util.Prefs;
//import org.mbs.juniuploader.util.Util;

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
public class pnlAbout extends javax.swing.JPanel {

    static final long serialVersionUID = 1L;
    private static JLabel lblImage1;
    
    private static Log log = LogFactory.getLog(Prefs.class);
    private static JLabel lblImage2;
    private JLabel jLabel1;
    private JLabel lblLogo1Desc;

    public pnlAbout() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            GridBagLayout thisLayout = new GridBagLayout();
            thisLayout.rowWeights = new double[] {0.0, 0.0, 0.1, 0.0, 0.0, 0.1, 0.0};
            thisLayout.rowHeights = new int[] {7, 25, 20, 8, 23, 20, 6};
            thisLayout.columnWeights = new double[] {0.0, 0.1, 0.1};
            thisLayout.columnWidths = new int[] {11, 7, 20};
            this.setLayout(thisLayout);
            this.setPreferredSize(new java.awt.Dimension(626, 368));
            {
                lblImage1 = new JLabel();
                File fImage = new File("logo1.gif");
                if(fImage != null & fImage.canRead())
                {
                    ImageIcon i = new ImageIcon(fImage.getAbsolutePath());
                    lblImage1.setIcon(i);
                }
                this.add(lblImage1, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
            }
            {
                lblLogo1Desc = new JLabel();
                this.add(getLblLogo1Desc(), new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                lblLogo1Desc.setText("Logo2:");
            }
            {
                jLabel1 = new JLabel();
                this.add(jLabel1, new GridBagConstraints(
                    1,
                    1,
                    1,
                    1,
                    0.0,
                    0.0,
                    GridBagConstraints.CENTER,
                    GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0),
                    0,
                    0));
                jLabel1.setText("Logo1:");
            }
            {
                lblImage2 = new JLabel();
                File fImage2 = new File("logo2.gif");
                if(fImage2 != null & fImage2.canRead())
                {
                    ImageIcon i = new ImageIcon(fImage2.getAbsolutePath());
                    lblImage2.setIcon(i);
                }

                this.add(lblImage2, new GridBagConstraints(1, 5, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
            }
        } catch (Exception ex) {
            log.error("initGui() exception", ex);
        }
    }
    
    public static void repaintLogo()
    {
        File fImage = new File("logo1.gif");
        if(fImage != null & fImage.canRead())
        {
            ImageIcon i = new ImageIcon(fImage.getAbsolutePath());
            lblImage1.setIcon(i);
        }
        
        File fImage2 = new File("logo2.gif");
        if(fImage2 != null & fImage2.canRead())
        {
            ImageIcon i = new ImageIcon(fImage2.getAbsolutePath());
            lblImage2.setIcon(i);
        }

        lblImage1.invalidate();
        lblImage2.invalidate();
    }
    
    public JLabel getLblLogo1Desc() {
        return lblLogo1Desc;
    }

}
