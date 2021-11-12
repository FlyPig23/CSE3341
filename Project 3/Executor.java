import java.io.IOException;
import java.util.*;

class CoreVar {
    Core type;
    Integer value;

    public CoreVar(Core type) {
        this.type = type;
        if (type == Core.INT) {
            this.value = 0;
        } else if (type == Core.REF) {
            this.value = null;
        }
    }
}

class Executor {
    static HashMap<String, CoreVar> globalSpace;        // Store global variables
    static Stack<HashMap<String, CoreVar>> stackSpace;  // Store local variables
    static ArrayList<Integer> heapSpace;                // Store ref variable by index
    public static Scanner S;

    static void initialize() {
        globalSpace = new HashMap<String, CoreVar>();
        // StackSpace is an empty stack at this point, need to remember to do stackSpace.push(new HashMap<String, CoreVar>>()) later
        stackSpace = new Stack<HashMap<String, CoreVar>>();
        heapSpace = new ArrayList<Integer>();
    }

    static void allocate(String i, Core type) {
         CoreVar clause = new CoreVar(type);
         // If the stackSpace is empty then the ID must belong to globalSpace
         if (stackSpace.isEmpty()) {
             globalSpace.put(i, clause);
         } else {
             HashMap<String, CoreVar> topLayer = stackSpace.pop();
             topLayer.put(i, clause);
             stackSpace.push(topLayer);
         }
    }

    static CoreVar searchCorrespondingCoreVar(String i) {
        CoreVar clause = null;

        for (int index = stackSpace.size() - 1; index >= 0; index--) {
            if (stackSpace.get(index).containsKey(i)) {
                clause = stackSpace.get(index).get(i);
                break;
            }
        }

        if (clause == null) {
            clause = globalSpace.get(i);
        }

        return clause;
    }

    static int inputRead() throws IOException {
        int result = 0;

        if (S.currentToken() != Core.EOF) {
            result = S.getCONST();
            S.nextToken();
        } else {
            System.out.println("ERROR: No more data to read");
            System.exit(1);
        }

        return result;
    }
}
