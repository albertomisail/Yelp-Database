//define a grammar (using ANTLR) called Structured Query

grammar Query;

//nonterminals for parser
root: QUERYTOKEN orExpr+ EOF;

orExpr : andExpr (OR andExpr)*;
andExpr : atom (AND atom)*;

atom : in|category|rating|price|name|LPAREN orExpr RPAREN;

in : IN LPAREN STRING RPAREN;
category : CATEGORYTOKEN LPAREN STRING RPAREN;
name : NAMETOKEN LPAREN STRING RPAREN;
rating : RATINGTOKEN ineq NUM;
price : PRICETOKEN ineq NUM;
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

QUERYTOKEN : 'QUERY';
CATEGORYTOKEN: 'category';
NAMETOKEN: 'name';
RATINGTOKEN: 'rating';
PRICETOKEN : 'price';


