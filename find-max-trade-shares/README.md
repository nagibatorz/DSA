<h2>Find Max Trade Shares</h2><hr><p>You are given a list of order records for a single stock. Each record is a list containing the limit price (a number), quantity (number of shares), and order type (either <code>"buy"</code> or <code>"sell"</code>).</p>

<p>Your task is to calculate the total number of shares traded based on the following rules:</p>

<ul>
	<li>A buy order can be matched with a sell order if the sell price is less than or equal to the buy price.</li>
	<li>A sell order can be matched with a buy order if the buy price is greater than or equal to the sell price.</li>
	<li>If no matching order exists, the order is stored until a matching counter-order arrives.</li>
	<li>Orders must be matched at the best possible price:
Buy orders match with the lowest sell price available.
Sell orders match with the highest buy price available.</li>
	<li>Orders may be partially filled if the available quantity is less than the order's quantity.</li>
	<li>Return the total number of shares successfully traded.</li>
</ul>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> orders = [["150", "5", "buy"],["190", "1", "sell"],["200", "1", "sell"],["100", "9", "buy"],["140", "8", "sell"],["210", "4", "buy"]]
<strong>Output:</strong> 9
<strong>Explanation:</strong> There's no trade in the first four orders. Trading begins with the fifth order, a sell at 140 for 8 shares, which matches a stored buy order at 150, executing 5 shares. The sixth order, a buy at 210 for 4 shares, then triggers trading by matching the remaining 3 shares from the sell at 140 and 1 share from the sell at 190. In total, 5 + 4 = 9 shares are traded.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> orders = [["100","10","buy"],["105","5","buy"],["110","8","buy"]]
<strong>Output:</strong> 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> orders = [["120","10","buy"],["115","5","sell"],["110","3","sell"]]
<strong>Output:</strong> 8
</pre>
