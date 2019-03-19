package ru.sbt.mipt.oop;

public interface Action<T> {
    public boolean checkArgs(Class arg);
    public void run(T arg);
}
