package exo2;

public class Value implements Expr {
    private final int value;

    public Value(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public <E> E accept(ExprVisitor<E> visitor) {
        return visitor.visitValue(this);
    }
}
