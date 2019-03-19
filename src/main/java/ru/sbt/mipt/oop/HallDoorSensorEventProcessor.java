package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallDoorSensorEventProcessor implements SensorEventProcessor {
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() != DOOR_CLOSED) return;
        
        smartHome.execute(new Action<Door>() {
            public boolean checkArgs(Class t) {
                return t == Door.class;
            }
            public void run(Door door) {
                // если мы получили событие о закрытии двери в холле - это значит, что была закрыта входная дверь.
                // в этом случае мы хотим автоматически выключить свет во всём доме (это же умный дом!)
                if (!door.getId().equals(event.getObjectId())) {
                    return;
                }
                if (!door.getRoom().getName().equals("hall")) {
                    return;
                }

                smartHome.execute(new Action<Light>() {
                    public boolean checkArgs(Class t) {
                        return t == Light.class;
                    }
                    public void run(Light light) {
                        turnOffLight(light);
                    }
                });
            }
        });
    }
    
    public void turnOffLight(Light light) {
        light.setOn(false);
        SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
        SensorCommandExecutor.executeCommand(command);
    }
}