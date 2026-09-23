class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        int n = searchWord.length();
        Arrays.sort(products);
        int l = 0, r = products.length - 1;
        for(int i = 0 ; i < n; i++){
            char c = searchWord.charAt(i);
            List<String> level = new ArrayList<>();
            
            while(l <= r && (products[l].length() <= i || products[l].charAt(i) != c)) l++;
            while(l <= r && (products[r].length() <= i || products[r].charAt(i) != c)) r--;

            int window = r - l + 1;
            for(int j = 0; j < Math.min(3, window); j++){
                level.add(products[j + l]);
            }

            res.add(level);
        }
        return res;
    }
}