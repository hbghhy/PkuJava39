import java.io.BufferedReader;
import java.io.InputStreamReader;

 
public class GobangGame {
	// 定义达到赢条件的棋子数目
	private final int WIN_COUNT = 5;
	// 定义用户输入的X坐标
	private int posX = 0;
	// 定义用户输入的X坐标
	private int posY = 0;
	// 定义棋盘
	private Chessboard chessboard;

	/**
	 * 空构造器
	 */
	public GobangGame() {
	}

	/**
	 * 构造器，初始化棋盘和棋子属性
	 * 
	 * @param chessboard
	 *            棋盘类
	 */
	public GobangGame(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	/**
	 * 检查输入是否合法。
	 * 
	 * @param inputStr
	 *            由控制台输入的字符串。
	 * @return 字符串合法返回true,反则返回false。
	 */
	public boolean isValid(String inputStr) {
		// 将用户输入的字符串以逗号(,)作为分隔，分隔成两个字符串
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("请以(数字,数字)的格式输入：");
			return false;
		}
		// 检查输入数值是否在范围之内
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X与Y坐标只能大于等于1,与小于等于" + Chessboard.BOARD_SIZE
					+ ",请重新输入：");
			return false;
		}
		// 检查输入的位置是否已经有棋子
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "十") {
			chessboard.printBoard();
			System.out.println("此位置已经有棋子，请重新输入：");
			return false;
		}
		return true;
	}

	/**
	 * 开始下棋
	 */
	public void start() throws Exception {
		// true为游戏结束
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// 获取键盘的输入
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:每当键盘输入一行内容按回车键，则输入的内容被br读取到
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// 如果不合法，要求重新输入，再继续
				continue;
			}
			// 把对应的数组元素赋为"●"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// 判断用户是否赢了
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// 计算机随机选择位置坐标
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				// 判断计算机是否赢了
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// 如果产生胜者，询问用户是否继续游戏
			if (isOver) {
				// 如果继续，重新初始化棋盘，继续游戏
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				// 如果不继续，退出程序
				break;
			}
			chessboard.printBoard();
			System.out.println("请输入您下棋的坐标，应以x,y的格式输入：");
		}
	}

	/**
	 * 是否重新开始下棋。
	 * 
	 * @param chessman
	 *            "●"为用户，"○"为计算机。
	 * @return 开始返回true，反则返回false。
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "恭喜您，您赢了，"
				: "很遗憾，您输了，";
		System.out.println(message + "再下一局？(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// 开始新一局
			return true;
		}
		return false;

	}
	/**
	 * 一个简单的ai
	 * 决策树AI可参考：http://blog.csdn.net/pi9nc/article/details/10858411
	 * 本ai考虑棋盘上空点对本方与对方的价值--即本方在该点下子之后，本方所有已下子点周围连续可达最大数，利用一个二维数组存储
	 * 同时评估本方与对方情况，若本方下子后出现双4或单5 或对方某点出先单5双4进行封堵
	 */
	/*
	 * 首先实现一个求单点价值的函数，ico为该点颜色
	 */
	public int value(int posX,int posY,String ico ) {
		int i=posX,j=posY,count1=0,count2=0,max=0;
		int[] di={1,1,1,0};
		int[] dj={1,0,-1,1};
		for(int k=0;k<di.length;k++)
		{
			count1=count2=0;
			i=posX-di[k];j=posY-dj[k];
			while(i<chessboard.BOARD_SIZE && j<chessboard.BOARD_SIZE && i>=0 && j>=0)
			{
				if(chessboard.getBoard()[i][j]==ico) count1++;
				else break;
				i-=di[k];j-=dj[k];				
			}
			i=posX+di[k];j=posY+dj[k];
			while(i<chessboard.BOARD_SIZE && j<chessboard.BOARD_SIZE && i>=0 && j>=0)
			{
				if(chessboard.getBoard()[i][j].equals(ico)) count2++;
				else break;
				i+=di[k];j+=dj[k];				
			}
			if(count1+count2+1>=max)  max=count1+count2+1;
		}		
		return max;		
	}
	
	/*
	 * 求出落子后整个棋盘的局势
	 */
	public int[] valueCount(String ico) {
		int i,j,count1=0,count2=0,count3=0,count4=0,count5=0,k;
		for(i=0;i<chessboard.BOARD_SIZE;i++)
			for(j=0;j<chessboard.BOARD_SIZE;j++)
			{
				//count1=count2=count3=count4=count5=0;
				if(!chessboard.getBoard()[i][j].equals(ico))
					continue;
				k=value(i, j, ico);
				if(k>=5) count5++;
				else if(k==4) count4++;
				else if(k==3) count3++;
				else if(k==2) count2++;
				else {
					count1++;
				}
			}
		int[] ans=new int[5];
		ans[0]=count5;
		ans[1]=count4;
		ans[2]=count3;
		ans[3]=count2;
		ans[4]=count1;
		return ans;
	}
	/*
	 * 枚举每个点落子后局势，并求出本方最优落点，及其情况
	 */
	public  int[] ai(String ico)
	{
		int i,j,k;
		int[] ans=new int[4];
		int[] t=new int[5];
		int[] max=new int[5];
		for(i=0;i<chessboard.BOARD_SIZE;i++)
			for(j=0;j<chessboard.BOARD_SIZE;j++)
			{
				k=1;
				if(!((chessboard.getBoard()[i][j]).equals("十")))
					continue;
				chessboard.setBoard(i, j, ico);
				t=valueCount(ico);
				if(t[0]>0)
				{
					ans[0]=i;ans[1]=j;
					ans[2]=5;ans[3]=1;
					return ans;
				}
				while(k<5)
				{
					if(t[k]>max[k])
					{
						ans[0]=i;ans[1]=j;
						ans[2]=5-k;ans[3]=t[k];
						for(int l=0;l<5;l++)
						{
							max[l]=t[l];
						}
						break;
					}
					else if(t[k]<max[k])
					{
						break;
					}
					k++;
				}
				chessboard.setBoard(i, j, "十");				
			}		
		return ans;
	}
	
	
	
	

	/**
	 * 计算机随机下棋
	 */
	public int[] computerDo() {
		int[] a1=ai(Chessman.WHITE.getChessman());
		int[] a2=ai(Chessman.BLACK.getChessman());
		int[] ans=new int[2];
		int k;
		k=5;
		while(k>1)
		{
			if(a1[2]==k)
			{
				ans[0]=a1[0];ans[1]=a1[1];
				return ans;
			}
			if(a2[2]==k)
			{
				ans[0]=a2[0];ans[1]=a2[1];
				return ans;
			}
			k--;
		}
		ans[0]=(int)(Math.random() * (Chessboard.BOARD_SIZE - 1));
		ans[1]=(int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		String[][] board = chessboard.getBoard();
		while (board[ans[0]][ans[1]] != "十") {
			ans[0] = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			ans[1] = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		}
		return ans;
		
	}

	/**
	 * 判断输赢
	 * 
	 * @param posX
	 *            棋子的X坐标。
	 * @param posY
	 *            棋子的Y坐标
	 * @param ico
	 *            棋子类型
	 * @return 如果有五颗相邻棋子连成一条直接，返回真，否则相反。
	 */
	public boolean isWon(int posX, int posY, String ico) {
		int i=posX,j=posY,count1=0,count2=0;
		int[] di={1,1,1,0};
		int[] dj={1,0,-1,1};
		for(int k=0;k<di.length;k++)
		{
			count1=0;count2=0;
			i=posX-di[k];j=posY-dj[k];
			while(i<chessboard.BOARD_SIZE && j<chessboard.BOARD_SIZE && i>=0 && j>=0)
			{
				if((chessboard.getBoard())[i][j].equals(ico)) count1++;
				else break;
				i-=di[k];j-=dj[k];				
			}
			i=posX+di[k];j=posY+dj[k];
			while(i<chessboard.BOARD_SIZE && j<chessboard.BOARD_SIZE && i>=0 && j>=0)
			{
				if((chessboard.getBoard()[i][j]).equals(ico)) count2++;
				else break;
				i+=di[k];j+=dj[k];				
			}
			if(count1+count2+1>=WIN_COUNT) return true;
		}		
		return false;
	}

	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}
