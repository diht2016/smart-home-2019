package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallDoorSensorEventProcessor implements SensorEventProcessor {
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() != DOOR_CLOSED) return;
        
        smartHome.execute(new Action<Door, Room>() {
            public boolean checkArgs(Class t1, Class t2) {
                return t1 == Door.class && t2 == Room.class;
            }
            public void run(Door door, Room room) {
                // если мы получили событие о закрытии двери в холле - это значит, что была закрыта входная дверь.
                // в этом случае мы хотим автоматически выключить свет во всём доме (это же умный дом!)
                if (!door.getId().equals(event.getObjectId())) {
                    return;
                }
                if (!room.getName().equals("hall")) {
                    return;
                }

                smartHome.execute(new Action<Light, Room>() {
                    public boolean checkArgs(Class t1, Class t2) {
                        return t1 == Light.class && t2 == Room.class;
                    }
                    public void run(Light light, Room room) {
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