package exo1.lib;

public class HelloCmd implements STPCommand {

    @Override
    public void accept(STPCommandVisitor visitor) {
        visitor.visit(this);
    }
}
