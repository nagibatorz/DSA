class Solution {
    Map<Character, char[]> digitToLetter;
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0) return new ArrayList<>();
        digitToLetter = new HashMap<>();
        digitToLetter.put('2', new char[]{'a', 'b', 'c'});
        digitToLetter.put('3', new char[]{'d', 'e', 'f'});
        digitToLetter.put('4', new char[]{'g', 'h', 'i'});
        digitToLetter.put('5', new char[]{'j', 'k', 'l'});
        digitToLetter.put('6', new char[]{'m', 'n', 'o'});
        digitToLetter.put('7', new char[]{'p', 'q', 'r', 's'});
        digitToLetter.put('8', new char[]{'t', 'u', 'v'});
        digitToLetter.put('9', new char[]{'w', 'x', 'y', 'z'});
        char[] carr = digits.toCharArray();
        List<String> res = new ArrayList<>();
        bt(0, new StringBuilder(), carr, res);
        return res;
    }

    private void bt(int idx, StringBuilder soFar, char[] carr, List<String> res){
        if(soFar.length() == carr.length){
            res.add(soFar.toString());
            return;
        }
        for(char c : digitToLetter.get(carr[idx])){
            soFar.append(c);
            bt(idx + 1, soFar, carr, res);
            soFar.deleteCharAt(soFar.length() - 1);
        }
    }

}
