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
    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }else if(root.left==null&&root.right==null){
            return 1;
        }else{
            int a,b;
            a=minDepth(root.left);
            b=minDepth(root.right);
            if(a==0){
                return b+1;
            }else if(b==0){
                return a+1;
            }else{
                return a<b?a+1:b+1;
            }
        }
    }
}