//define a grammar (using ANTLR) called Structured Query

grammar Query;

//nonterminals for parser
query: 'QUERY' orExpr+;

orExpr : andExpr (OR andExpr)*;
andExpr : atom(AND atom)*;

atom : in|category|rating|price|name|LParen orExpr RParen;

in : IN LPAREN STRING RPAREN;
category : 'category' LParen STRING RPAREN;
name : 'name' LParen STRING RParen;
rating : 'rating' STRING NUM;
price : 'price' ineq NUM;
ineq: GT | GTE | LT | LTE |EQ;

//tokens for lexer
OR : '||';
AND : '&&';

GT : '>';
GTE : '>=';
LT : '<';
LTE : '<=';
EQ : '=';

IN : 'in';
CATEGORY : 'category';

NUM : [1-5];
STRING : [a-zA-Z]+([ \t][a-zA-Z]+)*;
LPAREN : '(';
RPAREN : ')';

