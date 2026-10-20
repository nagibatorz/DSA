<h2>Match Payment to Invoice Follow-up</h2><hr><p>A payment reconciliation system manages two types of data: payments and invoices.</p>

<p><b>Payment Record:</b> Each payment is provided as a string in the format <code>"&lt;paymentId&gt;,&lt;paymentAmount&gt;,&lt;memo&gt;"</code>. The memo field is a note that may include a reference like <code>"paying off:{invoiceId}"</code>, indicating which invoice the payment is for.</p>

<p><b>Invoice Record:</b> Each invoice is given as a string in the format <code>"&lt;invoiceId&gt;,&lt;invoiceAmount&gt;,&lt;dueDate&gt;"</code>.</p>

<p>Given a single payment string and a list of invoice strings, implement a function to link the payment to the correct invoice and produce an event string in the following structure, using the corresponding paymentId, paymentAmount, invoiceId, and dueDate: <code>"&lt;paymentId&gt; paid &lt;paymentAmount&gt; for invoice &lt;invoiceId&gt; on date &lt;dueDate&gt;"</code></p>

<p>Use the following logic to match the payment to an invoice:</p>

<p><b>Memo Reference:</b> If the payment's memo includes <code>"paying off:&lt;invoiceId&gt;"</code>, extract the invoiceId. If this invoiceId matches one in the invoice list, generate the event string for that invoice.</p>

<p><b>Amount Match:</b> If the memo does not reference an invoice, look for invoices with an amount equal to paymentAmount. If there are multiple matches, choose the one with the earliest dueDate (<code>"YYYY-MM-DD"</code> format). Generate the event string for this invoice if found.</p>

<p><b>No Match:</b> If neither approach finds a matching invoice, return an empty string.</p>

<p><b>Constraints:</b></p>

<ul>
	<li><code>1 &le; payment.length &le; 200</code></li>
	<li><code>0 &le; invoices.length &le; 10<sup>4</sup></code></li>
	<li><code>paymentAmount</code> and every <code>invoiceAmount</code> are positive integers.</li>
	<li>Each <code>invoiceId</code> is unique.</li>
	<li>There is at most one matching invoice for any given payment by the described rules.</li>
</ul>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> payment = "PAY123,1000,paying off: INV123", invoices = ["INV111,500,2024-05-01", "INV123,1000,2024-06-01"]
<strong>Output:</strong> "PAY123 paid 1000 for invoice INV123 on date 2024-06-01"
<strong>Explanation:</strong> The memo contains "paying off: INV123" and invoice "INV123" exists, so the function returns the formatted string using its due date.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> payment = "PAY456,500,partial payment", invoices = ["INV111,500,2024-05-01", "INV123,1000,2024-06-01", "INV222,500,2024-04-20"]
<strong>Output:</strong> "PAY456 paid 500 for invoice INV222 on date 2024-04-20"
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> payment = "PAY999,200,unrelated", invoices = ["INV111,500,2024-05-01", "INV123,1000,2024-06-01"]
<strong>Output:</strong> ""
</pre>

<p><b>Follow-up:</b></p>

<p>In this follow-up, if a payment does not match any invoice by identifier or by exact amount, you are also given an integer <code>forgiveness</code>.</p>

<p>Among all invoices where the absolute difference between invoice amount and payment amount is at most <code>forgiveness</code> (that is, <code>abs(invoiceAmount - paymentAmount) &le; forgiveness</code>), select the one with the earliest due date.</p>

<p>Return a string in the following format, including the forgiven amount: <code>"&lt;paymentId&gt; paid &lt;paymentAmount&gt; for invoice &lt;invoiceId&gt; on date &lt;dueDate&gt; (forgiven &lt;forgivenAmount&gt;)"</code></p>

<p>where <code>forgivenAmount</code> is the absolute difference between paymentAmount and invoiceAmount.</p>

<p>If no such invoice exists, return an empty string.</p>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> payment = "PAY789,510,overpayment", invoices = ["INV111,500,2024-05-01", "INV123,1000,2024-06-01"], forgiveness = 15
<strong>Output:</strong> "PAY789 paid 510 for invoice INV111 on date 2024-05-01 (forgiven 10)"
<strong>Explanation:</strong> There is no invoice ID in the memo, and no invoice with an exact amount of 510. However, invoice "INV111" has an amount of 500, which differs from the payment amount by 10. Since 10 is less than or equal to the forgiveness limit of 15, this invoice is selected. The forgiven amount is 10, and its due date is "2024-05-01".
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> payment = "PAY123,500,paying off: INV123", invoices = ["INV111,500,2024-05-01", "INV123,1000,2024-06-01"], forgiveness = 10
<strong>Output:</strong> "PAY123 paid 500 for invoice INV123 on date 2024-06-01"
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> payment = "PAY100,300,payment", invoices = ["INV1,300,2024-03-01", "INV2,300,2024-01-01"], forgiveness = 5
<strong>Output:</strong> "PAY100 paid 300 for invoice INV2 on date 2024-01-01"
</pre>
