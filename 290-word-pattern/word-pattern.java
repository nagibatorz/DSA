class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        char[] carr = pattern.toCharArray();
        int len = carr.length;
        if(words.length != len) return false;
        Set<String> seen = new HashSet<>();
        Map<Character, String> map = new HashMap<>();
        for(int i = 0; i < len; i++){
            if(!map.containsKey(carr[i])){
                if(seen.contains(words[i])) return false;
                map.put(carr[i], words[i]);
                seen.add(words[i]);
            } else{
                if(!map.get(carr[i]).equals(words[i])) return false;
            }
        }
        return true;

    }
}