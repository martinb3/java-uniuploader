/*
 * Created on Feb 11, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs.juniuploader.objects.localobjects;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mbs.juniuploader.jUniUploader;

import java.util.Vector;

/**
 * This class represents a single account directory under World of Warcraft. It
 * remembers all LUA files for that account, and can rescan for new LUA files. 
 *    
 * @see         jUniUploader
 */
public class WAccount implements Comparable
{
    private static Log log = LogFactory.getLog(WAccount.class);

    protected Vector luaFiles;
    protected File accountDirectory;
    protected WDirectory wowDirectory;
    
    /**
     * 
     */
    
    public WAccount (File accountDirectory, WDirectory wd)
    {
        super();
        log.trace("Account created for " + accountDirectory.getAbsolutePath());
        
        luaFiles = new Vector();
        this.accountDirectory = accountDirectory;
        this.wowDirectory = wd;
        
        this.rescanLuaFiles();
    }
    
    public boolean isValid()
    {
        if(this.luaFiles == null)
            return false;
        else if(this.accountDirectory == null)
            return false;
        else if(!this.accountDirectory.exists())
            return false;
        else if(!this.accountDirectory.isDirectory())
            return false;
        return true;
    }

    protected void rescanLuaFiles()
    {
        log.debug("Attemping to rescan " + this.getShortName() + " lua files");
        if(!this.isValid())
        {
            log.trace("The account object is invalid or inconsistent");
            return;
        }
      
        if(luaFiles.size() > 0)
            luaFiles.removeAllElements();
        
        File luaDir = new File(accountDirectory.getAbsoluteFile() + File.separator + "SavedVariables");
        
        File [] luas = luaDir.listFiles();
        for(int i = 0; i < luas.length ; i++)
        {
            File ptr = luas[i];
            if(ptr != null && ptr.exists() && ptr.isFile() && ptr.getName().endsWith(new String("lua")))
            {
                LUAFile lf = new LUAFile(ptr, this);
                luaFiles.addElement(lf);
                log.trace("Added a file " + ptr.getName());
            }
            else
            {
                log.trace("Skipped a file " + ptr.getName());
            }
        }
        
    }
    
    /**
     * @return Returns the value of accountDirectory.
     */
    public File getAccountDirectory ()
    {
        return this.accountDirectory;
    }

    public String toString()
    {
        if(!this.isValid())
            return new String();
        return this.accountDirectory.getAbsolutePath();
    }
    
    public int compareTo(Object o)
    {
        if(!this.isValid())
            return -1;
        
        String objToString = o.toString();
        if(objToString.equals(this.toString()))
            return 0;
        return -1;
    }
    
    public String [] luaFiles()
    {
        log.trace("Enumerating lua files from an account");
        if(!this.isValid())
            return new String[0];
        
        int len = this.luaFiles.size();
        String [] n = new String[len];
        
        for(int i = 0; i < len; i++)
            n[i] = ((LUAFile)luaFiles.get(i)).getShortName();
        
        return n;
    }
    
    public void selectLuaFile(String shortName)
    {
        if(!this.isValid())
            return;
        
        for(int i = 0; i < this.luaFiles.size(); i++)
        {
            LUAFile lf = (LUAFile)this.luaFiles.get(i);
            
            if(shortName.equals(lf.getShortName()))
            {
                lf.setSelected(true);
                log.trace(shortName + " marked as selected");
            }
        }
    }
    
    public LUAFile getLuaFile(String shortName)
    {
        if(!this.isValid())
            return null;
        
        for(int i = 0; i < this.luaFiles.size(); i++)
        {
            LUAFile lf = (LUAFile)this.luaFiles.get(i);
            if(shortName.equals(lf.getShortName()))
                return lf;
        }
        log.warn("Returning a null LuaFile for " + this.getShortName() + "'s file " + shortName);
        return null;
    }
    
    public void deselectLuaFile(String shortName)
    {
        if(!this.isValid())
            return;
        
        for(int i = 0; i < this.luaFiles.size(); i++)
        {
            LUAFile lf = (LUAFile)this.luaFiles.get(i);
            if(shortName.equals(lf.getShortName()))
            {
                lf.setSelected(false);
                log.trace(shortName + " marked as unselected");
            }
        }
    }
    
    public String [] selectedLuaFiles()
    {
        if(!this.isValid())
            return new String[0];
        
        int count = 0;
        
        for(int i = 0; i < this.luaFiles.size(); i++)
        {
            LUAFile lf = (LUAFile)this.luaFiles.get(i);
            if(lf.isSelected())
                count++;
        }        
        
        String [] n = new String[count];
        
        
        int k = 0;
        for(int i = 0; i < this.luaFiles.size(); i++)
        {
            LUAFile lf = (LUAFile)this.luaFiles.get(i);
            if(lf.isSelected())
            {
                n[k] = lf.getShortName();
                k++;
            }
        }        
                 
        return n;        
    }
    
    public boolean isSelectedLuaFile(String shortName)
    {
        if(!this.isValid())
            return false;
        
        for(int i = 0; i < this.luaFiles.size(); i++)
        {
            LUAFile lf = (LUAFile)this.luaFiles.get(i);
            if(lf.isSelected())
                return true;
        }
        return false;
    }

    
    /**
     * @return Returns the value of luaFiles.
     */
    public Vector getLuaFiles ()
    {
        return this.luaFiles;
    }

    public Vector getSelectedLuaFiles ()
    {
        Vector ret = new Vector();
        
        for(int i = 0; i < this.luaFiles.size(); i++)
        {
            LUAFile lf = (LUAFile)this.luaFiles.get(i);
            if(lf.isSelected())
                ret.addElement(lf);
        }
        
        return ret;
    }
    
    public String getShortName()
    {
        return this.accountDirectory.getName();
    }
    
}
