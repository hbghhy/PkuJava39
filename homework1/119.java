public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans=new ArrayList<Integer>(rowIndex+1);
        int k,half=rowIndex/2,j,i;
        long last=1;
        for(i=0;i<=half;i++)
        {
            if(i==0) ans.add(1);
            else
            {
                last=last*(rowIndex-i+1)/i;
                ans.add((int)last);
            }
        }
        for(;i<=rowIndex;i++)
        {
            j=ans.get(rowIndex-i);
            ans.add(j);
        }
        return ans;
        
    }
}