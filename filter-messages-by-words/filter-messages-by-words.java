import java.util.*;
import java.math.*;

class Solution {
    public List<List<String>> filterMessagesByWords(List<List<String>> messages, List<String> unsafeWords) {
        // TODO: Implement filterMessagesByWords logic
        Set<String> unsafe = new HashSet<>();
        List<List<String>> res = new ArrayList<>();
        for(String word : unsafeWords){
            unsafe.add(word.toLowerCase());
        }

        for(int i = 0; i < messages.size(); i++){
            String user = messages.get(i).get(0);
            String message = messages.get(i).get(1);
            boolean isSafe = true;
            for(String word : message.split("\\s+")){
                if(unsafe.contains(word.toLowerCase(Locale.ROOT))){
                    isSafe = false;
                    break;
                }
            }
            if(isSafe){
                res.add(messages.get(i));
            }
        }
        return res;
    }
};
