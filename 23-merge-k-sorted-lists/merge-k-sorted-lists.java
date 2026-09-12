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


// Iterative divide and conquer approach
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // handle 0 and 1 list case
        if(lists.length == 0) return null;
        if(lists.length == 1) return lists[0];


        while(lists.length > 1){
            List<ListNode> merged = new ArrayList<>();
            // merge 2 lists at a time
            for(int i = 0; i < lists.length; i+=2){
                ListNode l1 = lists[i];
                ListNode l2 = (i + 1 < lists.length) ? lists[i+1] : null;
                merged.add(merge(l1, l2));
            }
            // converty to an array with dummy node
            lists = merged.toArray(new ListNode[0]);
        }

        return lists[0];
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