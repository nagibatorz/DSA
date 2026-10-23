<h2>Get Candlesticks</h2>

<p>You are given a list of timestamped prices represented as pairs of [time, price]. Your task is to generate candlestick data for fixed intervals of 10 time units. For each interval, produce a candlestick with the following information:</p>

<ul>
<li><strong>Start Time:</strong> The beginning of the interval (round down the time to the nearest multiple of 10).</li>
<li><strong>Maximum Price:</strong> The highest price recorded within the interval.</li>
<li><strong>Minimum Price:</strong> The lowest price within the interval.</li>
<li><strong>Opening Price:</strong> The price corresponding to the earliest time in the interval.</li>
<li><strong>Closing Price:</strong> The price corresponding to the latest time in the interval.</li>
</ul>

<p>If there is a gap between consecutive intervals (for example, data exists for intervals 0-9 and 20-29 but not for 10-19), fill the missing intervals with candlestick data using the previous interval's closing price for all fields (max, min, open, and close).</p>

<p>Note that the input may be in any order. And the output should be a list of candlesticks in the format [&lt;start_time&gt;, &lt;max_price&gt;, &lt;min_price&gt;, &lt;open_price&gt;, &lt;close_price&gt;].</p>

<p><strong>Example 1:</strong></p>
<pre>
Input: timePrices = [[1, 2], [3, 4], [9, 8], [5, 10], [13, 18], [34, 32], [55, 44]]
Output:  [[0, 10, 2, 2, 8], [10, 18, 18, 18, 18], [20, 18, 18, 18, 18], [30, 32, 32, 32, 32], [40, 32, 32, 32, 32], [50, 44, 44, 44, 44]]
Explanation:

Interval 0 (0-9):
Pairs in this interval are [1, 2], [3, 4], [5, 10], [9, 8].
The opening price is 2 (from time 1) and the closing price is 8 (from time 9).
The minimum price is 2 and the maximum price is 10.
Hence, the candlestick for interval 0 is [0, 10, 2, 2, 8].
Interval 10 (10-19):
Only [13,18] falls here. All fields are 18, resulting in [10, 18, 18, 18, 18].
Missing intervals:
Since there is no data for time 20-29, the candlestick is filled using the previous interval's closing price (18), resulting in [20, 18, 18, 18, 18].
Subsequent intervals are handled similarly.
</pre>

<p><strong>Example 2:</strong></p>
<pre>
Input: timePrices = [[0, 5], [3, 6], [9, 7], [10, 10], [11, 9], [15, 12], [19, 5]]
Output: [[0, 7, 5, 5, 7], [10, 12, 5, 10, 5]]
</pre>

<p><strong>Example 3:</strong></p>
<pre>
Input: timePrices = [[0, 10]]
Output: [[0, 10, 10, 10, 10]]
</pre>

<p><strong>Constraints:</strong></p>
<ul>
<li>Each time is an integer in the range [0, 10<sup>9</sup>].</li>
<li>Each price is an integer in the range [0, 10<sup>5</sup>].</li>
</ul>
