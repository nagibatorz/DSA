class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        // Pre-allocate a character array of the exact required length
        char[] current = new char[n * 2];
        bt(res, n, 0, 0, current, 0);
        return res;
    }

    private void bt(List<String> res, int n, int open, int close, char[] current, int index) {
        // Base case: the array is full
        if (index == 2 * n) {
            res.add(new String(current));
            return;
        }

        // Add '(' if we haven't used all 'n' opening brackets
        if (open < n) {
            current[index] = '(';
            bt(res, n, open + 1, close, current, index + 1);
        }
        
        // We always have to add "(" before we can add ")"
        // so we check whether we have enough "("
        if (close < open) {
            current[index] = ')';
            bt(res, n, open, close + 1, current, index + 1);
        }
    }
}
