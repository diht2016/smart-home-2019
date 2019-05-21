package ru.sbt.mipt.oop;

import java.io.IOException;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) throws IOException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
        sensorEventsManager.start();
    }
}
