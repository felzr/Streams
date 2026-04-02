package org.example.metrics;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class MetricsUtil {
    private static final ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();

    public static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    public static long getProcessCpuTime() {
        if (threadBean.isCurrentThreadCpuTimeSupported()) {
            return threadBean.getCurrentThreadCpuTime();
        }
        return -1;
    }

    public static void printMetrics(String label, long startTime, long endTime,
                                    long startMemory, long endMemory,
                                    long startCpu, long endCpu) {

        long executionTime = endTime - startTime;
        long memoryUsed = (endMemory - startMemory) / (1024 * 1024);
        long cpuUsed = (endCpu - startCpu) / 1_000_000; // ns → ms

        System.out.println("\n=== " + label + " ===");
        System.out.println("Tempo execução: " + executionTime + " ms");
        System.out.println("Memória usada:  " + memoryUsed + " MB");
        System.out.println("CPU usada:      " + cpuUsed + " ms");
    }
}
