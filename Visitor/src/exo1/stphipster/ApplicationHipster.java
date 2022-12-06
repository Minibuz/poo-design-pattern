package exo1.stphipster;

import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class ApplicationHipster {
    public static void main(String[] args) {
        HashMap<Integer,Long> timers = new HashMap<>();
        var scan = new Scanner(System.in);
        while(scan.hasNextLine()){
            var line = scan.nextLine();
            if (line.equals("quit")){
                break;
            }
            Optional<STPCommand> answer = STPParser.parse(line);
            if (answer.isEmpty()){
                System.out.println("Unrecognized command");
                continue;
            }
            STPCommand cmd = answer.get();
            cmd.accept(timers);
        }
    }
}
