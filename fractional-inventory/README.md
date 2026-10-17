<h2>Fractional Inventory</h2><hr><p>You have a list of orders and an initial inventory of stock fractions, both sorted by stock symbol. Each order is given as a list in the format <code>[&lt;symbol&gt;, &lt;order_type&gt;, &lt;quantity&gt;, &lt;unit_price&gt;]</code> where:</p>

<p><code>symbol</code> is the stock symbol like <code>"AAPL"</code>.
<code>order_type"</code> is an order type, with <code>"B"</code> for buy and <code>"S"</code> for sell.
<code>quantity</code> can be either a number representing a fraction of a share or a dollar amount prefixed with <code>"$"</code>.
<code>unit_price</code> is the current stock price in hundredths.
The inventory is a list where each item consists of a stock symbol and the corresponding fraction of a share (expressed in hundredths).</p>

<p><b>Note:</b> The final two digits of all numbers represent the decimal part. For example, an order <code>["AAPL", "B", "42", "100"]</code> indicates a buy order for 0.42 shares of <code>"AAPL"</code>, where <code>"AAPL"</code> is priced at $1.00 per share. Similarly, <code>["AAPL", "B", "$50", "100"]</code> means to purchase shares worth $0.50, which equates to buying 0.50 shares at $1.00 per share. An inventory entry like <code>["AAPL", "50"]</code> means you start with 0.50 shares of <code>"AAPL"</code>.</p>

<p>Each stock's inventory is strictly a fractional share. If, after processing orders, the share count exceeds 1, only the fractional part is kept (the whole number portion is removed). The task is to process each order sequentially and then return the updated fractional inventory, sorted by symbol. The rules for processing orders are as follows:</p>

<p><b>Buy orders:</b>
If the current inventory holds enough fractional shares to cover the buy order, simply subtract the order amount from the inventory. If not, determine the shortfall and purchase enough whole shares to cover it. After the purchase, subtract the order amount and keep any leftover as the new fractional inventory.</p>

<p><b>Sell orders:</b>
Add the sold fraction to the inventory, then adjust the total by removing any whole shares, keeping only the fractional part to ensure the inventory remains less than one whole share.</p>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> orders = [["AAPL", "B", "42", "100"], ["GOOG", "S", "$80", "160"]], inventory = [["AAPL", "99"], ["GOOG", "60"]]
<strong>Output:</strong> [["AAPL", "57"], ["GOOG", "10"]]
<strong>Explanation:</strong>
For the "AAPL" order:
It is a buy order with a quantity of 42, which represents 0.42 shares (since values are in hundredths).
The initial inventory for AAPL is 99 (i.e., 0.99 shares).
Since 0.99 shares are sufficient to cover the 0.42 shares order, we subtract 42 from 99, resulting in 57 (i.e., 0.57 shares remaining).
For the "GOOG" order:
It is a sell order worth $80. For dollar-based orders, convert the dollar amount to shares using the current price: $0.80 / $1.60 = 0.5 shares
The initial inventory for GOOG is 60 (i.e., 0.6 shares).
For a sell order, we add the calculated order shares to the inventory: 60 + 50 = 110.
Finally, we "flatten" the inventory by taking the modulo 100, which leads to 10 in the end.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> orders = [["AAPL","B","$42","100"]], inventory = [["AAPL","50"]]
<strong>Output:</strong> [["AAPL","8"]]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> orders = [["AAPL","S","75","100"]], inventory = [["AAPL","60"]]
<strong>Output:</strong> [["AAPL","35"]]
</pre>
