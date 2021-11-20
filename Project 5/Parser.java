import java.util.HashMap;
import java.util.List;
import java.util.Stack;

class Parser {
    public static Scanner S;
    public static Stack<Stack<HashMap<String, Core>>> scopeLayer;
    public static HashMap<String, FuncDecl> functionDefinition;

    // Check whether the "id" has been declared
    static boolean checkByLayer(String id) {
        boolean declared = false;

        if (!scopeLayer.peek().isEmpty()) {
            HashMap<String, Core> topLayer = scopeLayer.peek().pop();
            if (topLayer.containsKey(id)) {
                declared = true;
            } else {
                declared = checkByLayer(id);
            }
            scopeLayer.peek().push(topLayer);
        }

        return declared;
    }

    // Check whether the "id" has been double declared
    static boolean checkCurrentLayer(String id) {
        boolean declared = false;

        if (!scopeLayer.peek().isEmpty()) {
            HashMap<String, Core> currentLayer = scopeLayer.peek().peek();
            declared = currentLayer.containsKey(id);
        }

        return declared;
    }

    static Core getType(String id) {
        Core type = null;

        if (!scopeLayer.peek().isEmpty()) {
            HashMap<String, Core> topLayer = scopeLayer.peek().pop();
            if (topLayer.containsKey(id)) {
                type = topLayer.get(id);
            } else {
                type = getType(id);
            }
            scopeLayer.peek().push(topLayer);
        }

        return type;
    }

    static void expectedToken(Core expected) {
        if (S.currentToken() != expected) {
            System.out.println("ERROR: Parser expected " + expected + " but received " + S.currentToken() + ".");
            System.exit(1);
        }
    }

    static boolean checkOverload(String id) {
        boolean overloaded = functionDefinition.containsKey(id);
        return overloaded;
    }

}
