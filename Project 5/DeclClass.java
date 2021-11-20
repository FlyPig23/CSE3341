import java.io.IOException;

public class DeclClass {
    IdList il;

    void parse(Scanner S) throws IOException {
        Parser.expectedToken(Core.REF);
        S.nextToken();
        il = new IdList();
        il.parse(S);
        Parser.expectedToken(Core.SEMICOLON);
        S.nextToken();
    }

    void semantic() {
        il.semanticForClass();
    }

    void print(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.print("ref ");
        il.print();
        System.out.println(";");
    }

    void execute() {
        il.executeForClass();
    }
}
