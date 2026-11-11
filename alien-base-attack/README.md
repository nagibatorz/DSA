<h2>Alien Base Attack</h2>
<hr>
<p>Design a simulation for an alien base attack game. The alien base is represented by the interval [0, n], where n is a positive integer. Each attack is executed at a coordinate x and destroys the segment [x, x+1] of the base. After a series of attacks, determine whether the entire base has been destroyed.</p>

<p>Implement AlienBaseAttack class that supports the following operations:</p>

<ul>
	<li><strong>AlienBaseAttack(int base):</strong> Initializes the object with an alien base of length n.</li>
	<li><strong>void attack(double x):</strong> Simulates an attack at coordinate x, destroying the segment [x, x+1].</li>
	<li><strong>boolean isGameOver():</strong> Returns true if the entire base [0, n] has been destroyed; otherwise, returns false.</li>
</ul>

<p><strong>Constraints:</strong></p>

<ul>
	<li>n is a positive integer.</li>
	<li>Coordinate x can be a decimal.</li>
	<li>Attacks may target fractional coordinates.</li>
	<li>All attack coordinates are within the base range [0, n].</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> ["AlienBaseAttack", "attack", "isGameOver", "attack", "isGameOver"]
[[1.0], [0.25], [], [0], []]
<strong>Output:</strong> [null, null, false, null, true]
<strong>Explanation:</strong> AlienBaseAttack game = new AlienBaseAttack(1); // The base covers the interval [0, 1].
game.attack(0.25); // Attacks at 0.25, destroying the segment [0.25, 1.25]; the base is not yet fully destroyed.
game.isGameOver(); // Return false, since the entire base [0, 1] is not fully destroyed.
game.attack(0); // Attacks at 0, destroying the segment [0, 1]. The intervals merge, forming [0, 1.25].
game.isGameOver(); // Return true, since the entire base [0, 1] is fully destroyed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> ["AlienBaseAttack", "attack", "attack", "isGameOver", "attack", "isGameOver"]
[[2.0], [0], [1.5], [], [1.0], []]
<strong>Output:</strong> [null, null, null, false, null, true]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> ["AlienBaseAttack", "attack", "attack", "isGameOver"]
[[2.0], [0.3], [0.9], []]
<strong>Output:</strong> [null, null, null, false]
</pre>
