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

    public Set<YelpRestaurant> evaluate(Set<YelpRestaurant> set){
        Set<YelpRestaurant> result = new HashSet<>();
        for(Operation e : operationList){
            result.addAll(e.evaluate(set));
        }
        return result;
    }
}
