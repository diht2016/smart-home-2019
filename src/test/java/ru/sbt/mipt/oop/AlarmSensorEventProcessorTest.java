package ru.sbt.mipt.oop;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class AlarmSensorEventProcessorTest {
    @Test
    public void notTriggeredByDefault() {
        SensorEventProcessor processor = new AlarmSensorEventProcessor();
        
        Light light = new Light("1", false);
        Door door = new Door("1", false);
        Room room = new Room(Arrays.asList(light), Arrays.asList(door), "hall");
        SmartHome testHome = new SmartHome(Arrays.asList(room));
        
        Assert.assertFalse(testHome.getAlarm().isTriggered());
    }
    
    @Test
    public void openDoorTrigger() {
        SensorEventProcessor processor = new AlarmSensorEventProcessor();
        
        Light light = new Light("1", false);
        Door door = new Door("1", false);
        Room room = new Room(Arrays.asList(light), Arrays.asList(door), "hall");
        SmartHome testHome = new SmartHome(Arrays.asList(room));
        
        SensorEvent event = new SensorEvent(DOOR_OPEN, "1");
        SensorEvent alarmEvent = new AlarmSensorEvent(ALARM_ACTIVATE, "some_code");
        
        processor.processEvent(testHome, event);
        Assert.assertFalse(testHome.getAlarm().isTriggered());
        processor.processEvent(testHome, alarmEvent);
        Assert.assertFalse(testHome.getAlarm().isTriggered());
        processor.processEvent(testHome, event);
        Assert.assertTrue(testHome.getAlarm().isTriggered());
    }
    
    @Test
    public void wrongCodeTrigger() {
        SensorEventProcessor processor = new AlarmSensorEventProcessor();
        
        Light light = new Light("1", false);
        Door door = new Door("1", false);
        Room room = new Room(Arrays.asList(light), Arrays.asList(door), "hall");
        SmartHome testHome = new SmartHome(Arrays.asList(room));
        
        SensorEvent alarmEventOn = new AlarmSensorEvent(ALARM_ACTIVATE, "some_code");
        SensorEvent alarmEventOff = new AlarmSensorEvent(ALARM_DEACTIVATE, "wrong_code");
        
        processor.processEvent(testHome, alarmEventOn);
        Assert.assertFalse(testHome.getAlarm().isTriggered());
        processor.processEvent(testHome, alarmEventOff);
        Assert.assertTrue(testHome.getAlarm().isTriggered());
    }
}