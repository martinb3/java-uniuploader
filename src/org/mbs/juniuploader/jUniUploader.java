package org.mbs.juniuploader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mbs.juniuploader.gui.frmMain;
import org.mbs.juniuploader.util.GUILog;
import org.mbs.juniuploader.util.Prefs;
import org.mbs.juniuploader.objects.UAInterface;

/**
 * This is the class that contains the driver program for the jUU Swing GUI.    
 */
public final class jUniUploader
{
    public final static String VERSION = "Beta 10";
    public static ShutdownThread shutdown;
    public static Thread shutdownThread;
    public static frmMain inst;
    
    /**
     * Default constructor, not callable. This is a driver class.
     */
    private jUniUploader()
    {
    }

    /**
     * This main method creates all threads and the Swing GUI for jUU; if 
     * you want to just use the library, do <b>not</b> call this method.
     * <p>
     * When this method is over, logging is setup, a status thread and a
     * shutdown thread have been created, the Swing GUI is created, and the
     * status thread is running.
     *
     * @param  args Command line parameters; they aren't checked yet.
     * @return      none
     * @see         frmMain
     */
    public static void main (String[] args)
    {
        // setup logging package
        System.getProperties().setProperty(org.apache.commons.logging.Log.class.getName(), GUILog.class.getName());
        
        // remove this for dist
        //String s = GUILog.class.getName() + "." + Prefs.class.getName();
        //System.err.println(s);
        //System.setProperty(s,  "trace");
        
        Log log = LogFactory.getLog(jUniUploader.class);
        log.info("jUniUploader " + jUniUploader.VERSION + " starting up.");

        // create a status thread for delivering events
        log.debug("main() creating a status thread");
        StatusThread status = new StatusThread();
        Thread statusThread = new Thread(status, "jUniUploader " + jUniUploader.VERSION + " message delivery");
        statusThread.setDaemon(true);
        
        // prep prefs
        log.debug("main() calling Prefs.init()");
        Prefs.init();
        UAInterface.init();
        
        inst = new frmMain();
        inst.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        
        status.setLabel(frmMain.lblStatus);        
        statusThread.start();
        StatusThread.addMessage("jUniUploader, version " + jUniUploader.VERSION + " starting up.");
        
        log.debug("main() creating a shutdown thread and hook");
        // setup a hook for shutting down the app
        shutdown = new ShutdownThread(inst);
        shutdownThread = new Thread(shutdown, "jUniUploader " + jUniUploader.VERSION + " shutdown hook");
        Runtime.getRuntime().addShutdownHook(shutdownThread);
        
        log.trace("main() setting main frame visible");
        inst.setVisible(true);
    }

}
