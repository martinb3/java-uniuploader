/*
 * Created on Feb 3, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs3.juniuploader.util;



/**
 * This class implements various handy utility functions like dealing
 * with strings and streams and swing collection conversion to a vector.
 * 
 * @author Martin Smith
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.parsers.DOMParser;

import org.mbs3.juniuploader.objects.remoteobjects.FormPair;
import org.mbs3.juniuploader.objects.remoteobjects.FormPairGroup;
import org.mbs3.juniuploader.objects.remoteobjects.UploadRule;
import org.w3c.dom.Document;

public class Util
{
    private static Log log = LogFactory.getLog(Util.class);

    public static boolean isMac()
    {
        String osUpper = System.getProperty("os.name").toUpperCase();
        if(osUpper.startsWith("MAC OS X"))
            return true;
        return false;
    }
    
    
    public static void removeFromDLM(Vector v, DefaultComboBoxModel dlm)
    {
        java.util.Iterator i = v.iterator();
        while(i.hasNext())
        {
            Object o = i.next();
            dlm.removeElement(o);
           
        }
    }
    
    public static File getRemoteFile(String targetURL)
    {
        log.trace("getRemoteFile() called for " + targetURL);
        
        GetMethod fileGet = new GetMethod(targetURL);
        
        fileGet.getParams().setParameter(HttpMethodParams.USER_AGENT, getUserAgent());
        InputStream responseStream = null;
        File tmpFile = null;
        try {
            tmpFile = File.createTempFile("jUniUploader", ".zip");
        } catch (Exception e) {
            log.trace("Util: getRemoteFile() had error creating temp file");
        }
        
        try {
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

            int status = client.executeMethod(fileGet);
           
            if (status == HttpStatus.SC_OK) {
                log.trace("getRemoteFile() reported HTTP OK");
            } else {
                log.trace("getRemoteFile() reported HTTP Error");
            }

            responseStream = fileGet.getResponseBodyAsStream();
            
            FileOutputStream out = new FileOutputStream(tmpFile);
            int b = -1;
            while((b = responseStream.read()) != -1)
            {
                out.write(b);
            }
            out.close();
       
        } catch (Exception ex) {
            log.trace("getRemoteFile() encountered Exception");
        } finally {
            fileGet.releaseConnection();
        }
        tmpFile.deleteOnExit();
        return tmpFile;
        
    }
    
    public static Document getXMLOutput(String targetURL, String queryString)
    {
        log.trace("Util: getRemoteFile() called for " + targetURL + " with " + queryString);
        GetMethod fileGet = new GetMethod(targetURL);
        fileGet.getParams().setParameter(HttpMethodParams.USER_AGENT, getUserAgent());
        InputStream responseStream = null;
        DOMParser p = null;
        
        try {
            fileGet.setQueryString(queryString);
            
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

            int status = client.executeMethod(fileGet);
           
            if (status == HttpStatus.SC_OK) {
                log.trace("Util: getXMLOutput() reported HTTP OK");
            } else {
                log.trace("Util: getXMLOutput() reported HTTP Error");
            }

            responseStream = fileGet.getResponseBodyAsStream();
            p = new DOMParser();

            org.xml.sax.InputSource is = new org.xml.sax.InputSource(responseStream);
            try {
                p.parse(is);
            } catch (Exception ex) {
                log.trace("getXMLOutput() XML Parsing failed");
            }
        } catch (Exception ex) {
            log.trace("Util: getXMLOutput() reported HTTP Exception");
        } finally {
            fileGet.releaseConnection();
        }
        
        return p.getDocument();
        
        
    }
    
    public static String getStringOutput(String targetURL, String queryString)
    {
        log.trace("Util: getStringOutput() called");
        GetMethod fileGet = new GetMethod(targetURL);
        fileGet.getParams().setParameter(HttpMethodParams.USER_AGENT, getUserAgent());
        InputStream responseStream; String response = null;
        
        try {
            
            fileGet.setQueryString(queryString);
            
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

            int status = client.executeMethod(fileGet);
           
            if (status == HttpStatus.SC_OK) {
                log.trace("Util: getStringOutput() reported HTTP OK");
            } else {
                log.trace("Util: getStringOutput() reported HTTP Error");
            }

            responseStream = fileGet.getResponseBodyAsStream();
            
            response = Util.parseISToString(responseStream);
            
        } catch (Exception ex) {
            log.trace("Util: getStringOutput() encountered an exception during HTTP Get");
        } finally {
            fileGet.releaseConnection();
        }
        
        return response;
    }

    public static void postFileUpload(UploadRule ur)
    {
        if(!ur.isValid())
        {
            log.warn("postFileUpload thought the rule it was trying isn't valid");
        }
        File targetFile = ur.getFile();
        String targetURL = ur.getUploadSite().getUrl();
        FormPairGroup fpg = ur.getPairGroup();
        FormPair [] uploadPairs = null;
        if(fpg != null && !fpg.getName().equals("none"))
            uploadPairs = fpg.getArrayPairs();
        
        if(uploadPairs != null)
            log.info("postFileUpload posting " + targetFile + " and " + uploadPairs.length + " variables to " + targetURL);
        else
            log.info("postFileUpload posting " + targetFile + " to " + targetURL);
        
        PostMethod filePost = new PostMethod(targetURL);
        filePost.getParams().setParameter(HttpMethodParams.USER_AGENT, getUserAgent());
        
        try {
            Part[] parts = null;
            if(fpg != null && uploadPairs != null)
                parts = new Part[uploadPairs.length + 1];
            else
                parts = new Part[1];
            for(int i = 0; uploadPairs != null && i < uploadPairs.length; i++)
            {
                FormPair toUp = uploadPairs[i];
                String key = toUp.getName();
                String val = toUp.getValue();
                
                parts[i] = new StringPart(key, val);
                log.trace("postFileUpload added key " + key + " and associated value (hidden)");
            }
            String fname = targetFile.getName();
            String noExt = fname.substring(0, fname.length()-4);
            parts[parts.length-1] = new FilePart(noExt, targetFile);
            log.trace("postFileUpload added file " + noExt + "=" + targetFile.getName() + " to envelope");
            
            
            filePost.setRequestEntity(
                new MultipartRequestEntity(parts, filePost.getParams())
                );

            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().
                getParams().setConnectionTimeout(5000);
            
            int status = client.executeMethod(filePost);

            if(log.isDebugEnabled())
                log.debug(Util.parseISToString(filePost.getResponseBodyAsStream()));
            
            if (status == HttpStatus.SC_OK) {
                log.info("postFileUpload reported HTTP OK for " + ur);
                org.mbs3.juniuploader.StatusThread.addMessage("Done with " + ur);
            } else {
                log.info("postFileUpload encounted HTTP Error for " + fname + " to "+ ur);
                org.mbs3.juniuploader.StatusThread.addMessage("Failed with " + ur);
            }

        } catch (Exception ex) {
            log.error("postFileUpload reported an exception while working on " + ur, ex);
        } finally {
            filePost.releaseConnection();
        }
        
    }

    
    /**
     * 
     */
    public static String parseISToString (InputStream is)
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        boolean eof = false; int ch = 0;
        while(!eof)
        {
            try {
                ch = br.read();
            } catch (Exception e) {
                log.trace("Util: parseISToString() found exception reading stream");
            }
            
            if(ch != -1)
            {
                sb.append((char)ch);
            } else {
                break;
            }
        }
        
        return sb.toString();
    }
    
    public static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);
    
        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
    
    /**
     * @return Returns the value of userAgent.
     */
    public static String getUserAgent ()
    {
        String name = Prefs.getPrefPrefix() + ".useragent";
        String value = System.getProperty(name);
        return value;
    }

    
    /**
     * Sets userAgent to userAgent.
     * @param userAgent The new useragent to masquerade as
     */
    public static void setUserAgent (String userAgent)
    {
        log.trace("User agent set to " + userAgent);
        String name = Prefs.getPrefPrefix() + ".useragent";
        System.setProperty(name, userAgent);
    }

    public static Vector DCBMtoVector(DefaultComboBoxModel dcbm)
    {
        Vector v = new Vector();
        for(int i = 0; i < dcbm.getSize(); i++)
            v.add(dcbm.getElementAt(i));
        return v;
    }

}