package exo1.lib;

public class TreatmentVisitor implements STPCommandVisitor{
    @Override
    public void visit(ElapsedTimeCmd cmd) {
        System.out.println("Non implémenté");
    }

    @Override
    public void visit(HelloCmd cmd) {
        System.out.println("Au revoir");
    }

    @Override
    public void visit(StartTimerCmd cmd) {
        System.out.println("Non implémenté");
    }

    @Override
    public void visit(StopTimerCmd cmd) {
        System.out.println("Non implémenté");
    }
}
