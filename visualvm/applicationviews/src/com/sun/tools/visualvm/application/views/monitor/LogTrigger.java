package com.sun.tools.visualvm.application.views.monitor;

import java.io.*;

public class LogTrigger implements ILogTrigger{

    private boolean commonLogging = false;
    private boolean stateLogging;
    private long cpu;
    private long maxHeap;
    private long heapUsed;
    private long threads;

    /**
     * @throws IOException
     */
    LogTrigger() throws IOException {
        File config = new File("config_log.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(config.getAbsoluteFile()));
            commonLogging = reader.readLine().equalsIgnoreCase("true");
            if (commonLogging){
                cpu = Long.parseLong(reader.readLine());
                reader.readLine(); //GC
                maxHeap = Long.parseLong(reader.readLine());
                heapUsed = Long.parseLong(reader.readLine());
                reader.readLine(); //Classes
                threads = Long.parseLong(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage() + " file 'config_log.txt' is missing");
        }
    }

    @Override
    public boolean checkCPU(long cpuUsage) {
        if (commonLogging){
            stateLogging = cpuUsage >= cpu;
            return stateLogging;
        }
        else return false;
    }

    @Override
    public boolean checkMaxHeap(long maxHeap) {
        if (commonLogging){
            stateLogging = maxHeap >= this.maxHeap;
            return stateLogging;
        }
        else return false;
    }

    @Override
    public boolean checkUsedHeap(long usedHeap) {
        if (commonLogging){
            stateLogging = usedHeap >= heapUsed;
            return stateLogging;
        }
        else return false;
    }

    @Override
    public boolean checkThreads(long threads) {
        if (commonLogging){
            stateLogging = threads >= this.threads;
            return stateLogging;
        }
        else return false;
    }
}
