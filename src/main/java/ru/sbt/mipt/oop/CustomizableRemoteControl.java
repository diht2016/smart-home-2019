package ru.sbt.mipt.oop;

import java.util.Map;
import java.util.TreeMap;

import rc.RemoteControl;

public class CustomizableRemoteControl implements RemoteControl {
    private Map<String, RCCommand> commands;
    
    public CustomizableRemoteControl() {
        commands = new TreeMap<>();
    }
    
    public void onButtonPressed(String buttonCode, String rcId) {
        if (commands.containsKey(buttonCode)) {
            commands.get(buttonCode).execute();
        }
    }
    
    public void setCommand(String buttonCode, RCCommand command) {
        commands.put(buttonCode, command);
    }
}
