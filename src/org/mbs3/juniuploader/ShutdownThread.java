/*
 * Created on Feb 22, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs3.juniuploader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mbs3.juniuploader.gui.frmMain;
import org.mbs3.juniuploader.util.GUILog;
import org.mbs3.juniuploader.util.Prefs;

/**
 * This is a class that is created during the main method of the 
 * jUniUploader class. Its primary function is to halt the status thread
 * and save preferences gracefully before the program ends.
 *    
 * @see         jUniUploader
 */

public class ShutdownThread implements Runnable
{
    private static Log log = LogFactory.getLog(ShutdownThread.class);
    frmMain frm;

    public static boolean noSave = false;
    /**
     * Constructor for the shutdown thread. 
     *
     * @param frm  The main form of type frmMain.
     * @see frmMain 
     *             
     */
    public ShutdownThread (frmMain frm)
    {
        super();
        this.frm = frm;
        
        if(frm == null)
        {
            log.fatal("Shutdown thread created with a null Frame");
        }
        else
            log.debug("Shutdown thread created");
    }
    
    public void run()
    {
        log.debug("Shutdown thread activated");
        // shutdown actions happen here

        log.trace("Halting delivery of logging to the GUI");
        GUILog.setJTextArea(null);
        
        //log.trace("Ending the status thread for the info bar in the GUI");
        //StatusThread.setRunning(false);
        if(noSave)
        {
            log.warn("Trying to exit without saving preferences");
            try {
                Prefs.getPrefs().clear();
            } catch (Exception ex) { log.error(ex); }
            log.info("jUniUploader " + jUniUploader.VERSION + " shutdown completed.");
            return;
            
        }
        try {
            if(frm != null)
            {
                log.trace("Storing main form preferences");
                frmMain.storePreferences();
                
                //log.trace("dispose()'ing main form");
                //this.frm.dispose();
                //frm.setVisible(false);
                //frm = null;
            } else {
                log.trace("Main form was already null, so not disposing it");
            }
        } catch (Exception ex)
        {
            log.debug("run() gracefully tried to dispose of the main form, but failed. This is okay.");
        }

        log.trace("Calling the preference handlers deinit()");
        Prefs.deinit();
        
        log.info("jUniUploader " + jUniUploader.VERSION + " shutdown completed.");
    }

}
