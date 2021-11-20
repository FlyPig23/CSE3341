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
    static HashMap<String, CoreVar> globalSpace;                // Store global variables
    static Stack<Stack<HashMap<String, CoreVar>>> stackSpace;   // Store local variables
    static ArrayList<Integer> heapSpace;                        // Store ref variable by index
    static HashMap<String, FuncDecl> functionDefinition;        // Store function definitions
    public static Scanner S;

    static void initialize() {
        globalSpace = new HashMap<String, CoreVar>();
        // StackSpace is an empty stack at this point, need to remember to do stackSpace.push(new HashMap<String, CoreVar>>()) later
        stackSpace = new Stack<Stack<HashMap<String, CoreVar>>>();
        heapSpace = new ArrayList<Integer>();
        functionDefinition = new HashMap<String, FuncDecl>();
    }

    static void allocate(String id, Core type) {
         CoreVar clause = new CoreVar(type);
         // If the stackSpace is empty then the ID must belong to globalSpace
         if (stackSpace.isEmpty()) {
             globalSpace.put(id, clause);
         } else {
             HashMap<String, CoreVar> topLayer = stackSpace.peek().peek();
             topLayer.put(id, clause);
         }
    }

    static CoreVar searchCorrespondingCoreVar(String id) {
        CoreVar clause = null;

        for (int index = stackSpace.peek().size() - 1; index >= 0; index--) {
            if (stackSpace.peek().get(index).containsKey(id)) {
                clause = stackSpace.peek().get(index).get(id);
                break;
            }
        }

        if (clause == null) {
            clause = globalSpace.get(id);
        }

        return clause;
    }

    static int inputRead() throws IOException {
        int result = 0;

        if (S.currentToken() != Core.EOF) {
            result = S.getCONST();
            S.nextToken();
        } else {
            System.out.println("ERROR: No more data to read.");
            System.exit(1);
        }

        return result;
    }

    static void pushStackForCall(Formals formal, Formals actual) {
        Stack<HashMap<String, CoreVar>> callStack = new Stack<HashMap<String, CoreVar>>();
        callStack.push(new HashMap<String, CoreVar>());

        List<String> formalParameters = formal.getParameters();
        List<String> actualParameters = actual.getParameters();
        for (int i = 0; i < formalParameters.size(); i++) {
            CoreVar actualCoreVar = new CoreVar(Core.REF);
            actualCoreVar = searchCorrespondingCoreVar(actualParameters.get(i));
            callStack.peek().put(formalParameters.get(i), actualCoreVar);
        }

        stackSpace.push(callStack);
    }
}
