import java.util.*;

class Task {
    int id;
    int duration;
    
    public Task(int id, int duration) {
        this.id = id;
        this.duration = duration;
    }
}

class Worker {
    int totalLoad;
    String name;
    public Worker(String name){
        this.name = name;
    }
}

public class Solution {
    public List<String> assignTasks(List<String> workers, List<Task> tasks) {
        // TODO: Implement assignTasks logic.
        Map<Task, Worker> assignments = new HashMap<>();
        // min heap based on worker's load and lexicographically smallest;
        PriorityQueue<Worker> pq = new PriorityQueue<>((a,b) -> 
            a.totalLoad != b.totalLoad ? Integer.compare(a.totalLoad, b.totalLoad) : a.name.compareTo(b.name));
        for(String w : workers){
            pq.offer(new Worker(w));
        }
        for(Task t : tasks){
            Worker w = pq.poll();
            w.totalLoad += t.duration;
            assignments.put(t, w);
            pq.offer(w);
        }
        List<String> res = new ArrayList<>();
        for(Map.Entry<Task, Worker> entry : assignments.entrySet()){
            String taskMatch = String.valueOf(entry.getKey().id) + ":" + entry.getValue().name;
            res.add(taskMatch);
        }
        return res;
    } 

    public static void test1() {
        System.out.println("===== Test 1 =====");
        Solution solution = new Solution();
        List<String> workers = Arrays.asList("alice", "bob", "carol");
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 4));
        tasks.add(new Task(2, 2));
        tasks.add(new Task(3, 7));
        tasks.add(new Task(4, 1));
        List<String> res = solution.assignTasks(workers, tasks);
        System.out.println(res);  // Expected: [1:alice, 2:bob, 3:carol, 4:bob]
    }

    public static void test2() {
        System.out.println("===== Test 2 =====");
        Solution solution = new Solution();
        List<String> workers = Arrays.asList("alice", "bob");
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 1));
        tasks.add(new Task(2, 1));
        tasks.add(new Task(3, 1));
        List<String> res = solution.assignTasks(workers, tasks);
        System.out.println(res);  // Expected: [1:alice, 2:bob, 3:alice]
    }

    public static void test3() {
        System.out.println("===== Test 3 =====");
        Solution solution = new Solution();
        List<String> workers = Arrays.asList("alice", "bob", "carol", "dave", "eve");
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 10));
        tasks.add(new Task(2, 5));
        tasks.add(new Task(3, 3));
        tasks.add(new Task(4, 8));
        tasks.add(new Task(5, 2));
        tasks.add(new Task(6, 7));
        tasks.add(new Task(7, 1));
        tasks.add(new Task(8, 4));
        List<String> res = solution.assignTasks(workers, tasks);
        System.out.println(res);  // Expected: [1:alice, 2:bob, 3:carol, 4:dave, 5:eve, 6:eve, 7:carol, 8:carol]
    }

    public static void test4() {
        System.out.println("===== Test 4 =====");
        Solution solution = new Solution();
        List<String> workers = Arrays.asList("alice");
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 5));
        tasks.add(new Task(2, 3));
        tasks.add(new Task(3, 2));
        List<String> res = solution.assignTasks(workers, tasks);
        System.out.println(res);  // Expected: [1:alice, 2:alice, 3:alice]
    }

    public static void test5() {
        System.out.println("===== Test 5 =====");
        Solution solution = new Solution();
        List<String> workers = Arrays.asList("zebra", "alice", "bob");
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 5));
        tasks.add(new Task(2, 5));
        tasks.add(new Task(3, 5));
        List<String> res = solution.assignTasks(workers, tasks);
        System.out.println(res);  // Expected: [1:alice, 2:bob, 3:zebra]
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }
}
