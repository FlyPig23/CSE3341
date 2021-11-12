import java.io.IOException;

class In {
    Id i;

    void parse(Scanner S) throws IOException {
        Parser.expectedToken(Core.INPUT);
        S.nextToken();
        i = new Id();
        i.parse(S);
        Parser.expectedToken(Core.SEMICOLON);
        S.nextToken();
    }

    void semantic() {
        i.declareSemantic();
    }

    void print(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.print("input ");
        i.print();
        System.out.println(";");
    }
}
