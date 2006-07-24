/*
 * Created on Feb 12, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs.juniuploader.objects.remoteobjects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class represents a single field name / field value pair
 * that is part of a group of pairs that is then assigned to an upload rule. 
 *    
 * @see         FormPairGroup
 * @see         UploadRule
 */
public class FormPair
{
    private static Log log = LogFactory.getLog(FormPair.class);

    protected String name;
    protected String value;
    /**
     * 
     */
    public FormPair (String name, String value)
    {
        super();
        log.trace("FormPair constructor for name=" + name);
        
        this.name = name;
        this.value = value;
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
     * @param name Name of the key
     */
    public void setName (String name)
    {
        this.name = name;
    }
    
    /**
     * @return Returns the value of value.
     */
    public String getValue ()
    {
        return this.value;
    }
    
    /**
     * Sets value to value.
     * @param value Name of the key's value
     */
    public void setValue (String value)
    {
        this.value = value;
    }

    public String toString()
    {
        return new String(this.name + "=" + this.value);
    }
}
