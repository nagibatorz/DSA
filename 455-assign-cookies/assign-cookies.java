// Two pointers approach
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        //one pointer for each array
        int i = 0;
        int j = 0;
        while(i < g.length && j < s.length){
            if(s[j] >= g[i]){
                i++;
            }
            j++;
        }

        // at the end i points to how many children were satisfied
        return i;
    }
}