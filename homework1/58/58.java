public class Solution {
    public int lengthOfLastWord(String s) {
        String[] str=s.trim().split(" ");
		int length=str[str.length-1].length();
		return length;
    }
}