/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
         ListNode p1=new ListNode(0);
	         ListNode p2=new ListNode(0);
	         if(head==null||head.next==null||head.next.next==null)
	             return false;
	         p1=p2=head;
	         while(p1!=null&&p1.next!=null)
	         {
	        	 p1=p1.next.next;
	             p2=p2.next;
	             if(p1==p2)
	               return true;
	             
	         }
	         return false;
        
    }
}