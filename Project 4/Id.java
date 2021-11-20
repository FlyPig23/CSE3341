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
            System.out.println("ERROR: " + i + " has not been declared.");
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
            System.out.println("ERROR: " + i + " inappropriately used as " + assignedType + ".");
            System.exit(1);
        }
    }

    void addToScope(Core type) {
        Parser.scopeLayer.peek().peek().put(i,type);
    }

    void print() {
        System.out.print(i);
    }

    void classAllocate() {
        Executor.allocate(i, Core.REF);
    }

    void intAllocate() {
        Executor.allocate(i, Core.INT);
    }

    Integer idValue() {
        CoreVar clause = Executor.searchCorrespondingCoreVar(i);
        Integer value = null;

        if (clause.type == Core.INT) {
            value = clause.value;
        } else if (clause.type == Core.REF) {
            try {
                value = Executor.heapSpace.get(clause.value);
            } catch (Exception e) {
                System.out.println("ERROR: The read value process is invalid.");
                System.exit(1);
            }
        }

        return value;
    }

    String stringForm() {
        return i;
    }

    void allocateToHeap() {
        CoreVar clause = Executor.searchCorrespondingCoreVar(i);
        Executor.heapSpace.add(null);
        clause.value = Executor.heapSpace.size() - 1;
    }

    void referenceAssign(Id source) {
        CoreVar lClause = Executor.searchCorrespondingCoreVar(i);
        CoreVar rClause = Executor.searchCorrespondingCoreVar(source.stringForm());
        lClause.value = rClause.value;
    }

    void expressionAssign(int value) {
        CoreVar clause = Executor.searchCorrespondingCoreVar(i);
        if (clause.type == Core.INT) {
            clause.value = value;
        } else if (clause.type == Core.REF){
            try {
                Executor.heapSpace.set(clause.value, value);
            } catch (Exception e) {
                System.out.println("ERROR: The assign process is invalid.");
                System.exit(1);
            }
        }
    }
}
