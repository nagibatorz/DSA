class Solution {
    public String minWindow(String s, String t) {
        if(s == null || t == null || s.isEmpty() || t.isEmpty() || s.length() < t.length()) return "";
        Map<Character, Integer> freq = new HashMap<>();
        for(char c : t.toCharArray()){
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        int minLength = Integer.MAX_VALUE;
        int i = 0, j = 0;
        int count = freq.size();
        int left = 0, right = s.length() - 1;
        boolean found = false;

        while(j < s.length()){
            char endChar = s.charAt(j++);

            //check if we have found any needed chars
            if(freq.containsKey(endChar)){
                freq.put(endChar, freq.get(endChar) - 1);
                if(freq.get(endChar) == 0) count -= 1;
            }

            //have not found all needed chars
            if(count > 0) continue;

            // shrink the window while we have needed chars
            while(count == 0){
                char startChar = s.charAt(i++);
                if(freq.containsKey(startChar)){
                    freq.put(startChar, freq.get(startChar) + 1);
                    if(freq.get(startChar) > 0) count += 1;
                }
                // cannot do math.min because we need to save the pointers as well
                if(j - i < minLength){
                    left = i;
                    right = j;
                    minLength = j - i;

                    found = true;
                }
            }    
        }

        //returning left - 1 and right due to how I was incrementing pointers in the loop
        return found ? s.substring(left - 1, right) : "";
    }
}