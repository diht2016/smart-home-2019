package ru.sbt.mipt.oop;

public class AlarmStateActive extends AlarmState {
    private String code;
    
    public AlarmStateActive(Alarm alarm, String code) {
        super(alarm);
        this.code = code;
    }
    
    public AlarmState deactivate(String input) {
        if (code.equals(input)) {
            return new AlarmStateInactive(alarm);
        } else {
            return trigger();
        }
    }
    
    public AlarmState trigger() {
        System.out.println("Sending SMS");
        return new AlarmStateActiveTriggered(alarm, code);
    }
}
