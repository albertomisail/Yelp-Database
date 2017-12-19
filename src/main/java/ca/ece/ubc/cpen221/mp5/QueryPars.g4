//tokens for parser
parser grammar QueryPars;

options { tokenVocab = QueryLex; }

//nonterminals for parser
root: orExpr+;

orExpr : andExpr (OR andExpr)*;
andExpr : atom (AND atom)*;

atom : in|category|rating|price|name|LPAREN orExpr RPAREN;

in : IN LPAREN STRING RPAREN;
category : CATEGORYTOKEN LPAREN STRING RPAREN;
name : NAMETOKEN LPAREN STRING RPAREN;
rating : RATINGTOKEN ineq NUM;
price : PRICETOKEN ineq NUM;
ineq: GT | GTE | LT | LTE |EQ;
