class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new LinkedList<>();
        bt(res, new ArrayList<>(), s, 0, s.length());
        return res;
    }

    private void bt(List<List<String>> res, List<String> branchList, String s, int i, int n){
        if(i == n){
            res.add(new ArrayList<>(branchList));
            return;
        }
        for(int j = i; j < n; j++){
            if(checkPalindrome(i, j, s)){
                branchList.add(s.substring(i, j + 1));
                bt(res, branchList, s, j + 1, n);
                branchList.remove(branchList.size() - 1);
            }
        }
    }

    private boolean checkPalindrome(int start, int end, String s){
        while(start <= end){
            if(s.charAt(start) != s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}