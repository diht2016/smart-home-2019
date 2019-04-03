package ru.sbt.mipt.oop;

public abstract class RCCommand {
    protected SmartHome smartHome;

    public RCCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
    
    public abstract void execute();
}
