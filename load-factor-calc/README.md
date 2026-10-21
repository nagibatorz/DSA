<h2>Load Factor Calc</h2><hr><p>You are building an application composed of multiple services, where each service may rely on one or more other services, forming a directed acyclic graph (DAG) of dependencies. One of these services serves as the entry point that receives user requests and, upon receiving a request, calls each of its dependencies. These dependencies then call their own dependencies, and so on.</p>

<p>The load factor of a service is defined as the number of units of load it must handle when the entry point receives 1 unit of load, assuming all upstream services issue their requests simultaneously. When multiple paths lead to the same service, the corresponding loads accumulate.</p>

<p>You are provided with a list of service definitions in the format <code>"&lt;service_name&gt;=&lt;dependency_1&gt;,&lt;dependency_2&gt;,..."</code>. A service may have no dependencies (e.g., <code>"auth="</code>), and any dependency that is not defined within the list should be ignored. Each service appears exactly once.</p>

<p>Given a designated entry point service (which is guaranteed to be defined in the list), determine the worst-case load factor for every service that is reachable from the entry point, including the entry point itself. The result should be returned as an array of strings formatted as <code>"&lt;service_name&gt;*&lt;load_factor&gt;"</code>, sorted lexicographically by the service name, and should include only those services that are reachable from the entry point.</p>

<p><b>Constraints:</b></p>

<ul>
	<li>The input is a list of unique service definitions.</li>
	<li>Non-defined dependencies should be ignored.</li>
</ul>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> serviceList = ["logging=", "user=logging", "orders=user,foobar", "recommendations=user,orders", "dashboard=user,orders,recommendations"], entryPoint = "dashboard"
<strong>Output:</strong> ["dashboard*1", "logging*4", "orders*2", "recommendations*1", "user*4"]
<strong>Explanation:</strong> Starting from dashboard (load factor = 1):

recommendations: receives loads only from upstream dashboard.
orders: accumulates loads from upstreams - dashboard and recommendations.
user: receives load both directly from dashboard and indirectly via orders and recommendations, resulting in a cumulative load of 4.
logging: receives load solely through user, accumulating to 4.
Thus, the final sorted output is ["dashboard*1", "logging*4", "orders*2", "recommendations*1", "user*4"].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> serviceList = ["A=B","B=C","C="], entryPoint = "A"
<strong>Output:</strong> ["A*1", "B*1", "C*1"]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> serviceList = ["auth=", "db=", "user=auth", "orders=user,db", "inventory=db", "shipping=orders,inventory", "reporting=shipping,user"], entryPoint = "reporting"
<strong>Output:</strong> ["auth*2", "db*2", "inventory*1", "orders*1", "reporting*1", "shipping*1", "user*2"]
</pre>
