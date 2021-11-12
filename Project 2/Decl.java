import java.io.IOException;

class Decl {
    DeclInt di;
    DeclClass dc;

    void parse(Scanner S) throws IOException {
        if (S.currentToken() == Core.INT) {
            di = new DeclInt();
            di.parse(S);
        } else if (S.currentToken() == Core.REF) {
            dc = new DeclClass();
            dc.parse(S);
        } else {
            System.out.println("ERROR: (Decl) Inappropriate toke found: " + S.currentToken());
            System.exit(1);
        }
    }

    void semantic() {
        if (di != null) {
            di.semantic();
        } else if (dc != null){
            dc.semantic();
        }
    }

    void print(int indent) {
        if (di != null) {
            di.print(indent);
        } else if (dc != null) {
            dc.print(indent);
        }
    }
}
