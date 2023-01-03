package exo2;

public class ToStringVisitor implements ExprVisitor<String> {
    @Override
    public String visitValue(Value value) {
        return Integer.toString(value.getValue());
    }

    @Override
    public String visitBinOp(BinOp binOp) {
        return "(" + binOp.getLeft() + " " + binOp.getOpName() + " " + binOp.getRight() + ")";
    }
}
