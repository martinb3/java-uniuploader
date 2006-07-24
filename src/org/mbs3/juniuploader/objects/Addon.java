/*
 * Created on Feb 4, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs3.juniuploader.objects;


/**
 * Encapsulates all the data from a single addon. 
 *    
 * @see         AddonFile
 * @see         UAInterface
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Vector;
import org.mbs3.juniuploader.objects.localobjects.WDirectory;
import org.mbs3.juniuploader.util.CheckSummer;
import org.mbs3.juniuploader.util.Util;

public class Addon
{
    private static Log log = LogFactory.getLog(Addon.class);
    
    protected String name;
    protected Vector addonFiles;
    protected String URL;
    
    public Addon ()
    {
        super();
    }
    
    public Addon(String name, Vector addonFiles)
    {
        super();
        log.debug("Created addon object " + name + " with " + addonFiles.size() + " files");
        this.name = name;
        this.addonFiles = addonFiles;
    }
    
    public boolean compareToFS(WDirectory wd)
    // returns true if all checksums valid, false otherwise
    {
        log.debug("Comparing " + this.name + " with the file system");
        boolean t = false;
        for(int i = 0; i < addonFiles.size(); i++)
        {
            // take each file, examine the checksum
            AddonFile af = (AddonFile)addonFiles.get(i);
                String fullPath = wd.getFileObject().getAbsolutePath() + File.separator + af.getName();
            t = CheckSummer.compareFileToChecksum(fullPath, af.getMd5sum());
            if(t) 
            {
                log.trace("Compared " + fullPath + " with checksum, verified");
            }
            else
            {
                log.trace("Compared " + fullPath + " with checksum, invalid");
                log.info(this.name + " failed to be in sync with the file system");
                return false;
            }
        }
        log.info(this.name + " is in sync with the file system");
        return true;
    }
    
    public void SyncWithFileSystem(WDirectory wd)
    // the caller MUST set the proper URL first
    {
        // This makes sure interface and addon folders exist
        File wdir = wd.getFileObject();
        File iffile = new File(wdir.getAbsoluteFile() + File.separator + "Interface");
        if((!iffile.exists()) || (!iffile.isDirectory()))
        {
            log.warn("Interface folder didn't exist, creating it.");
            iffile.mkdir();
        }
        File adfile = new File(iffile.getAbsoluteFile() + File.separator + "Addons");
        if((!adfile.exists()) || (!adfile.isDirectory()))
        {
            adfile.mkdir();
            log.warn("Interface folder didn't exist, creating it.");
        }

        org.mbs3.juniuploader.StatusThread.addMessage("Synchronizing " + this.name + " with filesystem");
        log.info("Downloading " + this.name + " into a temporary file");
        File tmpFile = Util.getRemoteFile(UAInterface.getRemoteAddonURL(this.name));
        ZipFile z = null;
        try {
            log.trace("Opening the temporary zip file that was downloaded");
            z = new ZipFile(tmpFile, ZipFile.OPEN_READ);
        } 
        catch (Exception e)
        {
            log.error("Failed somewhere while trying to read downloaded zipfile", e);
        } finally {
            if(z == null)
                return;
        }
        
        log.debug("Forcibly syncing all files for " + this.name);
        for(int i = 0; i < addonFiles.size(); i++)
        {
            AddonFile aof = (AddonFile)addonFiles.get(i);
            String fname = aof.getName().substring(1);

            String fullPath = wd.getFileObject().getAbsolutePath() + File.separator + fname;
            if(aof != null || !CheckSummer.compareFileToChecksum(fullPath, aof.getMd5sum())) 
            {
                log.trace("Attemping to overwrite the file " + aof.getName());
                
                try {
                    ZipEntry getEntry = z.getEntry(fname);
                    if(z == null)
                    {
                        log.error("Couldn't find the entry " + fname + " in the zip file");
                        continue;
                    }


                    BufferedInputStream bfis = new BufferedInputStream(z.getInputStream(getEntry));
                    /* if(File.separator != "/")
                        fullPath = fullPath.replaceAll("///", File.separator);
                    else if(File.separator != "\\")
                        fullPath = fullPath.replaceAll("/\\/", File.separator); */
                    
                    File fosFile = new File(fullPath);
                    
                    Addon.createPath(fosFile);
                    
                    FileOutputStream fos = new FileOutputStream(fosFile);
                    BufferedOutputStream bfos = new BufferedOutputStream(fos);
                    
                    log.trace("Writing from zip file to local file");
                    int b;
                    while((b = bfis.read()) != -1 )
                    {
                        bfos.write(b);
                    }
                    log.trace("Writing operation complete, closing streams");
                    
                    
                    // close output streams
                    bfos.close(); 
                    fos.close();
                    
                    // close input stream
                    bfis.close();
                    
                    
                } catch (Exception ex)
                {
                    log.error("Exception when manipulating " + fullPath, ex);
                }
            } else {
                log.trace(aof.getName() + " was up to date versus it's md5 checksum");
            }
            
        }
        
        try {
            log.trace("Trying to close temporary downloaded file");
            z.close();
            tmpFile.delete();
        } catch (Exception ex) {
            log.error("Exception while closing zip file" + z.getName(), ex);
        }
        log.info("Completed sync operation for " + this.name);
    }
        
    /**
     * @return Returns the value of name.
     */
    public String getName ()
    {
        return this.name;
    }

    
    /**
     * Sets the name of the addon.
     * @param name Name of the addon
     */
    public void setName (String name)
    {
        this.name = name;
    }

    
    /**
     * @return Returns the value of uRL.
     */
    public String getURL ()
    {
        return this.URL;
    }

    
    /**
     * Sets the URL of the addon's zip file.
     * @param url The URL of the addon's zip file
     */
    public void setURL (String url)
    {
        this.URL = url;
    }
    
    private static void createPath (File f)
    {
        log.trace("createPath called for " + f.getAbsolutePath());
        if(f.exists())
        {
            log.trace(f.getAbsolutePath() + " already exists, stopping");
            return;
        }
        
        File ptr = f.getParentFile();
        log.trace("Calling createPath on parent " + ptr.getAbsolutePath());
        Addon.createPath(ptr);
        log.trace("Well f.isDir= " + (f.getName().indexOf(".") == -1) + " and !f.exists=" + !f.exists() + " for " + f.getAbsolutePath());
        if(!f.exists() && f.getName().indexOf(".") == -1)
        {
            log.trace("Creating " + f.getAbsolutePath());
            f.mkdir();
        }
        

    }
}
