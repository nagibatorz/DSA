<h2>Calculate Usage Cost, Follow Up 2</h2><hr><p>You are developing a billing module for a cloud computing platform. Given a JSON <code>usage_report</code> specifying the target region and a JSON object <code>pricing_table</code> containing hourly rates for services in each region, determine the total cost for the usage.</p>

<p>Example data:</p>

<pre>
// Usage Report
{
    "region": "us-east-1",
    "services": [
        {"service": "db.t3.micro", "hours": 20},
          ...
    ]
    ...
}
// Pricing Table
{
    "us-east-1": [
        {"service": "db.t3.micro", "rate": 550},
          ...
    ],
    ...
}
</pre>

<p>The total cost for a single service is determined by multiplying its usage hours by the hourly rate for that service in the given region. If the report's region does not exist in the <code>pricing_table</code>, or if any service in the report is missing for that region, return <code>-1</code>.</p>

<p><b>Constraints:</b></p>

<ul>
	<li><code>0 &le; services.length &le; 1000</code></li>
	<li><code>0 &le; hours &le; 1000</code></li>
	<li>All rates are non-negative integers</li>
	<li>Service names and region codes are case-sensitive</li>
</ul>

<p><strong class="example">Example:</strong></p>

<pre>
<strong>Input:</strong>
usage_report = {
  "region": "us-east-1",
  "services": [
    {"service": "db.t3.micro", "hours": 20},
    {"service": "ec2.m5.large", "hours": 5}
  ]
}
pricing_table = {
  "us-east-1": [
    {"service": "db.t3.micro", "rate": 550},
    {"service": "ec2.m5.large", "rate": 1000}
  ],
  "ca-central-1": [
    {"service": "db.t3.micro", "rate": 750},
    {"service": "ec2.m5.large", "rate": 1100}
  ]
}
<strong>Output:</strong> 16000
<strong>Explanation:</strong> The usage is in us-east-1:

"db.t3.micro" cost: 20 x 550 = 11000
"ec2.m5.large" cost: 5 x 1000 = 5000
Total = 11000 + 5000 = 16000.
</pre>

<p><b>Follow-up 1:</b></p>

<p>Building on the previous part, pricing is now determined by usage tiers with incremental strategy. The rate per unit changes depending on how much usage has already been processed in previous tiers.</p>

<p>The <code>pricing_table</code> data is updated such that each service contains an array of <code>tiers</code> objects. Each tier object contains <code>minHours</code>, <code>maxHours</code>, and <code>rate</code>. Pricing follows a progressive calculation:</p>

<ul>
	<li>Usage is assigned to tiers in the order they are provided.</li>
	<li>A <code>maxHours</code> of <code>null</code> indicates the range extends to infinity.</li>
</ul>

<p>For example:</p>

<p>If a tier is defined as <code>{"min": 0, "max": 2, "rate": 1000}</code>, the first 2 hours cost 1000 each.<br>
If the next tier is <code>{"min": 3, "max": null, "rate": 900}</code>, the 3rd hour and all subsequent hours cost 900 each.</p>

<p>Return <code>-1</code> if the report's region does not exist in <code>pricing_table</code>, or if any service is missing a pricing rule.</p>

<p><strong class="example">Example:</strong></p>

<pre>
<strong>Input:</strong>
usage_report = {
  "region": "us-east-1",
  "services": [
    {
      "service": "ec2.m5.large",
      "hours": 5
    }
  ]
},
pricing_table = {
  "us-east-1": [
    {
      "service": "ec2.m5.large",
      "tiers": [
        {
          "minHours": 0,
          "maxHours": 2,
          "rate": 1000
        },
        {
          "minHours": 3,
          "maxHours": null,
          "rate": 900
        }
      ]
    }
  ]
}
<strong>Output:</strong> 4700
<strong>Explanation:</strong> The report contains 5 hours of "ec2.m5.large":

Tier 1: The first 2 hours cost 1000 each. Subtotal: 2 x 1000 = 2000.
Tier 2: The remaining 3 hours cost 900 each. Subtotal: 3 x 900 = 2700.
Total = 2000 + 2700 = 4700.
</pre>

<p><b>Follow-up 2:</b></p>

<p>Some pricing tiers are now <b>fixed</b> rather than incremental. Each cost tier now includes a <code>type</code> field that determines how the cost is applied:</p>

<ul>
	<li><code>"incremental"</code>: The cost is calculated by multiplying the tier's rate by the number of hours that fall within that tier's range, which is the same as the previous question.</li>
	<li><code>"fixed"</code>: A flat fee is charged once if at least one hour (or part of it) is assigned to the tier, regardless of how many hours fill it.</li>
</ul>

<p>For example, if a tier is defined as <code>{"min": 0, "max": 2, "type": "fixed", "rate": 1000}</code>, it means:</p>

<ul>
	<li>A usage of 1 hour costs 1000.</li>
	<li>A usage of 2 hours costs 1000.</li>
</ul>

<p>The total usage cost is the sum of the costs calculated for every tier that the total hours touch. Return <code>-1</code> if the destination region is not found or if a service is missing a pricing rule.</p>

<p><strong class="example">Example:</strong></p>

<pre>
<strong>Input:</strong>
usage_report = {
  "region": "us-east-1",
  "services": [
    {
      "service": "ec2.m5.large",
      "hours": 5
    }
  ]
},
pricing_table = {
  "us-east-1": [
    {
      "service": "ec2.m5.large",
      "tiers": [
        {
          "type": "fixed",
          "minHours": 0,
          "maxHours": 2,
          "rate": 1000
        },
        {
          "type": "incremental",
          "minHours": 3,
          "maxHours": null,
          "rate": 900
        }
      ]
    }
  ]
}
<strong>Output:</strong> 3700
<strong>Explanation:</strong> The report contains 5 hours of ec2.m5.large:

Tier 1 (Fixed): The first 2 hours are allocated to this tier. Since it is fixed, the cost of 1000 is charged once.
Tier 2 (Incremental): The remaining 3 hours fall into this tier and are charged at 900 per hour. Subtotal: 3 x 900 = 2700.
Total: 1000 + 2700 = 3700.
</pre>
