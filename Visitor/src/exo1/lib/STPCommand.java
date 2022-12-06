package exo1.lib;

public interface STPCommand {
    void accept(STPCommandVisitor visitor);
}
