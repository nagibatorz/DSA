<h2>Transactional In-Memory Key-Value Store</h2> <img src='https://img.shields.io/badge/Difficulty-Hard-red' alt='Difficulty: Hard' /><hr><p>Design an in-memory key-value store that supports nested transactions. The store should allow basic operations like setting a key-value pair, retrieving a value by key, removing a key, and counting how many records share a particular value. On top of these, you must provide transactional operations to start a transaction (begin), roll back recent changes (rollback), and apply them permanently (commit).</p>

<p>Implement the <code>InMemoryKVDB</code> class:</p>

<ul>
	<li><code>InMemoryKVDB()</code> — Initializes the key-value database.</li>
	<li><code>void set(String key, String value)</code> — Sets or updates the value associated with the key.</li>
	<li><code>String get(String key)</code> — Returns the current value for the key if it exists; otherwise, returns "Not Exist".</li>
	<li><code>void unset(String key)</code> — Removes the key (and its associated value) from the database if it exists.</li>
	<li><code>int countValues(String value)</code> — Retrieves the number of records assigned the specified value, including any uncommitted changes.</li>
	<li><code>void begin()</code> — Starts a new transaction block.</li>
	<li><code>boolean rollback()</code> — Restores the database state to the point when the most recent transaction block began, undoing all changes made within that transaction block. Returns true if the operation is successful, false if there's no active transaction block.</li>
	<li><code>void commit()</code> — Commits all open transaction blocks, permanently applying all pending changes and clearing the transaction stack.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong>
["InMemoryKVDB", "set", "begin", "get", "set", "begin", "set", "get", "rollback", "get", "begin", "set", "set", "commit", "get", "get", "countValues", "countValues", "countValues", "unset", "get"]
[[], ["a", "1"], [], ["a"], ["a", "2"], [], ["a", "3"], ["a"], [], ["a"], [], ["a", "5"], ["b", "1"], [], ["a"], ["b"], ["1"], ["5"], ["2"], ["a"], ["a"]]
<strong>Output:</strong>
[null, null, null, "1", null, null, null, "3", true, "2", null, null, null, null, "5", "1", 1, 1, 0, null, "Not Exist"]
<strong>Explanation:</strong>
InMemoryKVDB db = new InMemoryKVDB(); // Initializes the database.
db.set("a", "1"); // Sets key "a" to "1".
db.begin(); // Starts transaction T1. Output: null.
db.get("a"); // Returns "1" because "a" was set to "1".
db.set("a", "2"); // In transaction T1, updates "a" to "2".
db.begin(); // Starts a nested transaction T2 inside T1.
db.set("a", "3"); // In T2, updates "a" to "3".
db.get("a"); // Returns "3", the most recent value in T2.
db.rollback(); // Rollbacks T2, reverting "a" back to "2" as set in T1.
db.get("a"); // Returns "2", the value in T1 after rollback.
db.begin(); // Starts a new transaction T3.
db.set("a", "5"); // In T3, updates "a" to "5".
db.set("b", "1"); // In T3, sets key "b" to "1".
db.commit(); // Commits all open transactions (T1 and T3), making the changes permanent.
db.get("a"); // Returns "5" (committed value for "a").
db.get("b"); // Returns "1" (committed value for "b").
db.countValues("1"); // Returns 1 because only key "b" is set to "1".
db.countValues("5"); // Returns 1 because only key "a" is set to "5".
db.countValues("2"); // Returns 0 because no key is set to "2" after the commit.
db.unset("a"); // Removes key "a" from the database.
db.get("a"); // Returns "Not Exist" because "a" has been removed.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>get</code> returns <code>"Not Exist"</code> if the key does not exist.</li>
	<li><code>rollback</code> returns <code>false</code> if there is no active transaction block.</li>
	<li><code>commit</code> commits all open transaction blocks and clears the transaction stack.</li>
</ul>
