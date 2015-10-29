class MinStack {
    ListNode stack=null;
    ListNode minStack=null;
    ListNode p;
    public void push(int x) {
        p=new ListNode(x,stack);
        stack=p;
        if(minStack==null || minStack.val>x)
        {
            p=new ListNode(x,minStack);
            minStack=p;
        }
        else
        {
            p=new ListNode(minStack.val,minStack);
            minStack=p;
        }
    }

    public void pop() {
        stack=stack.next;
        minStack=minStack.next;
    }

    public int top() {
        return stack.val;
    }

    public int getMin() {
        return minStack.val;
    }
}
class ListNode
{
    int val;
    ListNode next;
    ListNode(int x,ListNode y)
    {
        this.val=x;this.next=y;
    }
}