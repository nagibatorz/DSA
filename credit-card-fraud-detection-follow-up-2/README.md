<h2>Credit Card Fraud Detection, Follow Up 2</h2><hr><p>A payment platform processes payments on behalf of merchants. Each payment starts as an authorization request, representing an intent to transfer funds from an end user's credit card to a merchant.</p>

<p>You are given <code>requests</code>, a list of Authorization Requests. Each request is encoded as a CSV string in the following format: <code>"&lt;timestamp_seconds&gt;,&lt;unique_id&gt;,&lt;amount&gt;,&lt;card_number&gt;,&lt;merchant&gt;"</code>. All input records are pre-validated and guaranteed to be well-formed. For this part, the platform's policy is simple: Approve every request.</p>

<p>Write a function that returns a human-readable report containing all requests in chronological order. Requests should be sorted by <code>timestamp_seconds</code> in numeric order, not lexicographic order.</p>

<p>Each output line should use the following format: <code>"&lt;timestamp&gt; &lt;unique_id&gt; &lt;amount&gt; APPROVE"</code>, with single spaces separating fields in the output. The amount must be preserved exactly as it appears in the input string.</p>

<p><b>Constraints:</b></p>

<ul>
	<li><code>1 &le; requests.size() &le; 10<sup>5</sup></code></li>
	<li><code>1 &le; timestamp_seconds &le; 10<sup>9</sup></code>; all timestamps are distinct</li>
	<li><code>amount</code> is a positive decimal value, such as <code>5.60</code> or <code>500.00</code></li>
	<li>No field contains commas or surrounding whitespace</li>
	<li>Field values consist only of alphanumeric characters, underscores, and periods</li>
</ul>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> requests = ["10,R2,200.00,card_b,shop_b", "2,R1,50.00,card_a,shop_a", "9,R3,90.00,card_c,shop_c"]
<strong>Output:</strong> ["2 R1 50.00 APPROVE", "9 R3 90.00 APPROVE", "10 R2 200.00 APPROVE"]
<strong>Explanation:</strong> All requests are approved.
The requests are sorted by timestamp numerically: 2, then 9, then 10.
The output keeps each request's original amount string and appends "APPROVE".
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> requests = ["5,R1,5.60,4242424242424242,bobs_burgers", "10,R2,500.00,4242111111111111,a_corp"]
<strong>Output:</strong> ["5 R1 5.60 APPROVE", "10 R2 500.00 APPROVE"]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> requests = ["5,R1,500.00,card_a,shop_a", "3,R2,0.10,card_b,shop_b"]
<strong>Output:</strong> ["3 R2 0.10 APPROVE", "5 R1 500.00 APPROVE"]
</pre>

<p><b>Follow-up 1:</b></p>

<p>Building on the previous question, the system now also receives a stream of Fraud Rules.</p>

<p>Each fraud rule is encoded as a CSV string in the following format: <code>"&lt;time&gt;,&lt;field&gt;,&lt;value&gt;"</code>. The field is always either <code>"merchant"</code> or <code>"card_number"</code>.</p>

<p>A fraud rule means that starting from <code>time</code> seconds onward, any Authorization Request whose specified field equals <code>value</code> must be considered fraudulent and marked as REJECT. Fraud rules never expire. A rule with time = t applies to every request with timestamp_seconds &gt;= t, including requests that occur exactly at time t. If multiple active rules match the same request, the request should still appear only once in the output and should be marked with a single "REJECT".</p>

<p>Update the <code>generateReport</code> function so that it accepts both <code>requests</code> and <code>fraudRules</code>, and returns a report where fraudulent requests use "REJECT" instead of "APPROVE".</p>

<p><b>Constraints:</b></p>

<ul>
	<li><code>0 &le; fraudRules.size() &le; 10<sup>5</sup></code></li>
	<li><code>1 &le; Fraud rule time &le; 10<sup>9</sup></code></li>
</ul>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> requests = ["5,R1,5.60,4242424242424242,bobs_burgers", "10,R2,500.00,4242111111111111,a_corp"], fraudRules = ["1,merchant,bobs_burgers", "20,card_number,4242111111111111"]
<strong>Output:</strong> ["5 R1 5.60 REJECT", "10 R2 500.00 APPROVE"]
<strong>Explanation:</strong> R1 at t=5: the merchant rule for "bobs_burgers" was active from t=1, so 5 &gt;= 1 → REJECT.
R2 at t=10: the card_number rule only activates at t=20, so no rule is active yet → APPROVE.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> requests = ["10,R1,50.00,fraud_card,bad_shop"], fraudRules = ["5,merchant,bad_shop", "3,card_number,fraud_card"]
<strong>Output:</strong> ["10 R1 50.00 REJECT"]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> requests = ["2,R1,20.00,card_a,shop_a", "5,R2,50.00,card_b,shop_b"], fraudRules = ["100,merchant,shop_a"]
<strong>Output:</strong> ["2 R1 20.00 APPROVE", "5 R2 50.00 APPROVE"]
</pre>

<p><b>Follow-up 2:</b></p>

<p>Some fraud is only discovered after a transaction has already been approved. If a request was approved when it was processed, but a fraud rule matching it is introduced later (rule time &gt; request timestamp_seconds), that transaction is a fraud loss (money unknowingly paid out before the fraud was detected).</p>

<p>Write a function that calculates the total amount lost to retroactive fraud. A request is a fraud loss if and only if:</p>

<ul>
	<li>it was approved at runtime: no rule with time &le; timestamp_seconds matched its merchant or card_number;</li>
	<li>at least one rule with time &gt; timestamp_seconds matches one of its fields.</li>
</ul>

<p>Requests rejected in real time are not fraud losses even if a later rule also matches them. Return <code>0.0</code> if there are no fraud losses.</p>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> requests = ["5,R1,5.60,4242424242424242,bobs_burgers", "10,R2,500.00,4242111111111111,a_corp"], fraudRules = ["20,card_number,4242111111111111", "30,merchant,bobs_burgers"]
<strong>Output:</strong> 505.60
<strong>Explanation:</strong> R1 at t=5: no active rule → approved. The merchant rule activates at t=30 &gt; 5, making R1 a fraud loss ($5.60).
R2 at t=10: no active rule → approved. The card_number rule activates at t=20 &gt; 10, making R2 a fraud loss ($500.00).
Total: $5.60 + $500.00 = $505.60.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> requests = ["10,R1,250.00,card_f,bad_shop"], fraudRules = ["5,merchant,bad_shop", "20,merchant,bad_shop"]
<strong>Output:</strong> 0.0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> requests = ["5,R1,100.00,card_a,shop_a"], fraudRules = ["1,merchant,other_shop"]
<strong>Output:</strong> 0.0
</pre>
