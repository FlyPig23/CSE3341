import java.io.IOException;

class DeclInt {
    IdList il;

    void parse(Scanner S) throws IOException {
        Parser.expectedToken(Core.INT);
        S.nextToken();
        il = new IdList();
        il.parse(S);
        Parser.expectedToken(Core.SEMICOLON);
        S.nextToken();
    }

    void semantic() {
        il.semanticForInt();
    }

    void print(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.print("int ");
        il.print();
        System.out.println(";");
    }
}
