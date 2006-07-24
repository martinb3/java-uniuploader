/*
 * Created on Mar 19, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs.juniuploader.util;


import java.util.*;
import java.io.*;
import java.net.*;

/**
 * This class can monitor multiple files and requires that callers
 * implement the FileChangeListener interface if they want callbacks.
 * I borrowed this from an example somewhere, but I can't find the citation!
 * 
 *   Let me know if you find it, so I can give proper credit.
 * 
 * @see FileChangeListener
 * 
 */
public class FileMonitor {

    private static final FileMonitor instance = new FileMonitor();

    private Timer timer;
    private Hashtable timerEntries;

    public static FileMonitor getInstance() {
        return instance;
    }

    protected FileMonitor() { 
        // Create timer, run timer thread as daemon.
        timer = new Timer(true);
        timerEntries = new Hashtable();
    }
    
    /** Add a monitored file with a FileChangeListener.
     * @param listener listener to notify when the file changed.
     * @param fileName name of the file to monitor.
     * @param period polling period in milliseconds.
     */
    public void addFileChangeListener(FileChangeListener listener, String fileName, long period) throws FileNotFoundException
    {
        removeFileChangeListener(listener, fileName);
        FileMonitorTask task = new FileMonitorTask(listener, fileName);
        timerEntries.put(fileName + listener.hashCode(), task);
        timer.schedule(task, period, period);
    }

    /** Remove the listener from the notification list.
     * @param listener the listener to be removed.
     */
    public void removeFileChangeListener(FileChangeListener listener, String fileName) 
    {
        FileMonitorTask task = (FileMonitorTask) timerEntries.remove(fileName + listener.hashCode());
        if (task != null) {
            task.cancel();
        }
    }

    protected void fireFileChangeEvent(FileChangeListener listener, String fileName) {
        listener.fileChanged(fileName);
    }

    class FileMonitorTask extends TimerTask {
        FileChangeListener listener;
        String fileName;
        File monitoredFile;
        long lastModified;

        public FileMonitorTask(FileChangeListener listener, String fileName) throws FileNotFoundException {
            this.listener = listener;
            this.fileName = fileName;
            this.lastModified = 0;

            monitoredFile = new File(fileName);
            if (!monitoredFile.exists()) {  // but is it on CLASSPATH?
                URL fileURL = listener.getClass().getClassLoader().getResource(fileName);
                
                if (fileURL != null) {
                    monitoredFile = new File(fileURL.getFile());
                }
                else 
                {
                    throw new FileNotFoundException("File Not Found: " + fileName);
                }
            }
            this.lastModified = monitoredFile.lastModified();
        }

        public void run() {
            long lastModified = monitoredFile.lastModified();
            if (lastModified != this.lastModified) {
                this.lastModified = lastModified;
                fireFileChangeEvent(this.listener, this.fileName);
            }
        }
    }
}
