<h2>Validate Dataset Rows</h2><hr><p>You are developing a data ingestion component for a large-scale analytics platform. The platform receives raw data in a simplified CSV format, where each line represents a record with specific attributes. Before storage or analysis, all incoming data must be strictly validated to maintain data quality and comply with business requirements.</p>

<p>The system provides three inputs:</p>

<ul>
	<li><code>data</code>: A list of strings, with each string representing a row in a CSV table containing n rows and 6 columns per row. Each row follows the format <code>"col1,col2,col3,col4,col5,col6"</code>, and the first row is always the header.</li>
	<li><code>forbiddenWords</code>: A list of strings containing words that are prohibited in specific fields.</li>
	<li><code>ignoreWords</code>: A list of strings consisting of common words (stop words) that should be ignored only during cross-column content comparison.</li>
</ul>

<p>For each data row (excluding the header), determine whether it is <code>"VALID"</code> or <code>"INVALID"</code> by applying the following four rules in order:</p>

<p><b>RULE_1: Schema &amp; Completeness.</b> Every column value must be non-empty after trimming any leading or trailing whitespace.</p>

<p><b>RULE_2: Column 5 Length Check.</b> The 5th column (col5), once trimmed, must have a length between 5 and 31 characters (inclusive).</p>

<p><b>RULE_3: Forbidden Words in Column 2.</b> The 2nd column (col2) must not contain any word from <code>forbiddenWords</code>.</p>

<ul>
	<li>Matching is case-insensitive.</li>
	<li>Words are defined as tokens separated by whitespace.</li>
</ul>

<p><b>RULE_4: Cross-Column Word Match.</b> A valid word is a lowercase word not found in <code>ignoreWords</code>. The row meets this rule only if at least 50% of valid words in col2 are also present as valid words in either col4 or col5.</p>

<ul>
	<li>If col2 has zero valid words, the row is invalid.</li>
	<li>Repeated valid words in col2 are only counted once for percentage calculation.</li>
</ul>

<p>If a row satisfies all rules, label it as <code>"VALID"</code>. If it fails any rule, label it as <code>"INVALID: RULE_X"</code>, where X is the number of the first failed rule. Return a list of validation results of length n - 1 (excluding the header), with each result corresponding to a data row.</p>

<p><b>Constraints:</b></p>

<ul>
	<li><code>1 &le; data.length &le; 10<sup>4</sup></code></li>
	<li><code>data[i]</code> consists of ASCII characters.</li>
	<li><code>forbiddenWords</code> and <code>ignoreWords</code> can contain up to <code>10<sup>3</sup></code> elements.</li>
	<li>Fields are separated strictly by a single comma (<code>','</code>).</li>
	<li>There is no support for quotes or escape characters.</li>
	<li>Commas will never appear as part of the content within a field.</li>
</ul>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> data = ["col1,col2,col3,col4,col5,col6",
               "id1,Land Water,c,d,land water llc,f",
               "id2,Good Company,c,d,land water,f",
               "id3,Apple banana,c,d,banana orange,f",
               "id4, ,c,d,hello world,f",
               "id5,LLC Inc,c,d,something real,f",
               "id6,blue sky ocean,c,d,green yellow pink,f",
               "id7,Best Group,c,d,hello group,f",
               "id8,land water,c,d,land,f"],
forbiddenWords = ["company", "firm", "co.", "corporation", "group"],
ignoreWords = ["llc", "inc"]
<strong>Output:</strong> ["VALID", "INVALID:RULE_3", "VALID", "INVALID:RULE_1", "INVALID:RULE_4", "INVALID:RULE_4", "INVALID:RULE_3", "INVALID:RULE_2"]
<strong>Explanation:</strong> Row 2 contains forbidden word "Company" (RULE_3). Row 4 has only whitespace in col2 (RULE_1). Row 5 has zero valid words in col2, both are in ignoreWords (RULE_4). Row 6 words ("blue", "sky", "ocean") do not appear in col4 or col5 (RULE_4). Row 7 contains forbidden word "Group" (RULE_3). Row 8 col5 length is 4 (RULE_2).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> data = ["col1,col2,col3,col4,col5,col6",
               "id1,hello world,c,d,hello there,f"],
forbiddenWords = ["bad", "wrong"],
ignoreWords = []
<strong>Output:</strong> ["VALID"]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> data = ["col1,col2,col3,col4,col5,col6",
               "id1,good name,c,d,test,f"],
forbiddenWords = [],
ignoreWords = []
<strong>Output:</strong> ["INVALID:RULE_2"]
</pre>
