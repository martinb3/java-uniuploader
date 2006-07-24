/*
 * Created on Feb 5, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs3.juniuploader.objects;

/**
 * @author Martin Smith
 *
 * TODO None yet.
 */


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class encapsulates all you want to know about a single file
 * from an addon. It holds the md5 digest and the name fo the file.
 *    
 * @see Addon
 * @see UAInterface 
 */

public class AddonFile
{
    private static Log log = LogFactory.getLog(AddonFile.class);

    protected String name;
    protected String md5sum;
    
    public AddonFile (String name, String md5sum)
    {
        super();
        this.name = name;
        this.md5sum = md5sum;
        log.trace("New " + name + " AddonFile initialized");
    }
    
    /**
     * @return Returns the value of md5sum.
     */
    public String getMd5sum ()
    {
        return this.md5sum;
    }

    
    /**
     * Sets md5sum to md5sum.
     * @param md5sum the expected md5 sum of the file
     */
    public void setMd5sum (String md5sum)
    {
        this.md5sum = md5sum;
    }

    
    /**
     * @return Returns the value of name.
     */
    public String getName ()
    {
        return this.name;
    }

    
    /**
     * Sets name to name.
     * @param name Filename of the addon's file
     */
    public void setName (String name)
    {
        this.name = name;
    }

}
