import java.io.IOException;

public class Stmt {
    Assign a;
    If i;
    Loop l;
    In in;
    Out o;
    Decl d;

    void parse(Scanner S) throws IOException {
        if (S.currentToken() == Core.ID) {
            a = new Assign();
            a.parse(S);
        } else if (S.currentToken() == Core.IF) {
            i = new If();
            i.parse(S);
        } else if (S.currentToken() == Core.WHILE) {
            l = new Loop();
            l.parse(S);
        } else if (S.currentToken() == Core.INPUT) {
            in = new In();
            in.parse(S);
        } else if (S.currentToken() == Core.OUTPUT) {
            o = new Out();
            o.parse(S);
        } else if (S.currentToken() == Core.INT || S.currentToken() == Core.REF) {
            d = new Decl();
            d.parse(S);
        } else {
            System.out.println("ERROR: (Stmt) Inappropriate toke found: " + S.currentToken());
            System.exit(1);
        }
    }

    void semantic() {
        if (a != null) {
            a.semantic();
        } else if (i != null) {
            i.semantic();
        } else if (l != null) {
            l.semantic();
        } else if (in != null) {
            in.semantic();
        } else if (o != null) {
            o.semantic();
        } else if (d != null) {
            d.semantic();
        }
    }

    void print(int indent) {
        if (a != null) {
            a.print(indent);
        } else if (i != null) {
            i.print(indent);
        } else if (l != null) {
            l.print(indent);
        } else if (in != null) {
            in.print(indent);
        } else if (o != null) {
            o.print(indent);
        } else if (d != null) {
            d.print(indent);
        }
    }
}
