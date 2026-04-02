package org.example;

import org.example.metrics.MetricsUtil;
import org.example.tradicional.TraditionalThreadService;
import org.example.virtual.VirtualThreadService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


//    public static void main(String[] args) throws InterruptedException {
//        int numberOfTasks = 100;
//
//        TraditionalThreadService traditionalService = new TraditionalThreadService();
//        VirtualThreadService virtualService = new VirtualThreadService();
//
//        // =========================
//        // TRADICIONAL
//        // =========================
//        System.gc();
//        Thread.sleep(1000);
//
//        long startMemory = MetricsUtil.getUsedMemory();
//        long startCpu = MetricsUtil.getProcessCpuTime();
//        long startTime = System.currentTimeMillis();
//
//        traditionalService.executeTasks(numberOfTasks);
//
//        long endTime = System.currentTimeMillis();
//        long endCpu = MetricsUtil.getProcessCpuTime();
//        long endMemory = MetricsUtil.getUsedMemory();
//
//        MetricsUtil.printMetrics(
//                "Threads Tradicionais",
//                startTime, endTime,
//                startMemory, endMemory,
//                startCpu, endCpu
//        );
//
//        // =========================
//        // VIRTUAL
//        // =========================
//        System.gc();
//        Thread.sleep(1000);
//
//        startMemory = MetricsUtil.getUsedMemory();
//        startCpu = MetricsUtil.getProcessCpuTime();
//        startTime = System.currentTimeMillis();
//
//        virtualService.executeTasks(numberOfTasks);
//
//        endTime = System.currentTimeMillis();
//        endCpu = MetricsUtil.getProcessCpuTime();
//        endMemory = MetricsUtil.getUsedMemory();
//
//        MetricsUtil.printMetrics(
//                "Threads Virtuais",
//                startTime, endTime,
//                startMemory, endMemory,
//                startCpu, endCpu
//        );
//    }

    public static void main(String[] args) throws InterruptedException {
        TraditionalThreadService traditionalService = new TraditionalThreadService();
        VirtualThreadService virtualService = new VirtualThreadService();
        int dbTasks = 1000;

        System.out.println("\n==============================");
        System.out.println("SIMULAÇÃO DE BANCO (1000 USERS)");
        System.out.println("==============================");

// Tradicional
        System.gc();
        Thread.sleep(1000);

        long startMemory = MetricsUtil.getUsedMemory();
        long startCpu = MetricsUtil.getProcessCpuTime();
        long startTime = System.currentTimeMillis();

        long traditionalDbTime = traditionalService.executeDatabaseSimulation(dbTasks);

        long endTime = System.currentTimeMillis();
        long endCpu = MetricsUtil.getProcessCpuTime();
        long endMemory = MetricsUtil.getUsedMemory();

        MetricsUtil.printMetrics(
                "DB - Threads Tradicionais",
                startTime, endTime,
                startMemory, endMemory,
                startCpu, endCpu
        );

// Virtual
        System.gc();
        Thread.sleep(1000);

        startMemory = MetricsUtil.getUsedMemory();
        startCpu = MetricsUtil.getProcessCpuTime();
        startTime = System.currentTimeMillis();

        long virtualDbTime = virtualService.executeDatabaseSimulation(dbTasks);

        endTime = System.currentTimeMillis();
        endCpu = MetricsUtil.getProcessCpuTime();
        endMemory = MetricsUtil.getUsedMemory();

        MetricsUtil.printMetrics(
                "DB - Threads Virtuais",
                startTime, endTime,
                startMemory, endMemory,
                startCpu, endCpu
        );
    }
}
