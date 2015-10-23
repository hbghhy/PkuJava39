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
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> ans=new ArrayList<Integer>();
        if(root==null) return ans;
        LinkedList<TreeNode> stack=new LinkedList<TreeNode>();
        stack.add(root);
        TreeNode now=root.left;
        while(stack.size()>0)
        {
            while(now!=null)
            {
                stack.add(now);
                now=now.left;
            }
            now=stack.pollLast();
            ans.add(now.val);
            if(now.right!=null)
            {
                stack.add(now.right);
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