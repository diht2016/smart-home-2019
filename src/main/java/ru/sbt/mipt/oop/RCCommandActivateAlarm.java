package ru.sbt.mipt.oop;

public class RCCommandActivateAlarm extends RCCommand {
    private String code;
    
    public RCCommandActivateAlarm(SmartHome smartHome, String code) {
        super(smartHome);
        this.code = code;
    }
    
    public void execute() {
        Alarm alarm = this.smartHome.getAlarm();
        alarm.activate(code);
    }
}
