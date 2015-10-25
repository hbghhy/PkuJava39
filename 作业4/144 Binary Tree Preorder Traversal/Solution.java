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
        List<Integer> res = new ArrayList<Integer>();
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        if(root==null){
            return res;
        }
        stack.addLast(root);
        while(!stack.isEmpty()){
        	TreeNode tmp = stack.removeLast();
        	res.add(new Integer(tmp.val));
        	
        	if(tmp.right!=null){
        		stack.addLast(tmp.right);
        	}
        	if(tmp.left!=null){
        		stack.addLast(tmp.left);
        	}
        }
        return res;
    }
}