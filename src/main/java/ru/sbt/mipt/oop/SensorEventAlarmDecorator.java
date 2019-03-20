package ru.sbt.mipt.oop;

public class SensorEventAlarmDecorator implements SensorEventProcessor {
    private SensorEventProcessor processor;
    
    public SensorEventAlarmDecorator(SensorEventProcessor processor) {
        this.processor = processor;
    }
    
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!smartHome.getAlarm().isTriggered()) {
            processor.processEvent(smartHome, event);
        }
    }
}