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

//http://www.geeksforgeeks.org/expression-tree/
public class QueryBaseListener implements QueryListener {
	//private Stack<Set<YelpRestaurant>> stack;
	private Set<YelpRestaurant> allRests;
	private Stack<Addable> stack;
	/**
	 * Constructor
	 */
	public QueryBaseListener(Map<String, Record> records){
		stack = new Stack<>();
		//allRests = records.values().stream().filter(r -> r instanceof YelpRestaurant).map(r -> (YelpRestaurant) r).collect(Collectors.toList());
		allRests = records.entrySet().parallelStream().map(t->t.getValue())
				.filter(t->t.getType().equals("business"))
				.map(t->(YelpRestaurant)t).collect(Collectors.toSet());
		//stack.push(allRests);
		//System.out.println("ALL RESTAURANTS:" + allRests);
	}

	/**
	 * Enter a parse tree produced by {@link QueryParser#root}.
	 * @param ctx the parse tree
	 */
	public void enterRoot(QueryParser.RootContext ctx){
		//added
		System.out.println("enter root");
		stack.push(new Or());
	}

	/**
	 * Exit a parse tree produced by {@link QueryParser#root}.
	 * @param ctx the parse tree
	 */
	public void exitRoot(QueryParser.RootContext ctx){
		System.out.println("exit root");
		assert stack.size() == 1;
	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#orExpr}.
	 * @param ctx the parse tree
	 */
	public void enterOrExpr(QueryParser.OrExprContext ctx){

		System.out.println("enter or");
		stack.push(new Or());
	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#orExpr}.
	 * @param ctx the parse tree
	 */
	public void exitOrExpr(QueryParser.OrExprContext ctx){
		System.out.println("exit or");
		/*int numChildren = ctx.getChildCount()/2 + 1;
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
		}*/
		Or or = (Or) stack.pop();
		stack.peek().addOperation(or);
	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#andExpr}.
	 * @param ctx the parse tree
	 */
	public void enterAndExpr(QueryParser.AndExprContext ctx){

		System.out.println("enter and");
		stack.push(new And());
	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#andExpr}.
	 * @param ctx the parse tree
	 */
	public void exitAndExpr(QueryParser.AndExprContext ctx){
		System.out.println("exit and");
		And and = (And) stack.pop();
		stack.peek().addOperation(and);
	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#atom}.
	 * @param ctx the parse tree
	 */
	public void enterAtom(QueryParser.AtomContext ctx){
		System.out.println("enter atom");
	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#atom}.
	 * @param ctx the parse tree
	 */
	public void exitAtom(QueryParser.AtomContext ctx){
		System.out.println("exit atom");
	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#in}.
	 * @param ctx the parse tree
	 */
	public void enterIn(QueryParser.InContext ctx){
		System.out.println("enter in");
	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#in}.
	 * @param ctx the parse tree
	 */
	public void exitIn(QueryParser.InContext ctx){
		System.out.println("exit in");
		//TODO: Find the appropriate get method
		//rests = rests.stream().filter(r -> Arrays.asList(r.getAddress()).contains(tokenString)).collect(Collectors.toList());
		String tokenString = ctx.STRING().toString();

		stack.peek().addOperation(new In(tokenString));
	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#category}.
	 * @param ctx the parse tree
	 */
	public void enterCategory(QueryParser.CategoryContext ctx){
		System.out.println("enter category");
	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#category}.
	 * @param ctx the parse tree
	 */
	public void exitCategory(QueryParser.CategoryContext ctx){
		String tokenString = ctx.STRING().toString();
		stack.peek().addOperation(new Category(tokenString));
		System.out.println("exit category");
	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#name}.
	 * @param ctx the parse tree
	 */
	public void enterName(QueryParser.NameContext ctx){
		System.out.println("enter name");
	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#name}.
	 * @param ctx the parse tree
	 */
	public void exitName(QueryParser.NameContext ctx){
		String tokenString = ctx.STRING().toString();

		System.out.println("exit name");
		stack.peek().addOperation(new Name(tokenString));
	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#rating}.
	 * @param ctx the parse tree
	 */
	public void enterRating(QueryParser.RatingContext ctx){
		System.out.println("enter rating");
	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#rating}.
	 * @param ctx the parse tree
	 */
	public void exitRating(QueryParser.RatingContext ctx){
		//TODO check that is the right number
		String ineq = ctx.ineq().toString();
		Double num = Double.valueOf(ctx.NUM().toString());
		System.out.println("exit rating");
		stack.peek().addOperation(new Rating(ineq, num));
	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#price}.
	 * @param ctx the parse tree
	 */
	public void enterPrice(QueryParser.PriceContext ctx){
		System.out.println("enter price");
	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#price}.
	 * @param ctx the parse tree
	 */
	public void exitPrice(QueryParser.PriceContext ctx){
		//TODO check that it is the right int
		System.out.println("exit price");
		String ineq = ctx.ineq().toString();
		Integer price = Integer.valueOf(ctx.NUM().toString());
		System.out.println(ineq + price);
		stack.peek().addOperation(new Price(ineq, price));
	}
	/**
	 * Enter a parse tree produced by {@link QueryParser#ineq}.
	 * @param ctx the parse tree
	 */
	public void enterIneq(QueryParser.IneqContext ctx){
		System.out.println("enter ineq");
	}
	/**
	 * Exit a parse tree produced by {@link QueryParser#ineq}.
	 * @param ctx the parse tree
	 */
	public void exitIneq(QueryParser.IneqContext ctx){
		System.out.println("exit ineq");
	}

	/**
	 * Returns a filtered list of restaurants satisfying the query conditions
	 */
	public Set<YelpRestaurant> evaluate(){
		return stack.get(0).evaluate(allRests);
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