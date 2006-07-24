/*
 * Created on Feb 12, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs.juniuploader.objects.remoteobjects;

import java.io.File;
import java.util.prefs.Preferences;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mbs.juniuploader.objects.localobjects.LUAFile;
import org.mbs.juniuploader.objects.localobjects.WAccount;
import org.mbs.juniuploader.objects.localobjects.WDirectory;
import org.mbs.juniuploader.util.CheckSummer;
import org.mbs.juniuploader.util.Prefs;
import java.util.Vector;
import org.mbs.juniuploader.util.Util;
import org.mbs.juniuploader.util.FileChangeListener;

/**
 * This represents a single upload rule to a site including the form values. 
 *    
 * @see         FormPairGroup
 * @see         UploadSite
 * @see         LUAFile
 */
public class UploadRule implements Comparable, FileChangeListener
{
    private static Log log = LogFactory.getLog(UploadRule.class);
    
    protected LUAFile luaFile;
    protected WAccount account;
    protected WDirectory directory;
    protected UploadSite uploadSite;
    protected FormPairGroup pairGroup;

    /**
     * 
     */
    public UploadRule (LUAFile lf, WAccount wa, WDirectory wd, UploadSite us, FormPairGroup fpg)
    {
        super();
        
        luaFile = lf;
        account = wa;
        directory = wd;
        uploadSite = us;
        pairGroup = fpg;
        
    }
    
    public void fileChanged(String filename)
    {
        log.info(filename);
    }
    
    public void upload()
    {
        log.trace("upload() called");

        if(this.isValid())
        {
            org.mbs.juniuploader.StatusThread.addMessage("Uploading selected file to " + this.getUploadSite().getUrl());
            Util.postFileUpload(this);
            org.mbs.juniuploader.StatusThread.addMessage("Completed an upload to " + this.getUploadSite().getUrl());
        }

        log.trace("upload() completed");
    }

    
    public int compareTo(Object obj)
    {
        if(((LUAFile)obj).compareTo(this.luaFile) != 0)
            return -1;
        if(((WAccount)obj).compareTo(this.account) != 0)
            return -1;
        if(((WDirectory)obj).compareTo(this.directory) != 0)
            return -1;
        if(((UploadSite)obj).compareTo(this.uploadSite) != 0)
            return -1;
        return 0;
    }
    
    public LUAFile getLuaFile()
    {
        // use acct to get luafiles
        return this.luaFile;
    }
    
    public File getFile()
    {
        return this.luaFile.getRealLocation();
    }

    
    /**
     * @return Returns the value of uploadSite.
     */
    public UploadSite getUploadSite ()
    {
        return this.uploadSite;
    }
    
    public boolean isValid()
    {
        if(
                (luaFile != null && luaFile.isValid()) &&
                (account != null && account.isValid()) &&
                (directory != null && directory.isValid()) &&
                (uploadSite != null && uploadSite.isValid())
                )
            return true;
        log.warn("isValid() was false");
        return false;
    }

    
    /**
     * @return Returns the value of pairGroup.
     */
    public FormPairGroup getPairGroup ()
    {
        return this.pairGroup;
    }
    
    public String toString()
    {
        return new String(this.luaFile.getShortName() + " in " + this.account.getShortName() + " of " + this.directory.getFileObject().getAbsolutePath() + " to " + this.uploadSite.getUrl());
    }

    
    /**
     * @return Returns the value of account.
     */
    public WAccount getAccount ()
    {
        return this.account;
    }

    
    /**
     * @return Returns the value of directory.
     */
    public WDirectory getDirectory ()
    {
        return this.directory;
    }
    
    public static void storePreferences(Vector uploadRules)
    {
        Preferences prefs = Prefs.getPrefs();
        log.debug("Storing " + uploadRules.size() + " upload rules to preferences");
        // store upload rules
        try {
        Preferences rules = prefs.node(prefs.absolutePath() + "/rules");
        rules.removeNode();
        rules = prefs.node(prefs.absolutePath() + "/rules");
        for(int i = 0; i < uploadRules.size(); i++)
        {
            UploadRule ur = (UploadRule)uploadRules.get(i);
            // UUIDGen u = new UUIDGen();
            // String tmpName = u.generateUUID();
            String dir = ur.getDirectory().getFileObject().getAbsolutePath();
            String acct = ur.getAccount().getAccountDirectory().getAbsolutePath(); 
            String lf = ur.getLuaFile().getRealLocation().getAbsolutePath(); 
            String url = ur.getUploadSite().getUrl();
            String pg = null;
            if(ur.getPairGroup() != null)
                pg = ur.getPairGroup().getName();
            else
                pg = "";

            String trial = dir + acct + lf + url + pg; 
            String tmpName = CheckSummer.createChecksumFromString(trial);
/*            while(rules.nodeExists(tmpName))
            {
                UUIDGen u = new UUIDGen();
                tmpName = CheckSummer.createChecksumFromString(u.generateUUID() + tmpName);
            }
*/            
            log.trace("Storing rule " + ur + " as " + tmpName);
            Preferences nodeInQuestion = rules.node(rules.absolutePath() + "/" + tmpName);
            nodeInQuestion.put("directory", dir);
            nodeInQuestion.put("account", acct);
            nodeInQuestion.put("luafile", lf);
            nodeInQuestion.put("uploadsite", url);
            nodeInQuestion.put("formpairgroup", pg);
        }
        } catch (Exception ex) {}

    }
    
    public static void retrievePreferences(Vector uploadRules, Vector uploadLocations, Vector formGroups, Vector wowDirectories)
    {
        // restore upload rules
        Preferences prefs = Prefs.getPrefs();
        Preferences rules = prefs.node(prefs.absolutePath() + "/rules");
        try {
            String [] ruleKeys = rules.childrenNames();
            for(int i = 0; i < ruleKeys.length; i++)
            {
                Preferences nodeInQuestion = rules.node(rules.absolutePath() + "/" + ruleKeys[i]);
                String directory = nodeInQuestion.get("directory", "error");
                String account = nodeInQuestion.get("account", "error");
                String luafile = nodeInQuestion.get("luafile", "error");
                String uploadsite = nodeInQuestion.get("uploadsite", "error");
                String pairgroup = nodeInQuestion.get("formpairgroup", "error");
                
                log.debug("Attemping to restore rule " + ruleKeys[i]);
                boolean stop = 
                        luafile.equals("error") ||
                        account.equals("error") ||
                        directory.equals("error") ||
                        uploadsite.equals("error");
                
                if(!stop)
                {
                    // find the uploadsite
                    UploadSite us = null;
                    for(int z = 0; z < uploadLocations.size(); z++)
                    {
                        UploadSite ptr = (UploadSite)uploadLocations.get(z);
                        
                        if(uploadsite.equals(ptr.getUrl()))
                        {
                            log.trace("found the uploadsite the rule belongs with -- " + ptr.getUrl());
                            us = ptr;
                        }
                    }
                    
                    // find the pairgroup
                    FormPairGroup fpg = null;
                    for(int z = 0; z < formGroups.size(); z++)
                    {
                        FormPairGroup ptr = (FormPairGroup)formGroups.get(z);
                        
                        if(pairgroup.equals(ptr.getName()))
                        {
                            log.trace("found the group of form vars the rule belongs with -- " + ptr.getName());
                            fpg = ptr;
                        }
                    }
                    
                    
                    // find the dir/acct/luafile
                    Vector v = wowDirectories;
                    for(int j = 0; j < v.size(); j++)
                    {
                        WDirectory wd = (WDirectory)v.get(j);
                        //log.trace(directory + "?=" + wd.getFileObject().getAbsoluteFile().getAbsolutePath());
                        if(directory.equals(wd.getFileObject().getAbsoluteFile().getAbsolutePath()) )
                        {
                            // we've found the directory for this rule
                            log.trace("found the directory the rule belongs with -- " + wd.getFileObject().getAbsolutePath());
                            Vector accts = wd.getAccounts();
                            for(int k = 0; k < accts.size(); k++)
                            {
                                WAccount wa = (WAccount)accts.get(k);
                                if(wa.getAccountDirectory().getAbsoluteFile().getAbsolutePath().equals(account))
                                {
                                    // we've found the account for this rule
                                    log.trace("found the account the rule belongs with -- " + wa.getAccountDirectory().getAbsolutePath());
                                    Vector luas = wa.getLuaFiles();
                                    for(int l = 0; l < luas.size(); l++)
                                    {
                                        LUAFile lf = (LUAFile)luas.get(l);
                                        if(lf.getRealLocation().getAbsoluteFile().getAbsolutePath().equals(luafile))
                                        {
                                            log.trace("found the luafile the rule belongs with -- " + lf.getRealLocation().getAbsolutePath());
                                            // we've found the lua file for this rule
                                            /* log.warn(new Boolean(us != null && us.isValid()));
                                            log.warn(new Boolean(wd != null && wd.isValid()));
                                            log.warn(new Boolean(wa != null && wa.isValid()));
                                            log.warn(new Boolean(lf != null && lf.isValid())); */
                                            if(
                                                    (us != null && us.isValid()) &&
                                                    (wd != null && wd.isValid()) &&
                                                    (wa != null && wa.isValid()) &&
                                                    (lf != null && lf.isValid())
                                                    )
                                            {
                                                log.trace("Well, that's enough of the rule for us, let's add this match!");
                                                // we have a valid match, add the rule
                                                UploadRule newRule = new UploadRule(lf, wa, wd, us, fpg);
                                                //System.out.println("New rule added from reg: " + newRule);
                                                uploadRules.add(newRule);
                                                log.trace("Added rule " + newRule);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                        
                }
            }
        } catch (Exception e) {}       

    }
}
