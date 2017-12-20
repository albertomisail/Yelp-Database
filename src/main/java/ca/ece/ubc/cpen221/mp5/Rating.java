package ca.ece.ubc.cpen221.mp5;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Rating implements Operation {
    private String ineq;
    private double stars;
    private static final double tolerance = Math.pow(10, -8);

    public Rating(String ineq, Double stars){
        this.stars = stars;
        this.ineq = ineq;
    }

    /**
     * Evaluates the operation, returns a set conformed by the restaurnats of set such that restaurant.stars() ineq stars is true.
     * @param set the input set of restaurants
     * @return a set x such that a restaurant r  is in x if and only if r is in set and conforms to the operation
     */
    @Override
    public Set<YelpRestaurant> evaluate(Set<YelpRestaurant> set) {
        Set<YelpRestaurant> result = new HashSet<>();

        if(ineq.equals("<")) result = set.stream().filter(r -> (stars-r.getStars())>tolerance).collect(Collectors.toSet());
        else if(ineq.equals("<=")) result = set.stream().filter(r -> (stars-r.getStars())>tolerance||(stars-r.getStars()<tolerance&&r.getStars()-stars>tolerance)).collect(Collectors.toSet());
        else if(ineq.equals("=")) result = set.stream().filter(r -> (stars-r.getStars()<tolerance&&r.getStars()-stars<tolerance)).collect(Collectors.toSet());
        else if(ineq.equals(">=")) result = set.stream().filter(r -> (r.getStars()-stars)<tolerance).collect(Collectors.toSet());
        else if(ineq.equals(">")) result = set.stream().filter(r -> (r.getStars()-stars)>tolerance).collect(Collectors.toSet());
        else assert(false);

        return result;
    }
}
