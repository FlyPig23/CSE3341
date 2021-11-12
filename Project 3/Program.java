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
        Executor.stackSpace.push(new HashMap<String, CoreVar>());
        ss.execute();
        Executor.stackSpace.pop();
    }
}
