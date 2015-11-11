public class Solution {
    private int[] val={1000,900,500,400,100,90,50,40,10,9,5,4,1};
    private String[] Roman={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    public String intToRoman(int num) {
        StringBuilder ans=new StringBuilder();
        int t;
        for(int i=0;i<val.length;i++)
        {
            while(num>=val[i])
            {
                t=num/val[i];
                num%=val[i];
                for(int j=0;j<t;j++)
                {
                    ans.append(Roman[i]);
                }
            }
        }
        return ans.toString();
    }
}