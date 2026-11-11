<h2>Job Scheduler</h2> <img src='https://img.shields.io/badge/Difficulty-Medium-yellow' alt='Difficulty: Medium' /><hr><p>Given a list of jobs stored by start time. Each job consists of three parts:</p>

<ul>
	<li><strong>Job ID:</strong> A unique identifier (e.g. &quot;J1&quot;).</li>
	<li><strong>Start Time:</strong> A 24-hour time in &quot;HHMM&quot; format (e.g. &quot;0023&quot; represents 00:23).</li>
	<li><strong>Duration:</strong> The time in minutes required to complete the job.</li>
</ul>

<p>Your task is to assign each job to a machine following these rules:</p>

<ul>
	<li>A machine can work on only one job at a time.</li>
	<li>A machine becomes available once its current job&rsquo;s finish time is less than or equal to the start time of a new job.</li>
	<li>When multiple machines are free, assign the job to the machine with the smallest index.</li>
	<li>If no machine is available at the new job&rsquo;s start time, allocate a new machine with the maximum index + 1.</li>
</ul>

<p>Return a list where each element represents a job and its assigned machine (machine numbers are prefixed by &quot;M&quot;) in the format <code>[&quot;job_id&quot;, &quot;machine_id&quot;]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> jobs = [[&quot;J1&quot;, &quot;0023&quot;, &quot;45&quot;], [&quot;J2&quot;, &quot;0025&quot;, &quot;10&quot;], [&quot;J3&quot;, &quot;0100&quot;, &quot;60&quot;], [&quot;J4&quot;, &quot;0300&quot;, &quot;10&quot;]]
<strong>Output:</strong> [[&quot;J1&quot;, &quot;M1&quot;], [&quot;J2&quot;, &quot;M2&quot;], [&quot;J3&quot;, &quot;M2&quot;], [&quot;J4&quot;, &quot;M1&quot;]]
<strong>Explanation:</strong> J1: starts at 00:23. Machine M1 is allocated, and J1 will finish at 00:23 + 45 min = 00:68. J2: starts at 00:25. Since M1 is busy until 00:68, a new machine M2 is allocated. J2 finishes at 00:25 + 10 min = 00:35. J3: starts at 01:00. At this time, M2 (which finished at 00:35) is free, so J3 is assigned to M2. J4: starts at 03:00. At this time, M1 (which finished at 01:08) and M2 (which finished at 02:00) are both free, so J4 is assigned to M1 due to the smaller index.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> jobs = [[&quot;J1&quot;, &quot;0000&quot;, &quot;30&quot;], [&quot;J2&quot;, &quot;0030&quot;, &quot;15&quot;], [&quot;J3&quot;, &quot;0045&quot;, &quot;20&quot;]]
<strong>Output:</strong> [[&quot;J1&quot;, &quot;M1&quot;], [&quot;J2&quot;, &quot;M1&quot;], [&quot;J3&quot;, &quot;M1&quot;]]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> jobs = [[&quot;J1&quot;, &quot;0100&quot;, &quot;50&quot;], [&quot;J2&quot;, &quot;0105&quot;, &quot;30&quot;], [&quot;J3&quot;, &quot;0110&quot;, &quot;20&quot;], [&quot;J4&quot;, &quot;0120&quot;, &quot;30&quot;]]
<strong>Output:</strong> [[&quot;J1&quot;, &quot;M1&quot;], [&quot;J2&quot;, &quot;M2&quot;], [&quot;J3&quot;, &quot;M3&quot;], [&quot;J4&quot;, &quot;M4&quot;]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>Jobs are sorted by the start time when given.</li>
	<li>Jobs are processed in the order provided.</li>
	<li>Start times are given in &quot;HHMM&quot; format.</li>
	<li>Duration is provided in minutes.</li>
	<li>All jobs are scheduled for the same day.</li>
</ul>
