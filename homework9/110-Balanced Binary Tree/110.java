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
    public boolean isBalanced(TreeNode root) {
        if(root==null)
            return true;
       
        int l=deep(root.left);
        System.out.println(l);
        
        int r=deep(root.right);
        System.out.println(r);
        if(Math.abs(l-r)<=1)
        {
            return isBalanced(root.left)&&isBalanced(root.right);
        }
        else
            return false;
    }
    public int deep(TreeNode root)
    {
        if(root==null)
            return 0;
        int l=deep(root.left)+1;
        int r=deep(root.right)+1;
        return l>r?l:r;
    }
}