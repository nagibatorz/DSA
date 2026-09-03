class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        
        int[] freq = new int[26];
        // 1. Populate the frequency array for s1
        for (char c : s1.toCharArray()) {
            freq[c - 'a']++;
        }
        
        int required = s1.length(); // How many total valid characters we need
        char[] s2Chars = s2.toCharArray();
        int l = 0;
        
        for (int r = 0; r < s2Chars.length; r++) {
            // If the character at 'r' is something we need, decrease our required count
            if (freq[s2Chars[r] - 'a'] > 0) {
                required--;
            }
            // Always decrease the frequency in our map
            freq[s2Chars[r] - 'a']--;
            
            // If our window gets too big, shrink it from the left
            if (r - l + 1 > s1.length()) {
                // If the character we are kicking out was one we needed, increase required
                if (freq[s2Chars[l] - 'a'] >= 0) {
                    required++;
                }
                // Always add the character back to the frequency map
                freq[s2Chars[l] - 'a']++;
                l++;
            }
            
            // If we found all required characters, we have a match!
            if (required == 0) {
                return true;
            }
        }
        
        return false;
    }
}