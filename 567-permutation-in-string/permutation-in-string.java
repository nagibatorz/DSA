class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) return false;
        int[] freq = new int[26];
        char[] carr = s1.toCharArray();
        char[] carr2 = s2.toCharArray();
        for(char c : carr){
            freq[c - 'a']++;
        }
        int l = 0;
        for(int i = 0; i < s1.length(); i++){
            freq[carr2[i] - 'a']--;
        }
        for(int i = s1.length(); i < carr2.length; i++){
            if(foundAll(freq)) return true;
            freq[carr2[i] - 'a']--;
            freq[carr2[l] - 'a']++;
            l++;
        }
        return foundAll(freq);
    }

    private boolean foundAll(int[] freq){
        for(int a : freq){
            if(a != 0){
                return false;
            }
        }
        return true;
    }

}