package ca.ece.ubc.cpen221.mp5;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents an IN operation
 * RI: address is not null
 * AF: each in operation is defined by its one field, address
 */
public class In implements Operation{
    private String address;

    public In(String address){
        this.address = address;
    }

    /**
     * Evaluates the operation, returns a set conformed by all the restaurants of set that their address contains the string address
     * @param set the input set of restaurants
     * @return a set x such that a restaurant r  is in x if and only if r is in set and conforms to the operation
     */
    public Set<YelpRestaurant> evaluate(Set<YelpRestaurant> set){
        Set<YelpRestaurant> result = new HashSet<>();
        set.stream().forEach(t->{
            if(t.getAddress().contains(address)){
                result.add(t);
            }
        });
        return result;
    }
}
