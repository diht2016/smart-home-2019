package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.AlarmState.*;

public class Alarm {
    private AlarmState state;

    public Alarm() {
        state = new AlarmStateInactive(this);
    }

    public boolean isInactive() {
        return state instanceof AlarmStateInactive;
    }

    public boolean isTriggered() {
        return state instanceof AlarmStateActiveTriggered;
    }

    public void activate(String input) {
        setState(state.activate(input));
    }

    public void deactivate(String input) {
        setState(state.deactivate(input));
    }

    public void trigger() {
        setState(state.trigger());
    }
    
    private void setState(AlarmState state) {
        if (state != null) {
            this.state = state;
        }
    }
}
