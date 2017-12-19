package ca.ece.ubc.cpen221.mp5;

import java.util.HashSet;
import java.util.Set;

public class Name implements Operation{
    private String name;

    public Name(String name){
        this.name = name;
    }

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
