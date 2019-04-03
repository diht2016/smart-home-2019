package ru.sbt.mipt.oop;

public class RCCommandTriggerAlarm extends RCCommand {
    private String code;
    
    public RCCommandTriggerAlarm(SmartHome smartHome, String code) {
        super(smartHome);
        this.code = code;
    }
    
    public void execute() {
        Alarm alarm = this.smartHome.getAlarm();
        alarm.activate(code);
        alarm.trigger();
    }
}
