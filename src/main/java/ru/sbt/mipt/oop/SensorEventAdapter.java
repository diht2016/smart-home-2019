package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;

public class SensorEventAdapter {
    public static SensorEvent from(CCSensorEvent event) {
        SensorEventType type;
        switch (event.getEventType()) {
            case "LightIsOn":
                type = SensorEventType.LIGHT_ON;
            break;
            case "LightIsOff":
                type = SensorEventType.LIGHT_OFF;
            break;
            case "DoorIsOpen":
                type = SensorEventType.DOOR_OPEN;
            break;
            case "DoorIsClosed":
                type = SensorEventType.DOOR_CLOSED;
            break;
            default: return null;
        }
        return new SensorEvent(type, event.getObjectId());
    }
}
