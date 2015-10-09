public class Solution {
    public boolean isPalindrome(String s) {
        s=s.trim();s=s.toLowerCase();
        int i=0,j=s.length()-1;
        char ci,cj;
        while(i<j)
        {
            ci=s.charAt(i);
            cj=s.charAt(j);
            while(!((ci>='a' && ci<='z') || (ci>='0' && ci<='9')))
            {
                i++;
                if(i>=j) return true;
                ci=s.charAt(i);
            }
            while(!((cj>='a' && cj<='z') || (cj>='0' && cj<='9')))
            {
                if(j<=i) return true;
                j--;cj=s.charAt(j);
            }
            if(ci!=cj) return false;
            i++;j--;
        }
        return true;
    }
}
