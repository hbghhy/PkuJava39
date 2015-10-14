/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
int i,j;
    	ListNode p=new ListNode(0);
    	ListNode s=new ListNode(0);
    	ListNode head1=new ListNode(0);
    	p=head;
    	i=j=0;
    	if(head==null||head.next==null)
    	{
    		return true;
    	}
    	while(p!=null)
    	{
    		p=p.next;
    		i++;//记录结点个数
    	}
    	p=head;
    	while(j<i/2)
    	{
    		p=p.next;
    		j++;
    	}
    	head1=p;
    	s=p.next;
    	while(s!=null)
    	{
    		p.next=s.next;
    		s.next=head1;
    		head1=s;
    		s=p.next;
    			
    	}
    	
    	while(head!=null&&head1!=null)
    	{
    		if(head.val==head1.val)
    		{
    			head=head.next;
    			head1=head1.next;
    		}
    		else
    			return false;
    	}
    	return true;
    }
}