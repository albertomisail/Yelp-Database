package ca.ece.ubc.cpen221.mp5;

import java.util.*;
import java.util.stream.*;

public class QueryListenerCollect implements QueryListener {
    private Stack<List<YelpRestaurant>> restaurants;
    private List<YelpRestaurant> allRests;

    /**
     * Constructor
     */
    public QueryListenerCollect(Map<String, Record> records){
       restaurants = new Stack<>();
       //@TODO: is this the right type?
       this.allRests = records.values().stream().filter(r -> Arrays.asList(r.getType()).equals("Business")).collect(Collectors.toList());
    }
    /**
     * Returns a copy of the list of all restaurants
     */
    //may need to sync this with database (i.e. it changes when the database is modified)
    public List<YelpRestaurant> getRestaurants(){
        List<YelpRestaurant> ret = new ArrayList<>(allRests);
        return ret;
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
        List<YelpRestaurant> rests= restaurants.pop();
        List<YelpRestaurant> toCompare;
        while(!restaurants.isEmpty()){
            toCompare = restaurants.pop();
            for(YelpRestaurant r: toCompare){
                if(!rests.contains(r)) rests.add(r);
            }
        }
        restaurants.push(rests);
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
        List<YelpRestaurant> rests= restaurants.pop();
        List<YelpRestaurant> toCompare;
        while(!restaurants.isEmpty()){
            toCompare = restaurants.pop();
            rests.stream().filter(r -> toCompare.contains(r)).collect(Collectors.toList());
        }
        restaurants.push(rests);

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

        List<YelpRestaurant> rests = getRestaurants();

        //TODO: Find the appropriate get method
        rests = rests.stream().filter(r -> Arrays.asList(r.get()).contains(tokenString)).collect(Collectors.toList());

        this.restaurants.push(rests);

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
        String ineq = ctx.ineq().toString();
        Double num = Double.valueOf(ctx.NUM().toString());

        List<YelpRestaurant> rests = getRestaurants();

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

        List<YelpRestaurant> rests = getRestaurants();

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
}
