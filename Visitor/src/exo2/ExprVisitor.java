package exo2;

public interface ExprVisitor {

    int visitValue(Value value);
    int visitBinOp(BinOp binOp);
}
