/*
 * Created on Feb 11, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs3.juniuploader.objects.localobjects;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Instances of this class represent LUA files within a WoW folder and
 * within a specific account. This class can select and deselect files as
 * well as check to see if they are valid and exist. 
 *    
 * @see WAccount
 * @see WDirectory
 */
public class LUAFile implements Comparable
{
    private static Log log = LogFactory.getLog(LUAFile.class);

    protected boolean selected;
    protected File realLocation;
    protected WAccount realAccount;
    
    /**
     * 
     */

    public LUAFile (File realLocation, WAccount wa)
    {
        super();
        
        this.selected = false;
        this.realLocation = realLocation;
        this.realAccount = wa;
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
    
    public String toString()
    {
        if(this.isValid())
            return this.realLocation.getName();
        else
            return new String();
    }

    
    /**
     * @return Returns the value of realLocation.
     */
    public File getRealLocation ()
    {
        return this.realLocation;
    }

    
    /**
     * @return Returns the value of selected.
     */
    public boolean isSelected ()
    {
        return this.selected;
    }

    
    /**
     * Sets selected to selected.
     * @param selected True when the LUA File should be uploaded
     */
    public void setSelected (boolean selected)
    {
        log.debug("LUAFile: setSelected(boolean) called for " + this.getShortName());
        this.selected = selected;
    }
    
    public boolean isValid()
    {
        if(this.realLocation == null)
            return false;
        else if(!this.realLocation.exists())
            return false;
        else if(!this.realLocation.isFile())
            return false;
        return true;
    }
    
    public String getShortName()
    {
        if(!this.isValid())
            return new String("Invalid File");
        else
            return this.realLocation.getName();
    }
}
