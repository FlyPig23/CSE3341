import java.io.IOException;

class Assign {
    Id iLeft;
    Id iRight;
    Expr e;
    int declareType;

    void parse(Scanner S) throws IOException {
        iLeft = new Id();
        iLeft.parse(S);
        Parser.expectedToken(Core.ASSIGN);
        S.nextToken();
        if (S.currentToken() == Core.ID || S.currentToken() == Core.CONST || S.currentToken() == Core.LPAREN) {
            declareType = 1;    // id = <expr>
            e = new Expr();
            e.parse(S);
        } else if (S.currentToken() == Core.NEW) {
            declareType = 2;    // id = new
            S.nextToken();
        } else if (S.currentToken() == Core.REF) {
            declareType = 3;    // id = red id
            S.nextToken();
            iRight = new Id();
            iRight.parse(S);
        } else {
            System.out.println("ERROR: (Assign) Inappropriate toke found: " + S.currentToken());
            System.exit(1);
        }
        Parser.expectedToken(Core.SEMICOLON);
        S.nextToken();
    }

    void semantic() {
        iLeft.declareSemantic();
        if (declareType == 1) {
            e.semantic();
        } else if (declareType == 2) {
            iLeft.typeSemantic(Core.REF);
        } else if (declareType == 3) {
            iLeft.typeSemantic(Core.REF);
            iRight.declareSemantic();
            iRight.typeSemantic(Core.REF);
        }
    }

    void print(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        iLeft.print();
        System.out.print("=");
        if (declareType == 1) {
            e.print();
        } else if (declareType == 2) {
            System.out.print("new");
        } else if (declareType == 3) {
            System.out.print("ref ");
            iRight.print();
        }
        System.out.println(";");
    }

    void execute() {
        if (declareType == 1) {
            iLeft.expressionAssign(e.execute());
        } else if (declareType == 2) {
            iLeft.allocateToHeap();
        } else if (declareType == 3) {
            iLeft.referenceAssign(iRight);
        }
    }
}
