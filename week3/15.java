public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans=new ArrayList<List<Integer>>();
        if(nums.length<3) return ans;
        Set<List<Integer>> set=new HashSet<List<Integer>>();
        Arrays.sort(nums);
        int i,j,k;
        for(i=0;i<nums.length-2;i++)
        {
            j=i+1;k=nums.length-1;
            while(j<k)
            {
                if((nums[i]+nums[j]+nums[k])==0)
                {
                    List<Integer> now=new ArrayList();
                    now.add(nums[i]);
                    now.add(nums[j]);
                    now.add(nums[k]);
                    set.add(now);
                    j++;k--;
                }
                else if((nums[i]+nums[j]+nums[k])<0)
                {
                    j++;
                }
                else
                {
                    k--;
                }
            }
        }
        for(List<Integer> s:set)
        {
            ans.add(s);
        }
        return ans;
    }
}