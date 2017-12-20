package ca.ece.ubc.cpen221.mp5;

import java.util.HashSet;
import java.util.Set;

public class Category implements Operation{
    private String category;

    public Category(String category){
        this.category = category;
    }

    /**
     * Evaluates the operation, returns a set conformed by all the restaurants of set for which category is present in the set of categories of the restaurant
     * @param set the input set of restaurants
     * @return a set x such that a restaurant r  is in x if and only if r is in set and conforms to the operation
     */
    public Set<YelpRestaurant> evaluate(Set<YelpRestaurant> set){
        Set<YelpRestaurant> result = new HashSet<>();
        set.stream().forEach(t->{
            if(t.getCategories().contains(category)){
                result.add(t);
            }
        });
        return result;
    }
}
