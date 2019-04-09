package ru.sbt.mipt.oop;

public class RCCommandTurnOnHallLights extends RCCommand {
    public RCCommandTurnOnHallLights(SmartHome smartHome) {
        super(smartHome);
    }
    
    public void execute() {
        this.smartHome.execute(new Action<Light>() {
            public boolean checkArgs(Class t) {
                return t == Light.class;
            }
            public void run(Light light) {
                if (light.getRoom().getName().equals("hall")) {
                    light.setOn(true);
                }
            }
        });
    }
}
