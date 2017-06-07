package ru.sbt.jmm.ExecutionManager;

public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
