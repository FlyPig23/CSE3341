import java.io.IOException;

class Cmpr {
    Expr eLeft;
    Expr eRight;
    int cmprType;

    void parse(Scanner S) throws IOException {
        eLeft = new Expr();
        eLeft.parse(S);
        if (S.currentToken() == Core.EQUAL) {
            cmprType = 1;
        } else if (S.currentToken() == Core.LESS) {
            cmprType = 2;
        } else if (S.currentToken() == Core.LESSEQUAL) {
            cmprType = 3;
        }
        S.nextToken();
        eRight = new Expr();
        eRight.parse(S);
    }

    void semantic() {
        eLeft.semantic();
        eRight.semantic();
    }

    void print() {
        eLeft.print();
        if (cmprType == 1) {
            System.out.print("==");
        } else if (cmprType == 2) {
            System.out.print("<");
        } else if (cmprType == 3) {
            System.out.print("<=");
        }
        eRight.print();
    }
}
