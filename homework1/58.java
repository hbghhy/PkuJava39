public class Solution {
    public int lengthOfLastWord(String s) {
        int ans=0;
        String[] t=s.split(" ");
        if(t.length>0)
            ans=t[t.length-1].length();
        return ans;
    }
}