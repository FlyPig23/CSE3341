import java.io.IOException;

class Cond {
    Cmpr cm;
    Cond co;
    int condType;

    void parse(Scanner S) throws IOException {
        if (S.currentToken() == Core.NEGATION) {
            condType = 2;   // !(<cond>)
            S.nextToken();
            Parser.expectedToken(Core.LPAREN);
            S.nextToken();
            co = new Cond();
            co.parse(S);
            Parser.expectedToken(Core.RPAREN);
            S.nextToken();
        } else {
            condType = 1;   // <cmpr>
            cm = new Cmpr();
            cm.parse(S);
            if (S.currentToken() == Core.OR) {
                condType = 3;   // <cmpr> or <cond>
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

    boolean execute() {
        boolean result = false;

        if (condType == 1) {
            result = cm.execute();
        } else if (condType == 2) {
            result = !co.execute();
        } else if (condType == 3) {
            result = cm.execute() || co.execute();
        }

        return result;
    }
}
