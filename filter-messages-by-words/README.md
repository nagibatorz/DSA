<h2>Filter Messages by Words</h2><hr><p>You are given an array of messages <code>messages</code> and an array of unsafe words <code>unsafeWords</code>. Each message is an array of exactly two strings <code>[username, text]</code>. A message is unsafe if its text contains at least one unsafe word. Return only the safe messages, meaning those whose text contains no unsafe word, preserving the original order.</p>

<p>Word matching follows two rules:</p>

<ul>
	<li>A word is a maximal sequence of characters delimited by spaces: punctuation is part of the word, not a separator, so <code>"Nintendo,"</code> and <code>"Nintendo"</code> are distinct words.</li>
	<li>Matching is case-insensitive: <code>"nintendo"</code> and <code>"NINTENDO"</code> are the same word.</li>
</ul>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> messages = [["tom", "i am enjoying the nintendo switch"], ["jerry", "i prefer the wii"], ["tom", "my best friend is sonya"], ["tom", "do you have a usb charger"], ["jerry", "no but i do have a USB-C charger"], ["dan", "dafNintendo-doo is a fun app"]], unsafeWords = ["Nintendo", "Microsoft", "SONY", "usb-C"]
<strong>Output:</strong> [["jerry", "i prefer the wii"], ["tom", "my best friend is sonya"], ["tom", "do you have a usb charger"], ["dan", "dafNintendo-doo is a fun app"]]
<strong>Explanation:</strong> Each message is checked by comparing its space-delimited tokens against the unsafe words.

tom ("i am enjoying the nintendo switch"): the token "nintendo" matches "Nintendo" case-insensitively, so this message is unsafe and excluded.
jerry ("i prefer the wii"): no token matches any unsafe word, so this message is safe.
tom ("my best friend is sonya"): "sonya" != "sony" (different tokens), so this message is safe.
tom ("do you have a usb charger"): "usb" != "usb-C" (punctuation makes them distinct), so this message is safe.
jerry ("no but i do have a USB-C charger"): "USB-C" matches "usb-C" case-insensitively, so this message is unsafe and excluded.
dan ("dafNintendo-doo is a fun app"): "dafNintendo-doo" != "Nintendo" (different token), so this message is safe.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> messages = [["a", "Nintendo is great"], ["b", "I love my NintendoSwitch"], ["c", "SONY sells TVs"], ["d", "sonya is my friend"], ["e", "Buy usb-c today"], ["f", "I use USB cable"]], unsafeWords = ["Nintendo", "SONY", "usb-C"]
<strong>Output:</strong> [["b", "I love my NintendoSwitch"], ["d", "sonya is my friend"], ["f", "I use USB cable"]]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> messages = [["a", "Nintendo, is mentioned"], ["b", "Nintendo is mentioned"], ["c", "I like usb-c."], ["d", "I like usb-c"]], unsafeWords = ["Nintendo", "usb-c"]
<strong>Output:</strong> [["a", "Nintendo, is mentioned"], ["c", "I like usb-c."]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= messages.size() &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= unsafeWords.size() &lt;= 10<sup>4</sup></code></li>
	<li><code>username</code> and <code>text</code> are non-empty strings.</li>
	<li><code>text</code> contains only printable ASCII characters.</li>
	<li>Words in <code>text</code> are separated by single spaces; there are no leading or trailing spaces.</li>
	<li>Each entry in <code>unsafeWords</code> is a non-empty string that contains no spaces.</li>
</ul>
