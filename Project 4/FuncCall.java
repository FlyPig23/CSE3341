import java.io.IOException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class FuncCall {
    Id i;
    Formals f;

    void parse(Scanner S) throws IOException {
        Parser.expectedToken(Core.BEGIN);
        S.nextToken();
        i = new Id();
        i.parse(S);
        Parser.expectedToken(Core.LPAREN);
        S.nextToken();
        f = new Formals();
        f.parse(S);
        Parser.expectedToken(Core.RPAREN);
        S.nextToken();
        Parser.expectedToken(Core.SEMICOLON);
        S.nextToken();
    }

    void semantic() {
        // Check whether the function called has been declared before use.
        if (!Parser.functionDefinition.containsKey(i.stringForm())) {
            System.out.println("ERROR: Function " + i.stringForm() + " has not been declared.");
            System.exit(1);
        }

        FuncDecl callee = Parser.functionDefinition.get(i.stringForm());
        List<String> formalParameters = callee.f.getParameters();
        List<String> actualParameters = f.getParameters();
        // Check whether the actual parameters have been declared before use.
        for (String actual : actualParameters) {
            if (!Parser.checkByLayer(actual)) {
                System.out.println("ERROR: Parameter" + actual + " has not been declared.");
                System.exit(1);
            }
        }
        // Check whether the function call has a valid target.
        if (formalParameters.size() != actualParameters.size()) {
            System.out.println("ERROR: There is no function defined in the declaration sequence that matches the number of arguments in the call.");
            System.exit(1);
        }

        Stack<HashMap<String, Core>> callStack = new Stack<HashMap<String, Core>>();
        callStack.push(new HashMap<String, Core>());
        for (String formal : formalParameters) {
            callStack.peek().put(formal, Core.REF);
        }
        Parser.scopeLayer.push(callStack);
        Parser.scopeLayer.peek().push(new HashMap<String, Core>());
        f.semantic();
        Parser.scopeLayer.pop();
    }

    void print(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.print("begin ");
        i.print();
        System.out.print("(");
        f.print();
        System.out.print(")");
    }

    void execute() throws IOException {
        FuncDecl callee = Executor.functionDefinition.get(i.stringForm());
        Formals calleeFormalParameter = callee.f;
        StmtSeq calleeFunctionBody = callee.ss;
        Executor.pushStackForCall(calleeFormalParameter, f);
        Executor.stackSpace.peek().push(new HashMap<String, CoreVar>());
        calleeFunctionBody.execute();
        Executor.stackSpace.pop();
    }
}
