import java.io.IOException;

class Factor {
    Id i;
    Expr e;
    int constant;
    int factType;

    void parse(Scanner S) throws IOException {
        if (S.currentToken() == Core.ID) {
            factType = 1;   // factor is id
            i = new Id();
            i.parse(S);
        } else if (S.currentToken() == Core.CONST) {
            factType = 2;   // factor is const
            constant = S.getCONST();
            S.nextToken();
        } else if (S.currentToken() == Core.LPAREN) {
            factType = 3;   // factor is (<expr>)
            S.nextToken();
            e = new Expr();
            e.parse(S);
            Parser.expectedToken(Core.RPAREN);
            S.nextToken();
        } else {
            System.out.println("ERROR: (Factor) Inappropriate toke found: " + S.currentToken());
            System.exit(1);
        }
    }

    void semantic() {
        if (factType == 1) {
            i.declareSemantic();
        } else if (factType == 3) {
            e.semantic();
        }
    }

    void print() {
        if (factType == 1) {
            i.print();
        } else if (factType == 2) {
            System.out.print(constant);
        } else if (factType == 3) {
            System.out.print("(");
            e.print();
            System.out.print(")");
        }
    }

    int execute() {
        int result = 0;

        if (factType == 1) {
            if (i != null) {
                try {
                    result = i.idValue();
                } catch (NullPointerException npe) {
                    System.out.println("ERROR: " + i.stringForm() + "currently has a null value");
                    System.exit(1);
                }
            }
        } else if (factType == 2) {
            result = constant;
        } else if (factType == 3) {
            result = e.execute();
        }
        
        return result;
    }
}
