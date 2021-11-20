import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Formals {
    Id i;
    Formals f;

    void parse(Scanner S) throws IOException {
        i = new Id();
        i.parse(S);
        if (S.currentToken() == Core.COMMA) {
            S.nextToken();
            f = new Formals();
            f.parse(S);
        }
    }

    void semantic() {
        i.doubleSemantic();
        Parser.scopeLayer.peek().peek().put(i.stringForm(), Core.REF);
        if (f != null) {
            f.semantic();;
        }
    }

    void print() {
        i.print();
        if (f != null) {
            System.out.print(", ");
            f.print();
        }
    }

    // Get all the parameters of current function and check duplicated formal parameters
    List<String> getParameters(){
        List<String> parameters;
        if (f != null) {
            parameters = f.getParameters();
        } else {
            parameters = new ArrayList<String>();
        }
        parameters.add(i.stringForm());
        return parameters;
    }
}
