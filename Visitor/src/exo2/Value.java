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
    public int eval() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

}
