package exo1.stphipster;

import java.util.HashMap;

public record StartTimerCmd(int timerId) implements STPCommand {


    @Override
    public void accept(HashMap<Integer, Long> timers) {
        var timerId = this.timerId();
        if (timers.get(timerId)!=null){
            System.out.println("Timer "+timerId+" was already started");
            return;
        }
        var currentTime =  System.currentTimeMillis();
        timers.put(timerId,currentTime);
        System.out.println("Timer "+timerId+" started");
    }
}
