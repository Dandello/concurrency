package ru.sbt.jmm.Task;

import java.util.concurrent.Callable;

public class Task<T> {
    private final Callable<? extends T> callable;
    private volatile T result;
    private volatile boolean exceptionFlag = false;
    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }
    public T get() throws Exception {
        if(result == null)
            synchronized (this) {
                if(exceptionFlag) {
                    throw new TaskException("Task execution error");
                }
                if(result == null)
                    try {
                        result = callable.call();
                    } catch (Exception e) {
                        exceptionFlag = true;
                        throw new TaskException("Task execution error");
                    }
            }
        return result;
    }
}
