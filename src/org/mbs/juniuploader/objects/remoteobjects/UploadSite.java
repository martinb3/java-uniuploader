/*
 * Created on Feb 12, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs.juniuploader.objects.remoteobjects;

import java.util.prefs.Preferences;
import java.util.Vector;
import org.mbs.juniuploader.util.Prefs;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * This class represents a single Roster upload site / URL.
 *
 * @see UploadRule
 */
public class UploadSite
{
    private static Log log = LogFactory.getLog(UploadSite.class);

    // the actual URL to upload to
    protected String url;
    
    public UploadSite (String url)
    {
        super();
        this.url = url;
    }
    
    /**
     * @return Returns the value of url.
     */
    public String getUrl ()
    {
        return this.url;
    }
    
    /**
     * Sets url to url.
     * @param url The URL to upload the files to
     */
    public void setUrl (String url)
    {
        this.url = url;
    }
    
    public int compareTo(Object obj)
    {
        UploadSite us = (UploadSite)obj;
        if(this.url.equals(us.getUrl()))
            return 0;
        return -1;
    }
    
    public String toString()
    {
        return this.url;
    }
    
    public boolean isValid()
    {
        if(url != null && !url.equals(""))
            return true;
        log.warn("isValid() was false");
        return false;
    }
    
    public static Vector retrievePreferences()
    {
        Vector uploadLocations = new Vector();
        Preferences prefs = Prefs.getPrefs();
        Preferences sites = prefs.node(prefs.absolutePath() + "/sites");

        try {
            String urls[] = sites.keys();
                
            for(int i = 0; i < urls.length; i++)
            {
                boolean found = false;
                for(int j = 0; j < uploadLocations.size(); j++)
                {
                    UploadSite ptr = (UploadSite)uploadLocations.get(j);
                    if(urls[i].equals(ptr.getUrl()))
                    {
                        found = true;
                    }
                }
                if(!found)
                {
                    UploadSite toAdd = new UploadSite(urls[i]);
                    uploadLocations.addElement(toAdd);
                }
            }

        } catch (Exception e) {
            log.error("Backing store failed when updating uploadLocations", e);
        }
        return uploadLocations;
    }
    
    public static void storePreferences(Vector uploadLocations)
    {
        log.trace("Storing upload site preferences");
        Preferences prefs = Prefs.getPrefs();
        Preferences sites = prefs.node(prefs.absolutePath() + "/sites");
        
        // store upload locations vector
        try {
            
            sites.removeNode();
            sites = prefs.node(prefs.absolutePath() + "/sites");
            for(int i = 0; i < uploadLocations.size(); i++)
            {
                UploadSite us = (UploadSite)uploadLocations.get(i);
                log.trace("Storing upload site " + us.getUrl());
                sites.put(us.getUrl(), us.getUrl());
            }
                
        } catch (Exception e) {
            log.error("Backing store failed when saving uploadLocations", e);
        }

    }
    
}
