package ca.ece.ubc.cpen221.mp5;
// Generated from Query.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class provides an empty implementation of {@link QueryListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
public class QueryBaseListener implements QueryListener {
	private Stack<List<YelpRestaurant>> restaurants;
	private List<YelpRestaurant> allRests;

	/**
	 * Constructor
	 */
	public QueryBaseListener(Map<String, Record> records){
		restaurants = new Stack<>();
		allRests = records.values().stream().filter(r -> r instanceof YelpRestaurant).map(r -> (YelpRestaurant) r).collect(Collectors.toList());
		System.out.println("ALL RESTAURANTS:" + allRests);
	}

	/**
	 * Enter a parse tree produced by {@link QueryParser#root}.
	 * @param ctx the parse tree
	 */
	public void enterRoot(QueryParser.RootContext ctx){

	}

	/**
	 * Exit a parse tree produced by {@link QueryParser#root}.
	 * @param ctx the parse tree
	 */
	public void exitRoot(QueryParser.RootContext ctx){

	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#orExpr}.
	 * @param ctx the parse tree
	 */
	public void enterOrExpr(QueryParser.OrExprContext ctx){
	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#orExpr}.
	 * @param ctx the parse tree
	 */
	public void exitOrExpr(QueryParser.OrExprContext ctx){
		int numChildren = ctx.getChildCount()/2 + 1;
		if(numChildren >= 1) {
			List<YelpRestaurant> rests= restaurants.pop();
			for (int i = 1; i < numChildren; i++) {
				List<YelpRestaurant> toCompare = restaurants.pop();
				System.out.println("ORING: " + rests + "with" + toCompare);
				for(YelpRestaurant r: toCompare){
					if(!rests.contains(r)) rests.add(r);
				}
				System.out.println("ORED RES:" + rests);
			}
			restaurants.push(rests);
		}
	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#andExpr}.
	 * @param ctx the parse tree
	 */
	public void enterAndExpr(QueryParser.AndExprContext ctx){

	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#andExpr}.
	 * @param ctx the parse tree
	 */
	public void exitAndExpr(QueryParser.AndExprContext ctx){
		int numChildren = ctx.getChildCount()/2 + 1;
		if(numChildren >= 1) {
			List<YelpRestaurant> rests= restaurants.pop();
			for (int i = 1; i < numChildren; i++) {
				List<YelpRestaurant> toCompare = restaurants.pop();
				System.out.println("aNDING: " + rests + "with" + toCompare);
				rests.retainAll(toCompare);
			}
			System.out.println("AND RESULT:" + rests);
			restaurants.push(rests);
		}
	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#atom}.
	 * @param ctx the parse tree
	 */
	public void enterAtom(QueryParser.AtomContext ctx){

	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#atom}.
	 * @param ctx the parse tree
	 */
	public void exitAtom(QueryParser.AtomContext ctx){

	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#in}.
	 * @param ctx the parse tree
	 */
	public void enterIn(QueryParser.InContext ctx){

	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#in}.
	 * @param ctx the parse tree
	 */
	public void exitIn(QueryParser.InContext ctx){
		String tokenString = ctx.STRING().toString();

		//TODO: Find the appropriate get method
		//rests = rests.stream().filter(r -> Arrays.asList(r.getAddress()).contains(tokenString)).collect(Collectors.toList());

		//this.restaurants.push(rests);

	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#category}.
	 * @param ctx the parse tree
	 */
	public void enterCategory(QueryParser.CategoryContext ctx){

	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#category}.
	 * @param ctx the parse tree
	 */
	public void exitCategory(QueryParser.CategoryContext ctx){
		String tokenString = ctx.STRING().toString();

		List<YelpRestaurant> rests = new ArrayList<>(allRests);

		rests = rests.stream().filter(r -> Arrays.asList(r.getCategories()).contains(tokenString)).collect(Collectors.toList());

		this.restaurants.push(rests);
		System.out.println("IN THIS CATEGORY:" + rests);
	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#name}.
	 * @param ctx the parse tree
	 */
	public void enterName(QueryParser.NameContext ctx){

	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#name}.
	 * @param ctx the parse tree
	 */
	public void exitName(QueryParser.NameContext ctx){
		String tokenString = ctx.STRING().toString();

		List<YelpRestaurant> rests = new ArrayList<>(allRests);

		rests = rests.stream().filter(r -> Arrays.asList(r.getName()).contains(tokenString)).collect(Collectors.toList());

		this.restaurants.push(rests);
		System.out.println("WITH THIS NAME:" + rests);
	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#rating}.
	 * @param ctx the parse tree
	 */
	public void enterRating(QueryParser.RatingContext ctx){

	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#rating}.
	 * @param ctx the parse tree
	 */
	public void exitRating(QueryParser.RatingContext ctx){
		String ineq = ctx.ineq().toString();
		Double num = Double.valueOf(ctx.NUM().toString());

		List<YelpRestaurant> rests = new ArrayList<>(allRests);

		switch(ineq) {
			case "<":
				rests = rests.stream().filter(r -> r.getStars() < num).collect(Collectors.toList());
			case "<=":
				rests = rests.stream().filter(r -> r.getStars() <= num).collect(Collectors.toList());
			case "=":
				rests = rests.stream().filter(r -> r.getStars() == num).collect(Collectors.toList());
			case ">=":
				rests = rests.stream().filter(r -> r.getStars() >= num).collect(Collectors.toList());
			case ">":
				rests = rests.stream().filter(r -> r.getStars() > num).collect(Collectors.toList());
			default:
				assert(false);
		}
		this.restaurants.push(rests);
		System.out.println("WITH THIS RATING:" + rests);
	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#price}.
	 * @param ctx the parse tree
	 */
	public void enterPrice(QueryParser.PriceContext ctx){

	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#price}.
	 * @param ctx the parse tree
	 */
	public void exitPrice(QueryParser.PriceContext ctx){
		String ineq = ctx.ineq().toString();
		Double num = Double.valueOf(ctx.NUM().toString());

		List<YelpRestaurant> rests = new ArrayList<>(allRests);

		switch(ineq) {
			case "<":
				rests = rests.stream().filter(r -> r.getPrice() < num).collect(Collectors.toList());
			case "<=":
				rests = rests.stream().filter(r -> r.getPrice() <= num).collect(Collectors.toList());
			case "=":
				rests = rests.stream().filter(r -> r.getPrice() == num).collect(Collectors.toList());
			case ">=":
				rests = rests.stream().filter(r -> r.getPrice() >= num).collect(Collectors.toList());
			case ">":
				rests = rests.stream().filter(r -> r.getPrice() > num).collect(Collectors.toList());
			default:
				assert(false);
		}
		this.restaurants.push(rests);
		System.out.println("WITH THIS PRICE:" + rests);

	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#ineq}.
	 * @param ctx the parse tree
	 */
	public void enterIneq(QueryParser.IneqContext ctx){

	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#ineq}.
	 * @param ctx the parse tree
	 */
	public void exitIneq(QueryParser.IneqContext ctx){

	}

	/**
	 * Returns a filtered list of restaurants satisfying the query conditions
	 */
	public List<YelpRestaurant> getFilteredList(){
		return restaurants.peek();
	}


	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void visitTerminal(TerminalNode node) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void visitErrorNode(ErrorNode node) { }
}