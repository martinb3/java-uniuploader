/*
 * Created on Feb 24, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs3.juniuploader;

import java.util.Vector;

import javax.swing.JLabel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class handles status messages sent from the lower API to the
 * Swing GUI. If the lower API is activated by the jUniUploader main() method,
 * the lower API's status messages will be correctly set up to be delivered here,
 * otherwise the Apache Commons logging will log the status messages to
 * Commons logging.
 *    
 * @see         jUniUploader
 */
public class StatusThread implements Runnable
{
    private static Log log = LogFactory.getLog(StatusThread.class);
    private static Vector queue;
    private static JLabel jStatusBar;
    private static boolean running;
    
    public StatusThread ()
    {
        super();
        
        queue = new Vector();
        running = false;
        
        log.debug("Status thread created");
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run ()
    {
        running = true;
        if(StatusThread.jStatusBar == null)
        {
            log.warn("Status thread activated with no status bar object, exiting");
            running = false;
            return;
        }

        log.trace("Status thread transitioned to the running state");
        while(running) {
            try {
                if(!StatusThread.queue.isEmpty())
                {
                    StatusThread.writeStatus((String)queue.remove(0));
                }
                Thread.sleep(1000);
                Thread.yield();
            } catch (Exception ex) {
                log.error("Status thread died/interrupted, the status bar in the GUI will no longer update");
                running = false;
                return;
            }
            log.trace("Status thread while loop iteration end, waiting objects: " + StatusThread.queue.size());

            if(Thread.interrupted())
                running = false;
            
/*            System.err.println("active threads: "+Thread.activeCount());
            int s = Thread.activeCount();
            Thread [] t = new Thread[s];
            Thread.enumerate(t);
            for(int i = 0; i < t.length; i++)
            {
                Thread ptr = t[i];
                if(ptr != null && ptr.isAlive())
                    System.err.println(ptr.getName());
            }
*/            
        } 
        log.trace("Status thread exited");
    }
    
    private static synchronized void writeStatus(final String s)
    {
        if(!running)
            return;
        
        Runnable r = new Runnable() {
            public void run() {
                StatusThread.jStatusBar.setText(s);        
            }
        };
        javax.swing.SwingUtilities.invokeLater(r);
    }
    
    public static synchronized void addMessage(String m)
    {
        if(!running)
            return;
        
        log.trace("Queued message " + m);
        StatusThread.queue.add(m);
    }
    
    public static synchronized void setRunning(boolean b)
    {
        StatusThread.running = b;
    }
    
    public void setLabel(JLabel sb)
    {
        StatusThread.jStatusBar = sb;
    }

}
