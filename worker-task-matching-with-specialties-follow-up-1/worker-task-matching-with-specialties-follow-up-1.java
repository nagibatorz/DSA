import java.util.*;

class Task {
    int id;
    int duration;
    String requiredSpecialty;

    public Task(int id, int duration, String requiredSpecialty) {
        this.id = id;
        this.duration = duration;
        this.requiredSpecialty = requiredSpecialty;
    }
}

class Worker {
    String name;
    List<String> specialties;
    

    public Worker(String name, List<String> specialties) {
        this.name = name;
        this.specialties = specialties;
    }
}
// map specialty to heap 
public class Solution {
    public List<String> assignTasks(List<Worker> workers, List<Task> tasks) {
        // TODO: Implement assignTasks logic.
        // TODO: Implement assignTasks logic.
        // min heap based on worker's load and lexicographically smallest;
        PriorityQueue<Map.Entry<Worker, Integer>> pq = new PriorityQueue<>((a,b) -> 
            a.getValue() != b.getValue() ? Integer.compare(a.getValue(), b.getValue()) : a.getKey().name.compareTo(b.getKey().name));
        
        Map<Worker, Set<String>> workerSpecialties = new HashMap<>();
        for(Worker w : workers){
            workerSpecialties.put(w, new HashSet<>(w.specialties));
            pq.offer(Map.entry(w, 0));
        }
        List<String> res = new ArrayList<>();
        for(Task t : tasks){
            List<Map.Entry<Worker, Integer>> idle = new ArrayList<>();
            while(!pq.isEmpty()){
                Map.Entry<Worker, Integer> curr = pq.poll();
                Worker w = curr.getKey();
                int load = curr.getValue();
                if(workerSpecialties.get(w).contains(t.requiredSpecialty)){
                    String build = String.valueOf(t.id) + ":" + w.name;
                    res.add(build);
                    idle.add(Map.entry(w, load + t.duration));
                    break;
                } else{
                    idle.add(curr);
                }
            }
            pq.addAll(idle);
        }
        return res;
    }

    public static void test1() {
        System.out.println("===== Test 1 =====");
        Solution solution = new Solution();
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("alice", Arrays.asList("frontend", "backend")));
        workers.add(new Worker("bob", Arrays.asList("backend")));
        workers.add(new Worker("carol", Arrays.asList("frontend")));

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 2, "frontend"));
        tasks.add(new Task(2, 4, "backend"));
        tasks.add(new Task(3, 1, "database"));

        List<String> res = solution.assignTasks(workers, tasks);
        System.out.println(res);  // Expected: [1:alice, 2:bob]
    }

    public static void test2() {
        System.out.println("===== Test 2 =====");
        Solution solution = new Solution();
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("alice", Arrays.asList("backend")));
        workers.add(new Worker("bob", Arrays.asList("backend")));
        workers.add(new Worker("carol", Arrays.asList("backend")));

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 5, "backend"));
        tasks.add(new Task(2, 5, "backend"));
        tasks.add(new Task(3, 5, "backend"));
        tasks.add(new Task(4, 1, "backend"));

        List<String> res = solution.assignTasks(workers, tasks);
        System.out.println(res);  // Expected: [1:alice, 2:bob, 3:carol, 4:alice]
    }

    public static void test3() {
        System.out.println("===== Test 3 =====");
        Solution solution = new Solution();
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("alice", Arrays.asList("frontend", "backend", "devops")));
        workers.add(new Worker("bob", Arrays.asList("backend", "database")));
        workers.add(new Worker("carol", Arrays.asList("frontend")));
        workers.add(new Worker("dave", Arrays.asList("devops", "database")));

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 10, "frontend"));
        tasks.add(new Task(2, 5, "backend"));
        tasks.add(new Task(3, 3, "database"));
        tasks.add(new Task(4, 8, "devops"));
        tasks.add(new Task(5, 2, "frontend"));
        tasks.add(new Task(6, 7, "backend"));
        tasks.add(new Task(7, 1, "database"));
        tasks.add(new Task(8, 4, "devops"));

        List<String> res = solution.assignTasks(workers, tasks);
        System.out.println(res);  // Expected: [1:alice, 2:bob, 3:dave, 4:dave, 5:carol, 6:bob, 7:dave, 8:alice]
    }

    public static void test4() {
        System.out.println("===== Test 4 =====");
        Solution solution = new Solution();
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("alice", Arrays.asList("frontend")));
        workers.add(new Worker("bob", Arrays.asList("backend")));
        workers.add(new Worker("carol", Arrays.asList("frontend", "backend")));

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 5, "frontend"));
        tasks.add(new Task(2, 10, "backend"));
        tasks.add(new Task(3, 3, "frontend"));
        tasks.add(new Task(4, 2, "backend"));
        tasks.add(new Task(5, 1, "frontend"));
        tasks.add(new Task(6, 8, "backend"));

        List<String> res = solution.assignTasks(workers, tasks);
        System.out.println(res);  // Expected: [1:alice, 2:bob, 3:carol, 4:carol, 5:alice, 6:carol]
    }

    public static void test5() {
        System.out.println("===== Test 5 =====");
        Solution solution = new Solution();
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("alice", Arrays.asList("frontend")));
        workers.add(new Worker("bob", Arrays.asList("backend")));
        workers.add(new Worker("carol", Arrays.asList()));

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 5, "database"));
        tasks.add(new Task(2, 3, "devops"));
        tasks.add(new Task(3, 2, "ml"));

        List<String> res = solution.assignTasks(workers, tasks);
        System.out.println(res);  // Expected: []
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }
}
