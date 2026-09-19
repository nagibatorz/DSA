class Solution {
    public String decodeString(String s) {
        Deque<Integer> counts = new ArrayDeque<>();

        Deque<StringBuilder> results = new ArrayDeque<>(); //stacks, queues, etc. are implemented using generics so storing an object works

        int idx = 0;
        StringBuilder res = new StringBuilder(); //current string 

        while(idx < s.length()){
            char c = s.charAt(idx);
            if(Character.isDigit(c)){ //get the entire number of repetitions
                int count = 0;
                while(Character.isDigit(s.charAt(idx))){
                    count = count * 10 + (s.charAt(idx) - '0');
                    idx++;
                }
                counts.push(count);
            } else if(c == '['){ //remember current string and build a new one
                results.push(res);
                res = new StringBuilder();
                idx++;
            } else if(c == ']'){ //repeat current string 
                StringBuilder sb = new StringBuilder(results.pop());
                int count = counts.pop();
                for(int i = 0; i < count; i++){
                    sb.append(res);
                }
                res = sb;
                idx++;
            } else{ // build current string 
                res.append(c);
                idx++;
            }
        }
        return res.toString();
    }
}