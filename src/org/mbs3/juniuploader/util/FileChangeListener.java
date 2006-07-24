package org.mbs3.juniuploader.util;

/**
 * This interface is for file change listening for LUA files, but is
 * currently not implemented by a listener class yet.
 * 
 * @author Martin Smith
 *
 * 
 */
public interface FileChangeListener {
    /** Invoked when a file changes.   
     * @param fileName name of changed file.
     */
    public void fileChanged(String fileName);
}
