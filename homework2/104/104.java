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
    public int maxDepth(TreeNode root) {
        int l=0,r=0;
        if(root==null)
            return 0;
        if(root.left==null&&root.right==null)
            return 1;
        if(root.left!=null)
            l=maxDepth(root.left);
        if(root.right!=null)
            r=maxDepth(root.right);
        return l>r?l+1:r+1;
    }
}