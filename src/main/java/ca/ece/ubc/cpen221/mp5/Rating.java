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
