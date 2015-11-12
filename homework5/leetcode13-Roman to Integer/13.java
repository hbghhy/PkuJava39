public class Solution {
    public int romanToInt(String s) {
        HashMap <String,Integer>hm=new HashMap <String,Integer>();
		  hm.put("I", 1);
		  hm.put("V", 5);
		  hm.put("X", 10);
		  hm.put("L", 50);
		  hm.put("C", 100);
		  hm.put("D", 500);
		  hm.put("M", 1000);
		  int sum=hm.get(String.valueOf(s.charAt(s.length()-1)));
		 // System.out.println(sum);
		  for(int i=s.length()-2;i>=0;i--)
		  {
			  if(hm.get(String.valueOf(s.charAt(i)))<hm.get(String.valueOf(s.charAt(i+1))))
			  {
				  sum-=hm.get(String.valueOf(s.charAt(i)));
			  }
			  else
				  sum+=hm.get(String.valueOf(s.charAt(i)));
			  //System.out.println(sum);
		  }
		  return sum;
    }
}