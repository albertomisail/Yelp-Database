package ca.ece.ubc.cpen221.mp5;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Price implements Operation {
    private String ineq;
    private int price;

    public Price(String ineq, Integer price){
        this.price = price;
        this.ineq = ineq;
    }

    @Override
    public Set<YelpRestaurant> evaluate(Set<YelpRestaurant> set) {
        Set<YelpRestaurant> result = new HashSet<>();
        switch (ineq){
            case "<":
                result = set.stream().filter(r -> r.getPrice() < price).collect(Collectors.toSet());
                break;
            case "<=":
                result = set.stream().filter(r -> r.getPrice() <= price).collect(Collectors.toSet());
                break;
            case "=":
                result = set.stream().filter(r -> r.getPrice() == price).collect(Collectors.toSet());
                break;
            case ">=":
                result = set.stream().filter(r -> r.getPrice() >= price).collect(Collectors.toSet());
                break;
            case ">":
                result = set.stream().filter(r -> r.getPrice() > price).collect(Collectors.toSet());
                break;
            default:
                assert(false);
        }
        return result;
    }
}
