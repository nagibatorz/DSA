// Runtime optimized to O(26) + O(n) by state tracking using matches variable
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // s2 cannot contain permutation of s1 if s1 is longer
        if(s1.length() > s2.length()) return false;
        
        //Declare character frequency arrays for both strings
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        //convert to char array for convenience
        char[] carr1 = s1.toCharArray();
        char[] carr2 = s2.toCharArray();
        for(int i = 0; i < carr1.length; i++){
            freq1[carr1[i] - 'a']++;
            freq2[carr2[i] - 'a']++;
        }

        int matches = 0;

        //count how many matches we have in s2.substring(0, s1.length)
        // could be reduced and added in the main loop if optimizng further
        for(int i = 0; i < 26; i++){
            if(freq1[i] == freq2[i]) matches++;
        }

        int l = 0;
        for(int i = carr1.length; i < carr2.length; i++){
            if(matches == 26) return true;
            int r = carr2[i] - 'a';
            freq2[r]++;
            if(freq1[r] == freq2[r]){
                matches++;
            } else if(freq1[r] + 1 == freq2[r]){ //adding character broke the match we needed 
                matches--;
            }
            int left = carr2[l] - 'a';
            freq2[left]--;
            if(freq1[left] - 1 == freq2[left]){ //removing character broke the match we needed 
                matches--;
            } else if(freq1[left] == freq2[left]){
                matches++;
            }
            l++;
        }
        return matches == 26;
    }
}