/*
 * Created on Feb 4, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs.juniuploader.util;


/**
 * This class generates md5 checksums from file streams, strings, and compares
 * checksums with file streams. 
 *
 */

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CheckSummer
{

    private static Log log = LogFactory.getLog(CheckSummer.class);

    /**
     * This class cannot be instantiated. 
     */
    
    protected static byte[] createChecksum(InputStream is)
    {
        log.trace("createChecksum(InputStream) called with a stream");

        try {
            byte[] buffer = new byte[1024];
            MessageDigest complete = MessageDigest.getInstance("MD5");
            int numRead;
            do {
             numRead = is.read(buffer);
             if (numRead > 0) {
               complete.update(buffer, 0, numRead);
               }
             } while (numRead != -1);
            is.close();
            return complete.digest();
        }
        catch (Exception ex)
        {
            log.trace("createChecksum(InputStream) failed to create a proper checksum");
            return new byte[0];
        }
        
    }

    protected static byte[] ChecksumFromFile(String filename) {
        log.trace("createChecksumFromFile(String) for " + filename + " to compare with");
        try {
            InputStream fis =  new FileInputStream(filename);
            return createChecksum(fis);
        }
        catch (Exception ex)
        {
            log.error("createChecksumFromFile(String) failed to create a FileInputStream");
            return new byte[0];
        }
     }

    protected static byte[] ChecksumFromString(String input) throws Exception {
        log.trace("Creating a checksum for " + input + " to compare with");
        ByteArrayInputStream bais =  new ByteArrayInputStream(input.getBytes());
        return createChecksum(bais);
     }
    
    public static boolean compareFileToChecksum(String filename, String checksum)
    {
        log.trace("Comparing a file " + filename + " with it's checksum " + checksum);
        boolean ret = false;
        try {
            byte [] ba = CheckSummer.ChecksumFromFile(filename);
            
            String hexString = byteArrayToString(ba);
            if(checksum.equals(hexString.toString()))
            {
                log.trace("Comparing a file " + filename + " passed comparison, checksum accurate");
                ret = true;
            }
        } catch (Exception ex) {
            log.error("Creating a checksum for " + filename + " failed somewhere, bailing out", ex);
            ret = false;
        }
        return(ret);
    }
    
    public static String createChecksumFromString(String input)
    {
        String hexString = "unknown";
        try 
        {
            byte [] ba = CheckSummer.ChecksumFromString(input);
            hexString = byteArrayToString(ba);
        }
        catch (Exception ex)
        {
            log.error("getStringChecksum(String) could not process a string input");
        }
        return(hexString);
    }
    
    
    protected static String byteArrayToString(byte [] ba)
    {
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<ba.length;i++) {
            String s = new String(Integer.toHexString(0xFF & ba[i]));
            if(s.length() < 2)
                hexString.append("0");
            hexString.append(s);
        }
        return hexString.toString();
    }


}
