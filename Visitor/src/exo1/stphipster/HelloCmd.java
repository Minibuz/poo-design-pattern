package exo1.stphipster;

import java.time.LocalDateTime;
import java.util.HashMap;

public record HelloCmd() implements STPCommand {


    @Override
    public void accept(HashMap<Integer, Long> timers) {
        System.out.println("Hello the current date is "+ LocalDateTime.now());
    }
}
