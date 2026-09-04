class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] freq = new int[128];
        int l = 0;
        int max = 0;
        for(int r = 0; r < s.length(); r++){
            int idx = s.charAt(r);
            freq[idx]++;
            while(freq[idx] > 1){
                freq[s.charAt(l)]--;
                l++;
            }
            max = Math.max(max, r - l + 1);

        }
        return max;
    }
}