class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        char[] carr = s.toCharArray();
        int l = 0;
        int max = 0;
        for(int r = 0; r < carr.length; r++){
            while(set.contains(carr[r])){
                set.remove(carr[l++]);
            }
            set.add(carr[r]);
            max = Math.max(max, (r - l + 1));
        }
        return max;
    }
}