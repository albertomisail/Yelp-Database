package ca.ece.ubc.cpen221.mp5;

import java.util.*;
import java.util.stream.*;

public class QueryListenerCollect implements QueryListener {
    private Stack<List<YelpRestaurant>> restaurants;

    /**
     * Constructor
     */
    public QueryListenerCollect(){
       restaurants = new Stack<>();
    }
    /**
     *
     */
    //may need to sync this with database (i.e. it changes when the database is modified)
    public List<YelpRestaurant> getRestaurants(){
        //@TODO: Implement
        return null;
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

       List<YelpRestaurant> rests = getRestaurants();

       rests = rests.stream().filter(r -> Arrays.asList(r.getCategories()).contains(tokenString)).collect(Collectors.toList());

       this.restaurants.push(rests);
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

        List<YelpRestaurant> rests = getRestaurants();

        rests = rests.stream().filter(r -> Arrays.asList(r.getName()).contains(tokenString)).collect(Collectors.toList());

        this.restaurants.push(rests);
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
        String ineq = ctx.ineq.toString();

        List<YelpRestaurant> rests = getRestaurants();

        rests = rests.stream().filter(r -> Arrays.asList(r.getStars()) < rating).collect(Collectors.toList());

        this.restaurants.push(rests);

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
}
