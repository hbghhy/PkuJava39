public class Solution {
    public boolean isAnagram(String s, String t) {
        char[] ss=s.toCharArray(),tt=t.toCharArray();
        int ls=ss.length,lt=tt.length;
        if(ls!=lt) return false;
        Arrays.sort(ss);
        Arrays.sort(tt);
        for(int i=0;i<ls;i++)
        {
            if(ss[i]!=tt[i]) return false;
        }
        return true;
    }
}