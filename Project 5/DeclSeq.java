import java.io.IOException;

class DeclSeq {
    Decl d;
    DeclSeq ds;
    FuncDecl fd;

    void parse(Scanner S) throws IOException {
        if (S.currentToken() == Core.ID) {
            fd = new FuncDecl();
            fd.parse(S);
        } else if (S.currentToken() == Core.INT || S.currentToken() == Core.REF){
            d = new Decl();
            d.parse(S);
        }
        if (S.currentToken() != Core.BEGIN) {
            ds = new DeclSeq();
            ds.parse(S);
        }
    }

    void semantic() {
        if (fd != null) {
            fd.semantic();
        } else {
            d.semantic();
        }
        if (ds != null) {
            ds.semantic();
        }
    }

    void print(int indent) {
        if (fd != null) {
            fd.print(indent);
        } else {
            d.print(indent);
        }
        if (ds != null) {
            ds.print(indent);
        }
    }

    void execute() {
        if (fd != null) {
            fd.execute();
        } else {
            d.execute();
        }
        if (ds != null) {
            ds.execute();
        }
    }
}
