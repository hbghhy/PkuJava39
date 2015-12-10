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
    public TreeNode invertTree(TreeNode root) {
        
        if(root!=null)
        {
            TreeNode s=null;
            s=root.left;
            root.left=root.right;
            root.right=s;
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
        
    }
}