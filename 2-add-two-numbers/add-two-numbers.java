/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        //initialize carry
        int carry = 0;

        while(l1 != null || l2 != null){
            // if one of the nodes is null we add it as a zero
            int addend1 = (l1 == null) ? 0 : l1.val;
            int addend2 = (l2 == null) ? 0 : l2.val;
            int sum = addend1 + addend2 + carry;

            //digit to be carried to the next place
            carry = sum / 10;
            int sumVal = sum % 10;
            ListNode sumNode = new ListNode(sumVal);

            //do not move pointers on null
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
            curr.next = sumNode;
            curr = curr.next;
        }
        // for example 7 + 8 case
        if(carry > 0){
            curr.next = new ListNode(carry);
        }
        return dummy.next;
    }
}