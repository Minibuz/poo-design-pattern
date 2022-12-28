package exo2;

public class EvalExprVisitor implements ExprVisitor {

    @Override
    public int visitValue(Value value) {
        return value.eval();
    }

    @Override
    public int visitBinOp(BinOp binOp) {
        return binOp.eval();
    }
}
