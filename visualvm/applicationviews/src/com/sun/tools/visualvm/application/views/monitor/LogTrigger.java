package com.sun.tools.visualvm.application.views.monitor;

public class LogTrigger implements ILogTrigger{

    @Override
    public boolean IsLoggingOn() {
        return false;
    }

    @Override
    public boolean checkCPU(long cpuUsage) {
        return false;
    }

    @Override
    public boolean checkMaxHeap(long maxHeap) {
        return false;
    }

    @Override
    public boolean checkUsedHeap(long usedHeap) {
        return false;
    }

    @Override
    public boolean checkThreads(long threads) {
        return false;
    }
}
