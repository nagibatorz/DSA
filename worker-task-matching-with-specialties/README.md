<h2>Worker-Task Matching with Specialties</h2>

<p>You are designing a system to distribute a sequence of tasks among several workers, aiming to minimize the workload difference between them. The input consists of a list of worker names and a list of tasks, where each task is defined by a unique integer id and a positive integer duration.</p>

<p>Tasks are to be assigned sequentially, one at a time, in the given order. For each task, assign it to the worker whose current total workload (the sum of durations for all previously assigned tasks) is the lowest among all workers. If more than one worker has the minimum workload, choose the worker with the lexicographically smallest name.</p>

<p>Return a list of assignment strings in the format <code>"&lt;task_id&gt;:&lt;worker_name&gt;"</code>. The order of the returned list can be arbitrary.</p>

<p><strong>Example:</strong></p>
<pre>
Input:
workers = ["alice", "bob", "carol"],
tasks =
[
  { "id": 1, "duration": 4 },
  { "id": 2, "duration": 2 },
  { "id": 3, "duration": 7 },
  { "id": 4, "duration": 1 }
]
Output: ["1:alice", "2:bob", "3:carol", "4:bob"]
Explanation:

Task 1 is assigned to "alice" because all workers start with workload 0, and "alice" is lexicographically smallest.
Task 2 is assigned to "bob" because "bob" and "carol" both have workload 0, and "bob" is lexicographically smaller.
Task 3 is assigned to "carol" because she has the minimum workload 0.
Task 4 is assigned to "bob" because after previous assignments, "bob" has the lowest workload among all workers.
</pre>

<p><strong>Constraints:</strong></p>
<ul>
<li>1 &le; workers.length &le; 1000</li>
<li>1 &le; tasks.length &le; 1000</li>
<li>1 &le; duration &le; 1000</li>
<li>All worker names are unique, non-empty strings containing only lowercase English letters.</li>
</ul>
