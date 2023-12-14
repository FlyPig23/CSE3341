# CSE3341
Design a "Core language" based on Java.

This is a class project for CSE 3341 at the Ohio State University. It is an interpreter for a new language called Core. Core is an imperative language that supports many features of modern programming such as loops, switch cases, stored variables, output to the console, compile time error reporting, execution time error reporting, etc.

Core Language overview:
###
    <prog> ::= program <decl-seq> begin <stmt-seq> end 
    <decl-seq> ::= <decl> | <decl><decl-seq>
    <stmt-seq> ::= <stmt> | <stmt><stmt-seq>
    <decl> ::= int <id-list> ; 
    <id-list> ::= id | id , <id-list> 
    <stmt> ::= <assign> | <if> | <loop> | <in> | <out> 
    <assign> ::= id := <expr> ;
    <in> ::= input id ; 
    <out> ::= output <expr> ; 
    <if> ::= if <cond> then <stmt-seq> endif ; | if <cond> then <stmt-seq> else <stmt-seq> endif ;
    <loop> ::= while <cond> begin <stmt-seq> endwhile ; 
    <cond> ::= <cmpr> | ! ( <cond> ) | <cmpr> or <cond>
    <cmpr> ::= <expr> = <expr> | <expr> < <expr> | <expr> <= <expr>
    <expr> ::= <term> | <term> + <expr> | <term> – <expr>
    <term> ::= <factor> | <factor> * <term>
    <factor> ::= const | id | ( <expr> )
    <id> ::= <letter> | <id><letter> | <id><digit>
    <letter> ::= A | B |...| Z | a | b | ... | z
###

Detailed explanations are in each milestone folder's readme file.
