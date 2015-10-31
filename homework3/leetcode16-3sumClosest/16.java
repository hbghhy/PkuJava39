public class Solution {
    public int threeSumClosest(int[] nums, int target) {
       	int i, j, p, q, sum;
		if (nums.length < 3)
			return 0;
		Arrays.sort(nums);
		int min = Integer.MAX_VALUE;
		int ret=0;
		for (p = 0; p < nums.length - 2; p++)
		{
			if (p != 0 && nums[p - 1] == nums[p])
				continue;
			i = p + 1;
			j = nums.length - 1;
			sum = target - nums[p];
			while (i < j) {
				if (nums[i] + nums[j] < sum)
				{
					if(sum - (nums[i] + nums[j]) < min)
					{
						min=sum - (nums[i] + nums[j]);
						ret = target - min;
					}
					i++;
					while (i < j && nums[i] == nums[i - 1])
						i++;
				}

				else if (nums[i] + nums[j] > sum) {
					if(nums[i] + nums[j]-sum < min)
					{
						min=nums[i] + nums[j]-sum;
						ret = target + min;
					}
					j--;
					while (i < j && nums[j] == nums[j + 1])
						j--;
				} else {
					ret = target;
					return ret;
				}
			}

		}
		return ret;
    }
}