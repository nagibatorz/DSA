class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        int n = searchWord.length();
        Arrays.sort(products);

        for(int i = 1 ; i <= n; i++){
            String key = searchWord.substring(0, i);
            List<String> level = new ArrayList<>();
            for(String s : products){
                if(s.length() >= i && s.substring(0, i).equals(key)){
                    level.add(s);
                }
                if(level.size() == 3){
                    break;
                }
            }
            res.add(level);
        }
        return res;
    }
}