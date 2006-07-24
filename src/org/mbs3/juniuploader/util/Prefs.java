/*
 * Created on Feb 3, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs3.juniuploader.util;

/**
 * @author Martin Smith
 *
 * TODO Save logging data too next time
 */

import org.mbs3.juniuploader.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.prefs.Preferences;

import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This mostly static class handles injection and setting of user prefs
 * from the local operating system via the Java Preferences API.  
 * <P>
 * Right now, we're storing everything as strings, even 0's and 1's, simply
 * because HTTP delivers the values to the program as a string anyway. It stores lots
 * of key->value pairs right now.
 * 
 * 
 */
public class Prefs
{

    private static Log log = LogFactory.getLog(Prefs.class);
    private static String prefPrefix = new String(Prefs.class.getName());
    protected static String [] keys =
    {
            ".interfaceurl",
            ".useragent",
            ".syncsettings",
            ".overwritesettings",
            ".syncaddons",
            ".overwriteaddons",
            ".lookandfeelclassname",
            ".logo1",
            ".logo2",
    };
    
    protected static String [] defaults =
    {
        "",
        "UniUploader",
        "false",
        "false",
        "false",
        "false",
        UIManager.getSystemLookAndFeelClassName(),
        "",
        "",
    };
   
    public static void init()
    {
        log.debug("Initializing user preferences");
        // set any system properties permanently
        System.setProperty("apple.laf.useScreenMenuBar", "true"); 
        System.setProperty("apple.awt.fileDialogForDirectories", "true");
        
        
        Preferences prefs = Prefs.getPrefs();
        try {
            // keep this here, for old version
            if(prefs.nodeExists(prefs.absolutePath() + "/uniuploader"))
            {
                prefs.node(prefs.absolutePath() + "/uniuploader").removeNode();
            } 
            log.debug("Using path " + prefs.absolutePath());
        } catch (Exception e) {
            log.error("Possible backing store failure when initializing saved settings", e);
        } finally {
            log.trace("Preferences Initialization complete");
        }
        
        try {
            File pFile = new File("juniuploader.xml");
            log.trace("Attemping to check for XML File " + pFile.getAbsolutePath());
            
            if(pFile != null && pFile.canRead())
            {
                log.info("Discovered a preferences file, trying to import from it");
                FileInputStream fis = new FileInputStream(pFile);
                BufferedInputStream bis = new BufferedInputStream(fis);
                Preferences.importPreferences(bis);
                bis.close();
                fis.close();
                
                // the pref file will be destroyed
                pFile.delete();
                
            }
        } catch (Exception ex) 
        {
            log.error("Error reading preferences file from disk or deleting the file", ex);
        }

        for(int i = 0; i < keys.length; i++)
        {
            String currentKey = prefPrefix + keys[i];
            String currentValue = prefs.get(currentKey, "");
            String defaultValue = defaults[i];
            if(currentValue == null || currentValue.equals(""))
                currentValue = defaultValue;
        
            System.setProperty(currentKey, currentValue);
            
            log.trace("Retrieved and restored " + currentKey + "=" + currentValue);
        }
    }
    
    public static void deinit()
    {
        log.trace("deinit() started");
        Prefs.flush();
        log.trace("deinit() completed");
    }

    public static void flush()
    {
        log.trace("flush() started");
        Preferences prefs = Prefs.getPrefs();
        for(int i = 0; i < keys.length; i++)
        {
            String currentKey = prefPrefix + keys[i];
            String currentValue = System.getProperty(currentKey);
            String defaultValue = defaults[i];
            if(currentValue != null && !currentValue.equals(""))
                prefs.put(currentKey, currentValue);
            else
                prefs.put(currentKey, defaultValue);
            
            log.trace("Stored " + currentKey + "=" + currentValue);
        }
        log.trace("flush() completed");
    }
    
    public static void exportFile(File fOut)
    {
        if(fOut != null && fOut.canWrite())
        {
            org.mbs3.juniuploader.StatusThread.addMessage("Attempting to export settings to " + fOut.getName());
            log.debug("exportFile() thinks it can write to the correct file");
            try {
                
                Prefs.getPrefs().exportSubtree(new java.io.FileOutputStream(fOut));
                log.debug("exportFile() finished with " + fOut.getName());
                org.mbs3.juniuploader.StatusThread.addMessage("Export of settings to " + fOut.getName() + " completed");
            } catch (Exception ex)
            {
                log.warn("exportFile() thought everything was fine, but an exception occurred", ex);
                org.mbs3.juniuploader.StatusThread.addMessage("Export settings to " + fOut.getName() + " caused an exception");
            }
        }
        else
        {
            log.debug("exportFile() failed, the specified file was null/unwriteable");
            org.mbs3.juniuploader.StatusThread.addMessage("Export settings to " + fOut.getName() + " failed, file was null or unwritable");
        }
    }
    
    public static Preferences getPrefs()
    {
        return Preferences.userNodeForPackage(jUniUploader.class);
    }

    
    /**
     * @return Returns the value of prefPrefix.
     */
    public static String getPrefPrefix ()
    {
        return prefPrefix;
    }
}
