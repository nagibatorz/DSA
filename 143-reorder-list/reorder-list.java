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

// Strategy: find the middle of the list -> reverse second half -> merge first half and reversed second half one-by-one
class Solution {
    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // find middle + even/odd edge case
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // first and second half pointers
        ListNode first = head;
        ListNode second = slow.next;
        //cut off second half from first half
        slow.next = null;

        // reverse second half
        ListNode curr = second;
        ListNode prev = null;
        while(curr != null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        second = prev;


        while(first != null && second != null){
            // save references
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;

            //first points to second
            first.next = second;
            //second points to the rest of first 
            second.next = temp1;

            //move pointers
            first = temp1;
            second = temp2;
        }
    }
}