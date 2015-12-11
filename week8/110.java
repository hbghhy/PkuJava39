public class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root==null)
        {
            return true;
        }
        int i=high(root.left),j=high(root.right);
        return (i-j<=1) && (i-j>=-1) && isBalanced(root.left) && isBalanced(root.right);
    }
    public int high(TreeNode root)
    {
        if(root==null)
        {
            return 0;
        }
        int i=high(root.left),j=high(root.right);
        return (i>j?i:j)+1;
    }
    
}