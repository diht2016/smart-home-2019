package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;

public class AlarmSensorEventProcessor implements SensorEventProcessor {
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        Alarm alarm = smartHome.getAlarm();
        if (isAlarmEvent(event)) {
            String code = ((AlarmSensorEvent) event).getCode();
            if (event.getType() == ALARM_ACTIVATE) {
                alarm.activate(code);
            } else {
                alarm.deactivate(code);
            }
        } else {
            alarm.trigger();
        }
    }

    private static boolean isAlarmEvent(SensorEvent event) {
        return (event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE);
    }
}