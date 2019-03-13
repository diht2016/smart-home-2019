package ru.sbt.mipt.oop;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;

public class LightSensorEventProcessorTest {
    @Test
    public void onLight() {
        SensorEventProcessor processor = new LightSensorEventProcessor();
        
        Light light = new Light("1", false);
        Door door = new Door(false, "1");
        Room room = new Room(Arrays.asList(light), Arrays.asList(door), "hall");
        SmartHome testHome = new SmartHome(Arrays.asList(room));
        
        SensorEvent event = new SensorEvent(LIGHT_ON, "1");
        processor.processEvent(testHome, event);
        
        Assert.assertTrue(light.isOn());
    }
    
    @Test
    public void offLight() {
        SensorEventProcessor processor = new LightSensorEventProcessor();
        
        Light light = new Light("1", true);
        Door door = new Door(false, "1");
        Room room = new Room(Arrays.asList(light), Arrays.asList(door), "hall");
        SmartHome testHome = new SmartHome(Arrays.asList(room));
        
        SensorEvent event = new SensorEvent(LIGHT_OFF, "1");
        processor.processEvent(testHome, event);
        
        Assert.assertTrue(!light.isOn());
    }
}