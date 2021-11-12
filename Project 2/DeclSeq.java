import java.io.IOException;

class DeclSeq {
    Decl d;
    DeclSeq ds;

    void parse(Scanner S) throws IOException {
        d = new Decl();
        d.parse(S);
        if (S.currentToken() != Core.BEGIN) {
            ds = new DeclSeq();
            ds.parse(S);
        }
    }

    void semantic() {
        d.semantic();
        if (ds != null) {
            ds.semantic();
        }
    }

    void print(int indent) {
        d.print(indent);
        if (ds != null) {
            ds.print(indent);
        }
    }
}
