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
        if(head==null) return true;
        int l=0,i,j,k;
        ListNode p=head,q=head,head2,h;
        while(p!=null)
        {
            p=p.next;l++;
        }
        if(l==1) return true;
        k=l>>1;
        for(i=0;i<k;i++)
        {
            q=q.next;
        }
        if((l&1)==1) q=q.next;
        head2=q;
        q=q.next;
        head2.next=null;
        while(q!=null)
        {
            h=q.next;
            q.next=head2;
            head2=q;
            q=h;
        }
        p=head;
        while(head2!=null)
        {
            if(head2.val!=p.val) return false;
            p=p.next;head2=head2.next;
        }
        return true;
        
    }
}