public class Solution {
    public int singleNumber(int[] nums) {
        HashSet<Integer>hs=new HashSet<Integer>();//不能使用额外的空间，可以使用 HashSet或者其他容器吗？
    	// HashMap<Integer,Integer>hs=new HashMap<Integer,Integer>();
    	 for(int i=0;i<nums.length;i++)
    	 {
    		 if(!hs.contains(nums[i]))
    		 {
    			 hs.add(nums[i]);
    		 }
    		 else
    			 hs.remove(nums[i]);
    	 }
         Iterator it=hs.iterator();
         String s;
    	 if(it.hasNext())
    	 {
    		
    		s=it.next().toString();
    	 }
    	 else s="0";
         int x=Integer.parseInt(s);
    	return x;
    } 
}