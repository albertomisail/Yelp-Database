package ca.ece.ubc.cpen221.mp5;

import java.util.HashSet;
import java.util.Set;

/**
 * Represent the name operation
 */
public class Name implements Operation{
    private String name;

    public Name(String name){
        this.name = name;
    }

    /**
     * Evaluates the operation, returns a set conformed by all the restaurants of set that their name is equal to name.
     * @param set the input set of restaurants
     * @return a set x such that a restaurant r  is in x if and only if r is in set and conforms to the operation
     */
    public Set<YelpRestaurant> evaluate(Set<YelpRestaurant> set){
        Set<YelpRestaurant> result = new HashSet<>();
        set.stream().forEach(t->{
            if(t.getName().equals(name)){
                result.add(t);
            }
        });
        return result;
    }
}
