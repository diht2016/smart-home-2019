package ru.sbt.mipt.oop;

public class AlarmSensorEvent extends SensorEvent {
    private final String code;

    public AlarmSensorEvent(SensorEventType type, String code) {
        super(type, null);
        this.code = code;
    }

    public AlarmSensorEvent(SensorEventType type, String code, String objectId) {
        super(type, objectId);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
