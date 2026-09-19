class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        String res = "";
        int n = s.length();
        for(String str : dictionary){
            if(str.length() > n) continue;
            if(valid(str, s, n)){
                if(res.length() < str.length() 
                || (res.length() == str.length() && res.compareTo(str) >= 1)){ // avoid sorting by checking whether new potential answer is lexicographically smaller
                    res = str;
                }
            }
        }
        return res;
    }

    // check if string in dictionary can be built
    private boolean valid(String str, String s, int n){
        int i = 0, j = 0;
        while(i < n && j < str.length()){
            if(str.charAt(j) == s.charAt(i)) j++;
            i++;
        }
        return j == str.length();
    }
}