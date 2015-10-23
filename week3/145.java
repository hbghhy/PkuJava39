/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack=new LinkedList<TreeNode>();
        ArrayList<Integer> ans=new ArrayList<Integer>();
        if(root==null) return ans;
        TreeNode pre=null,now=root.left,p;
        stack.add(root);
        while(!stack.isEmpty())
        {
            while(now!=null)
            {
                stack.add(now);
                now=now.left;
            }
            p=(stack.peekLast().right);
            if(p!=null && pre!=p)
            {
                now=p;
                stack.add(now);
                now=now.left;
            }
            else
            {
                p=stack.pollLast();
                ans.add(p.val);
                pre=p;
                now=null;
            }
        }
        return ans;
    }
}