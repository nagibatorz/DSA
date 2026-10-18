<h2>applicant coding skills matching followups 1-3</h2><hr><p>In the modern recruitment process, Applicant Tracking Systems (ATS) automatically filter candidates based on their listed skills. An applicant provides a list of their technical skills in a comma-separated string, ordered by their proficiency and preference. The job description lists the required skills for the position.</p>

<p>Given an applicant's skill string, such as:</p>

<p>Applicant-Skills: <code>"Java-8, Python-3, Go-Lang"</code><br>The applicant is stating their skills in order of strength:</p>

<ul>
	<li>Java (version 8) - strongest skill</li>
	<li>Python (version 3)</li>
	<li>Go (Lang) - weakest skill</li>
</ul>

<p>The recruitment system has a specific list of skills required for a job. When evaluating a candidate, the system must identify which of the applicant's skills are actually relevant to the job requirements, preserving the order of the applicant's strength/preference.</p>

<p>You are tasked to implement a function that, given a string <code>applicantSkills</code> (the applicant's list) and a list of strings <code>requiredSkills</code> (the job's requirements), returns a list of relevant skills found in the applicant's list, in order of their appearance.</p>

<p>The returned list should include each matching skill at most once, according to its first occurrence in the applicant's list.<br>Matching must be exact (case-sensitive, no normalization or variant matching).<br>Whitespace surrounding each skill in the string should be ignored.<br>If no required skills appear in the applicant's list, return an empty list.</p>

<p><b>Constraints:</b></p>

<ul>
	<li><code>1 &le; requiredSkills.length &le; 1000</code></li>
	<li><code>0 &le; applicantSkills.length &le; 10<sup>4</sup></code></li>
	<li>Each skill consists of alphanumeric characters and hyphens, e.g., <code>"Java-8"</code>, <code>"C++-17"</code>, <code>"AWS"</code>.</li>
	<li>All elements in <code>requiredSkills</code> are unique.</li>
	<li>You may assume the inputs are well-formed as described.</li>
</ul>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> applicantSkills = "Java-8, Python-3, Go-Lang", requiredSkills = ["Go-Lang", "Java-8"]
<strong>Output:</strong> ["Java-8", "Go-Lang"]
<strong>Explanation:</strong> Only "Java-8" and "Go-Lang" are required. They are returned in the order they appear in the applicant's list.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> applicantSkills = "Python-3, Go-Lang", requiredSkills = ["Java-8", "Go-Lang"]
<strong>Output:</strong> ["Go-Lang"]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> applicantSkills = "Java-8", requiredSkills = ["Java-8", "Python-3"]
<strong>Output:</strong> ["Java-8"]
</pre>

<p><b>Follow-up 1:</b><br>In this follow-up, the Applicant-Skills list may contain generic skill categories that are not version-specific. For example, a skill listed simply as <code>"Java"</code> refers to any version of Java, such as <code>"Java-8"</code> or <code>"Java-11"</code>.</p>

<p>Extend your function so that when the applicant lists a generic skill (with no version/hyphen), it matches all required skills that begin with that prefix and have a hyphen, in addition to still matching any exact skills.</p>

<p>The returned list should still:</p>

<p>Contain only required skills.<br>Reflect the applicant's order of preference as expressed in their list.<br>Include each required skill at most once, based on its first appearance in the applicant's list.</p>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> applicantSkills = "Java", requiredSkills = ["Java-8", "Python-3", "Python-2"]
<strong>Output:</strong> ["Java-8"]
<strong>Explanation:</strong> The applicant lists "Java", which is a generic category. The only required skill matching this prefix is "Java-8".
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> applicantSkills = "Python", requiredSkills = ["Java-8", "Python-3", "Python-2"]
<strong>Output:</strong> ["Python-3", "Python-2"]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> applicantSkills = "Python-3, Python", requiredSkills = ["Java-8", "Python-3", "Python-2"]
<strong>Output:</strong> ["Python-3", "Python-2"]
<strong>Explanation:</strong> "Python-3" is matched exactly first. Then "Python" matches the remaining "Python-2".
</pre>

<p><b>Follow-up 2:</b><br>In this follow-up, the Applicant-Skills list may include a wildcard entry, represented by an asterisk <code>"*"</code>. The wildcard indicates that the applicant possesses (or is willing to work with) all other required skills that haven't been explicitly listed or matched yet.</p>

<p>When processing the list, if a wildcard <code>"*"</code> is encountered, add every remaining required skill (those not already selected) to the result. The relative order for these skills should follow their order in the <code>requiredSkills</code> list.</p>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> applicantSkills = "Java-8, *", requiredSkills = ["Java-8", "Python-3", "Go-Lang"]
<strong>Output:</strong> ["Java-8", "Python-3", "Go-Lang"]
<strong>Explanation:</strong> "Java-8" is matched first. The wildcard "*" then adds "Python-3" and "Go-Lang", which are the remaining required skills.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> applicantSkills = "Python-3, Python, *", requiredSkills = ["Java-8", "Python-3", "Python-2"]
<strong>Output:</strong> ["Python-3", "Python-2", "Java-8"]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> applicantSkills = "*", requiredSkills = ["Java-8", "Python-3", "C++-17"]
<strong>Output:</strong> ["Java-8", "Python-3", "C++-17"]
</pre>
