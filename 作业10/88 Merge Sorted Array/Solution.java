public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1,j=n-1,cur=m+n-1;
        int tmp;
        while(i>=0&&j>=0){
            if(nums2[j]>nums1[i]){
                nums1[cur--]=nums2[j--];
            }else{
                nums1[cur--]=nums1[i--];
            }
        }
        
        while(i>=0){
            nums1[cur--]=nums1[i--];
        }
        
        while(j>=0){
            nums1[cur--]=nums2[j--];
        }
    }
}