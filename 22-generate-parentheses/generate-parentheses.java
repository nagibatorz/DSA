// Backtracking with two trackers
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        bt(res, n, 0, 0, new ArrayList<>());
        return res;
    }

    private void bt(List<String> res, int n, int o, int c, List<String> soFar){
        if(c == n){
            res.add(String.join("", new ArrayList<>(soFar)));
            return;
        }
        // We always have to add "(" before we can add ")"
        // so we check whether we have enough "("
        if(o > c){
            soFar.add(")");
            bt(res, n, o, c + 1, soFar);
            soFar.remove(soFar.size() - 1);
        }

        // Check if we still have parenthesis to add
        if(o < n){
            soFar.add("(");
            bt(res, n, o + 1, c, soFar);
            soFar.remove(soFar.size() - 1);
        }
    }
}