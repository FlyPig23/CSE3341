import java.io.IOException;

class Expr {
    Term t;
    Expr e;
    int exprType;

    void parse(Scanner S) throws IOException {
        t = new Term();
        t.parse(S);
        if (S.currentToken() == Core.ADD) {
            exprType = 1;   // <term> + <expr>
            S.nextToken();
            e = new Expr();
            e.parse(S);
        } else if (S.currentToken() == Core.SUB) {
            exprType = 2;   // <term> - <expr>
            S.nextToken();
            e = new Expr();
            e.parse(S);
        }
    }

    void semantic() {
        t.semantic();
        if (e != null) {
            e.semantic();
        }
    }

    void print() {
        t.print();
        if (exprType == 1) {
            System.out.print("+");
            e.print();
        } else if (exprType == 2) {
            System.out.print("-");
            e.print();
        }
    }

    int execute() {
        int result = t.execute();

        if (exprType == 1) {
            result = result + e.execute();
        } else if (exprType == 2) {
            result = result - e.execute();
        }

        return result;
    }
}
