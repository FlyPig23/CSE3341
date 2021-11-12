import java.io.IOException;

class Term {
    Factor f;
    Term t;

    void parse(Scanner S) throws IOException {
        f = new Factor();
        f.parse(S);
        if (S.currentToken() == Core.MULT) {
            S.nextToken();
            t = new Term();
            t.parse(S);
        }
    }

    void semantic() {
        f.semantic();
        if (t != null) {
            t.semantic();
        }
    }

    void print() {
        f.print();
        if (t != null) {
            System.out.print("*");
            t.print();
        }
    }
}
