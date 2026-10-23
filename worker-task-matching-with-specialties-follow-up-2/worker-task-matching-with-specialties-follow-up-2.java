import java.util.*;

class Task {
    int id;
    int duration;
    String requiredSpecialty;
    String account;
    
    public Task(int id, int duration, String requiredSpecialty, String account) {
        this.id = id;
        this.duration = duration;
        this.requiredSpecialty = requiredSpecialty;
        this.account = account;
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

public class Solution {
    public List<String> assignTasks(List<Worker> workers, List<Task> tasks) {
        // TODO: Implement assignTasks logic.
        // TODO: Implement assignTasks logic.
        // min heap based on worker's load and lexicographically smallest;
        PriorityQueue<Map.Entry<Worker, Integer>> pq = new PriorityQueue<>((a,b) -> 
            {
                int c = Integer.compare(a.getValue(), b.getValue());
                return c != 0 ? c : a.getKey().name.compareTo(b.getKey().name);
            });

        Map<String, Worker> handler = new HashMap<>();
        Map<Worker, Integer> loads = new HashMap<>();
        Map<Worker, Set<String>> workerSpecialties = new HashMap<>();
        for(Worker w : workers){
            workerSpecialties.put(w, new HashSet<>(w.specialties));
            pq.offer(Map.entry(w, 0));
            loads.put(w, 0);
        }
        List<String> res = new ArrayList<>();
        for(Task t : tasks){
            String account = t.account;
            Worker assignedToAcc = handler.get(account);
            if(assignedToAcc != null && workerSpecialties.get(assignedToAcc).contains(t.requiredSpecialty)){
                pq.remove(Map.entry(assignedToAcc, loads.get(assignedToAcc)));
                loads.put(assignedToAcc, loads.get(assignedToAcc) + t.duration);
                pq.add(Map.entry(assignedToAcc, loads.get(assignedToAcc)));
                res.add(t.id + ":" + assignedToAcc.name);
                continue;
            }
            List<Map.Entry<Worker, Integer>> idle = new ArrayList<>();
            while(!pq.isEmpty()){
                Map.Entry<Worker, Integer> curr = pq.poll();
                Worker w = curr.getKey();
                int load = curr.getValue();
                if(workerSpecialties.get(w).contains(t.requiredSpecialty)){
                    String build = String.valueOf(t.id) + ":" + w.name;
                    res.add(build);
                    handler.put(account, w);
                    idle.add(Map.entry(w, load + t.duration));
                    loads.put(w, load + t.duration);
                    break;
                }
                idle.add(curr);
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
        tasks.add(new Task(1, 2, "frontend", "acct1"));
        tasks.add(new Task(2, 4, "backend", "acct2"));
        tasks.add(new Task(3, 3, "frontend", "acct1"));
        List<String> res = solution.assignTasks(workers, tasks);
        System.out.println(res);  // Expected: [1:alice, 2:bob, 3:alice]
    }

    public static void test2() {
        System.out.println("===== Test 2 =====");
        Solution solution = new Solution();
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("alice", Arrays.asList("backend")));
        workers.add(new Worker("bob", Arrays.asList("backend")));
        workers.add(new Worker("carol", Arrays.asList("backend")));
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 10, "backend", "acct1"));
        tasks.add(new Task(2, 5, "backend", "acct2"));
        tasks.add(new Task(3, 1, "backend", "acct1"));
        tasks.add(new Task(4, 8, "backend", "acct2"));
        List<String> res = solution.assignTasks(workers, tasks);
        System.out.println(res);  // Expected: [1:alice, 2:bob, 3:alice, 4:bob]
    }

    public static void test3() {
        System.out.println("===== Test 3 =====");
        Solution solution = new Solution();
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("alice", Arrays.asList("frontend", "backend")));
        workers.add(new Worker("bob", Arrays.asList("backend", "database")));
        workers.add(new Worker("carol", Arrays.asList("frontend", "database")));
        workers.add(new Worker("dave", Arrays.asList("devops")));
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 5, "frontend", "acct1"));
        tasks.add(new Task(2, 3, "backend", "acct2"));
        tasks.add(new Task(3, 2, "database", "acct3"));
        tasks.add(new Task(4, 4, "frontend", "acct1"));
        tasks.add(new Task(5, 1, "backend", "acct2"));
        tasks.add(new Task(6, 6, "database", "acct3"));
        tasks.add(new Task(7, 2, "devops", "acct4"));
        List<String> res = solution.assignTasks(workers, tasks);
        System.out.println(res);  // Expected: [1:alice, 2:bob, 3:carol, 4:alice, 5:bob, 6:carol, 7:dave]
    }

    public static void test4() {
        System.out.println("===== Test 4 =====");
        Solution solution = new Solution();
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("alice", Arrays.asList("frontend")));
        workers.add(new Worker("bob", Arrays.asList("backend")));
        workers.add(new Worker("carol", Arrays.asList("frontend", "backend")));
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 5, "frontend", "acct1"));
        tasks.add(new Task(2, 3, "backend", "acct1"));
        tasks.add(new Task(3, 2, "frontend", "acct1"));
        List<String> res = solution.assignTasks(workers, tasks);
        System.out.println(res);  // Expected: [1:alice, 2:bob, 3:carol]
    }

    public static void test5() {
        System.out.println("===== Test 5 =====");
        Solution solution = new Solution();
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("alice", Arrays.asList("frontend")));
        workers.add(new Worker("bob", Arrays.asList("backend")));
        workers.add(new Worker("carol", Arrays.asList()));
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 5, "database", "acct1"));
        tasks.add(new Task(2, 3, "devops", "acct2"));
        tasks.add(new Task(3, 2, "ml", "acct3"));
        List<String> res = solution.assignTasks(workers, tasks);
        System.out.println(res);  // Expected: []
    }

    public static void test6() {
        System.out.println("===== Test 6 =====");
        Solution solution = new Solution();
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("alice", Arrays.asList("frontend", "backend")));
        workers.add(new Worker("bob", Arrays.asList("backend", "database")));
        workers.add(new Worker("carol", Arrays.asList("frontend")));
        workers.add(new Worker("dave", Arrays.asList("database", "devops")));
        workers.add(new Worker("erin", Arrays.asList("devops", "frontend")));
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 3, "frontend", "acct1"));
        tasks.add(new Task(2, 4, "backend", "acct2"));
        tasks.add(new Task(3, 2, "frontend", "acct1"));
        tasks.add(new Task(4, 5, "database", "acct3"));
        tasks.add(new Task(5, 1, "backend", "acct1"));
        tasks.add(new Task(6, 2, "devops", "acct2"));
        tasks.add(new Task(7, 3, "devops", "acct2"));
        tasks.add(new Task(8, 1, "devops", "acct5"));
        tasks.add(new Task(9, 1, "database", "acct2"));
        tasks.add(new Task(10, 2, "database", "acct2"));
        tasks.add(new Task(11, 4, "frontend", "acct2"));
        tasks.add(new Task(12, 1, "backend", "acct2"));
        tasks.add(new Task(13, 2, "ml", "acct4"));
        tasks.add(new Task(14, 1, "database", "acct3"));
        tasks.add(new Task(15, 3, "frontend", "acct3"));
        tasks.add(new Task(16, 2, "frontend", "acct3"));
        List<String> res = solution.assignTasks(workers, tasks);
        System.out.println(res);
        // Expected: [1:alice, 2:bob, 3:alice, 4:dave, 5:alice, 6:erin, 7:erin,
        // 8:dave, 9:bob, 10:bob, 11:carol, 12:alice, 14:dave, 15:carol, 16:carol]
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }
}
