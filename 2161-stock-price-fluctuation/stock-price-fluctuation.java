class StockPrice {
    Map<Integer, Integer> records;
    TreeMap<Integer, Integer> timestamps;
    int current;

    public StockPrice() {
        this.records = new HashMap<>();
        this.timestamps = new TreeMap<>();
        this.current = 0;
    }
    
    public void update(int timestamp, int price) {
        if(records.containsKey(timestamp)){
            int prev = records.get(timestamp);
            timestamps.put(prev, timestamps.get(prev) - 1);
            if(timestamps.get(prev) == 0) timestamps.remove(prev);
        }
        records.put(timestamp, price);
        current = Math.max(timestamp, current);
        timestamps.put(price, timestamps.getOrDefault(price, 0) + 1);
    }
    
    public int current() {
        return records.get(current);
    }
    
    public int maximum() {
        return timestamps.lastKey();
    }
    
    public int minimum() {
        return timestamps.firstKey();
    }
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */