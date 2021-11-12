import java.io.IOException;

class Out {
    Expr e;

    void parse(Scanner S) throws IOException {
        Parser.expectedToken(Core.OUTPUT);
        S.nextToken();
        e = new Expr();
        e.parse(S);
        Parser.expectedToken(Core.SEMICOLON);
        S.nextToken();
    }

    void semantic() {
        e.semantic();
    }

    void print(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.print("output ");
        e.print();
        System.out.println(";");
    }
}
