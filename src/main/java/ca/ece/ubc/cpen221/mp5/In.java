package ca.ece.ubc.cpen221.mp5;

import java.util.HashSet;
import java.util.Set;

public class In implements Operation{
    private String address;

    public In(String address){
        this.address = address;
    }

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
