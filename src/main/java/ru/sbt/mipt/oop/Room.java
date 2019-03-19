package ru.sbt.mipt.oop;

import java.util.Collection;

public class Room {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        for (Light light : lights) {
            light.setRoom(this);
        }
        for (Door door : doors) {
            door.setRoom(this);
        }
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    public void execute(Action action) {
        if (action.checkArgs(Light.class, Room.class)) {
            for (Light light : lights) {
                action.run(light, this);
            }
        }
        if (action.checkArgs(Door.class, Room.class)) {
            for (Door door : doors) {
                action.run(door, this);
            }
        }
    }
}
