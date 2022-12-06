package exo1.stphipster;

import java.util.HashMap;

public sealed interface STPCommand permits HelloCmd, ElapsedTimeCmd, StartTimerCmd, StopTimerCmd {

    void accept(HashMap<Integer,Long> timers);

    default void visit(HashMap<Integer,Long> timers) {
        switch (this) {
            case HelloCmd helloCmd -> helloCmd.accept(timers);
            case ElapsedTimeCmd elapsedTimeCmd -> elapsedTimeCmd.accept(timers);
            case StartTimerCmd startTimerCmd -> startTimerCmd.accept(timers);
            case StopTimerCmd stopTimerCmd -> stopTimerCmd.accept(timers);
        }
    }
}
