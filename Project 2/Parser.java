import java.util.HashMap;
import java.util.Stack;

class Parser {
    public static Scanner S;
    public static Stack<HashMap<String, Core>> scopeLayer;

    static boolean checkByLayer(String id) {
        boolean declared = false;

        if (!scopeLayer.isEmpty()) {
            HashMap<String, Core> topLayer = scopeLayer.pop();
            if (topLayer.containsKey(id)) {
                declared = true;
            } else {
                declared = checkByLayer(id);
            }
            scopeLayer.push(topLayer);
        }

        return declared;
    }

    static boolean checkCurrentLayer(String id) {
        boolean declared = false;

        if (!scopeLayer.isEmpty()) {
            HashMap<String, Core> currentLayer = scopeLayer.pop();
            declared = currentLayer.containsKey(id);
            scopeLayer.push(currentLayer);
        }

        return declared;
    }

    static Core getType(String id) {
        Core type = null;

        if (!scopeLayer.isEmpty()) {
            HashMap<String, Core> topLayer = scopeLayer.pop();
            if (topLayer.containsKey(id)) {
                type = topLayer.get(id);
            } else {
                type = getType(id);
            }
            scopeLayer.push(topLayer);
        }

        return type;
    }

    static void expectedToken(Core expected) {
        if (S.currentToken() != expected) {
            System.out.println("ERROR: Parser expected " + expected + " but received " + S.currentToken());
            System.exit(1);
        }
    }
}
