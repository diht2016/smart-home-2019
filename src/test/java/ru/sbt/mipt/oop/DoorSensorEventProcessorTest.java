package ru.sbt.mipt.oop;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorSensorEventProcessorTest {
    @Test
    public void openDoor() {
        SensorEventProcessor processor = new DoorSensorEventProcessor();
        
        Light light = new Light("1", false);
        Door door = new Door(false, "1");
        Room room = new Room(Arrays.asList(light), Arrays.asList(door), "hall");
        SmartHome testHome = new SmartHome(Arrays.asList(room));
        
        SensorEvent event = new SensorEvent(DOOR_OPEN, "1");
        processor.processEvent(testHome, event);
        
        Assert.assertTrue(door.isOpen());
    }
    
    @Test
    public void closeDoor() {
        SensorEventProcessor processor = new DoorSensorEventProcessor();
        
        Light light = new Light("1", false);
        Door door = new Door(true, "1");
        Room room = new Room(Arrays.asList(light), Arrays.asList(door), "hall");
        SmartHome testHome = new SmartHome(Arrays.asList(room));
        
        SensorEvent event = new SensorEvent(DOOR_CLOSED, "1");
        processor.processEvent(testHome, event);
        
        Assert.assertTrue(!door.isOpen());
    }
}