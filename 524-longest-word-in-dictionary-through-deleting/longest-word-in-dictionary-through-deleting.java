class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        String res = "";
        for(String str : dictionary){
            if(valid(str, s)){
                if(res.length() < str.length() || (res.length() == str.length() && res.compareTo(str) >= 1)){
                    res = str;
                }
            }
        }
        return res;
    }

    private boolean valid(String str, String s){
        int i = 0, j = 0;
        while(i < s.length() && j < str.length()){
            if(str.charAt(j) == s.charAt(i)) j++;
            i++;
        }
        return j == str.length();
    }
}