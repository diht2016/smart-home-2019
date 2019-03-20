package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.AlarmState.*;

public class Alarm {
    private AlarmState state;
    private String code;

    public Alarm() {
        state = ALARM_INACTIVE;
    }

    public boolean isInactive() {
        return state == ALARM_INACTIVE;
    }

    public boolean isTriggered() {
        return state == ALARM_TRIGGERED;
    }

    public void activate(String input) {
        if (!isInactive()) {
            return;
        }
        if (input == null) {
            return;
        }
        
        state = ALARM_READY;
        code = input;
    }

    public void deactivate(String input) {
        if (isInactive()) {
            return;
        }
        
        if (code.equals(input)) {
            state = ALARM_INACTIVE;
        } else {
            trigger();
        }
    }

    public void trigger() {
        if (isInactive()) {
            return;
        }
        state = ALARM_TRIGGERED;
        System.out.println("Sending SMS");
    }
}
