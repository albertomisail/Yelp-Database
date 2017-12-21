package ca.ece.ubc.cpen221.mp5;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * RI: ineq and price are not null
 * AF: value of ineq and price fields
 */

public class Price implements Operation {
    private String ineq;
    private double price;

    public Price(String ineq, double price){
        this.price = price;
        this.ineq = ineq;
    }

    /**
     * Evaluates the operation, returns a set conformed by the restaurnats of set such that restaurant.price() ineq price is true.
     * @param set the input set of restaurants
     * @return a set x such that a restaurant r  is in x if and only if r is in set and conforms to the operation
     */
    @Override
    public Set<YelpRestaurant> evaluate(Set<YelpRestaurant> set) {
        Set<YelpRestaurant> result = new HashSet<>();
        if(ineq.equals("<")) result = set.stream().filter(r -> r.getPrice() < price).collect(Collectors.toSet());
        else if(ineq.equals("<=")) result = set.stream().filter(r -> r.getPrice() <= price).collect(Collectors.toSet());
        else if(ineq.equals("=")) result = set.stream().filter(r -> r.getPrice() == price).collect(Collectors.toSet());
        else if(ineq.equals(">=")) result = set.stream().filter(r -> r.getPrice() >= price).collect(Collectors.toSet());
        else if(ineq.equals(">")) result = set.stream().filter(r -> r.getPrice() > price).collect(Collectors.toSet());
        return result;
    }
}
