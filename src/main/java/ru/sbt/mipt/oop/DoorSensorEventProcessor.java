package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorSensorEventProcessor implements SensorEventProcessor {
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isDoorEvent(event)) return;
        
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    setDoorState(room, door, event.getType() == DOOR_OPEN);
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