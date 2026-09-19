class Solution {
    public String decodeString(String s) {
        Deque<Integer> counts = new ArrayDeque<>();
        Deque<StringBuilder> results = new ArrayDeque<>();

        int idx = 0;
        StringBuilder res = new StringBuilder();

        while(idx < s.length()){
            char c = s.charAt(idx);
            if(Character.isDigit(c)){
                int count = 0;
                while(Character.isDigit(s.charAt(idx))){
                    count = count * 10 + (s.charAt(idx) - '0');
                    idx++;
                }
                counts.push(count);
            } else if(c == '['){
                results.push(res);
                res = new StringBuilder();
                idx++;
            } else if(c == ']'){
                StringBuilder sb = new StringBuilder(results.pop());
                int count = counts.pop();
                for(int i = 0; i < count; i++){
                    sb.append(res);
                }
                res = sb;
                idx++;
            } else{
                res.append(c);
                idx++;
            }
        }
        return res.toString();
    }
}