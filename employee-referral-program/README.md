<h2>Employee Referral Program</h2><hr><p>Implement a referral leaderboard generator for a referral program. In this program, when a user refers a new user, they automatically become responsible for all users in the subsequent referral chain. Given two arrays—<code>referrers</code> representing the referrers (in the order they referred) and <code>referrals</code> representing the corresponding new users, build a leaderboard that lists the top three referrers along with their cumulative referral counts. A user's referral count includes all direct and indirect referrals down their chain.</p>

<p><b>Referral Rules:</b></p>

<ul>
	<li>A user can only be referred once.</li>
	<li>Once a user joins the platform, they cannot be referred by someone else.</li>
</ul>

<p><b>Leaderboard Rules:</b></p>

<ul>
	<li>Only users with at least one referral appear.</li>
	<li>Display at most three users.</li>
	<li>The leaderboard is sorted by descending referral count; ties are broken alphabetically by username.</li>
</ul>

<p><b>Constraints:</b></p>

<ul>
	<li>You can assume all referral records are valid and strictly follow the rules, and no cycle exists.</li>
</ul>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> referrers = ["A", "A", "B", "C"], referrals = ["B", "C", "D", "E"]
<strong>Output:</strong> ["A 4", "B 1", "C 1"]
<strong>Explanation:</strong> The referral chain is: "A" refers "B" and "C", "B" refers "D", and "C" refers "E".

User "A": Directly referred "B" and "C", and indirectly referred "D" (through "B") and "E" (through "C"). Totally 4 referrals.
User "B": Directly referred "D", totaling 1 referral.
User "C": Directly referred "E", totaling 1 referral.
User "D" and "E": No successful referral, ignored.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> referrers = ["A", "A", "B", "A", "C", "B", "D"], referrals = ["B", "C", "D", "E", "F", "G", "H"]
<strong>Output:</strong> ["A 7", "B 3", "C 1"]
<strong>Explanation:</strong> D is excluded because we only output the top 3 referrers sorted by the total referral counts and their names.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> referrers = ["A", "D", "B", "C"], referrals = ["B", "E", "C", "D"]
<strong>Output:</strong> ["A 4", "B 3", "C 2"]
</pre>
