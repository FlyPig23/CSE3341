- Name: Hangxiao Zhu

- Files: Project2.zip, Project2, README, Assign.java, Cmpr.java, Cond.java, Core.java, Decl.java, DeclClass.java, DeclInt.java, DeclSeq.java, Expr.java, Factor.java, Id.java, IdList.java, If.java, In.java, Loop.java, Main.java, Out.java, Parser.java, Program.java, Scanner.java, Stmt.java, StmtSeq.java, Term.java

- Program.java: This class file contains three methods: parse(Scanner s), semantic(), print(int indent). The parse function parses <prog> into a tree constructed by <decl-seq> and <stmt-seq>. The semantic function checks the semantic errors through the whole tree. The print function prints the tree rooted on program.

- DeclSeq.java: This class file contains three methods: parse(Scanner s), semantic(), print(int indent). The parse function parses <DeclSeq> into a tree constructed by <decl> or <decl><decl-seq>. The semantic function checks the semantic errors through <decl> or <decl><decl-seq>. The print function prints the tree generated from <decl> or <decl><decl-seq>.

- Decl.java: This class file contains three methods: parse(Scanner s), semantic(), print(int indent). The parse function parses <decl> into a tree constructed by <decl-int> or <decl-class>. The semantic function checks the semantic errors through <decl-int> or <decl-class>. The print function prints the tree generated from <decl-int> or <decl-class>.

- DeclInt.java: This class file contains three methods: parse(Scanner s), semantic(), print(int indent). The parse function parses <decl-int> into a <id-list>. The semantic function checks the semantic errors in the <id-list>. The print function prints the <id-list>.

- DeclClass.java: This class file contains three methods: parse(Scanner s), semantic(), print(int indent). The parse function parses <decl-class> into a <id-list>. The semantic function checks the semantic errors in the <id-list>. The print function prints the <id-list>.

- StmtSeq.java: This class file contains three methods: parse(Scanner s), semantic(), print(int indent). The parse function parses <stmt-seq> into a tree constructed by <stmt> or <stmt><stmt-seq>. The semantic function checks the semantic errors through <stmt> or <stmt><stmt-seq>. The print function prints the tree generated from <stmt> or <stmt><stmt-seq>.

- Stmt.java: This class file contains three methods: parse(Scanner s), semantic(), print(int indent). The parse function parses <assign> or <if> or <loop> or <in> or <out> or <decl> into the corresponding trees. The semantic function checks the semantic errors in <assign> or <if> or <loop> or <in> or <out> or <decl>. The print function prints the tree generated from <assign> or <if> or <loop> or <in> or <out> or <decl>.

- Assign.java: This class file contains three methods: parse(Scanner s), semantic(), print(int indent). The parse function parses three different types of id assignment. The semantic function checks the semantic errors in the three possible assignments. The print function prints the corresponding id assignment sentence.

- If.java: This class file contains three methods: parse(Scanner s), semantic(), print(int indent). The parse function parses the two different kinds of if-flow. The semantic function checks the semantic errors in the two possible different kinds of if-flow. The print function prints the if-flow sentences.

- While.java: This class file contains three methods: parse(Scanner s), semantic(), print(int indent). The parse function parses the while loop. The semantic function checks the semantic errors in the while loop. The print function prints the while loop sentences.

- In.java: This class file contains three methods: parse(Scanner s), semantic(), print(int indent). The parse function parses the input statement. The semantic function checks the semantic errors in the input statement. The print function prints the input statement.

- Out: This class file contains three methods: parse(Scanner s), semantic(), print(int indent). The parse function parses the output statement. The semantic function checks the semantic errors in the output statement. The print function prints the output statement.

- Cond.java: This class file contains three methods: parse(Scanner s), semantic(), print(). The parse function parses the three possible condition statements. The semantic function checks the semantic errors in the three possible condition statements. The print function prints the three possible condition statements.

- Cmpr.java: This class file contains three methods: parse(Scanner s), semantic(), print(). The parse function parses the three possible comparison statements. The semantic function checks the semantic errors in the three possible comparison statements. The print function prints the three possible comparison statements.

- Expr.java: This class file contains three methods: parse(Scanner s), semantic(), print(). The parse function parses the three different expressions. The semantic function checks the semantic errors in the three different expressions. The print function prints the three different expressions.

- Term.java: This class file contains three methods: parse(Scanner s), semantic(), print(). The parse function parses the two different terms. The semantic function checks the semantic errors in the two different terms. The print function prints the two different terms.

- Id.java: This class file contains four methods: parse(Scanner S), declareSemantic(), doubleSemantic(), typeSemantic(Core assignedType), print() and addToScope(Core type). The parse function gets the id read by scanner. The declareSemantic function checks whether the id has been declared before use. The doubleSemantic function checks whether the id has been doubly declared. The typeSemantic function checks whether the id type is consistent with the assigned type. The print function prints the id. The addToScope function adds the id to the current scope.

- IdList.java: This class file contains four methods: parse(Scanner S), semanticForClass(), semanticForInt() and print(). The parse function interprets <id-list> into <id>. The semanticForClass() aims at checking the parsed id and add it to its scope as a class id. The semanticForInt() aims at checking the parsed id and add it to its scope as an int id. The print function prints the ids.

- Factor.java: This class file contains three methods: parse(Scanner s), semantic(), print(). The parse function parses the three different factors, including id, constant and expression. The semantic function checks the semantic errors in the three different factors. The print function prints the three different factors.

- Parser.java: This class file contains for methods: checkByLayer(String id), checkCurrentLayer(String id), getType(String id) and expectedToken(Core expected). The checkByLayer function searches both local and global scopes to check whether the specific id declaration exists. The checkCurrentLayer function checks whether the specific id has already been declared in current scope. The getType function returns the type of the specific id. The expectedToken function checks whether the current token satisfies the parse tree requirements.

- Scanner.java: Same as the file in Project 1.

- Core.java: Same as the file in Project 1.

- Special features: Retain the consistency of almost all codes of different classes, i.e., basically each independent class contains three basic methods: parse, semantic and print; Utilize both the Stack and HashMap data structure to store the variables and their corresponding types; Utilize an indent variable to keep trace of the needed indent space; Utilize recursive descent to parse the tree.

- Description of the overall design: I applied the top-down recursive descent approach to create the parse tree. That means I followed the grammar stated in the requirement and started from the <prog>, which is regarded as the root, and went all the way down to the bottom <factor>. After creating the tree successfully, I read through the expected output files to conclude the rule of the indentation and then implemented the print() functions for different classes. Then I spent hours on implementing the semantic() functions to ensure my output are legal. 

- Test Process: I used the "java Main Correct/#.code" to check my output one by one. At the beginning, many outputs had problems, and their problems were almost all about the parsing of semicolon. So I summarized the similarities of different input files to locate the specific problems of my program and fix them. In addition, I created some special examples and tested my code. After all the tests are completed, I ran the tester.sh file to make the final checks.

- - Bugs: No bug remaining.

