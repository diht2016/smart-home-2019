package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorSensorEventProcessor {
    public static void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isDoorEvent(event)) return;
        
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    setDoorState(room, door, event.getType() == DOOR_OPEN);
                    
                    if (event.getType() == DOOR_CLOSED) {
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        if (room.getName().equals("hall")) {
                            smartHome.turnOffAllLights();
                        }
                    }
                }
            }
        }
    }

    private static void setDoorState(Room room, Door door, boolean state) {
        door.setOpen(state);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was " + (state ? "opened" : "closed") + ".");
    }

    private static boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}