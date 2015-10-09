/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void deleteNode(ListNode node) {
        ListNode p=node.next;
        while(p!=null)
        {
            node.val=p.val;
            if(p.next==null)
            {
                node.next=null;
                return;
            }
            node=p;
            p=p.next;
        }
        return;
    }
}