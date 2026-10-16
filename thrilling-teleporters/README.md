<h2>Thrilling Teleporters</h2><hr><p>A board game, called "Thrilling Teleporters", consists of squares numbered from <code>0</code> to <code>lastSquare</code>. Some squares contain teleporters that instantly move a player to a new square. Each teleporter is described by a string <code>"from,to"</code>, where landing on <code>from</code> moves the player to <code>to</code>, and each <code>from</code> is unique.</p>

<p>At the start of a turn, a player is on <code>currentSquare</code>. The player rolls a die with <code>dieSides</code> faces, numbered from <code>1</code> to <code>dieSides</code>. The turn proceeds as follows:</p>

<ul>
	<li>The player moves forward by the number shown on the die.</li>
	<li>If this move would land at or past <code>lastSquare</code>, the player stops at <code>lastSquare</code> and does not activate any teleporter.</li>
	<li>Otherwise, if the player lands on a square with a teleporter, they are instantly moved to that teleporter's destination. Only one teleporter can be activated per turn, even if the destination also contains a teleporter.</li>
</ul>

<p>Return the set of distinct squares the player could end up on after exactly one turn. The order of returned values does not matter.</p>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> teleporters = ["3,1", "4,2", "5,10"], dieSides = 6, currentSquare = 0, lastSquare = 20
<strong>Output:</strong> [1, 2, 6, 10]
<strong>Explanation:</strong>
Rolling 1 lands on 1 (no teleporter).
Rolling 2 lands on 2 (no teleporter).
Rolling 3 lands on 3, teleports to 1.
Rolling 4 lands on 4, teleports to 2.
Rolling 5 lands on 5, teleports to 10.
Rolling 6 lands on 6 (no teleporter).
After removing duplicates, the set of distinct possible destinations is {1, 2, 6, 10}.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> teleporters = [], dieSides = 6, currentSquare = 5, lastSquare = 20
<strong>Output:</strong> [6, 7, 8, 9, 10, 11]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> teleporters = ["18,5"], dieSides = 6, currentSquare = 16, lastSquare = 20
<strong>Output:</strong> [17, 5, 19, 20]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= currentSquare &lt;= lastSquare &lt;= 1000</code></li>
	<li><code>1 &lt;= dieSides &lt;= lastSquare + 1</code></li>
	<li><code>0 &lt;= teleporters.length &lt;= lastSquare + 1</code></li>
	<li>Each <code>from</code> and <code>to</code> are integers in the range <code>[0, lastSquare]</code></li>
</ul>
