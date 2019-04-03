package ru.sbt.mipt.oop;

public class RCCommandCloseHallDoors extends RCCommand {
    public RCCommandCloseHallDoors(SmartHome smartHome) {
        super(smartHome);
    }
    
    public void execute() {
        this.smartHome.execute(new Action<Door>() {
            public boolean checkArgs(Class t) {
                return t == Door.class;
            }
            public void run(Door door) {
                if (door.getRoom().getName().equals("hall")) {
                    door.setOpen(false);
                }
            }
        });
    }
}
