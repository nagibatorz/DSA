class Solution {
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        char[] carr = s.toCharArray();
        int l = 0;
        int maxF = 0;
        int res = 0;
        for(int r = 0; r < carr.length; r++){
            int idx = carr[r] - 'A';
            int count = freq[idx] + 1;
            freq[idx]++;
            maxF = Math.max(count, maxF);

            if((r - l + 1) - maxF > k){
                freq[carr[l] - 'A']--;
                l++;
            } else{
                res = Math.max(res, (r - l + 1));
            }
        }
        return res;
    }
}