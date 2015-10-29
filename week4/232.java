class MyQueue {
    Deque<Integer> s1=new LinkedList();
    Deque<Integer> s2=new LinkedList();
    // Push element x to the back of queue.
    public void push(int x) {
        s1.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        while(s1.size()>1)
        {
            s2.push(s1.peek());
            s1.pop();
        }
        s1.pop();
        while(s2.size()>0)
        {
            s1.push(s2.peek());
            s2.pop();
        }
    }

    // Get the front element.
    public int peek() {
        while(s1.size()>1)
        {
            s2.push(s1.peek());
            s1.pop();
        }
        int ans=s1.peek();
        s2.push(ans);
        s1.pop();
        while(s2.size()>0)
        {
            s1.push(s2.peek());
            s2.pop();
        } 
        return ans;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return s1.size()==0 && s2.size()==0;
    }
}