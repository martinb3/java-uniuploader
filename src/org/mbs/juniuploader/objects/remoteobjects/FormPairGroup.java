/*
 * Created on Feb 13, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs.juniuploader.objects.remoteobjects;

import java.util.prefs.Preferences;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Vector;
import org.mbs.juniuploader.util.Prefs;

/**
 * This class represents a group of form pairs to be submitted as part
 * of an upload rule. 
 *    
 * @see         FormPair
 * @see         UploadRule
 */
public class FormPairGroup implements Comparable
{
    private static Log log = LogFactory.getLog(FormPairGroup.class);
    private Preferences prefs = Prefs.getPrefs();
    
    Vector pairs;
    String name;
    /**
     * 
     */
    /*public FormPairGroup (String name, Vector pairs)
    {
        super();
        this.pairs = pairs;
        this.name = name;
    }*/

    public FormPairGroup (String name)
    {
        super();
        log.trace("Form pairs grouping constructor for " + name);
        this.pairs = new Vector();
        this.name = name;
    }
    
    public void retrieveGroupPreference()
    {
        Preferences fpgs = Prefs.getPrefs().node(this.prefs.absolutePath() + "/formpairgroups");
        Preferences child = fpgs.node(fpgs.absolutePath() + "/" + this.name);
        
        try {
            String [] keys = child.keys();
            for(int i = 0; i < keys.length; i++)
            {
                String key = keys[i];
                String value = child.get(key, "error");
                
                if(!value.equals("error"))
                {
                    FormPair gp = new FormPair(key, value);
                    this.pairs.addElement(gp);
                }
            }
        } catch (Exception ex)
        {
            log.warn("Could not restore the form pair group " + this.name);
        }
    }
    
    public void storeGroupPreference()
    {
        try {
            Preferences fpgs = Prefs.getPrefs().node(this.prefs.absolutePath() + "/formpairgroups");
            Preferences child = fpgs.node(fpgs.absolutePath() + "/" + this.name);
            child.removeNode();

            //fpgs = Prefs.getPrefs().node(this.prefs.absolutePath() + "/formpairgroups");
            child = fpgs.node(fpgs.absolutePath() + "/" + this.name);
            child.clear();
            log.debug("Clearing options in path " + child.absolutePath());

            for(int i = 0; i < this.pairs.size(); i++)
            {
                FormPair fp = (FormPair)this.pairs.get(i);
                child.put(fp.getName(), fp.getValue());
                log.trace("Stored pref group " + fp.getName() + " to " + child.absolutePath());
            }
        } catch (Exception ex)
        {
            log.warn("Could not store the form pair group " + this.name);
        }    }
    
    /**
     * @return Returns the value of name.
     */
    public String getName ()
    {
        return this.name;
    }
    
    /**
     * Sets name to name.
     * @param name Name of the group of key/value pairs
     */
    public void setName (String name)
    {
        this.name = name;
    }
    
    /**
     * @return Returns the value of pairs.
     */
    public FormPair [] getArrayPairs ()
    {
        FormPair [] fp = new FormPair[this.pairs.size()];
        
        for(int i = 0; i < this.pairs.size(); i++)
        {
            fp[i] = (FormPair)this.pairs.get(i);
        }
            
        return fp;
    }
    
    public Vector getPairs()
    {
        return this.pairs;
    }
    
    public String toString() 
    {
        return this.name;
    }
    
    public int compareTo(Object obj)
    {
        FormPairGroup fpg = (FormPairGroup)obj;
        String oName = fpg.getName();
        if(!oName.equals(this.name))
            return -1;
        
        Vector oPairs = fpg.getPairs();
        if(oPairs.size() != this.pairs.size())
            return -1;
        
        for(int i = 0; i < oPairs.size(); i++)
            if(oPairs.get(i) != this.pairs.get(i))
                return -1;
        
        return 0;
    }
    
    public static Vector retrievePreferences()
    {
        Vector formGroups = new Vector();
        Preferences prefs = Prefs.getPrefs();
        // restore formpair groups
        Preferences fpgs = prefs.node(prefs.absolutePath() + "/formpairgroups");
        try {
        String [] groups  = fpgs.childrenNames();
        
        
            for(int i = 0; i < groups.length; i++)
            {
                //Preferences child = fpgs.node(fpgs.absolutePath() + "/" + groups[i]);
            
                String name = groups[i];
            
                FormPairGroup fpg = new FormPairGroup(name);
                fpg.retrieveGroupPreference();
                formGroups.addElement(fpg);
                    
            }
        } catch (Exception ex) {
            log.warn("Error trying to retrieve preferences from settings repository");
        }
        return formGroups;
    }
    
    public static void storePreferences(Vector formGroups)
    {
        log.debug("Begining form group prefs save operation");
        try {
            Preferences fpgs = Prefs.getPrefs().node(Prefs.getPrefs().absolutePath() + "/formpairgroups");
            fpgs.removeNode();
            for(int i = 0; i < formGroups.size(); i++)
            {
                FormPairGroup fpg = (FormPairGroup)formGroups.get(i);
                if(fpg != null)
                {
                    String fpgname = fpg.getName();                    
                    if(fpgname != null && fpgname.equals("none"))
                        continue;

                    log.trace("Storing prefs for " + fpgname);
                    fpg.storeGroupPreference();
                }
                    
            }
        } catch (Exception ex) {
            log.warn("Error trying to store formpairgroups preferences to settings repository");
        }
    }

}
