import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Initialize the scanner with the input file
        Scanner S1 = new Scanner(args[0]);
        Scanner S2 = new Scanner(args[1]);
        Parser.S = S1;
        Executor.S = S2;

        Program root = new Program();

        try {
            root.parse(S1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            root.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //root.print();
    }
}
