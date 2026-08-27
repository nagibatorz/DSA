class TimeMap {
    Map<String, List<Pair>> map;

    public TimeMap() {
        this.map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)){
            List<Pair> list = new ArrayList<>();
            Pair pair = new Pair(timestamp, value);
            list.add(pair);
            map.put(key, list);
        } else{
            map.get(key).add(new Pair(timestamp, value));
        }
    }
    
    public String get(String key, int timestamp) {
        if(!map.containsKey(key)) return "";
        String res = "";
        List<Pair> list = map.get(key);
        int size = list.size();
        if(timestamp >= list.get(size - 1).ts){
            return list.get(size - 1).val;
        }
        int l = 0, r = size - 1;
        while(l <= r){
            int mid = l + (r - l) / 2;

            if(list.get(mid).ts <= timestamp){
                res = list.get(mid).val;
                l = mid + 1;
            } else{
                r = mid - 1;
            }
        }
        return res;
    }

    class Pair{
        int ts;
        String val;
        
        public Pair(int ts, String val){
            this.val = val;
            this.ts = ts;
        }
    }
}
