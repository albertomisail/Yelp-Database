package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class And implements Addable{
    private List<Operation> operationList;

    public And(){
        operationList = new ArrayList<>();
    }

    public void addOperation(Operation o){
        operationList.add(o);
    }

    public Set<YelpRestaurant> evaluate(Set<YelpRestaurant> set){
        Set<YelpRestaurant> result = new HashSet<>(set);
        for(Operation e : operationList){
            //https://stackoverflow.com/questions/8882097/how-to-calculate-the-intersection-of-two-sets
            result.retainAll(e.evaluate(set));
        }
        return result;
    }
}

