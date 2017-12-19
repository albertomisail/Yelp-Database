// Generated from QueryPars.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QueryPars}.
 */
public interface QueryParsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QueryPars#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(QueryPars.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryPars#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(QueryPars.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryPars#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(QueryPars.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryPars#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(QueryPars.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryPars#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(QueryPars.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryPars#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(QueryPars.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryPars#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(QueryPars.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryPars#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(QueryPars.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryPars#in}.
	 * @param ctx the parse tree
	 */
	void enterIn(QueryPars.InContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryPars#in}.
	 * @param ctx the parse tree
	 */
	void exitIn(QueryPars.InContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryPars#category}.
	 * @param ctx the parse tree
	 */
	void enterCategory(QueryPars.CategoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryPars#category}.
	 * @param ctx the parse tree
	 */
	void exitCategory(QueryPars.CategoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryPars#name}.
	 * @param ctx the parse tree
	 */
	void enterName(QueryPars.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryPars#name}.
	 * @param ctx the parse tree
	 */
	void exitName(QueryPars.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryPars#rating}.
	 * @param ctx the parse tree
	 */
	void enterRating(QueryPars.RatingContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryPars#rating}.
	 * @param ctx the parse tree
	 */
	void exitRating(QueryPars.RatingContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryPars#price}.
	 * @param ctx the parse tree
	 */
	void enterPrice(QueryPars.PriceContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryPars#price}.
	 * @param ctx the parse tree
	 */
	void exitPrice(QueryPars.PriceContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryPars#ineq}.
	 * @param ctx the parse tree
	 */
	void enterIneq(QueryPars.IneqContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryPars#ineq}.
	 * @param ctx the parse tree
	 */
	void exitIneq(QueryPars.IneqContext ctx);
}