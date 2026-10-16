<h2>Count Singer Role Co-occurrences</h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>A music analyst is studying the performance history of singers to discover which roles are frequently performed by the same artist within a short span of years.</p>

<p>You are given a list of performances, where each entry is a three-element list <code>[&lt;singerName&gt;, &lt;roleName&gt;, &lt;yearString&gt;]</code>, and an integer <code>period</code>.</p>

<p>Two different roles <code>A</code> and <code>B</code> co-occur if the same singer performed both roles and the absolute difference in their performance years is at most <code>period</code> (inclusive). Each co-occurring pair contributes one count to <code>A</code>'s relationship with <code>B</code> and one count to <code>B</code>'s relationship with <code>A</code>. If multiple singers independently perform the same pair of roles within the period, each singer's contribution is counted separately.</p>

<p>Return a list of rows representing all co-occurrence relationships. Each row is a three-element list <code>[&lt;roleName&gt;, &lt;cooccurringRoleName&gt;, &lt;countString&gt;]</code>, where <code>countString</code> is the co-occurrence count as a decimal string.</p>

<p>Every role that appears in performances must have at least one row in the result. If a role has no co-occurrences, represent it as <code>[&lt;roleName&gt;, "None", "0"]</code>.<br>The result must be sorted first by <code>roleName</code> lexicographically ascending, then by <code>cooccurringRoleName</code> lexicographically ascending.</p>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> performances = [["Daphne", "Role_D", "2013"], ["Alice", "Role_A", "2010"], ["Alice", "Role_B", "2013"], ["Alice", "Role_C", "2016"]], period = 3
<strong>Output:</strong> [["Role_A", "Role_B", "1"], ["Role_B", "Role_A", "1"], ["Role_B", "Role_C", "1"], ["Role_C", "Role_B", "1"], ["Role_D", "None", "0"]]
<strong>Explanation:</strong> The co-occurrence counts for this input are determined as follows:

Alice (Role_A: 2010, Role_B: 2013, Role_C: 2016):
|2010 - 2013| = 3 &lt;= 3 -&gt; Role_A and Role_B co-occur; each gains 1 count for the other.
|2013 - 2016| = 3 &lt;= 3 -&gt; Role_B and Role_C co-occur; each gains 1 count for the other.
|2010 - 2016| = 6 &gt; 3 -&gt; Role_A and Role_C do not co-occur.
Daphne performed only Role_D, so it has no co-occurrences and appears as ["Role_D", "None", "0"].
Rows are sorted lexicographically by role name. Role_B has two co-occurring roles, so it contributes two rows.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> performances = [["Alice", "Role_X", "2010"], ["Alice", "Role_Y", "2012"], ["Bob", "Role_X", "2015"], ["Bob", "Role_Y", "2017"]], period = 3
<strong>Output:</strong> [["Role_X", "Role_Y", "2"], ["Role_Y", "Role_X", "2"]]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> performances = [["Alice", "Role_A", "2010"], ["Alice", "Role_B", "2011"], ["Alice", "Role_C", "2015"], ["Alice", "Role_D", "2016"]], period = 2
<strong>Output:</strong> [["Role_A", "Role_B", "1"], ["Role_B", "Role_A", "1"], ["Role_C", "Role_D", "1"], ["Role_D", "Role_C", "1"]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= performances.size() &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= period &lt;= 10<sup>9</sup></code></li>
	<li>Singer names and role names are non-empty strings.</li>
	<li>For the same singer, the same role appears at most once.</li>
	<li>The string <code>"None"</code> does not appear as an actual role name in performances.</li>
	<li><code>yearString</code> is a valid string representation of a non-negative integer year that fits in a signed 64-bit integer.</li>
</ul>
