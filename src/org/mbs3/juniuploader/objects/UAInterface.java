/*
 * Created on Mar 7, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs3.juniuploader.objects;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Vector;
import org.mbs3.juniuploader.jUniUploader;
import org.mbs3.juniuploader.gui.pnlAbout;
import org.mbs3.juniuploader.objects.localobjects.WDirectory;
import org.mbs3.juniuploader.util.CheckSummer;
import org.mbs3.juniuploader.util.Prefs;
import org.mbs3.juniuploader.util.Util;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This is a class handles information and installation of remote addons
 * specified by a UniAdmin interface.php file. It can get the updated list of addons,
 * compare local and remote md5 digests, sync any files for which the md5 digest
 * doesn't match, download images stored and specified by UA, and download/negociate
 * the preferences specified by a UniAdmin interface.
 *    
 * @see         jUniUploader
 * @see         Addon
 * @see         AddonFile
 * @see         Prefs
 * @see         CheckSummer
 */
public class UAInterface
{

    private static Log log = LogFactory.getLog(UAInterface.class);
    
    // don't need to restore this
    protected static Vector remoteAddons;
    
    /**
     * 
     
    public UAInterface ()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    */
    public static void init()
    {
        if(remoteAddons == null)
            remoteAddons = new Vector();
    }
    
    public static  void compareAddonsLocalRemote(WDirectory wd)
    {
        log.trace("compareAddonsLocalRemote() called");
        if(!isSyncAddons())
        {
            log.trace("compareAddonsLocalRemote() skipping, per prefs");
            return;
        }
        
        UAInterface.getUpdatedAddonList();
        Vector addons = UAInterface.remoteAddons;
        
        for(int i = 0; i < addons.size(); i++)
        {
            Addon ao = ((Addon)addons.get(i));
            boolean go = ao.compareToFS(wd);
            if(!go)
            {
                if(isOverwriteAddons())
                {
                    log.trace("RemoteSystem: compareAddonsLocalRemote() detected (and fixing) mismatch on " + ao.getName());
                    ao.SyncWithFileSystem(wd);
                }
                else
                {
                    log.trace("RemoteSystem: compareAddonsLocalRemote() detected (but skipping) mismatch on " + ao.getName());
                }
            }
            else if(go)
            {
                log.trace("RemoteSystem: compareAddonsLocalRemote() discovered " + ao.getName() + " is already in sync");
            } 
        }
    }
    
    public static String getRemoteAddonURL(String name)
    {
        return Util.getStringOutput(UAInterface.getInterfaceLocation(), "OPERATION=GETADDON&ADDON=" + name);
    }

    protected static void getUpdatedAddonList()
    {
        log.trace("getUpdatedAddonList() called");
        Document doc = Util.getXMLOutput(UAInterface.getInterfaceLocation(), "OPERATION=GETADDONLIST");
        
        NodeList nodes = doc.getElementsByTagName("addon");
        for(int i = 0; i < nodes.getLength(); i++)
        // loop through each addon, and it's name attribute
        {
            // A NamedNodeMap containing the attributes of this node (if it is an Element) or null otherwise.
            NamedNodeMap addonRootNodeMap = nodes.item(i).getAttributes();
            
            //A Node (of any type) with the specified nodeName, or null if it does not identify any node in this map.
            Node addonRootNode = addonRootNodeMap.getNamedItem("name");

            String addonName = addonRootNode.getNodeValue();
            log.trace("getUpdatedAddonList() remote addon " + addonName + " decected");
            
            Vector addonFiles = new Vector();
            
            NodeList files = nodes.item(i).getChildNodes();
            for(int j = 0; j < files.getLength(); j++)
            // loop through each file spec
            {
                if(files.item(j).getNodeName().equals("file"))
                {
                    NamedNodeMap fileSpecs = files.item(j).getAttributes();

                    Node fileLocation = fileSpecs.getNamedItem("name");
                    Node md5sum = fileSpecs.getNamedItem("md5sum");

                    AddonFile myAddonFile = new AddonFile(fileLocation.getNodeValue(), md5sum.getNodeValue());
                    addonFiles.addElement(myAddonFile);
                }
            }

            Addon ao = new Addon(addonName, addonFiles);
            remoteAddons.addElement(ao);
            log.trace("getUpdatedAddonList() got remote name and checksums for " + ao.getName());
        }

        log.trace("getUpdatedAddonList() completed");
        
    }

    
    /**
     * @return Returns the value of overwriteAddons.
     */
    public static boolean isOverwriteAddons ()
    {
        String t = "true";
        String v = System.getProperty(Prefs.getPrefPrefix() + ".overwriteaddons");
        if(v.toLowerCase().equals(t))
            return true;
        return false;
    }

    
    /**
     * Sets overwriteAddons to overwriteAddons.
     * @param overwriteAddons If set to true, addons will be updated instead of just checked.
     */
    public static void setOverwriteAddons (boolean overwriteAddons)
    {
        String t = "true";
        String f = "false";
        String name = Prefs.getPrefPrefix() + ".overwriteaddons";
        if(overwriteAddons)
            System.setProperty(name, t);
        else
            System.setProperty(name, f);
    }

    
    /**
     * @return Returns the value of overwriteSettings.
     */
    public static boolean isOverwriteSettings ()
    {
        String t = "true";
        String v = System.getProperty(Prefs.getPrefPrefix() + ".overwritesettings");
        if(v.toLowerCase().equals(t))
            return true;
        return false;
    }

    
    /**
     * Sets overwriteSettings to overwriteSettings. Right now, this has no effect.
     * @param overwriteSettings If set to true, UniAdmin's settings will overwrite the local ones.
     */
    public static void setOverwriteSettings (boolean overwriteSettings)
    {
        String t = "true";
        String f = "false";
        String name = Prefs.getPrefPrefix() + ".overwritesettings";
        if(overwriteSettings)
            System.setProperty(name, t);
        else
            System.setProperty(name, f);
    }

    
    /**
     * @return Returns the value of syncAddons.
     */
    public static boolean isSyncAddons ()
    {
        String t = "true";
        String v = System.getProperty(Prefs.getPrefPrefix() + ".syncaddons");
        if(v.toLowerCase().equals(t))
            return true;
        return false;
    }

    
    /**
     * Sets syncAddons to syncAddons.
     * @param syncAddons Sets the property to sync addons
     */
    public static void setSyncAddons (boolean syncAddons)
    {
        String t = "true";
        String f = "false";
        String name = Prefs.getPrefPrefix() + ".syncaddons";
        if(syncAddons)
            System.setProperty(name, t);
        else
            System.setProperty(name, f);    
    }

    
    /**
     * @return Returns the value of syncSettings.
     */
    public static boolean isSyncSettings ()
    {
        String t = "true";
        String v = System.getProperty(Prefs.getPrefPrefix() + ".syncsettings");
        if(v.toLowerCase().equals(t))
            return true;
        return false;
    }

    
    /**
     * Sets syncSettings to syncSettings.
     * @param syncSettings If true, settings will be sync'd when you call remotePrefsToLocal()
     */
    public static void setSyncSettings (boolean syncSettings)
    {
        String t = "true";
        String f = "false";
        String name = Prefs.getPrefPrefix() + ".syncsettings";
        if(syncSettings)
            System.setProperty(name, t);
        else
            System.setProperty(name, f);    }

    
    public static void syncImages()
    {
        log.trace("Entered syncImages");
        String md5Logo [] = 
            {
                Util.getStringOutput(UAInterface.getInterfaceLocation(), "OPERATION=GETFILEMD5&FILENAME=logo1.gif"),
                Util.getStringOutput(UAInterface.getInterfaceLocation(), "OPERATION=GETFILEMD5&FILENAME=logo2.gif")
            };
        
        File logoFiles [] =
        {
                new File("logo1.gif"),
                new File("logo2.gif")
        };
        
        String shortLogoNames [] =
        {
                new String("logo1"),
                new String("logo2")
        };
        
        for(int i = 0; i < md5Logo.length; i++)
        {
            boolean success = false;
            File ptr = logoFiles[i];
            if(ptr != null && ptr.exists() && ptr.canRead())
            {
                // the file exists, let's check it's md5
                success = CheckSummer.compareFileToChecksum(logoFiles[i].getAbsolutePath(), md5Logo[i]);
            }
            
            if(!success)
            {
                File tmp = Util.getRemoteFile(System.getProperty(Prefs.getPrefPrefix() + "." + shortLogoNames[i]));
                log.info("Downloading " + ptr.getName() + " from remote; it wasn't found");
                try {
                    Util.copy(tmp, logoFiles[i]);
                    tmp.delete();
                } catch (Exception ex)
                {
                    log.error("Error downloading and copying logo to current directory", ex);
                }
            }
            else
            {
                log.debug(ptr.getName() + " was already downloaded and accurate, skipping");
            }
        }
        pnlAbout.repaintLogo();
    }

    public static void remotePrefstoLocal()
    // DO NOT CALL THIS FROM ANYTHING BUT UPLOADER
    {
        if(UAInterface.getInterfaceLocation() == null || UAInterface.getInterfaceLocation().equals(""))
        {
            log.warn("No interface selection exists, cannot sync!");
            return;
        }
        if(!isSyncSettings())
        {
            log.trace("RemoteSystem: remotePrefstoLocal() doing no actions, per prefs");
            return;
        }
            
        //if(true) return;
        //DebugPrint("Moving remote preferences to local, overwrite conflicting local values is " + overwriteConflicting, 1);
        String results = Util.getStringOutput(UAInterface.getInterfaceLocation(), "OPERATION=GETSETTINGS");
        String DNE = "DOES NOT EXIST";
            
        String [] eachPref = results.split("\\|");
        
        for(int i = 0; i < eachPref.length; i++)
        {
            String keyval[] = eachPref[i].split("=");
            String key = Prefs.getPrefPrefix() + "." + keyval[0].toLowerCase();
            String value = keyval[1];
            
            if(Prefs.getPrefs().get(key, DNE).equals(DNE))
            {
                // preference did not exist at all
                log.warn("RemoteSystem: Pref " + key + " did not exist at all! " + value);
                System.setProperty(key, value.toLowerCase());
            }
            else 
            {
                // it existed
                if(value.equals(Prefs.getPrefs().get(key,""))) 
                {
                    // value we're adding matches what is already set
                    log.debug("RemoteSystem: Pref " + key + " matched what existed: " + value);
                }
                else
                {
                    // value we're adding CONFLICTS what is already set
                    if(false && isOverwriteSettings())
                    {
                        log.trace("RemoteSystem: Pref " + key + " conflicting prefs, overwrote: " + value);
                        System.setProperty(key, value.toLowerCase());
                    }
                    else
                    {
                        log.trace("RemoteSystem: Pref " + key + " conflicting prefs, ignored: " + value);
                    }
                    
                }
            }
            
        }
    }

    
    /**
     * @return Returns the value of interfaceLocation.
     */
    public static String getInterfaceLocation ()
    {
        String name = Prefs.getPrefPrefix() + ".interfaceurl";
        String value = System.getProperty(name);
        return value;
    }
    
    /**
     * Sets interfaceLocation to interfaceLocation.
     * @param interfaceLocation The url of the UniAdmin interface.php
     */
    public static void setInterfaceLocation (String interfaceLocation)
    {
        log.trace("Interface location set to " + interfaceLocation);
        String name = Prefs.getPrefPrefix() + ".interfaceurl";
        System.setProperty(name, interfaceLocation);
    }

}
