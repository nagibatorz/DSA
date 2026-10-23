<h2>Worker-Task Matching with Specialties, Follow Up 2</h2>

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

<p><strong>Follow-up 1:</strong></p>

<p>In this variation, each worker has one or more specialties, and every task specifies a single <code>requiredSpecialty</code>.</p>

<p>A task can be assigned only to a worker who possesses the required specialty. If no eligible worker is available for a task, skip that task and do not include it in the output.</p>

<p>From the pool of qualified workers, assign the task to the worker with the lowest current total workload. If there is a tie, select the worker whose name comes first lexicographically.</p>

<p>Return a list of task assignment strings in the format <code>"&lt;task_id&gt;:&lt;worker_name&gt;"</code>, including only the tasks that were successfully assigned. You may return the result in any order.</p>

<p><strong>Constraints:</strong></p>
<ul>
<li>0 &le; worker.specialties.length &le; 100</li>
</ul>

<p><strong>Example:</strong></p>
<pre>
Input:
workers =
[
  { "name": "alice", "specialties": ["frontend", "backend"] },
  { "name": "bob", "specialties": ["backend"] },
  { "name": "carol", "specialties": ["frontend"] }
],
tasks =
[
  { "id": 1, "duration": 2, "requiredSpecialty": "frontend" },
  { "id": 2, "duration": 4, "requiredSpecialty": "backend" },
  { "id": 3, "duration": 1, "requiredSpecialty": "database" }
]

Output:
["1:alice", "2:bob"]

Explanation:

Task 1 requires "frontend". Eligible workers are "alice" and "carol", both with workload 0, so "alice" is chosen (lexicographically smallest).
Task 2 requires "backend". Eligible workers are "alice" (workload 2) and "bob" (workload 0), so "bob" is chosen.
Task 3 requires "database", but no worker has this specialty.
</pre>

<p><strong>Follow-up 2:</strong></p>

<p>Each task now has an <code>account</code> field, indicating the internal account required to complete the work. To reduce operational overhead and maintain security, it is important to minimize unnecessary transfers of account ownership between workers, since switching accounts often can lead to extra administrative steps and potential risk.</p>

<p>Assignments should follow these updated rules:</p>
<ul>
<li>If a worker has previously been assigned any task with a particular account and has the required specialty for the current task, all future tasks for that account must be assigned to this worker (regardless of current workload).</li>
<li>If the previous handler of the account does not have the required specialty, or if no worker has handled the account yet, assign the task to an eligible worker with the lowest total workload. If there is a tie, pick the worker whose name comes first lexicographically.</li>
<li>If no worker is eligible for a given task, skip it and exclude it from the output.</li>
</ul>

<p>Return a list of task assignment strings in the format <code>"&lt;task_id&gt;:&lt;worker_name&gt;"</code>, including only the tasks that were successfully assigned. You may return the result in any order.</p>

<p><strong>Example:</strong></p>
<pre>
Input:
workers =
[
  { "name": "alice", "specialties": ["frontend", "backend"] },
  { "name": "bob", "specialties": ["backend"] },
  { "name": "carol", "specialties": ["frontend"] }
],
tasks =
[
  { "id": 1, "duration": 2, "requiredSpecialty": "frontend", "account": "acct1" },
  { "id": 2, "duration": 4, "requiredSpecialty": "backend", "account": "acct2" },
  { "id": 3, "duration": 3, "requiredSpecialty": "frontend", "account": "acct1" }
]

Output:
["1:alice", "2:bob", "3:alice"]

Explanation:

Task 1 is assigned to "alice". Account mapping becomes: "acct1 -&gt; alice".
Task 2 requires "backend" and is assigned to "bob"). Account mapping becomes: "acct2 -&gt; bob".
Task 3 has account "acct1". The previous handler is "alice", and she has all the required specialty, task 3 must be assigned to "alice" regardless of workload.
</pre>
