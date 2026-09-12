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
    public ListNode reverseKGroup(ListNode head, int k) {
        //initialize dummy because head of the LinkedList will be modified
        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy;

        while(true){
            ListNode kth = findKth(groupPrev, k);
            if(kth == null) break; //exit condition
            ListNode groupNext = kth.next;

            // set prev = the first node in the next group, this way we don't end up splitting the list
            ListNode prev = kth.next, curr = groupPrev.next;
            //reverse the list
            while(curr != groupNext){
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            //node that is now the tail
            ListNode temp = groupPrev.next;

            // head of the current group, connect the list correctly
            groupPrev.next = kth;

            // groupPrev for the next group
            groupPrev = temp;
        }

        return dummy.next;
    }

    // if this retuns null that means we do not have enough nodes to reverse the list
    private ListNode findKth(ListNode curr, int k){
        while(curr != null && k > 0){
            curr = curr.next;
            k--;
        }
        return curr;
    }
}