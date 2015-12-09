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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> path1=new LinkedList<TreeNode>();
        LinkedList<TreeNode> path2=new LinkedList<TreeNode>();
        findPath(root,p,path1);
        findPath(root,q,path2);
        TreeNode ans=root;
        while(path1.peek()!=null && path1.peek()==path2.peek())
        {
            ans=path1.peek();
            path1.poll();
            path2.poll();
        }
        return ans;
    }
    public void findPath(TreeNode root,TreeNode p,LinkedList<TreeNode> path)
    {
        path.add(root);
        if(root==p)
        {
            return;
        }
        if(root.val<p.val)
        {
            findPath(root.right,p,path);
        }
        else if(root.val>p.val)
        {
            findPath(root.left,p,path);
        }
    }
}