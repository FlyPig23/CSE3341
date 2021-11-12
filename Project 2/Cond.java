import java.io.IOException;

class Cond {
    Cmpr cm;
    Cond co;
    int condType;

    void parse(Scanner S) throws IOException {
        if (S.currentToken() == Core.NEGATION) {
            condType = 2;
            S.nextToken();
            Parser.expectedToken(Core.LPAREN);
            S.nextToken();
            co = new Cond();
            co.parse(S);
            Parser.expectedToken(Core.RPAREN);
            S.nextToken();
        } else {
            condType = 1;
            cm = new Cmpr();
            cm.parse(S);
            if (S.currentToken() == Core.OR) {
                condType = 3;
                S.nextToken();
                co = new Cond();
                co.parse(S);
            }
        }
    }

    void semantic() {
        if (condType == 1) {
            cm.semantic();
        } else if (condType == 2) {
            co.semantic();
        } else if (condType == 3) {
            cm.semantic();
            co.semantic();
        }
    }

    void print() {
        if (condType == 1) {
            cm.print();
        } else if (condType == 2) {
            System.out.print("!(");
            co.print();
            System.out.print(")");
        } else if (condType == 3) {
            cm.print();
            System.out.print(" or ");
            co.print();
        }
    }
}
