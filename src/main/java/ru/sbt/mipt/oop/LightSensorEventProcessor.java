package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightSensorEventProcessor {
    public static void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isLightEvent(event)) return;
        
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    setLightState(room, light, event.getType() == LIGHT_ON);
                }
            }
        }
    }

    private static void setLightState(Room room, Light light, boolean state) {
        light.setOn(state);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned " + (state ? "on" : "off") + ".");
    }

    private static boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}