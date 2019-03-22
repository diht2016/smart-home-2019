package ru.sbt.mipt.oop;

public abstract class AlarmState {
    protected Alarm alarm;
    
    public AlarmState(Alarm alarm) {
        this.alarm = alarm;
    }
    
    public AlarmState activate(String input) {return null;}
    public AlarmState deactivate(String input) {return null;}
    public AlarmState trigger() {return null;}
}
