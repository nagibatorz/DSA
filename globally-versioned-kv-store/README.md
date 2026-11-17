<h2>Globally Versioned Key-Value Store</h2> <img src='https://img.shields.io/badge/Difficulty-Hard-red' alt='Difficulty: Hard' /><hr><p>Design a key-value store where every write gets a global version number. Versions are shared across all keys: the first write is version 1, the next write (to any key) is version 2, and so on.</p>

<p>Implement the <code>VersionedKV</code> class:</p>

<ul>
	<li><code>int set(String key, String value)</code> — stores <code>value</code> under <code>key</code> and returns the new global version.</li>
	<li><code>int delete(String key)</code> — deletes <code>key</code> and returns the new global version. Returns <code>-1</code> if the key was never written.</li>
	<li><code>String get(String key)</code> — returns the latest value for <code>key</code>, or <code>null</code> if the key does not exist or was deleted.</li>
	<li><code>String getAt(String key, int version)</code> — returns the value of <code>key</code> as of <code>version</code>: the value from the latest write at or before that version, or <code>null</code> if the key had not been written yet or was deleted as of that version.</li>
	<li><code>Map&lt;String, String&gt; snapshot(int version)</code> — returns all live keys and their values as of <code>version</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong>
["VersionedKV", "set", "set", "set", "get", "getAt", "getAt", "delete", "get", "getAt", "snapshot", "snapshot"]
[[], ["a", "x"], ["b", "y"], ["a", "z"], ["a"], ["a", 2], ["b", 1], ["a"], ["a"], ["a", 3], [2], [4]]
<strong>Output:</strong>
[null, 1, 2, 3, "z", "x", null, 4, null, "z", {"a": "x", "b": "y"}, {"b": "y"}]
<strong>Explanation:</strong>
The writes get global versions 1, 2, 3 in order, no matter which key they touch. getAt("a", 2) returns "x" because the latest write to "a" at or before version 2 is the version 1 write. getAt("b", 1) returns null because "b" was first written at version 2. After delete("a") at version 4, get("a") is null, but getAt("a", 3) still returns "z" because the delete had not happened yet as of version 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>Versions start at 1 and increase by 1 on every <code>set</code> and <code>delete</code>.</li>
	<li><code>getAt</code> with a version before a key's first write returns <code>null</code>.</li>
	<li><code>getAt</code> and <code>snapshot</code> with a version past the latest behave as of the latest version.</li>
	<li>Keys and values are strings; values passed to <code>set</code> are never <code>null</code>.</li>
</ul>
