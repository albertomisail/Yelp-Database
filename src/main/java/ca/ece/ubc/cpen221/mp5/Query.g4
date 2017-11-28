//define a grammar (using ANTLR) called Structured Query

grammar Query;

query: 'QUERY' orExpr+;

orExpr : andExpr (OR andExpr)*;
andExpr : atom(AND atom)*;

atom : in|category|rating|price|name|LParen orExpr RParen;

OR : '||';
AND : '&&';

INEQ : GT | GTE | LT | LTE |EQ;
fragment GT : '>';
fragment GTE : '>=';
fragment LT : '<';
fragment LTE : '<=';
fragment EQ : '=';

in : 'in' LParen string RParen;
category : 'category' LParen string RParen;
name : 'name' LParen string RParen;
rating : 'rating' INEQ NUM;
price : 'price' INEQ NUM;

NUM : [0-9];

string: '"' CHAR* '"';
fragment CHAR: .;

LParen : '(';
RParen : ')';


