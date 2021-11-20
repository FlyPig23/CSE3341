import java.io.IOException;

public class FuncDecl {
    Id i;
    Formals f;
    StmtSeq ss;

    void parse(Scanner S) throws IOException {
        i = new Id();
        i.parse(S);
        Parser.expectedToken(Core.LPAREN);
        S.nextToken();
        Parser.expectedToken(Core.REF);
        S.nextToken();
        f = new Formals();
        f.parse(S);
        Parser.expectedToken(Core.RPAREN);
        S.nextToken();
        Parser.expectedToken(Core.BEGIN);
        S.nextToken();
        ss = new StmtSeq();
        ss.parse(S);
        Parser.expectedToken(Core.ENDFUNC);
        S.nextToken();
    }

    void semantic() {
        if (Parser.checkOverload(i.stringForm())) {
            System.out.println("ERROR: Function" + i + " has already been declared.");
            System.exit(1);
        } else {
            Parser.functionDefinition.put(i.stringForm(), this);
        }
    }

    void print(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        i.print();
        System.out.print("(ref ");
        f.print();
        System.out.println(") begin");
        ss.print(indent + 1);
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.println("endfunc");
    }

    void execute() {
        Executor.functionDefinition.put(i.stringForm(), this);
    }
}
