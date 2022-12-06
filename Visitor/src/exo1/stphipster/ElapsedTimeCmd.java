package exo1.stphipster;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public record ElapsedTimeCmd(List<Integer> timers) implements STPCommand {

    public ElapsedTimeCmd(List<Integer> timers) {
        Objects.requireNonNull(timers);
        this.timers = List.copyOf(timers);
    }


    @Override
    public void accept(HashMap<Integer, Long> timers) {
        var currentTime =  System.currentTimeMillis();
        for(var timerId : this.timers()){
            var startTime = timers.get(timerId);
            if (startTime==null){
                System.out.println("Unknown timer "+timerId);
                continue;
            }
            System.out.println("Ellapsed time on timerId "+timerId+" : "+(currentTime-startTime)+"ms");
        }
    }
}
