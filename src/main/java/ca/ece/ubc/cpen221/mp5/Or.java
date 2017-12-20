package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Or implements Addable{
    private List<Operation> operationList;

    public Or(){
        operationList = new ArrayList<>();
    }

    public void addOperation(Operation o){
        operationList.add(o);
    }

    /**
     * Evaluates the operation, returns a set conformed by the union of the set of restaurants that conform to the condition of the operations that conform the and expression.
     * @param set the input set of restaurants
     * @return a set x such that a restaurant r  is in x if and only if r is in set and conforms to the operation
     */
    public Set<YelpRestaurant> evaluate(Set<YelpRestaurant> set){
        Set<YelpRestaurant> result = new HashSet<>();
        for(Operation e : operationList){
            result.addAll(e.evaluate(set));
        }
        return result;
    }
}
