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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // dummy node to save the head
        ListNode dummy = new ListNode(0, head);
        ListNode curr = head;
        ListNode leftPrev = dummy;
        // find the node where the reversal starts
        for(int i = 0; i < left - 1; i++){
            leftPrev = curr;
            curr = curr.next;
        }

        // curr -> node at left
        // leftPrev -> last node to not be reversed

        // reverse list set up
        ListNode prev = null;
        for(int i = 0; i < (right - left + 1); i++){  // reverse nodes in the range right to left inclusive
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        // curr -> the first node to not be reversed after revrsed nodes
        // prev -> last reversed nodes
        // we need to point last node to not be reversed -> last reversed node
        // first node to be reversed -> first node to not be reversed after reversal
        leftPrev.next.next = curr;
        leftPrev.next = prev;
        return dummy.next;
    }
}