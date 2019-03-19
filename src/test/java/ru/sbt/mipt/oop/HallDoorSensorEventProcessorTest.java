package ru.sbt.mipt.oop;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class HallDoorSensorEventProcessorTest {
    @Test
    public void openHallDoor() {
        SensorEventProcessor processor = new HallDoorSensorEventProcessor();
        
        Light light = new Light("1", true);
        Door door = new Door("1", false);
        Room room = new Room(Arrays.asList(light), Arrays.asList(door), "hall");
        SmartHome testHome = new SmartHome(Arrays.asList(room));
        
        SensorEvent event = new SensorEvent(DOOR_OPEN, "1");
        processor.processEvent(testHome, event);
        
        // nothing happens, light still on
        Assert.assertTrue(light.isOn());
    }
    
    @Test
    public void closeHallDoor() {
        SensorEventProcessor processor = new HallDoorSensorEventProcessor();
        
        Light light = new Light("1", true);
        Door door = new Door("1", true);
        Room room = new Room(Arrays.asList(light), Arrays.asList(door), "hall");
        SmartHome testHome = new SmartHome(Arrays.asList(room));
        
        SensorEvent event = new SensorEvent(DOOR_CLOSED, "1");
        processor.processEvent(testHome, event);
        
        // lights should turn off
        Assert.assertFalse(light.isOn());
    }
}