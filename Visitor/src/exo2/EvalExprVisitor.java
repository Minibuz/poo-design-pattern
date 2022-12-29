package exo2;

public class EvalExprVisitor implements ExprVisitor {

    @Override
    public int visitValue(Value value) {
        return value.getValue();
    }

    @Override
    public int visitBinOp(BinOp binOp) {
        return binOp.getOperator().applyAsInt(binOp.getLeft().accept(this), binOp.getRight().accept(this));
    }
}
