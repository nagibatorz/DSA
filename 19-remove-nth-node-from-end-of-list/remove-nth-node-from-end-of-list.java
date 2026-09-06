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

// Two-pointer one pass approach
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head); //use dummy node
        
        // declare pointers
        ListNode left = dummy;
        ListNode right = head;
        
        //keep moving right until the distance between left and right is n + 1
        for(int i = 0; i < n; i++){
            right = right.next;
        }

        // when right == null left.next is the node we want to remove
        while(right != null){
            right = right.next;
            left = left.next;
        }

        // remove the node
        left.next = left.next.next;
        return dummy.next;
    }
}