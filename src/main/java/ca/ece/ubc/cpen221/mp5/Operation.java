package ca.ece.ubc.cpen221.mp5;

import java.util.Set;

/**
 * Represents any operation that can be evaluated such as in an address, category, etc...
 */
public interface Operation {
    /**
     * Evaluates the operation, returns a set conformed by all the restaurants of set that conform to the condition of the operation.
     * @param set the input set of restaurants
     * @return a set x such that a restaurant r  is in x if and only if r is in set and conforms to the operation
     */
    Set<YelpRestaurant> evaluate(Set<YelpRestaurant> set);
}
