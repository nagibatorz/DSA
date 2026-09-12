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
    public ListNode mergeKLists(ListNode[] lists) {
        // handle 0 and 1 list case
        if(lists.length == 0) return null;
        if(lists.length == 1) return lists[0];

        // merge the lists where lists[i] will hold the result of merging all lists up to i
        for(int i = 1; i < lists.length; i++){
            lists[i] = merge(lists[i - 1], lists[i]);
        }
        return lists[lists.length - 1];
    }


    // the exact merge sorted lists implementation
    private ListNode merge(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                curr.next = l1;
                l1 = l1.next;
            } else{
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if(l1 == null && l2 != null){
            curr.next = l2;
        } else if(l1 != null && l2 == null){
            curr.next = l1;
        }
        return dummy.next;
    }
}