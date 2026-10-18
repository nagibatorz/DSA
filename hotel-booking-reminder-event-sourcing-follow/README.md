<h2>hotel booking reminder (event-sourcing follow)</h2><hr><p>A hotel reservation system is responsible for creating email communication schedules for its guests, based on their booking details. You're given two JSON-like objects: <code>reservations</code> and <code>emailTouchpoints</code>.</p>

<p>The <code>reservations</code> object contains:</p>

<ul>
	<li><code>"guest_name"</code>: the guest's name (string)</li>
	<li><code>"room_type"</code>: booked room category (string)</li>
	<li><code>"check_in_day"</code>: the day of check-in (integer)</li>
	<li><code>"stay_length"</code>: total number of days of the stay (integer)</li>
</ul>

<p>The <code>emailTouchpoints</code> object maps timing markers to email subjects:</p>

<ul>
	<li><code>"start"</code>: schedule an email on the check-in day</li>
	<li><code>"end"</code>: schedule an email on the check-out day (check_in_day + stay_length)</li>
	<li>Negative integer string keys (like <code>"-15"</code>, <code>"-7"</code>): schedule emails that many days before the check-out day</li>
</ul>

<p>Return all scheduled email notifications for all guests, sorted by the sending date (earliest to latest). Each notification should be formatted like this: <code>"&lt;day&gt;: [&lt;subject&gt;] Booking: &lt;guest_name&gt; (&lt;room_type&gt;)"</code></p>

<p>Only include notifications where the calculated day is on or after the guest's check_in_day. If multiple notifications fall on the same day, their order doesn't matter. Notifications on different days must be sorted chronologically.</p>

<p><b>Constraints:</b></p>

<ul>
	<li><code>1 &le; reservations.length &le; 1000</code></li>
	<li><code>0 &le; check_in_day &le; 10<sup>9</sup></code></li>
	<li><code>1 &le; stay_length &le; 1000</code></li>
	<li><code>emailTouchpoints</code> contains <code>"start"</code>, <code>"end"</code>, and any number of negative integer string keys</li>
</ul>

<p><strong class="example">Example:</strong></p>

<pre>
<strong>Input:</strong>
reservations = [
  {
    "guest_name": "John",
    "room_type": "Standard",
    "check_in_day": 0,
    "stay_length": 30
  },
  {
    "guest_name": "Alice",
    "room_type": "Suite",
    "check_in_day": 1,
    "stay_length": 15
  }
],
emailTouchpoints = {
  "start": "Welcome",
  "-15": "Upcoming Checkout",
  "end": "Thank You"
}

<strong>Output:</strong> ["0: [Welcome] Booking: John (Standard)",
         "1: [Upcoming Checkout] Booking: Alice (Suite)",
         "1: [Welcome] Booking: Alice (Suite)",
         "15: [Upcoming Checkout] Booking: John (Standard)",
         "16: [Thank You] Booking: Alice (Suite)",
         "30: [Thank You] Booking: John (Standard)"]

<strong>Explanation:</strong> The email sending schedule is calculated as follows:

"John" (check-in day 0, stay length 30):
Day 0: Welcome email (check-in)
Day 15: "Upcoming Checkout" reminder (15 days before checkout: 30 - 15 = 15)
Day 30: Thank You email (check-out)
"Alice" (check-in day 1, stay length 15):
Day 1: Welcome email and "Upcoming Checkout" reminder (15 days before checkout: 16 - 15 = 1)
Day 16: Thank You email
</pre>

<p><b>Follow-up 1:</b><br>You are now given a list <code>modifications</code>, where each modification contains:</p>

<ul>
	<li><code>"guest_name"</code>: Guest's name (string)</li>
	<li><code>"new_room_type"</code>: The new room category (string)</li>
	<li><code>"modification_date"</code>: The date when the room change occurs (integer)</li>
</ul>

<p>When a room change happens during an active stay:</p>

<p>Schedule a <code>"[Room Change]"</code> email on modification_date using the new room type<br>All emails scheduled after modification_date must use the new room type instead of the original one<br>Each modification generates its own <code>"[Room Change]"</code> email, and the room type for any notification is determined by the most recent modification that occurred strictly before that notification's scheduled day.</p>

<p><b>Constraints:</b></p>

<ul>
	<li><code>0 &le; modifications.length &le; 1000</code></li>
	<li>All modification entries refer to existing guests</li>
	<li>A guest may have multiple room changes during their stay</li>
</ul>

<p><strong class="example">Example:</strong></p>

<pre>
<strong>Input:</strong>
reservations = [
  {
    "guest_name": "A",
    "room_type": "Standard",
    "check_in_day": 0,
    "stay_length": 30
  },
  {
    "guest_name": "B",
    "room_type": "Suite",
    "check_in_day": 1,
    "stay_length": 15
  }
],
emailTouchpoints = {
  "start": "Welcome",
  "-15": "Upcoming Checkout",
  "end": "Thank You"
},
modifications = [
  {
    "guest_name": "A",
    "new_room_type": "Suite",
    "modification_date": 5
  }
]

<strong>Output:</strong> ["0: [Welcome] Booking: A (Standard)",
         "1: [Upcoming Checkout] Booking: B (Suite)",
         "1: [Welcome] Booking: B (Suite)",
         "5: [Room Change] Booking: A (Suite)",
         "15: [Upcoming Checkout] Booking: A (Suite)",
         "16: [Thank You] Booking: B (Suite)",
         "30: [Thank You] Booking: A (Suite)"]

<strong>Explanation:</strong> The email sending schedule is calculated as follows:

"Guest A" (Standard &rarr; Suite on day 5):
Day 0: Welcome email with Standard (original room)
Day 5: Room Change email (room changed to Suite)
Day 15: Upcoming Checkout with Suite (room changed before this email)
Day 30: Thank You with Suite
"Guest B" (Suite, no changes):
Day 1: Welcome and Upcoming Checkout emails with Suite
Day 16: Thank You with Suite
</pre>

<p><b>Follow-up 2:</b><br>In addition to the previous room change event, the modifications list can now also include stay extension events. Each extension entry contains:</p>

<ul>
	<li><code>"guest_name"</code>: Guest's name (string).</li>
	<li><code>"extension_days"</code>: Number of days to extend the stay (integer).</li>
	<li><code>"modification_date"</code>: The date when the extension occurs (integer).</li>
</ul>

<p>When a stay extension happens:</p>

<p>Schedule a <code>"[Extended]"</code> email on modification_date using the current room type at that moment<br>The booking's end date is increased by extension_days<br>All future notifications (e.g., upcoming, thank you) are recalculated based on the new end date<br>Room changes and extensions may occur in any order. Always process modifications in chronological order by their modification_date for each guest.</p>

<p><b>Constraints:</b></p>

<ul>
	<li>All dates are non-negative integers</li>
</ul>

<p><strong class="example">Example:</strong></p>

<pre>
<strong>Input:</strong>
reservations = [
  {
    "guest_name": "A",
    "room_type": "Standard",
    "check_in_day": 0,
    "stay_length": 30
  },
  {
    "guest_name": "B",
    "room_type": "Suite",
    "check_in_day": 1,
    "stay_length": 15
  }
],
emailTouchpoints = {
  "start": "Welcome",
  "-15": "Upcoming Checkout",
  "end": "Thank You"
},
modifications = [
  {
    "guest_name": "A",
    "new_room_type": "Suite",
    "modification_date": 5
  },
  {
    "guest_name": "B",
    "extension_days": 15,
    "modification_date": 3
  }
]

<strong>Output:</strong> ["0: [Welcome] Booking: A (Standard)",
         "1: [Welcome] Booking: B (Suite)",
         "1: [Upcoming Checkout] Booking: B (Suite)",
         "3: [Extended] Booking: B (Suite)",
         "5: [Room Change] Booking: A (Suite)",
         "15: [Upcoming Checkout] Booking: A (Suite)",
         "16: [Upcoming Checkout] Booking: B (Suite)",
         "30: [Thank You] Booking: A (Suite)",
         "31: [Thank You] Booking: B (Suite)"]

<strong>Explanation:</strong> The email sending schedule is calculated as follows:

"Guest A" (Standard &rarr; Suite on day 5):
Day 0: Welcome with Standard
Day 5: Changed to Suite
Day 15: Upcoming Checkout with Suite
Day 30: Thank You with Suite
"Guest B" (extends by 15 days on day 3):
Day 1: Welcome and Upcoming Checkout (before extension, end = 16)
Day 3: Extended (extension pushes end from 16 &rarr; 31)
Day 16: Upcoming Checkout (15 days before new end: 31 - 15 = 16)
Day 31: Thank You (new end date).
</pre>
