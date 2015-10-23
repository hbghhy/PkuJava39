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
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> ans=new ArrayList<Integer>();
        if(root==null) return ans;
        LinkedList<TreeNode> stack=new LinkedList<TreeNode>();
        stack.add(root);
        ans.add(root.val);
        TreeNode now=root.left;
        while(stack.size()>0)
        {
            while(now!=null)
            {
                stack.add(now);
                ans.add(now.val);
                now=now.left;
            }
            now=stack.pollLast();
            if(now.right!=null)
            {
                stack.add(now.right);
                ans.add(now.right.val);
                now=now.right.left;                
            }
            else
            {
                now=null;
            }
        }        

        return ans;        
    }
}