import java.io.IOException;
import java.util.HashMap;

class If {
    Cond c;
    StmtSeq ssThen;
    StmtSeq ssElse;

    void parse(Scanner S) throws IOException {
        Parser.expectedToken(Core.IF);
        S.nextToken();
        c = new Cond();
        c.parse(S);
        Parser.expectedToken(Core.THEN);
        S.nextToken();
        ssThen = new StmtSeq();
        ssThen.parse(S);
        if (S.currentToken() == Core.ELSE) {
            S.nextToken();
            ssElse = new StmtSeq();
            ssElse.parse(S);
        }
        Parser.expectedToken(Core.ENDIF);
        S.nextToken();
    }

    void semantic() {
        c.semantic();
        Parser.scopeLayer.push(new HashMap<String, Core>());
        ssThen.semantic();
        Parser.scopeLayer.pop();
        if (ssElse != null) {
            Parser.scopeLayer.push(new HashMap<String, Core>());
            ssElse.semantic();
            Parser.scopeLayer.pop();
        }
    }

    void print(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.print("if ");
        c.print();
        System.out.println(" then");
        ssThen.print(indent + 1);
        if (ssElse != null) {
            for (int i = 0; i < indent; i++) {
                System.out.print("\t");
            }
            System.out.println("else");
            ssElse.print(indent + 1);
        }
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.println("endif");
    }
}
