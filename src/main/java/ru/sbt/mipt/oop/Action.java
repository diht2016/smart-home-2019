package ru.sbt.mipt.oop;

public interface Action<T1, T2> {
    public boolean checkArgs(Class arg1, Class arg2);
    public void run(T1 arg1, T2 arg2);
}
