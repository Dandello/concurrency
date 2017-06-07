package ru.sbt.jmm.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Task<Integer> task = new Task<>(() -> {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName());
            return 1;
        });
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(() -> task.get());
        executorService.submit(() -> task.get());
        executorService.submit(() -> task.get());
        executorService.shutdown();

    }
}
