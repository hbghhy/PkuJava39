public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> ret=new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		for(int i=0;i<nums.length;i++){
			while(i>0&&i<(nums.length)&&(nums[i]==nums[i-1])){
				i++;
			}
			int j=i+1;
			int k=nums.length-1;
			while(j<k){
				int sum=nums[i]+nums[j]+nums[k];
				if(sum==0){
					List<Integer> tmp = new ArrayList<Integer>();
					tmp.add(nums[i]);
					tmp.add(nums[j]);
					tmp.add(nums[k]);
					ret.add(tmp);
					j++;
					while((j<k)&&nums[j]==nums[j-1]){
						j++;
					}
					k--;
					while((j<k)&&nums[k]==nums[k+1]){
						k--;
					}
				}else if(sum<0){
					j++;
					while((j<k)&&nums[j]==nums[j-1]){
						j++;
					}
				}else if(sum>0){
					k--;
					while((j<k)&&nums[k]==nums[k+1]){
						k--;
					}
				}
			}
		}
		return ret;
    }
}