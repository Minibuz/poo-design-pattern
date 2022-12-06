package exo1.lib;

public interface STPCommandVisitor {
    void visit(ElapsedTimeCmd cmd);
    void visit(HelloCmd cmd);
    void visit(StartTimerCmd cmd);
    void visit(StopTimerCmd cmd);
}
