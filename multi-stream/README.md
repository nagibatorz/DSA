<h2>Multi Stream</h2>
<hr>
<p>This question is a variation of the LeetCode question 158. Read N Characters Given read4 II - Call Multiple Times.</p>

<p>You are given a set of read-only Stream objects where each stream has a read(int n) method that returns up to n characters from the remaining part of the stream. If n is greater than the remaining characters, return all remaining characters. If the remaining is empty, return "".</p>

<p>For example, Stream stream = Stream("01234"), then</p>

<ul>
	<li>stream.read(2) will return "01"</li>
	<li>stream.read(4) will return "234"</li>
	<li>stream.read(2) will return ""</li>
</ul>

<p>Implement the class MultiStream that manages multiple streams and supports the following operations:</p>

<ul>
	<li><strong>MultiStream()</strong> Initialize the class.</li>
	<li><strong>void add(Stream stream)</strong> Add a new stream to the multistream.</li>
	<li><strong>void remove(Stream stream)</strong> Remove an existing stream from the multistream. If you remove one stream from MultiStream before reading it or while reading it, read() will not return any characters from the removed stream.</li>
	<li><strong>String read(int n)</strong> Read up to n characters sequentially from the streams in the order they were added. If the remaining characters are less than n, apply the same read rule in the Stream class.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<pre>
<strong>Input:</strong> stream1 = Stream("123"), stream2 = Stream("456")
["MultiStream", "add", "add", "read", "remove", "read"]
[[], [stream1], [stream2], [2], [stream2], [2]]
<strong>Output:</strong> [null, null, null, "12", null, "3"]
<strong>Explanation:</strong> MultiStream multiStream = new MultiStream();
multiStream.add(stream1); // Adds stream1, which contains "123".
multiStream.add(stream2); // Adds stream2, which contains "456".
multiStream.read(2); // Returns "12". Reads 2 characters from multiStream. Since stream1 is the first stream, it returns "12", leaving stream1 with "3".
multiStream.remove(stream2); // Removes stream2.
multiStream.read(2); // Returns "3". Reads 2 characters from the remaining stream (stream1) and only "3" is left.
</pre>
