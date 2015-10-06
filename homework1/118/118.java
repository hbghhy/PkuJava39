public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list=new LinkedList<List<Integer>>();
       // List<Integer> ls=new  LinkedList<Integer>();
        int i,j;
        if(numRows<=0)
        	return list;
        for(i=0;i<numRows;i++)
        {
             List<Integer> ls=new  LinkedList<Integer>();
        	for(j=0;j<=i;j++)
        	{
        		if(j==0||j==i)
        		{
        			ls.add(1);
        		}
        		else
        			ls.add(list.get(i-1).get(j-1)+list.get(i-1).get(j));
        	}
        	list.add(ls);
        //	ls.clear(); //如果ls.clear(),不仅仅是清除了ls的内容，因为list.add(ls),是加入了ls，并不是仅仅加入了ls的内容，list也会被清空
        }
        
        return list;
        
    }
}