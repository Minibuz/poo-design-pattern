package exo2;

import java.util.Iterator;
import java.util.regex.Pattern;

public class Calc {
    public static void main(String[] args) {
        Iterator<String> it = Pattern.compile(" ").splitAsStream("+ * 4 + 1 1 + 2 3").iterator();
        Expr expr = Expr.parseExpr(it);
        System.out.println(expr);
        System.out.println(expr.eval());
    }
}
