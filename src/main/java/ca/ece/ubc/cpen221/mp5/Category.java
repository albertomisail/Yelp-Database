package ca.ece.ubc.cpen221.mp5;

import java.util.HashSet;
import java.util.Set;

public class Category implements Operation{
    private String category;

    public Category(String category){
        this.category = category;
    }

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
