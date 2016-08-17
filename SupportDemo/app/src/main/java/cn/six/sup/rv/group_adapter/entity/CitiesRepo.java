package cn.six.sup.rv.group_adapter.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songzhw on 2016-08-15
 */
public class CitiesRepo {
    public static List<Province> country(){
        List<Province> ca = new ArrayList<>();
        Province p1 = new Province("Ontario");
        p1.addCity("Toronto");p1.addCity("London");p1.addCity("Waterloop");p1.addCity("York");p1.addCity("Ottawa");
        Province p2 = new Province("British Columbia");
        p2.addCity("Vacouver");p2.addCity("Kamploops");p2.addCity("Hope");
        Province p3 = new Province("Alberta");
        p3.addCity("Edmonton");p3.addCity("Calgary");
        ca.add(p1);ca.add(p2);ca.add(p3);
        return ca;
    }
}
