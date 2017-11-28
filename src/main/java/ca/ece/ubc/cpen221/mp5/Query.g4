//define a grammar (using ANTLR) called Structured Query

grammar Query;

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
rating : 'rating' INEQ num;
price : price INEQ num;

num : '1' .. '9';

string: CHAR*;
fragment CHAR: ('a' .. 'z')|('1' .. '9')|('A' .. 'Z');

LParen : '(';
RParen : ')';