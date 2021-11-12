import java.io.IOException;

class IdList {
    Id i;
    IdList il;

    void parse(Scanner S) throws IOException {
        i = new Id();
        i.parse(S);
        if (S.currentToken() == Core.COMMA) {
            S.nextToken();
            il = new IdList();
            il.parse(S);
        }
    }

    void semanticForClass() {
        i.doubleSemantic();
        i.addToScope(Core.REF);
        if (il != null) {
            il.semanticForClass();
        }
    }

    void semanticForInt() {
        i.doubleSemantic();
        i.addToScope(Core.INT);
        if (il != null) {
            il.semanticForInt();
        }
    }

    void print() {
        i.print();
        if (il != null) {
            System.out.print(",");
            il.print();
        }
    }

    void executeForClass() {
        i.classAllocate();
        if (il != null) {
            il.executeForClass();
        }
    }

    void executeForInt() {
        i.intAllocate();
        if (il != null) {
            il.executeForInt();
        }
    }
}
