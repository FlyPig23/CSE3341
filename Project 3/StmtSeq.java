import java.io.IOException;

class StmtSeq {
    Stmt s;
    StmtSeq ss;

    void parse(Scanner S) throws IOException {
        s = new Stmt();
        s.parse(S);
        if (S.currentToken() != Core.END && S.currentToken() != Core.ENDIF
                && S.currentToken() != Core.ELSE && S.currentToken() != Core.ENDWHILE)  {
            ss = new StmtSeq();
            ss.parse(S);
        }
    }

    void semantic() {
        s.semantic();
        if (ss != null) {
            ss.semantic();
        }
    }

    void print(int indent) {
        s.print(indent);
        if (ss != null) {
            ss.print(indent);
        }
    }

    void execute() throws IOException {
        s.execute();
        if (ss != null) {
            ss.execute();
        }
    }
}
