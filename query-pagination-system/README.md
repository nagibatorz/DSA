<h2>Query Pagination System</h2>

<p>Design a system to filter and paginate a list of transaction records. Each record follows a list of string format: [timestamp, id, userId, currency, amount]. The system should allow applying query filters for the following criteria:</p>

<ul>
<li>Time range filter</li>
<li>Amount range filter</li>
<li>User ID filter</li>
<li>Currency filter</li>
</ul>

<p>Additionally, a page size can be specified to return the results in chunks. And the records should be returned in non-descending order by timestamp.</p>

<p>Implement the QuerySystem Class:</p>

<ul>
<li><code>QuerySystem(List&lt;List&lt;String&gt;&gt; records)</code> Initializes the query system with the provided list of transaction records.</li>
<li><code>void setPageSize(int size)</code> Define the number of records to display per page. If the page size is not specified, the nextPage() call should return all matching results.</li>
<li><code>void setTimeRange(int start, int end)</code> Sets a filter to include only transactions within the specified time range (inclusive).</li>
<li><code>void setAmountRange(int start, int end)</code> Sets a filter to include only transactions within the specified amount range (inclusive).</li>
<li><code>void setUserId(String id)</code> Sets a filter to include only transactions associated with the specified userId.</li>
<li><code>void setCurrency(String currency)</code> Sets a filter to include only transactions using the specified currency.</li>
<li><code>List&lt;List&lt;String&gt;&gt; nextPage()</code> Retrieves the next page of filtered records.</li>
</ul>

<p><strong>Constraints:</strong></p>

<ul>
<li>1 &lt;= records.length &lt;= 10<sup>5</sup></li>
<li>1 &lt;= timestamp, amount &lt;= 10<sup>9</sup></li>
<li>1 &lt;= pageSize &lt;= 10<sup>4</sup></li>
</ul>

<p><strong>Example:</strong></p>

<pre>
Input:
["QuerySystem","setPageSize","setTimeRange","setUserId","nextPage","nextPage","nextPage"]
[
  [
    [
      ["1", "id-1", "user-1", "USD", "5"],
      ["2", "id-2", "user-2", "USD", "10"],
      ["3", "id-3", "user-1", "CAD", "20"],
      ["4", "id-4", "user-1", "CAD", "10"],
      ["5", "id-5", "user-1", "AUD", "30"],
      ["6", "id-6", "user-1", "JPY", "100"]
    ]
  ],
  [2], [1,5], ["user-1"], [], [], []
]

Output:
[null, null, null, null, [["1","id-1","user-1","USD","5"],["3","id-3","user-1","CAD","20"]], [["4","id-4","user-1","CAD","10"],["5","id-5","user-1","AUD","30"]], []]

Explanation:

QuerySystem system = QuerySystem(records);
system.setPageSize(2); // Set the page size to 2.
system.setTimeRange(1, 5); // Set the time range from 1 to 5.
system.setUserId("user-1"); // Filter records by userId equals to "user-1".
nextPage(); // Return [["1", "id-1", "user-1", "USD", "5"], ["3", "id-3", "user-1", "CAD", "20"]].
nextPage(); // Return [["4", "id-4", "user-1", "CAD", "10"], ["5", "id-5", "user-1", "AUD", "30"]].
nextPage(); // Return [] as there are no more eligible records.
</pre>
