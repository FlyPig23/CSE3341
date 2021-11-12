import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Initialize the scanner with the input file
        Scanner S = new Scanner(args[0]);
        Parser.S = S;

        Program root = new Program();

        try {
            root.parse(S);
        } catch (IOException e) {
            e.printStackTrace();
        }

        root.semantic();

        root.print();
    }
}
