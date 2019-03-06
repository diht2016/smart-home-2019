package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHome smartHome = JsonFileSmartHomeLoader.loadSmartHome();
        processAllEvents(smartHome);
    }

    private static void processAllEvents(SmartHome smartHome) {
        SensorEvent event = null;
        while (true) {
            event = FakeSensorEventGenerator.getNextSensorEvent();
            if (event == null) {
                break;
            }
            System.out.println("Got event: " + event);
            
            LightSensorEventProcessor.processEvent(smartHome, event);
            DoorSensorEventProcessor.processEvent(smartHome, event);
        }
    }
}
