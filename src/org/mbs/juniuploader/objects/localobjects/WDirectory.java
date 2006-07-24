/*
 * Created on Feb 11, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs.juniuploader.objects.localobjects;

import java.io.File;
import java.util.prefs.Preferences;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mbs.juniuploader.jUniUploader;
import org.mbs.juniuploader.util.Prefs;
import org.mbs.juniuploader.util.Util;

/**
 * This class represents a single WoW directory and creates/stores information
 * about all accounts in the directory. It can also scan for new account directories.
 *    
 * @see         jUniUploader
 */
public class WDirectory implements Comparable
{
    private static Log log = LogFactory.getLog(WDirectory.class);
    
    protected Vector accounts;
    protected File wowDirectory;
    
    public WDirectory (File wowDirectory)
    {
        super();
        log.trace("creating a new WDirectory item");
        if(wowDirectory == null || !wowDirectory.isDirectory())
            log.warn("WDirectory constructor called with an invalid directory");
        
        this.accounts = new Vector();
        this.wowDirectory = wowDirectory;
        this.rescanAccounts();
    }
    
    public boolean isValid()
    {
        if(this.accounts == null)
            return false;
        else if(this.wowDirectory == null)
            return false;
        else if(!this.wowDirectory.exists())
            return false;
        else if(!this.wowDirectory.isDirectory())
            return false;
        return true;
    }
    
    protected void rescanAccounts()
    {
        log.trace("Rescanning accounts");
        if(!this.isValid())
        {
            log.error("WoW Directory was somehow invalid, cannot scan for accounts");
            return;
        }
        
        // empty the accounts list, we're redetecting
        if(accounts.size() != 0)
            accounts.removeAllElements();

        // detect accounts
        String accountdir = wowDirectory.getAbsolutePath() + File.separator + "WTF" + File.separator + "Account" + File.separator;
        File rootdir = new File(accountdir);
        
        if(rootdir == null || !rootdir.exists() || !rootdir.isDirectory())
        {
            log.warn("rescanAccounts() thinks Accounts subdirectory was invalid, cannot scan accounts");
            return;
        }
        
        // loop through each account in the directory
        File[] accountsSearch = rootdir.listFiles();
        for(int i = 0; i < accountsSearch.length; i++) {
            if(accountsSearch[i].exists() && accountsSearch[i].isDirectory()) 
            {
                WAccount wa = new WAccount(accountsSearch[i], this);
                if(wa.isValid())
                {
                    accounts.addElement(wa);
                    log.trace("WDirectory: rescanAccounts() detected account " + accountsSearch[i].getAbsolutePath());
                }
                else {
                    log.trace("WDirectory: rescanAccounts() skipped account " + accountsSearch[i].getAbsolutePath() + ", invalid");
                }
                
            }
        }
    }
    
    public final File getFileObject()
    {
        if(this.isValid())
        {
            return this.wowDirectory;
        }
        log.warn("getFileObject() had to return a null because !isValid()");
        return null;
    }
    
    public Vector getAccounts()
    {
        if(this.isValid())
        {
            return this.accounts;
        }
        log.warn("getAccounts() had to return a null because !isValid()");
        return null;
    }
    
    public String toString()
    {
        return this.wowDirectory.getAbsolutePath();
    }

    public int compareTo(Object obj)
    {
        WDirectory wd = (WDirectory)obj;
        
        if(wd.getFileObject() == wowDirectory)
            return 0;
        return -1;
    }
    
    public static Vector retrievePreferences()
    {
        // we need to fill wowDirectories with data from prefs if there is any
        Vector wowDirectories = new Vector();
        Preferences prefsWoWDirs = Prefs.getPrefs().node("wowdirectories");
        try {
        log.trace("rescanDirectoriesFromPrefs() Preferences found " + prefsWoWDirs.keys().length + " wow dirs stored");
        //populate from prefs, don't guess
        String [] keys = prefsWoWDirs.keys();
        for(int i = 0; i < keys.length ; i++)
        {
            log.trace("rescanDirectoriesFromPrefs() Restoring preferences key " + keys[i]);
            File test = new File(keys[i]);
            if(test != null && test.exists() && test.isDirectory())
            {
                log.trace("LocalSystem() thinks " + test.getAbsolutePath() + " is a candidate for your WoW directory");
                WDirectory wd = new WDirectory(test);
                if(wd.isValid())
                {
                    if(wd.isValid())
                    {
                        log.trace("addWDirectory(File) added " + wd);
                        wowDirectories.addElement(wd);
                    }

                }
                else
                {
                    log.warn("LocalSystem() thinks " + test.getName() + " had some inconsistency, skipping it");
                }
            }

        }
        } catch (Exception ex) {}
           return wowDirectories;
    }
    
    public static void storePreferences(Vector wowDirectories)
    {
        Preferences prefsWoWDirs = Prefs.getPrefs().node("wowdirectories");
        try {
            prefsWoWDirs.removeNode();
            prefsWoWDirs = Prefs.getPrefs().node("wowdirectories");
        log.trace("storePreferences() storing " + wowDirectories.size() + " directories");
        for(int i = 0; i < wowDirectories.size(); i++)
        {
            WDirectory wd = (WDirectory)wowDirectories.get(i);
            log.trace("storePreferences() saving preferences key " + wd.getFileObject().getAbsolutePath());
            if(wd.isValid())
            {
                prefsWoWDirs.put(wd.toString(), wd.toString());
            }

        }
        } catch (Exception ex)
        {
            log.error("storePreferences() failture while storing preferences", ex);
        }

    }

    public static Vector rescanDirectories()
    {
        org.mbs.juniuploader.StatusThread.addMessage("Scanning for WoW Directories");
        log.trace("rescanDirectories() called");
        String [] initialDirectorySuggestions = {
            "C:/Program Files/World of Warcraft",
            "/Applications/World of Warcraft",
            "D:/Program Files/World of Warcraft",
        };
        
        Vector v = new Vector();
        String osUpper = System.getProperty("os.name").toUpperCase();
        if(osUpper.startsWith("WINDOWS"))
        {
            log.info("rescanDirectories() thinks you're running Windows");
            
        }
        else if(osUpper.startsWith("MAC OS X"))
        {
            log.info("rescanDirectories() thinks you're running OS X");
        }
        else
        {
            log.warn("rescanDirectories() has no idea what OS you're running");
        }
        
        int added = 0;
        for(int i = 0; i < initialDirectorySuggestions.length; i++)
        {
            File test = new File(initialDirectorySuggestions[i]);
            if(test != null && test.exists() && test.isDirectory())
            {
                log.trace("rescanDirectories() thinks " + test.getAbsolutePath() + " is a candidate for your WoW directory");
                WDirectory wd = new WDirectory(test);
                if(wd.isValid())
                {
                    v.addElement(wd);
                    added++;
                }
                else
                {
                    log.warn("rescanDirectories() thinks " + test.getName() + " had some inconsistency, skipping it");
                }
            }
        }
        
        log.info("rescanDirectories() added " + added + " possible WoW Directories");
        return v;
    }
    
    public void launch()
    {
        Runtime rt = Runtime.getRuntime();
        try {
            if(Util.isMac())
                rt.exec(this.wowDirectory.getAbsolutePath() + File.separator + "World of Warcraft.app");
            else
                rt.exec(this.wowDirectory.getAbsolutePath() + File.separator + "WoW.exe");
        } catch (Exception ex)
        {
            log.error("Failed to launch WoW");
            log.error(ex);
        }
    }
}
