package com.skills4testing.client;


import java.io.*;
import java.text.*;
import java.util.*;

public class CClientLogger {

    public static boolean mDebugOn = false;

    private static int instanceCounter = 0;
    private static CClientLogger itSelf;
    private String LOG_FILE_PATH;
    private String LOG_FILE;
    private SimpleDateFormat fmt;
    private Date date;
    private BufferedWriter writer;
    private String mOSName = "Linux";

    private CClientLogger() {
        mOSName = System.getProperty("os.name");
        if (mOSName.equalsIgnoreCase("Linux") || mOSName.equalsIgnoreCase("Uinux")) {
            LOG_FILE_PATH = File.separator + "tmp" + File.separator + "Metatude" + File.separator + CConstants.MCIC;
        } else {
            LOG_FILE_PATH = ".." + File.separator + "logs";
        }
        LOG_FILE = LOG_FILE_PATH + File.separator + CConstants.MCIC + "." + CConstants.MCIC_VERSION_NO + ".log";
        fmt = new SimpleDateFormat(CConstants.DATE_FORMAT);
        date = new Date();
    }

    public static CClientLogger getInstance() {
        if (instanceCounter == 0) {
            itSelf = new CClientLogger();
            instanceCounter++;
        }
        return itSelf;
    }

    public void openFile() throws Exception {
        if (writer == null) {
            File file = new File(LOG_FILE_PATH);
            if (!file.exists()) {
                System.err.println(file.mkdirs());
            }
            file = new File(LOG_FILE);
            if (!file.exists()) {
                file.createNewFile();
                if (mOSName.equalsIgnoreCase("Linux") || mOSName.equalsIgnoreCase("Uinux")) {
                    Runtime runtime = Runtime.getRuntime();
                    runtime.exec("chmod 666 " + LOG_FILE);
                }
            }
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
        }
    }

    public void closeLog() {
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.err.println("Error while closing MCIC log file: " + e.getMessage());
        }
    }


    /**
     *
     * @param pObj Object - this should contain the instance of the class where the logging is done.
     * @param pLogStr String
     */

    public synchronized void log(Object pObj, String pLogStr) {
        try {
            openFile();
            if (writer != null) {
                date.setTime(System.currentTimeMillis());
                writer.write(fmt.format(date) + "::" + pObj.getClass().getName() + ":" + pLogStr + "\r\n");
                writer.flush();
            }
        } catch (Exception e) {
            System.err.println("Error while writing to MCIC log file: " + e.getMessage());
        }
    }

}
