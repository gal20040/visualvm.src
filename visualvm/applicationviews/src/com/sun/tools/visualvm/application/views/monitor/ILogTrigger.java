package com.sun.tools.visualvm.application.views.monitor;

public interface ILogTrigger {
    boolean checkCPU(long cpuUsage);
    boolean checkMaxHeap(long maxHeap);
    boolean checkUsedHeap(long usedHeap);
    boolean checkThreads(long threads);
}
