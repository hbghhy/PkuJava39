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
        List<Integer> res = new ArrayList<Integer>();
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        if(root==null){
            return res;
        }
        
        TreeNode tmp = root;
        while(tmp!=null || !stack.isEmpty()){
        	while(tmp!=null){
        		stack.addLast(tmp);
        		tmp=tmp.left;
        	}
        	if(!stack.isEmpty()){
        		tmp = stack.removeLast();
        		res.add(new Integer(tmp.val));
        		tmp=tmp.right;
        	}
        }
        return res;
    }
}