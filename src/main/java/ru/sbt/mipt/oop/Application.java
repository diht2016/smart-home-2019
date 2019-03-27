package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.coolcompany.smarthome.events.SensorEventsManager;

public class Application {

    private static SmartHomeLoader loader = new JsonFileSmartHomeLoader();
    private static SensorEventSource eventSource = new FakeSensorEventGenerator();
    
    public static void main(String... args) throws IOException {
        SmartHome smartHome = loader.loadSmartHome();
        processAllEvents(smartHome);
    }

    private static void processAllEvents(SmartHome smartHome) {
        Collection<SensorEventProcessor> processors = configureSensorEventProcessors();
        
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(inputEvent -> {
            SensorEvent event = SensorEventAdapter.from(inputEvent);
            if (event == null) {
                return;
            }
            
            System.out.println("Got event: " + event);
            
            for (SensorEventProcessor processor : processors) {
                processor.processEvent(smartHome, event);
            }
        });
        sensorEventsManager.start();
    }
    
    private static Collection<SensorEventProcessor> configureSensorEventProcessors() {
        Collection<SensorEventProcessor> list = new ArrayList<>();
        list.add(new AlarmSensorEventProcessor());
        list.add(new SensorEventAlarmDecorator(new LightSensorEventProcessor()));
        list.add(new SensorEventAlarmDecorator(new DoorSensorEventProcessor()));
        list.add(new SensorEventAlarmDecorator(new HallDoorSensorEventProcessor()));
        return list;
    }
}
