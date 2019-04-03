package ru.sbt.mipt.oop;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

import rc.RemoteControl;
import rc.RemoteControlRegistry;

public class CustomizableRemoteControlTest {
    @Test
    public void turnOnLight() {
        Light light = new Light("1", false);
        Door door = new Door("1", false);
        Room room = new Room(Arrays.asList(light), Arrays.asList(door), "hall");
        SmartHome testHome = new SmartHome(Arrays.asList(room));
        
        CustomizableRemoteControl rc = new CustomizableRemoteControl();
        String rcId = "some id";
        RCCommand command = new RCCommandTurnOnAllLights(testHome);
        rc.setCommand("A", command);
        
        rc.onButtonPressed("A", rcId);
        
        Assert.assertTrue(light.isOn());
    }
    
    @Test
    public void turnOnLightWrongButton() {
        Light light = new Light("1", false);
        Door door = new Door("1", false);
        Room room = new Room(Arrays.asList(light), Arrays.asList(door), "hall");
        SmartHome testHome = new SmartHome(Arrays.asList(room));
        
        CustomizableRemoteControl rc = new CustomizableRemoteControl();
        String rcId = "some id";
        RCCommand command = new RCCommandTurnOnAllLights(testHome);
        rc.setCommand("A", command);
        
        rc.onButtonPressed("B", rcId);
        
        Assert.assertFalse(light.isOn());
    }
    
    @Test
    public void closeHallDoor() {
        Light light = new Light("1", false);
        Door door = new Door("1", true);
        Room room = new Room(Arrays.asList(light), Arrays.asList(door), "hall");
        SmartHome testHome = new SmartHome(Arrays.asList(room));
        
        CustomizableRemoteControl rc = new CustomizableRemoteControl();
        String rcId = "some id";
        RCCommand command = new RCCommandCloseHallDoors(testHome);
        rc.setCommand("A", command);
        
        rc.onButtonPressed("A", rcId);
        
        Assert.assertFalse(door.isOpen());
    }
    
    @Test
    public void triggerAlarm() {
        Light light = new Light("1", false);
        Door door = new Door("1", true);
        Room room = new Room(Arrays.asList(light), Arrays.asList(door), "hall");
        SmartHome testHome = new SmartHome(Arrays.asList(room));
        
        CustomizableRemoteControl rc = new CustomizableRemoteControl();
        String rcId = "some id";
        RCCommand command = new RCCommandTriggerAlarm(testHome, "some code");
        rc.setCommand("A", command);
        
        rc.onButtonPressed("A", rcId);
        
        Assert.assertTrue(testHome.getAlarm().isTriggered());
    }
}