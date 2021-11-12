import java.io.IOException;
import java.util.HashMap;

class Id {
    String i;

    void parse(Scanner S) throws IOException {
        Parser.expectedToken(Core.ID);
        i = S.getID();
        S.nextToken();
    }

    void declareSemantic() {
        if (!Parser.checkByLayer(i)) {
            System.out.println("ERROR: " + i + " not declared.");
            System.exit(1);
        }
    }

    void doubleSemantic() {
        if (Parser.checkCurrentLayer(i)) {
            System.out.println("ERROR: " + i + " has already been declared in current scope.");
            System.exit(1);
        }
    }

    void typeSemantic(Core assignedType) {
        if (Parser.getType(i) != assignedType) {
            System.out.println("ERROR: " + i + " inappropriately used as " + assignedType);
            System.exit(1);
        }
    }

    void print() {
        System.out.print(i);
    }

    void addToScope(Core type) {
        HashMap<String, Core> topLayer = Parser.scopeLayer.pop();
        topLayer.put(i, type);
        Parser.scopeLayer.push(topLayer);
    }
}
