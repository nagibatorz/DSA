import java.util.*;
import java.math.*;

class Solution {
    public List<List<Integer>> asteroidColliVar(List<Integer> mass, List<Integer> speed) {
        // TODO: Implement asteroidColliVar logic

        List<List<Integer>> res = new ArrayList<>();
        // using list as a stack
        List<Integer> sizes = new ArrayList<>();
        List<Integer> speeds = new ArrayList<>();
        res.add(sizes);
        res.add(speeds);
        for(int i = 0; i < mass.size(); i++){
            boolean broke = false;
            // is the asteroid destroyed - terminates the loop early
            // is processed asteroids not empty
            // are asteroids colliding because of moving towards each other
            // are asteroids moving at the same speed and one of them is catching up
            while(!broke && !speeds.isEmpty() && ((speeds.get(speeds.size() - 1) > 0 && speed.get(i) < 0) 
                 || (speeds.get(speeds.size() - 1) * speed.get(i) > 0) && speeds.get(speeds.size() - 1) > speed.get(i))){ // keep processing until we either have no available asteroids or the current one is destroyed or the current one doesn't meet collision criteria
                if(sizes.get(sizes.size() - 1) > mass.get(i)){ // current asteroid gets destroyed
                    broke = true;
                } else if(sizes.get(sizes.size() - 1) < mass.get(i)){ // asteroid on the top gets destroyed
                    sizes.remove(sizes.size() - 1);
                    speeds.remove(speeds.size() - 1);
                } else{ // both get destroyed
                    broke = true;
                    sizes.remove(sizes.size() - 1);
                    speeds.remove(speeds.size() - 1);
                }
            }
            // add the current asteroid to the stack 
            if(!broke){
                sizes.add(mass.get(i));
                speeds.add(speed.get(i));
            }
        }
    return res;
     }
};
