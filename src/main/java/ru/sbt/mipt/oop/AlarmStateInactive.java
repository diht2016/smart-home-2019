package ru.sbt.mipt.oop;

public class AlarmStateInactive extends AlarmState {
    public AlarmStateInactive(Alarm alarm) {
        super(alarm);
    }
    
    public AlarmState activate(String input) {
        if (input == null) {
            return null;
        }
        
        return new AlarmStateActive(alarm, input);
    }
}
