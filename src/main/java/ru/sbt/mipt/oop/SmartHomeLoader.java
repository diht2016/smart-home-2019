package ru.sbt.mipt.oop;

import java.io.IOException;

public interface SmartHomeLoader {
    public abstract SmartHome loadSmartHome() throws IOException;
}