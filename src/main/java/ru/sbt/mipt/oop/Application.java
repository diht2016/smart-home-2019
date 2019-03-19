package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Application {

    private static SmartHomeLoader loader = new JsonFileSmartHomeLoader();
    private static SensorEventSource eventSource = new FakeSensorEventGenerator();
    
    public static void main(String... args) throws IOException {
        SmartHome smartHome = loader.loadSmartHome();
        processAllEvents(smartHome);
    }

    private static void processAllEvents(SmartHome smartHome) {
        SensorEvent event = null;
        Collection<SensorEventProcessor> processors = configureSensorEventProcessors();
        while (true) {
            event = eventSource.getNextSensorEvent();
            if (event == null) {
                break;
            }
            System.out.println("Got event: " + event);
            
            for (SensorEventProcessor processor : processors) {
                processor.processEvent(smartHome, event);
            }
        }
    }
    
    private static Collection<SensorEventProcessor> configureSensorEventProcessors() {
        Collection<SensorEventProcessor> list = new ArrayList<>();
        list.add(new LightSensorEventProcessor());
        list.add(new DoorSensorEventProcessor());
        list.add(new HallDoorSensorEventProcessor());
        return list;
    }
}
