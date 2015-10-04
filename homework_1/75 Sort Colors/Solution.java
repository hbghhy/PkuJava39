public class Solution {
    public void sortColors(int[] nums) {
        int left=0;
        int right=nums.length-1;
        int i=left;
        while(i<=right){
        	if(nums[i]==0){
        		swap(nums,i,left);
        		i++;
        		left++;
        	}else if(nums[i]==1){
        		i++;
        	}else if(nums[i]==2){
        		swap(nums,i,right);
        		right--;
        	}
        }
    }
    
    public static void swap(int[] nums,int i,int j){
		int tmp=nums[j];
		nums[j]=nums[i];
		nums[i]=tmp;
	}
}