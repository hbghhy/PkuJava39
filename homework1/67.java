public class Solution {
    public String addBinary(String a, String b) {
        int ia=a.length()-1,ib=b.length()-1,s=0,k,c0=(int)'0',ta,tb;
        StringBuilder ans=new StringBuilder((ia>ib?ia:ib)+1);
        while(ia>=0 || ib>=0)
        {
            if(ia>=0) ta=((int)a.charAt(ia))-c0;
            else ta=0;
            if(ib>=0) tb=((int)b.charAt(ib))-c0;
            else tb=0;
            k=ta+tb+s;
            s=k>1?1:0;
            k=k>1?(k-2):k;
            ans.append(k);
            ia--;ib--;
        }
        if(s>0) ans.append(s);
        return ans.reverse().toString();
    }
}