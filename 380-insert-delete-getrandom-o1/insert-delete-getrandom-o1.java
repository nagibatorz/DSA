class RandomizedSet {

    private Random rand = new Random();
    private Map<Integer, Integer> indexes;
    private List<Integer> nums;

    public RandomizedSet() {
        this.indexes = new HashMap<>();
        this.nums = new ArrayList<>();
    }
    
    public boolean insert(int val) {
        if(indexes.containsKey(val)) return false;
        nums.add(val);
        indexes.put(val, nums.size() - 1);
        return true;
    }
    
    public boolean remove(int val) {
        if(!indexes.containsKey(val)) return false;
        int oldIndex = indexes.get(val);
        int temp = nums.get(nums.size() - 1);
        nums.set(oldIndex, temp);
        indexes.put(temp, oldIndex);
        indexes.remove(val);
        nums.remove(nums.size() - 1);
        return true;
    }

    
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */