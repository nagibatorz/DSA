<h2><a href="https://leetcode.com/problems/asteroid-collision">Asteroid Collision Variation</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-yellow' alt='Difficulty: Medium' /><hr><p>This question is a variation of LeetCode 735. Asteroid Collision. If you haven't completed that question yet, it is recommended to solve it first.</p>

<p>Given two arrays, one for asteroid masses and one for their speeds, simulate the collisions among the asteroids. Each asteroid&rsquo;s speed can be positive (moving right) or negative (moving left).</p>

<p>Two asteroids will collide if either of the following conditions is met:</p>

<ul>
	<li>The first asteroid is moving to the right while the second is moving to the left.</li>
	<li>Both asteroids are traveling in the same direction, but the faster one catches up to the slower one.</li>
</ul>

<p>When a collision occurs, the outcome is determined by these rules:</p>

<ul>
	<li>The asteroid with the smaller mass is destroyed.</li>
	<li>If both asteroids have equal mass, they are both destroyed.</li>
</ul>

<p>Repeat the simulation until no further collisions are possible, then return the masses and speeds of the remaining asteroids.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> mass = [5, 10, 5, 10], speed = [1, -2, 2, 1]
<strong>Output:</strong> [[10, 10], [-2, 1]]
<strong>Explanation:</strong> The first asteroid (mass=5, speed=1) moves right and it will collide with the second asteroid (mass=10, speed=-2) which moves left. Since 10 is greater than 5, the first asteroid is destroyed. The second asteroid (mass=10, speed=-2) and the third asteroid (mass=5, speed=1) will never collide, because the former moves left and the latter moves right. The third (mass=5, speed=2) and fourth (mass=10, speed=1) asteroids will collide, because the third one moves faster than the fourth one. Since 10 is greater than 5, the third asteroid is destroyed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mass = [8, 8, 8], speed = [1, -1, 1]
<strong>Output:</strong> [[8], [1]]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> mass = [10, 2, 5, 10], speed = [1, -2, 2, 1]
<strong>Output:</strong> [[10, 10], [1, 1]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>Mass array matches speed array in order.</li>
	<li><code>mass[i] != 0</code> and <code>speed[i] != 0</code>.</li>
</ul>
