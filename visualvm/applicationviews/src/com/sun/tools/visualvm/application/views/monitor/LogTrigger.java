package com.sun.tools.visualvm.application.views.monitor;

import com.sun.tools.visualvm.application.views.FileReaderWriter;

import java.io.*;
import java.util.Date;

public class LogTrigger implements ILogTrigger{

    private boolean commonLogging = false;
    private boolean stateLogging;
    private long cpu;
    private long maxHeap;
    private long heapUsed;
    private long threads;
    private boolean[] state = new boolean[6];
    public static String directory;

    LogTrigger(){
        File config = new File(LogName.CONFIG);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(config.getAbsoluteFile()));
            commonLogging = reader.readLine().equalsIgnoreCase("true");
            if (commonLogging){
                for (int i = 0; i < 6; i++) {
                    state[i] = false;
                }
                cpu = Long.parseLong(reader.readLine());
                reader.readLine(); //GC
                maxHeap = Long.parseLong(reader.readLine());
                heapUsed = Long.parseLong(reader.readLine());
                reader.readLine(); //Classes
                threads = Long.parseLong(reader.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + " file 'config_log.txt' is missing");
        }
    }

    public enum LogName {
        ;
        public static final String CPU = "cpu_log.txt";
        public static final String HEAP = "heap_log.txt";
        public static final String CLASS = "class_log.txt";
        public static final String THREAD = "thread_log.txt";
        public static final String OVERVIEW = "overview_log.txt";
        public static final String CONFIG = "log.config";
    }

    @Override
    public boolean checkCPU(long cpuUsage) {
        if (commonLogging){
            if (cpu != -1) {
                state[0] = cpuUsage >= cpu;
                stateLogging = isLoggingOn();
            }
                return stateLogging;
        }
        else return false;
    }

    @Override
    public boolean checkMaxHeap(long maxHeap) {
        if (commonLogging){
            if (this.maxHeap != -1) {
                state[1] = maxHeap >= this.maxHeap;
                stateLogging = isLoggingOn();
            }
            return stateLogging;
        }
        else return false;
    }

    @Override
    public boolean checkUsedHeap(long usedHeap) {
        if (commonLogging){
            if (heapUsed != -1) {
                state[2] = usedHeap >= heapUsed;
                stateLogging = isLoggingOn();
            }
            return stateLogging;
        }
        else return false;
    }

    @Override
    public boolean checkThreads(long threads) {
        if (commonLogging){
            if (this.threads != -1) {
                state[3] = threads >= this.threads;
                stateLogging = isLoggingOn();
            }
            return stateLogging;
        }
        else return false;
    }

    public boolean isLoggingOn(){
        return state[0] || state[1] || state[2] || state[3] || state[4] || state[5];
    }

    private String getLogHeader(String outputFileName) {
        String logHeader = "Date/time" +
                "\t" + "Date/time in milliseconds";

        if (outputFileName.equals(LogName.CLASS))
            logHeader = logHeader +
                    "\t" + "Total classes" +
                    "\t" + "Shared classes" +
                    "\t" + "Total unloaded classes" +
                    "\t" + "Shared unloaded classes";
        else if (outputFileName.equals(LogName.CPU))
            logHeader = logHeader +
                    "\t" + "CPU usage" +
                    "\t" + "GC usage" +
                    "\t" + "CPU detail" +
                    "\t" + "GC detail";
        else if (outputFileName.equals(LogName.HEAP))
            logHeader = logHeader +
                    "\t" + "Heap capacity" +
                    "\t" + "Heap used" +
                    "\t" + "Max heap";
        else if (outputFileName.equals(LogName.OVERVIEW))
            logHeader = "";
        else if (outputFileName.equals(LogName.THREAD))
            logHeader = logHeader +
                    "\t" + "Total threads" +
                    "\t" + "Daemon threads" +
                    "\t" + "Peak threads" +
                    "\t" + "Started threads";
        return logHeader;
    }

    void runLogging(FileReaderWriter fileReaderWriter, String outputFileName, String outputString) {
        fileReaderWriter = new FileReaderWriter(outputFileName);

        if (getFileSize(new File(outputFileName)) == 0) {
            String logHeader = getLogHeader(outputFileName);
            fileReaderWriter.appendToOutputFile(logHeader);
        }

        outputString = "\n" + getCurrentDateTime() +
                "\t" + getCurrentDateTimeInMilliSeconds() + outputString;
        fileReaderWriter.appendToOutputFile(outputString);
        fileReaderWriter.close();
    }

    private static long getFileSize(File file) {
        return file.length();
    }

    /**
     * Returns current date and time.
     * @return current date and time.
     */
    private static Date getCurrentDateTime() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * Returns current date and time in milliseconds.
     * @return current date and time in milliseconds.
     */
    private static long getCurrentDateTimeInMilliSeconds() {
        return getCurrentDateTime().getTime();
    }
}