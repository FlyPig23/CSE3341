import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

class Scanner {
    public HashMap<String,Core> charToCore;
    public BufferedReader fileReader;
    public Core coreToken;
    public StringBuilder tokenBuilder;
    public String id = "[a-zA-Z][a-zA-Z0-9]*";
    public String special = ";(),=!<+-*";
    static final int MIN_CONSTANT = 0;
    static final int MAX_CONSTANT = 1023;

    // Constructor should open the file and find the first token
    Scanner(String filename){
        this.mapInit();

        try {
            fileReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found.");
        }

        try {
            this.nextToken();
        } catch (IOException e) {
            System.out.println("ERROR: First token not found.");
        }
        coreToken = this.currentToken();
    }

    public void mapInit() {
        charToCore = new HashMap<String, Core>();

        charToCore.put("program", Core.PROGRAM);
        charToCore.put("begin", Core.BEGIN);
        charToCore.put("end", Core.END);
        charToCore.put("new", Core.NEW);
        charToCore.put("define", Core.DEFINE);
        charToCore.put("extends", Core.EXTENDS);
        charToCore.put("class", Core.CLASS);
        charToCore.put("endclass", Core.ENDCLASS);
        charToCore.put("int", Core.INT);
        charToCore.put("endfunc", Core.ENDFUNC);
        charToCore.put("if", Core.IF);
        charToCore.put("then", Core.THEN);
        charToCore.put("else", Core.ELSE);
        charToCore.put("while", Core.WHILE);
        charToCore.put("endwhile", Core.ENDWHILE);
        charToCore.put("endif", Core.ENDIF);
        charToCore.put(";", Core.SEMICOLON);
        charToCore.put("(", Core.LPAREN);
        charToCore.put(")", Core.RPAREN);
        charToCore.put(",", Core.COMMA);
        charToCore.put("=", Core.ASSIGN);
        charToCore.put("!", Core.NEGATION);
        charToCore.put("or", Core.OR);
        charToCore.put("==", Core.EQUAL);
        charToCore.put("<", Core.LESS);
        charToCore.put("<=", Core.LESSEQUAL);
        charToCore.put("+", Core.ADD);
        charToCore.put("-", Core.SUB);
        charToCore.put("*", Core.MULT);
        charToCore.put("input", Core.INPUT);
        charToCore.put("output", Core.OUTPUT);
        charToCore.put("ref", Core.REF);
    }

    // nextToken should advance the scanner to the next token
    public void nextToken() throws IOException {
        try{
            char nextChar;
            int next =  fileReader.read();
            // skip whitespace and reach the beginning of next token
            while (next != -1 && Character.isWhitespace(next)) {
                next = fileReader.read();
            }

            nextChar = (char) next;
            if (next == -1) {
                coreToken = Core.EOF;
            } else if (special.indexOf(next) >= 0) {
                // deal with the "=", "==", "<" and "<=" situation
                if (nextChar == '=' || nextChar == '<') {
                    // limit 1 character to read while preserving the mark
                    fileReader.mark(1);
                    int temp = fileReader.read();
                    if ((char) temp == '=' && nextChar == '=') {
                        coreToken = charToCore.get("==");
                    } else if ((char) temp == '=' && nextChar == '<') {
                        coreToken = charToCore.get("<=");
                    } else {
                        // get back to the recent mark
                        fileReader.reset();
                        coreToken = charToCore.get(Character.toString(nextChar));
                    }
                } else {
                    coreToken = charToCore.get(Character.toString(nextChar));
                }
            } else {
                boolean flag = true;
                tokenBuilder = new StringBuilder();
                // deal with ID, stop when hits a non-letter or non-digit
                if (Character.isLetter(nextChar)) {
                    while (flag) {
                        // append one character at one time
                        tokenBuilder.append(nextChar);
                        // limit 1 character to read while preserving the mark
                        fileReader.mark(1);
                        next = fileReader.read();
                        nextChar = (char) next;
                        if (next == -1 || !Character.isLetterOrDigit(nextChar)) {
                            flag = false;
                            fileReader.reset();
                        }
                    }
                } else if (Character.isDigit(nextChar)) {
                    while (flag) {
                        // append one character at one time
                        tokenBuilder.append(nextChar);
                        // limit 1 character to read while preserving the mark
                        fileReader.mark(1);
                        next = fileReader.read();
                        nextChar = (char) next;
                        if (next == -1 || !Character.isDigit(nextChar)) {
                            flag = false;
                            fileReader.reset();
                        }
                    }
                } else {
                    tokenBuilder.append(nextChar);
                }

                String tokenString = tokenBuilder.toString();
                if (charToCore.containsKey(tokenString)) {
                    coreToken = charToCore.get(tokenString);
                } else if (tokenString.matches(id)) {
                    coreToken = Core.ID;
                } else if (Integer.parseInt(tokenString) >= MIN_CONSTANT && Integer.parseInt(tokenString) <= MAX_CONSTANT) {
                    coreToken = Core.CONST;
                } else {
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            coreToken = Core.ERROR;
            System.out.println("ERROR: Invalid input " + tokenBuilder.toString());
        }
    }

    // currentToken should return the current token
    public Core currentToken() {
        return coreToken;
    }

    // If the current token is ID, return the string value of the identifier
    // Otherwise, return value does not matter
    public String getID() {
        return tokenBuilder.toString();
    }

    // If the current token is CONST, return the numerical value of the constant
    // Otherwise, return value does not matter
    public int getCONST() {
        return Integer.parseInt(tokenBuilder.toString());
    }
}

