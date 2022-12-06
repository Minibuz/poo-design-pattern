package exo1;

import exo1.lib.STPParser;
import exo1.lib.TreatmentVisitor;

public class Triviale {

    public static void main(String[] args) {
        var opt = STPParser.parse(args[0]);
        if(opt.isPresent()) {
            opt.get().accept(new TreatmentVisitor());
        } else {
            System.out.println("Pas compris");
        }
    }
}
