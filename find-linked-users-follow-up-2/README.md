<h2>Find Linked Users, Follow Up 2</h2>

<p>A data-quality team is working to identify duplicate or highly similar user profiles within a large dataset. Each user profile is represented as a row in a 2D string array <code>records</code>, where the first row is a header specifying field names (such as <code>"id"</code>, <code>"name"</code>, <code>"email"</code>, <code>"company"</code>). All subsequent rows contain string values for each field.</p>

<p>You are also given an array <code>weights</code>, where each element is formatted as <code>"&lt;field&gt;:&lt;weight&gt;"</code>. Each <code>&lt;field&gt;</code> corresponds to a field in the header other than <code>"id"</code>, and <code>&lt;weight&gt;</code> is a decimal number between [0, 1]. The sum of all <code>&lt;weight&gt;</code> values is exactly 1.0. A higher weight means that matching on that field is more important.</p>

<p>The similarity score between two user profiles is defined as the sum of the weights for all fields where their values are equal. Fields not listed in <code>weights</code> do not contribute to the similarity score.</p>

<p>Given a similarity threshold <code>threshold</code>, and a <code>targetUserId</code>, find all user profiles that are directly linked to the target profile. A profile is directly linked if its similarity score with the target profile is greater than or equal to <code>threshold</code>. The target profile itself should not be included in the result. Return the list of matching id values in any order. If the target profile itself does not exist in the records, return an empty list.</p>

<p><strong>Example 1:</strong></p>
<pre>
Input: records = [["id", "name", "email", "company"],
                  ["1", "Alice", "alice@gmail.com", "Stripe"],
                  ["2", "Alicia", "alice@gmail.com", "Stripe"],
                  ["3", "Alice", "alice@yahoo.com", "Google"],
                  ["4", "Bob", "bob@gmail.com", "Stripe"]],
       weights = ["name:0.2", "email:0.5", "company:0.3"], threshold = 0.5, targetUserId = 1
Output: [2]
Explanation: Profile 2 shares both "email" and "company" with the target (ID = 1), resulting in a similarity score of 0.5 + 0.3 = 0.8, which meets the threshold. Profiles 3 and 4 do not reach the threshold.
</pre>

<p><strong>Example 2:</strong></p>
<pre>
Input: records = [["id", "city", "age"],
                  ["1", "NYC", "25"],
                  ["2", "NYC", "25"],
                  ["3", "NYC", "30"],
                  ["4", "LA", "25"]],
       weights = ["city:0.6", "age:0.4"], threshold = 0.6, targetUserId = 1
Output: [2, 3]
</pre>

<p><strong>Example 3:</strong></p>
<pre>
Input: records = [["id", "name", "email"],
                  ["1", "Alice", "alice@a.com"],
                  ["2", "Bob", "bob@b.com"],
                  ["3", "Charlie", "charlie@c.com"]],
       weights = ["name:0.5", "email:0.5"], threshold = 0.5, targetUserId = 1
Output: []
</pre>

<p><strong>Constraints:</strong></p>
<ul>
<li>2 &le; records.length &le; 10<sup>4</sup></li>
<li>records[0] contains field names; one of them is always id</li>
<li>All id values are unique strings that represent integers.</li>
<li>1 &le; weights.length &le; records[0].length - 1</li>
<li>0 &lt; threshold &le; 1.0</li>
</ul>

<p><strong>Follow-up 1:</strong></p>

<p>In this follow-up, your task is to find all profiles that are at most one hop of the target profile. Specifically, return all id values that are either directly connected to the target or directly connected to target-linked profiles, excluding duplicates and the target itself. You may return the result in any order.</p>

<p><strong>Example 1:</strong></p>
<pre>
Input: records = [["id", "f1", "f2"],
                  ["1", "a", "b"],
                  ["2", "a", "c"],
                  ["3", "d", "c"],
                  ["4", "a", "b"]],
       weights = ["f1:0.5", "f2:0.5"], threshold = 0.5, targetUserId = 1
Output: [2, 3, 4]
Explanation: Profiles 2 and 4 are directly linked to the target. Profile 3 is directly linked to profile 2. All are within one hop, so the answer is [2, 3, 4].
</pre>

<p><strong>Example 2:</strong></p>
<pre>
Input: records = [["id", "name", "email", "company"],
                  ["1", "Alice", "alice@gmail.com", "Stripe"],
                  ["2", "Alicia", "alice@gmail.com", "Stripe"],
                  ["3", "Alice", "alice@yahoo.com", "Google"],
                  ["4", "Bob", "bob@gmail.com", "Stripe"]],
       weights = ["name:0.2", "email:0.5", "company:0.3"], threshold = 0.5, targetUserId = 1
Output: [2]
</pre>

<p><strong>Example 3:</strong></p>
<pre>
Input: records = [["id", "city", "age"],
                  ["1", "NYC", "25"],
                  ["2", "NYC", "30"],
                  ["3", "LA", "30"],
                  ["4", "LA", "40"]],
       weights = ["city:0.6", "age:0.4"], threshold = 0.4, targetUserId = 1
Output: [2, 3]
</pre>

<p><strong>Follow-up 2:</strong></p>

<p>In this follow-up, return all record ids that are in the same connected component as the target record, where an edge exists between any two records whose similarity score is at least <code>threshold</code>. Include all records that are reachable from the target through a sequence of such similarity links, using the rules from the previous questions, excluding the target profile itself. You may return the result in any order.</p>

<p><strong>Example 1:</strong></p>
<pre>
Input: records = [["id", "city", "age"],
                  ["1", "NYC", "25"],
                  ["2", "NYC", "30"],
                  ["3", "LA", "30"],
                  ["4", "LA", "40"]],
       weights = ["city:0.6", "age:0.4"], threshold = 0.4, targetUserId = 1
Output: [2, 3, 4]
Explanation: Profiles 2, 3, and 4 are all connected to the target (ID = 1) through a sequence of similarity links, so the answer is [2, 3, 4].
</pre>

<p><strong>Example 2:</strong></p>
<pre>
Input: records = [["id", "name", "email", "company"],
                  ["1", "Alice", "alice@gmail.com", "Stripe"],
                  ["2", "Alicia", "alice@gmail.com", "Stripe"],
                  ["3", "Alice", "alice@yahoo.com", "Google"],
                  ["4", "Bob", "bob@gmail.com", "Stripe"]],
       weights = ["name:0.2", "email:0.5", "company:0.3"], threshold = 0.5, targetUserId = 1
Output: [2]
</pre>

<p><strong>Example 3:</strong></p>
<pre>
Input: records = [["id", "f1", "f2"],
                  ["1", "a", "b"],
                  ["2", "a", "c"],
                  ["3", "a", "b"],
                  ["4", "x", "y"],
                  ["5", "x", "z"]],
       weights = ["f1:0.5", "f2:0.5"], threshold = 0.5, targetUserId = 1
Output: [2, 3]
</pre>
