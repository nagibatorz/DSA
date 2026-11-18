class TimeMap {
    Map<String, TreeMap<Integer, String>> map;

    public TimeMap() {
        this.map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)){
            map.put(key, new TreeMap<>());
        }
        map.get(key).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if(!map.containsKey(key)) return "";
        TreeMap<Integer, String> keyMap = map.get(key);
        Map.Entry<Integer, String> entry = keyMap.floorEntry(timestamp);
        return entry == null ? "" : entry.getValue();
    }

    
}
