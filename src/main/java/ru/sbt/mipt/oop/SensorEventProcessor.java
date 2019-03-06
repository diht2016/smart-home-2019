package ru.sbt.mipt.oop;

public interface SensorEventProcessor {
    public abstract void processEvent(SmartHome smartHome, SensorEvent event);
}
