//tokens for lexer
lexer grammar QueryLex;

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
LPAREN : '(' -> mode(STR);
RPAREN : ')';

CATEGORYTOKEN: 'category';
NAMETOKEN: 'name';
RATINGTOKEN: 'rating';
PRICETOKEN : 'price';

WHITE_SPACE : ( ' '|'\r'|'\t'|'\n' ) -> skip ;

mode STR;
STRING : [a-zA-Z]+([ \t\r\n][a-zA-Z]+)* -> mode(DEFAULT_MODE);
