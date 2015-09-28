public class Solution {
    public List<List<Integer>> generate(int numRows) {
        int i,j;
        List<List<Integer>> ansList=new ArrayList<List<Integer>>();
        for(i=0;i<numRows;i++)
        {
            List<Integer> list=new ArrayList<Integer>();
            for(j=0;j<i+1;j++)
            {
                if(j==0||j==i) list.add(1);
                else
                {
                    list.add(ansList.get(i-1).get(j-1)+ansList.get(i-1).get(j));
                }
            }
            ansList.add(list);
        }
        return ansList;
    }
}