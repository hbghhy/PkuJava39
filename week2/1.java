public class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> dic=new HashMap<Integer,Integer>();
        int[] ans=new int[2];
        int i,j;
        dic.put(nums[0],0);
        for(i=1;i<nums.length;i++)
        {
            j=target-nums[i];
            if(dic.containsKey(j))
            {
                ans[0]=dic.get(j)+1;
                ans[1]=i+1;
                return ans;
            }
            dic.put(nums[i],i);
        }
        return ans;
    }
}