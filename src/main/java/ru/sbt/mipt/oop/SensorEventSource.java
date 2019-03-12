package ru.sbt.mipt.oop;

import java.io.IOException;

public interface SensorEventSource {
    SensorEvent getNextSensorEvent();
}