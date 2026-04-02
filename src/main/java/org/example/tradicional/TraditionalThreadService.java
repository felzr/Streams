package org.example.tradicional;

import org.example.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TraditionalThreadService {

    public long executeTasks(int numberOfTasks) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        long start = System.currentTimeMillis();

        for (int i = 0; i < numberOfTasks; i++) {
            int taskId = i;
            executor.submit(() -> simulateIO(taskId));
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);

        long end = System.currentTimeMillis();

        return end - start;
    }

    private void simulateIO(int taskId) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public long executeDatabaseSimulation(int numberOfTasks) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        long start = System.currentTimeMillis();

        List<Future<User>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfTasks; i++) {
            int userId = i;

            futures.add(executor.submit(() -> simulateDatabaseCall(userId)));
        }

        // Aguarda todas as respostas (como faria num backend real)
        for (Future<User> future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);

        long end = System.currentTimeMillis();
        return end - start;
    }

    private User simulateDatabaseCall(int userId) {
        try {
            // Simula latência de banco (50ms a 150ms)
            Thread.sleep(50 + (int) (Math.random() * 100));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return new User(userId, "User_" + userId);
    }
}
