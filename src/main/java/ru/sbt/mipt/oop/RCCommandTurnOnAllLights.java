package ru.sbt.mipt.oop;

public class RCCommandTurnOnAllLights extends RCCommand {
    public RCCommandTurnOnAllLights(SmartHome smartHome) {
        super(smartHome);
    }
    
    public void execute() {
        this.smartHome.execute(new Action<Light>() {
            public boolean checkArgs(Class t) {
                return t == Light.class;
            }
            public void run(Light light) {
                light.setOn(true);
            }
        });
    }
}
