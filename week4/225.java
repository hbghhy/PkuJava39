class MyStack {
    Queue<Integer> q1=new LinkedList<Integer> ();
    Queue<Integer> q2=new LinkedList<Integer> ();
    // Push element x onto stack.
    public void push(int x) {
        q1.offer(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        while(q1.size()>0)
        {
            q2.offer(q1.peek());
            q1.poll();
        }
        while(q2.size()>1)
        {
            q1.offer(q2.peek());
            q2.poll();
        }
        q2.poll();
    }

    // Get the top element.
    public int top() {
        while(q1.size()>0)
        {
            q2.offer(q1.peek());
            q1.poll();
        }
        while(q2.size()>1)
        {
            q1.offer(q2.peek());
            q2.poll();
        }
        int ans=q2.peek();
        q1.offer(ans);
        q2.poll();
        return ans;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q1.size()==0 && q2.size()==0;
    }
}