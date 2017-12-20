package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents and an expression
 */
public class And implements Addable{
    private List<Operation> operationList;

    public And(){
        operationList = new ArrayList<>();
    }

    public void addOperation(Operation o){
        operationList.add(o);
    }

    /**
     * Evaluates the operation, returns a set conformed by the intersection of the set of restaurants that conform to the condition of the operations that conform the and expression.
     * @param set the input set of restaurants
     * @return a set x such that a restaurant r  is in x if and only if r is in set and conforms to the operation
     */
    public Set<YelpRestaurant> evaluate(Set<YelpRestaurant> set){
        Set<YelpRestaurant> result = new HashSet<>(set);
        for(Operation e : operationList){
            //idea taken from:
            //https://stackoverflow.com/questions/8882097/how-to-calculate-the-intersection-of-two-sets
            result.retainAll(e.evaluate(set));
        }
        return result;
    }
}

