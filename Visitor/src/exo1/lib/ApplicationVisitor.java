package exo1.lib;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ApplicationVisitor implements STPCommandVisitor {

    private final HashMap<Integer,Long> timers;

    public ApplicationVisitor(HashMap<Integer,Long> timers) {
        this.timers = timers;
    }

    @Override
    public void visit(ElapsedTimeCmd cmd) {
        var currentTime =  System.currentTimeMillis();
        for(var timerId : cmd.getTimers()){
            var startTime = timers.get(timerId);
            if (startTime==null){
                System.out.println("Unknown timer "+timerId);
                continue;
            }
            System.out.println("Ellapsed time on timerId "+timerId+" : "+(currentTime-startTime)+"ms");
        }
    }

    @Override
    public void visit(HelloCmd cmd) {
        System.out.println("Hello the current date is "+ LocalDateTime.now());
    }

    @Override
    public void visit(StartTimerCmd cmd) {
        var timerId = cmd.getTimerId();
        if (timers.get(timerId)!=null){
            System.out.println("Timer "+timerId+" was already started");
            return;
        }
        var currentTime =  System.currentTimeMillis();
        timers.put(timerId,currentTime);
        System.out.println("Timer "+timerId+" started");
    }

    @Override
    public void visit(StopTimerCmd cmd) {
        var timerId = cmd.getTimerId();
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