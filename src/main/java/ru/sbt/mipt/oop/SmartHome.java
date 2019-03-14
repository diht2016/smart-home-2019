package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }
    
    public void execute(Action action) {
        for (Room room : rooms) {
            room.execute(action);
        }
    }

    public void turnOffAllLights() {
        execute(new Action<Light, Room>() {
            public boolean checkArgs(Class t1, Class t2) {
                return t1 == Light.class && t2 == Room.class;
            }
            public void run(Light light, Room room) {
                light.turnOff();
            }
        });
    }
}
