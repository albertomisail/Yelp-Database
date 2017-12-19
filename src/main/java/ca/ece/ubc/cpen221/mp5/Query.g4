//define a grammar (using ANTLR) called Structured Query

grammar Query;

//nonterminals for parser
root: orExpr+;

orExpr : andExpr (OR andExpr)*;
andExpr : atom (AND atom)*;

atom : in|category|rating|price|name|LPAREN orExpr RPAREN;

in : INTOKEN LPAREN STRING RPAREN;
category : CATEGORYTOKEN LPAREN STRING RPAREN;
name : NAMETOKEN LPAREN STRING RPAREN;
rating : RATINGTOKEN ineq NUM;
price : PRICETOKEN ineq NUM;
ineq : GT | GTE | LT | LTE |EQ;

//tokens for lexer
OR : '||';
AND : '&&';

GT : '>';
GTE : '>=';
LT : '<';
LTE : '<=';
EQ : '=';

NUM : [1-5];
LPAREN : '(';
RPAREN : ')';

INTOKEN: 'in';
CATEGORYTOKEN: 'category';
NAMETOKEN: 'name';
RATINGTOKEN: 'rating';
PRICETOKEN : 'price';

WHITE_SPACE : ( ' '|'\r'|'\t'|'\n' ) -> skip;
STRING : [a-zA-Z]+([ \t][a-zA-Z]+)*;





