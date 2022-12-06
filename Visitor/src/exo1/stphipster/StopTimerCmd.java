package exo1.stphipster;

import java.util.HashMap;

public record StopTimerCmd(int timerId) implements STPCommand {


    @Override
    public void accept(HashMap<Integer, Long> timers) {
        var timerId = this.timerId();
        var startTime = timers.get(timerId);
        if (startTime==null){
            System.out.println("Timer "+timerId+" was never started");
            return;
        }
        var currentTime =  System.currentTimeMillis();
        System.out.println("Timer "+timerId+" was stopped after running for "+(currentTime-startTime)+"ms");
        timers.put(timerId,null);
    }
}
