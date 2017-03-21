package com.sun.tools.visualvm.application.views.monitor;

import java.io.*;

public class LogTrigger implements ILogTrigger{

    private boolean commonLogging = false;
    private boolean stateLogging;
    private long cpu;
    private long maxHeap;
    private long heapUsed;
    private long threads;
    private boolean[] state = new boolean[6];

    /**
     * @throws IOException
     */
    LogTrigger() throws IOException {
        File config = new File("config_log.txt");
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
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage() + " file 'config_log.txt' is missing");
        }
    }

    @Override
    public boolean checkCPU(long cpuUsage) {
        if (commonLogging){
            if (cpu != -1) {
                state[0] = cpuUsage >= cpu;
                stateLogging = state[0] || state[1] || state[2] || state[3] || state[4] || state[5];
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
                stateLogging = state[0] || state[1] || state[2] || state[3] || state[4] || state[5];
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
                stateLogging = state[0] || state[1] || state[2] || state[3] || state[4] || state[5];
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
                stateLogging = state[0] || state[1] || state[2] || state[3] || state[4] || state[5];
            }
            return stateLogging;
        }
        else return false;
    }
}
