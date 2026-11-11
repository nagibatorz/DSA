<h2>Pagination API</h2>
<hr>
<p>Design a PaginationAPI to sequentially retrieve items from an external data source, ExternalPaginatedAPI.</p>

<p>The provided external class ExternalPaginatedAPI only allows you to fetch data by page number using the getPage(int pageNum) method. The number of items in each page is not fixed, and you do not have access to the entire dataset. You only have access to the provided API methods.</p>

<p>The external API is structured as follows:</p>

<pre>
class ExternalPaginatedAPI {
    List&lt;List&lt;String&gt;&gt; pageContents;
    public ExternalPaginatedAPI(List&lt;List&lt;String&gt;&gt; pageContents) { ... }
    public List&lt;String&gt; getPage(int pageNum) { ... }
}
</pre>

<p>Implement the PaginationAPI class:</p>

<ul>
	<li><strong>PaginationAPI(ExternalPaginatedAPI api)</strong> Initializes the pagination state using the provided external API.</li>
	<li><strong>List&lt;String&gt; getNext(int n)</strong> Returns the next n sequential items, continuing from where the last call ended. Internally, fetch new pages as needed to collect a sufficient number of items.</li>
</ul>

<p><strong>Constraints:</strong></p>

<ul>
	<li>The external API only allows access by page number.</li>
	<li>Pages contain variable numbers of items.</li>
	<li>Total items/pages unknown in advance.</li>
	<li>API must return the next n sequential items per request.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<pre>
<strong>Input:</strong> [PaginationAPI, getNext, getNext, getNext]
[[["apple", "banana", "cherry", "date"], ["elderberry", "fig", "grape", "honeydew", "kiwi", "lemon"], ["mango", "nectarine", "orange", "pear", "quince"]], [5], [8], [3]]
<strong>Output:</strong> [[], ["apple", "banana", "cherry", "date", "elderberry"], ["fig", "grape", "honeydew", "kiwi", "lemon", "mango", "nectarine", "orange"], ["pear", "quince"]]
<strong>Explanation:</strong> PaginationAPI api = new PaginationAPI(pageContents);
api.getNext(5); // Returns ["apple", "banana", "cherry", "date", "elderberry"]. This takes 4 items from page 1 and 1 item from page 2.
api.getNext(8); // Returns ["fig", "grape", "honeydew", "kiwi", "lemon", "mango", "nectarine", "orange"]. This takes the remaining 5 items from page 2 and the first 3 items from page 3.
api.getNext(3); // Returns ["pear", "quince"]. Only 2 items remain on page 3, so both are returned.
</pre>
