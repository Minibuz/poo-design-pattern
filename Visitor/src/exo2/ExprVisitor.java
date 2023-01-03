package exo2;

public interface ExprVisitor<E> {

    E visitValue(Value value);
    E visitBinOp(BinOp binOp);
}
