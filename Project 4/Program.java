import java.io.IOException;
import java.util.*;

class Program {
    DeclSeq ds;
    StmtSeq ss;

    void parse(Scanner S) throws IOException {
        Parser.expectedToken(Core.PROGRAM);
        S.nextToken();
        if (S.currentToken() != Core.BEGIN) {
            ds = new DeclSeq();
            ds.parse(S);
        }
        Parser.expectedToken(Core.BEGIN);
        S.nextToken();
        ss = new StmtSeq();
        ss.parse(S);
        Parser.expectedToken(Core.END);
        S.nextToken();
        Parser.expectedToken(Core.EOF);
    }

    void semantic() {
        Parser.scopeLayer = new Stack<Stack<HashMap<String, Core>>>();
        Parser.functionDefinition = new HashMap<String, FuncDecl>();

        Parser.scopeLayer.push(new Stack<HashMap<String, Core>>());

        Parser.scopeLayer.peek().push(new HashMap<String, Core>());
        if (ds != null) {
            ds.semantic();
        }
        Parser.scopeLayer.peek().push(new HashMap<String, Core>());
        ss.semantic();

        Parser.scopeLayer.pop();
    }

    void print() {
        System.out.println("program");
        if (ds != null) {
            ds.print(1);
        }
        System.out.println("begin");
        ss.print(1);
        System.out.println("end");
    }

    void execute() throws IOException {
        Executor.initialize();
        if (ds != null) {
            ds.execute();
        }

        Executor.stackSpace.push(new Stack<HashMap<String, CoreVar>>());

        Executor.stackSpace.peek().push(new HashMap<String, CoreVar>());
        ss.execute();

        Executor.stackSpace.pop();
    }
}
