package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.coolcompany.smarthome.events.SensorEventsManager;
import rc.RemoteControl;
import rc.RemoteControlRegistry;

public class Application {

    private static SmartHomeLoader loader = new JsonFileSmartHomeLoader();
    private static SensorEventSource eventSource = new FakeSensorEventGenerator();
    
    public static void main(String... args) throws IOException {
        SmartHome smartHome = loader.loadSmartHome();
        configureRemoteControl(smartHome);
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
    
    private static void configureRemoteControl(SmartHome smartHome) {
        CustomizableRemoteControl rc = new CustomizableRemoteControl();
        RemoteControlRegistry registry = new RemoteControlRegistry();
        String rcId = "rc1";
        rc.setCommand("1", new RCCommandTurnOnAllLights(smartHome));
        rc.setCommand("2", new RCCommandTurnOffAllLights(smartHome));
        rc.setCommand("3", new RCCommandTurnOnHallLights(smartHome));
        rc.setCommand("A", new RCCommandCloseHallDoors(smartHome));
        rc.setCommand("B", new RCCommandActivateAlarm(smartHome, "rc code"));
        rc.setCommand("C", new RCCommandTriggerAlarm(smartHome, "rc code"));
        
        registry.registerRemoteControl(rc, rcId);
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
