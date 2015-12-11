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
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
			return true;
		}
		return judgeSymmetric(root.left,root.right);
    }
    
    public boolean judgeSymmetric(TreeNode r1,TreeNode r2){
		if(r1==null && r2==null){
			return true;
		}else if(r1==null || r2==null){
			return false;
		}else if(r1.val != r2.val){
			return false;
		}else{
			return (judgeSymmetric(r1.left,r2.right)&&judgeSymmetric(r1.right,r2.left));
		}
	}
}