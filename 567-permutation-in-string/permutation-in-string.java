class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) return false;
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        char[] carr1 = s1.toCharArray();
        char[] carr2 = s2.toCharArray();
        for(int i = 0; i < carr1.length; i++){
            freq1[carr1[i] - 'a']++;
            freq2[carr2[i] - 'a']++;
        }
        int matches = 0;
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
            } else if(freq1[r] + 1 == freq2[r]){
                matches--;
            }
            int left = carr2[l] - 'a';
            freq2[left]--;
            if(freq1[left] - 1 == freq2[left]){
                matches--;
            } else if(freq1[left] == freq2[left]){
                matches++;
            }
            l++;
        }
        return matches == 26;
    }
}