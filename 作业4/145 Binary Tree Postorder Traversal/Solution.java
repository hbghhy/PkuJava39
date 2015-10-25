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
        List<Integer> res = new ArrayList<Integer>();
        LinkedList<TreeNode> stackin = new LinkedList<TreeNode>();
        LinkedList<TreeNode> stackout = new LinkedList<TreeNode>();
        if(root==null){
            return res;
        }
        stackin.addLast(root);
        
        while(!stackin.isEmpty()){
        	TreeNode tmp = stackin.removeLast();
        	stackout.addLast(tmp);
        	
        	if(tmp.left!=null){
        		stackin.addLast(tmp.left);
        	}
        	if(tmp.right!=null){
        		stackin.addLast(tmp.right);
        	}
        }
        
        while(!stackout.isEmpty()){
        	TreeNode tmp = stackout.removeLast();
    		res.add(new Integer(tmp.val));
        }
        
        return res;
    }
}