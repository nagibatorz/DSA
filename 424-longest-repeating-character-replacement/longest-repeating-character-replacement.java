// Approach: Dynamic Sliding Winodw + Character Frequency array
class Solution {
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26]; // only uppercase English letters
        char[] carr = s.toCharArray();

        int l = 0;
        int maxF = 0; //max frequency variable, only updated at increment
        int res = 0;

        for(int r = 0; r < carr.length; r++){
            // increment character frequncy
            int idx = carr[r] - 'A'; //index of the char in the freq array
            int count = freq[idx] + 1;
            freq[idx]++;
            maxF = Math.max(count, maxF);

            // check if the substring is still valid
            // aka "do we have enough chars that are the same to maximize result"
            if((r - l + 1) - maxF > k){
                // decrement frequency and shrink current window
                freq[carr[l] - 'A']--;
                l++;
            } else{
                // if the window is still valid -> update max
                res = Math.max(res, (r - l + 1));
            }
        }
        return res;
    }
}