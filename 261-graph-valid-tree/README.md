<h2><a href="https://leetcode.com/problems/graph-valid-tree">Graph Valid Tree</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-yellow' alt='Difficulty: Medium' /><hr><p>You have a graph of <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>. You are given an integer <code>n</code> and a list of <code>edges</code> where <code>edges[i] = [ai, bi]</code> indicates that there is an undirected edge between nodes <code>ai</code> and <code>bi</code> in the graph.</p>

<p>Return <em>true</em> if the edges of the given graph make up a valid tree, and <em>false</em> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2000</code></li>
	<li><code>0 &lt;= edges.length &lt;= 5000</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= ai, bi &lt; n</code></li>
	<li><code>ai != bi</code></li>
	<li>There are no self-loops or repeated edges.</li>
</ul>
