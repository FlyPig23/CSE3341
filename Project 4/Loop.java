import java.io.IOException;
import java.util.HashMap;

class Loop {
    Cond c;
    StmtSeq ss;

    void parse(Scanner S) throws IOException {
        Parser.expectedToken(Core.WHILE);
        S.nextToken();
        c = new Cond();
        c.parse(S);
        Parser.expectedToken(Core.BEGIN);
        S.nextToken();
        ss = new StmtSeq();
        ss.parse(S);
        Parser.expectedToken(Core.ENDWHILE);
        S.nextToken();
    }

    void semantic() {
        c.semantic();
        Parser.scopeLayer.peek().push(new HashMap<String, Core>());
        ss.semantic();
        Parser.scopeLayer.peek().pop();
    }

    void print(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.print("while ");
        c.print();
        System.out.println(" begin");
        ss.print(indent + 1);
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.println("endwhile");
    }

    void execute() throws IOException {
        while (c.execute()) {
            Executor.stackSpace.peek().push(new HashMap<String, CoreVar>());
            ss.execute();
            Executor.stackSpace.peek().pop();
        }
    }
}
