class Solution {
    public boolean canChange(String start, String target) {
        int n = start.length();
        int i = 0, j = 0;

        while (i < n || j < n) {
            // 1. Advance past underscores with bounds checking
            while (i < n && start.charAt(i) == '_') {
                i++;
            }
            while (j < n && target.charAt(j) == '_') {
                j++;
            }

            // 2. Both reached the end simultaneously and didnt hit any later if statements -> valid match
            if (i == n && j == n) {
                return true;
            }

            // 3. One reached the end before the other -> count mismatch
            if (i == n || j == n) {
                return false;
            }

            // 4. Mismatched character type
            if (start.charAt(i) != target.charAt(j)) {
                return false;
            }

            // 5. Check directional movement rules
            // 'L' can only move left , so when start has left earlier than target -> invalid
            if (start.charAt(i) == 'L' && i < j) {
                return false;
            }
            // 'R' can only move right, so when start has R later than target -> invalid
            if (start.charAt(i) == 'R' && i > j) {
                return false;
            }

            // increment pointers
            i++;
            j++;
        }

        return true;
    }
}