/*
 * Created on Feb 18, 2006
 *
 * TODO Nothing yet.
 */
package org.mbs3.juniuploader.util;


import java.text.DateFormat;
import java.util.Date;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.util.prefs.Preferences;

import org.mbs3.juniuploader.*;

/**
 * This class is an implementation of an Apache Commons Logging logger
 * (org.apache.commons.logging.Log) and will log to a JTextArea in a GUI if 
 * properly setup. You should not need to play with this class other than to set
 * a JTextArea to intercept its calls if you use this logger.
 * 
 * @author Martin Smith
 *
 * 
 */
public class GUILog implements org.apache.commons.logging.Log
{
    private String name;
    private String property;
    
    private int level = -1;

    public final static int TRACE_LEVEL = 5;
    public final static int DEBUG_LEVEL = 4;
    public final static int INFO_LEVEL = 3;
    public final static int WARN_LEVEL = 2;
    public final static int ERROR_LEVEL = 1;
    public final static int FATAL_LEVEL = 0;
    
    private static JTextArea jTextArea = null;
    private static StringBuffer guiBuffer;

    public GUILog (java.lang.String name)
    {
        super();
        this.name = name;
        this.property = GUILog.class.getName() + "." + this.name;
        if(guiBuffer == null)
            guiBuffer = new StringBuffer();
        
        this.updateProperty();
        if(level == -1)
        {
            level = GUILog.INFO_LEVEL;
        }
    }
    
    private static String levelToString(int level)
    {
        StringBuffer sb = new StringBuffer();
        switch(level)
        {
            case GUILog.DEBUG_LEVEL: sb.append("DEBUG"); break;
            case GUILog.ERROR_LEVEL: sb.append("ERROR"); break;
            case GUILog.FATAL_LEVEL: sb.append("FATAL"); break;
            case GUILog.INFO_LEVEL: sb.append("INFO"); break;
            case GUILog.TRACE_LEVEL: sb.append("TRACE"); break;
            case GUILog.WARN_LEVEL: sb.append("WARN"); break;
            default:
                sb.append("UNKNOWN_LEVEL");
        }
        return sb.toString();
    }
    
    private static int stringToLevel(String level)
    {
        level = level.toUpperCase();
        if(level.equals("DEBUG"))
            return GUILog.DEBUG_LEVEL;
        else if(level.equals("ERROR"))
            return GUILog.ERROR_LEVEL;
        else if(level.equals("FATAL"))
            return GUILog.FATAL_LEVEL;
        else if(level.equals("INFO"))
            return GUILog.INFO_LEVEL;
        else if(level.equals("TRACE"))
            return GUILog.TRACE_LEVEL;
        else if(level.equals("WARN"))
            return GUILog.WARN_LEVEL;
        else
            return -1;
    }
    
    private void updateProperty()
    {
        Preferences p = Preferences.userNodeForPackage(jUniUploader.class);
        p = p.node(p.absolutePath() + "/logging");
        String prop = System.getProperty(this.property);

        if(prop == null)
            // if no property is set
        {
                this.level = GUILog.INFO_LEVEL;
        }
        else
        {
                this.level = GUILog.stringToLevel(prop);
        }
        p.put(this.name, GUILog.levelToString(this.level));

    }
    
    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#isDebugEnabled()
     */
    public boolean isDebugEnabled ()
    {
        this.updateProperty();
        return (level >= DEBUG_LEVEL);
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#isErrorEnabled()
     */
    public boolean isErrorEnabled ()
    {
        this.updateProperty();
        return (level >= ERROR_LEVEL);
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#isFatalEnabled()
     */
    public boolean isFatalEnabled ()
    {
        this.updateProperty();
        return (level >= FATAL_LEVEL);
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#isInfoEnabled()
     */
    public boolean isInfoEnabled ()
    {
        this.updateProperty();
        return (level >= INFO_LEVEL);
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#isTraceEnabled()
     */
    public boolean isTraceEnabled ()
    {
        this.updateProperty();
        return (level >= TRACE_LEVEL);
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#isWarnEnabled()
     */
    public boolean isWarnEnabled ()
    {
        this.updateProperty();
        return (level >= WARN_LEVEL);
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#trace(java.lang.Object)
     */
    public void trace (Object arg0)
    {
        if(this.isTraceEnabled())
        {
            this.writeLogLn(arg0, GUILog.TRACE_LEVEL);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#trace(java.lang.Object, java.lang.Throwable)
     */
    public void trace (Object arg0, Throwable arg1)
    {
        if(this.isTraceEnabled())
        {
            this.writeLogLn(arg0, GUILog.TRACE_LEVEL);
            this.writeLog(this.exceptionToString(arg1), GUILog.TRACE_LEVEL);
        }   
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#debug(java.lang.Object)
     */
    public void debug (Object arg0)
    {
        if(this.isDebugEnabled())
        {
            this.writeLogLn(arg0, GUILog.DEBUG_LEVEL);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#debug(java.lang.Object, java.lang.Throwable)
     */
    public void debug (Object arg0, Throwable arg1)
    {
        if(this.isDebugEnabled())
        {
            this.writeLogLn(arg0, GUILog.DEBUG_LEVEL);
            this.writeLog(this.exceptionToString(arg1), GUILog.DEBUG_LEVEL);
        }   
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#info(java.lang.Object)
     */
    public void info (Object arg0)
    {
        if(this.isInfoEnabled())
        {
            this.writeLogLn(arg0, GUILog.INFO_LEVEL);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#info(java.lang.Object, java.lang.Throwable)
     */
    public void info (Object arg0, Throwable arg1)
    {
        if(this.isInfoEnabled())
        {
            this.writeLogLn(arg0, GUILog.INFO_LEVEL);
            this.writeLog(this.exceptionToString(arg1), GUILog.INFO_LEVEL);
        }   
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#warn(java.lang.Object)
     */
    public void warn (Object arg0)
    {
        if(this.isWarnEnabled())
        {
            this.writeLogLn(arg0, GUILog.WARN_LEVEL);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#warn(java.lang.Object, java.lang.Throwable)
     */
    public void warn (Object arg0, Throwable arg1)
    {
        if(this.isWarnEnabled())
        {
            this.writeLogLn(arg0, GUILog.WARN_LEVEL);
            this.writeLog(this.exceptionToString(arg1), GUILog.WARN_LEVEL);
        }   
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#error(java.lang.Object)
     */
    public void error (Object arg0)
    {
        if(this.isErrorEnabled())
        {
            this.writeLogLn(arg0, GUILog.ERROR_LEVEL);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#error(java.lang.Object, java.lang.Throwable)
     */
    public void error (Object arg0, Throwable arg1)
    {
        if(this.isErrorEnabled())
        {
            this.writeLogLn(arg0, GUILog.ERROR_LEVEL);
            this.writeLog(this.exceptionToString(arg1), GUILog.ERROR_LEVEL);
        }   
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#fatal(java.lang.Object)
     */
    public void fatal (Object arg0)
    {
        if(this.isFatalEnabled())
        {
            this.writeLogLn(arg0, GUILog.FATAL_LEVEL);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#fatal(java.lang.Object, java.lang.Throwable)
     */
    public void fatal (Object arg0, Throwable arg1)
    {
        if(this.isFatalEnabled())
        {
            this.writeLogLn(arg0, GUILog.FATAL_LEVEL);
            this.writeLog(this.exceptionToString(arg1), GUILog.FATAL_LEVEL);
        }   
    }
    
    private String exceptionToString(Throwable arg0)
    {
        //writeLogLn("Converting exception frames into a string");
        StringBuffer sb = new StringBuffer();
        String sep = System.getProperty("line.separator");
        
        sb.append("Exception message: " + arg0.getLocalizedMessage() + sep);
        sb.append("Exception caused by " + arg0.getCause() + " stack trace, follows" + sep);

        StackTraceElement [] ste = arg0.getStackTrace();
        for(int i = 0; i < ste.length; i++)
            sb.append("\tframe " + i + ": " + ste[i].toString() + sep);

        return sb.toString();
    }
    
    /**
     * @return Returns the value of name.
     */
    public String getName ()
    {
        return this.name;
    }

    private synchronized void rawWrite(Object message)
    {
        System.err.print(message.toString());
        GUILog.guiBuffer.append(new String(message.toString()));
        if(GUILog.jTextArea != null)
            GUILog.flush();
    }
    
    public synchronized static void flush()
    {
        if(GUILog.jTextArea != null && GUILog.guiBuffer != null)
        // the visual logging component exists. drain any buffers and then write new 
        {
            if(GUILog.guiBuffer.length() > 0)
            {
                writeToGui(GUILog.guiBuffer.toString());
                GUILog.guiBuffer.setLength(0);
            } 
        }
    }

    // This is the THE callable message internally for errors/debug/etc
    private void writeLog(Object message, int msgLevel)
    {
        Date d = new Date();
        StringBuffer sb = new StringBuffer();
        
        sb.append(DateFormat.getDateInstance(DateFormat.SHORT).format(d)); 
        sb.append(" "); 
        sb.append(DateFormat.getTimeInstance(DateFormat.SHORT).format(d));
        sb.append(" at ");
        sb.append(GUILog.levelToString(msgLevel));
        sb.append(":");
        sb.append(this.name); 
        sb.append(": ");
        sb.append(message);
        
        this.rawWrite(sb.toString());
    }
    
    private void writeLogLn(Object message, int msgLevel)
    {
        this.writeLog(message, msgLevel);
        this.rawWrite(System.getProperty("line.separator"));
    }
    
    private static synchronized void writeToGui(final String s)
    {
        if(GUILog.jTextArea == null)
            return;
        
        final JTextArea jt = GUILog.jTextArea;
        Runnable r = new Runnable() {
          public void run() {
              jt.append(s);
              jt.setCaretPosition(jt.getText().length());
          }
        };
        SwingUtilities.invokeLater(r);   
    }
    
    
    /**
     * @return Returns the value of jTextArea.
     */
    public static synchronized javax.swing.JTextArea getJTextArea ()
    {
        return GUILog.jTextArea;
    }

    
    /**
     * Sets jTextArea to textArea.
     * @param textArea The Swing JTextArea to deliver messages to
     */
    public static synchronized void setJTextArea (javax.swing.JTextArea textArea)
    {
        GUILog.jTextArea = textArea;
    }
}
