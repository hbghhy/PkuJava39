public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
		HashSet<List<Integer>> hashset = new HashSet<List<Integer>>();
		ArrayList<List<Integer>> ret = new ArrayList<List<Integer>>();
	 
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int m = j + 1;
				int n = nums.length - 1;
	 
				while (m < n) {
					int sum = nums[i] + nums[j] + nums[m] + nums[n];
	 
					if (sum > target) {
						n--;
					} else if (sum < target) {
						m++;
					} else if (sum == target) {
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(nums[i]);
						tmp.add(nums[j]);
						tmp.add(nums[m]);
						tmp.add(nums[n]);
	 
						if (!hashset.contains(tmp)) {
							hashset.add(tmp);
							ret.add(tmp);
						}
						m++;
						n--;
					}
				}
			}
		}
		return ret;
    }
}