import java.util.*;
import java.math.*;


// THERE IS AN OPTIMIZATION THAT COULD BE MADE TO NOT UPDATE VALUE IF SET SETS THE KEY=VAL PAIR TO THE SAME THING
// Need to maximize methods that execute in O(1) complexity
class InMemoryKVDB {
    // history of active transaction
    // each map holds what the values in db were before current transaction
    private Deque<Map<String, String>> undoStack;

    // key -> value
    // updated by directly by each operation within any transaction
    // and rolled back using undoMap from undoStack on rollback() call;
    private Map<String, String> db;

    // value -> counts of the value
    // updated by directly by each operation within any transaction
    // and rolled back using undoMap from undoStack on rollback() call;
    private Map<String, Integer> valuesCount;
    
    public InMemoryKVDB() {
        // TODO: Initialize InMemoryKVDB
       this.db = new HashMap<>();
       this.valuesCount = new HashMap<>();
       this.undoStack = new ArrayDeque<>();
    }

    public void set(String key, String value) {
        // TODO: Implement set logic
        recordUndo(key);
        applySet(key, value);
        

     }

    public String get(String key) {
        // TODO: Implement get logic
        return db.getOrDefault(key, "Not Exist");
     }

    public void unset(String key) {
        // TODO: Implement unset logic
        recordUndo(key);
        applyUnset(key);
        
     }

    public int countValues(String value) {
        // TODO: Implement countValues logic
        return valuesCount.getOrDefault(value, 0);
       
     }

    public void begin() {
        // TODO: Implement begin logic
        // starts a new transaction -> start new map to record changes that will be rolled back
        undoStack.push(new HashMap<>());
        
     }

    public boolean rollback() {
        // TODO: Implement rollback logic
        // check if there are any uncommitted transactions
        if(undoStack.isEmpty()) return false;

        // get most recent transaction
        Map<String, String> undoMap = undoStack.pop();
        for(String key : undoMap.keySet()){
            String valKey = undoMap.get(key);
            // key was added as part of this transaction -> remove from db
            if(valKey == null){
                applyUnset(key);
            } else{ // key had a different value before this transaction -> set back to the old value
                applySet(key, valKey);
            }
        }
        return true;
     }

    public void commit() {
        // TODO: Implement commit logic
        // commit all changes -> clear the history to prevent rollbacks
        undoStack = new ArrayDeque<>();
        
     }

    // Sets value and edits counts in db
    private void applySet(String key, String value){

        // Deal with the old value and decrement its count because it is being changed
        String valKey = db.get(key);
        if(valKey != null){
            int newCount = valuesCount.get(valKey) - 1;
            if(newCount == 0){
                valuesCount.remove(valKey);
            } else{
                valuesCount.put(valKey, newCount);
            }
        }

        // update db and increment counts for the new value
        db.put(key, value);
        valuesCount.put(value, valuesCount.getOrDefault(value, 0) + 1);
    }

// Deletes value and edits counts in db
    private void applyUnset(String key){
        String valKey = db.get(key);

        // decrement count of the old value and remove entry from db if the key exists
        if(valKey != null){
            int newCount = valuesCount.get(valKey) - 1;
            if(newCount == 0){
                valuesCount.remove(valKey);
            } else{
                valuesCount.put(valKey, newCount);
            }
            db.remove(key);
        }

    }

// memorizes the change if it happenned with in transaction
    private void recordUndo(String key){
        // check if any transaction has been started
        if(!undoStack.isEmpty()){
            Map<String, String> tempMap = undoStack.peek();
            
            // record the change
            if(!tempMap.containsKey(key)){
                // db.get() returns null if the key did not exist
                tempMap.put(key, db.get(key)); // if the key did not exist we will set its value in undo as null

            }
        }

    }

    

    
};
