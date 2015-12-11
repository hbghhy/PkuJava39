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
        return !(getHeightDiff(root)==-1);
    }
    
    public int getHeightDiff(TreeNode root){
        if(root==null){
            return 0;
        }
        
        int left = getHeightDiff(root.left);
        if(left==-1){
            return -1;
        }
        
        int right = getHeightDiff(root.right);
        if(right==-1){
            return -1;
        }
        
        int diff = left-right;
        if(Math.abs(diff)>1){
            return -1;
        }else{
            return Math.max(left,right)+1;
        }
    }
}