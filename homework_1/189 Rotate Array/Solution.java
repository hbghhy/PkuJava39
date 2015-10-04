public class Solution {
    public void rotate(int[] nums, int k) {
        int n=nums.length;
        int s=k%n;
        reverseString(nums,0,n-1);
        reverseString(nums,0,s-1);
        reverseString(nums,s,n-1);
    }
    
    public static void reverseString(int[] nums,int start,int end){
		int i=start;
		int j=end;
		int tmp;
		while(i<j){
			tmp=nums[j];
			nums[j]=nums[i];
			nums[i]=tmp;
			
			i++;
			j--;
		}
	}
}