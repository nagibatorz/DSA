import java.util.*;

class Main {

    public static void main(String[] args){
        VersionedKV test = new VersionedKV();
        System.out.println(test.set("a", "x")); // 1
        System.out.println(test.set("b", "y")); // 2
        System.out.println(test.get("a")); // x
        System.out.println(test.delete("a")); // 3
        System.out.println(test.getAt("a", 2)); // x
        System.out.println(test.get("a")); // null
    }

}

class VersionedKV{
    int counter;
    Map<String, TreeMap<Integer, String>> snapshots;

    public VersionedKV(){
        this.counter = 0;
        this.snapshots = new HashMap<>();
    }

    public int set(String key, String value){
        int version = ++counter;
        if(snapshots.containsKey(key)){
            snapshots.get(key).put(version, value);
        } else{
            TreeMap<Integer, String> entry = new TreeMap<>();
            entry.put(version, value);
            snapshots.put(key, entry);
        }
        return version;
    }

    public int delete(String key){
        if(!snapshots.containsKey(key)) return -1;
        int version = ++counter;
        snapshots.get(key).put(version, null);
        return version;
    }

    public String get(String key){
        if(!snapshots.containsKey(key)) return null;
        TreeMap<Integer, String> keySnapshot = snapshots.get(key);
        return keySnapshot.get(keySnapshot.lastKey());
    }

    public String getAt(String key, int version){
        if(!snapshots.containsKey(key)) return null;
        TreeMap<Integer, String> keySnapshot = snapshots.get(key);
        Map.Entry<Integer, String> versionVal = keySnapshot.floorEntry(version);
        if(versionVal == null) return null;
        else return versionVal.getValue();
    }

    public Map<String,String> snapshot(int version){
        Map<String, String> snapshot = new HashMap<>();
        for(String key : snapshots.keySet()){
            String value = this.getAt(key, version);
            if(value == null) continue;
            snapshot.put(key, value);
        }
        return snapshot;
    }
}
