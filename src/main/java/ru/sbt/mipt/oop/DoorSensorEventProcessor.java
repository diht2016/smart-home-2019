package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorSensorEventProcessor implements SensorEventProcessor {
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isDoorEvent(event)) return;
        
        smartHome.execute(new Action<Door>() {
            public boolean checkArgs(Class t) {
                return t == Door.class;
            }
            public void run(Door door) {
                if (door.getId().equals(event.getObjectId())) {
                    setDoorState(door.getRoom(), door, event.getType() == DOOR_OPEN);
                }
            }
        });
    }

    private static void setDoorState(Room room, Door door, boolean state) {
        door.setOpen(state);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was " + (state ? "opened" : "closed") + ".");
    }

    private static boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}