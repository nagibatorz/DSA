class Solution {
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int l = 0;
        int maxF = 0;
        int res = 0;
        for(int r = 0; r < s.length(); r++){
            char c = s.charAt(r);
            if(!map.containsKey(c)){
                map.put(c, 1);
                maxF = Math.max(1, maxF);
            } else{
                int count = map.get(c) + 1;
                map.put(c, count);
                maxF = Math.max(count, maxF);
            }
            if((r - l + 1) - maxF > k){
                map.put(s.charAt(l), map.get(s.charAt(l)) - 1);
                l++;
            } else{
                res = Math.max(res, (r - l + 1));
            }
        }
        return res;
    }
}