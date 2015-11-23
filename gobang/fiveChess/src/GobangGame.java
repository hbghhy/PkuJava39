
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
				int[] computerPosArr = computerDo(posX, posY, chessman);///
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
	 * 计算机随机下棋
	 */
	public int[] computerDo(int x, int y, String ico) {
		String[][] board = chessboard.getBoard();
		int sumh,sums,sumz,sumy;
		sumh=sums=sumz=sumy=0;
		int flag1h,flag2h,flag1s,flag2s,flag1z,flag2z,flag1y,flag2y;
		flag1h=flag2h=flag1s=flag2s=flag1z=flag2z=flag1y=flag2y=0;
		int left=x;
		while(left>=0&&board[left][y]==ico)
		{
			sumh++;
			left--;
		}
		int leftx=left;
		if(board[left][y]!=ico&&left-1>=0)
		{
			left--;
			while(left>=0&&board[left][y]==ico)
			{
				flag1h++;
				left--;
			}		
		}
		int right=x+1;
		while(right<=Chessboard.BOARD_SIZE&&board[right][y]==ico)
		{
			sumh++;
			right++;
		}
		int rightx=right;
		if(board[right][y]!=ico&&right+1<=Chessboard.BOARD_SIZE)
		{
			right++;
			while(right<=Chessboard.BOARD_SIZE&&board[right][y]==ico)
			{
				flag2h++;
				right++;
			}		
		}
		sumh+=flag1h>flag2h?flag1h:flag2h;
		
		
		int up=y;
		while(up>=0&&board[x][up]==ico)
		{
			sums++;
			up--;
		}
		int upy=up;
		if(board[x][up]!=ico&&up-1>=0)
		{
			up--;
			while(up>=0&&board[x][up]==ico)
			{
				flag1s++;
				left--;
			}		
		}
		int down=y+1;
		while(down<=Chessboard.BOARD_SIZE&&board[x][down]==ico)
		{
			sums++;
			down++;
		}
		int downy=down;//记录下子位置
		if(board[x][down]!=ico&&down+1<=Chessboard.BOARD_SIZE)
		{
			down++;
			while(down<=Chessboard.BOARD_SIZE&&board[x][down]==ico)
			{
				flag2s++;
				down++;
			}		
		}
		sums+=flag1s>flag2s?flag1s:flag2s;
		
		

		int up1=y;
		int left1=x;
		while(up1>=0&&left1>=0&&board[left1][up1]==ico)
		{
			sumz++;
			up1--;
			left1--;
		}
		int left1x=left1;
		int up1y=up1;
		if(board[left1][up1]!=ico&&up1-1>=0&&left1-1>=0)
		{
			up1--;
			left1--;
			while(up1>=0&&left1>=0&&board[left1][up1]==ico)
			{
				flag1z++;
				left1--;
				up1--;
			}		
		}
		int right1=x+1;
		int down1=y+1;
		while(down1<=Chessboard.BOARD_SIZE&&right1<=Chessboard.BOARD_SIZE&&board[right1][down1]==ico)
		{
			sumz++;
			right1++;
			down1++;
		}
		int right1x=right1;
		int down1y=down1;
		if(board[right1][down1]!=ico&&down+1<=Chessboard.BOARD_SIZE&&right1<=Chessboard.BOARD_SIZE)
		{
			down1++;
			right1++;
			while(down1<=Chessboard.BOARD_SIZE&&right1<=Chessboard.BOARD_SIZE&&board[right1][down1]==ico)
			{
				flag2z++;
				right1++;
				down1++;
			}		
		}
		sumz+=flag1z>flag2z?flag1z:flag2z;
		
		
		

		int left2=x;
		int down2=y;
		while(left2>=0&&down2<=Chessboard.BOARD_SIZE&&board[left2][down2]==ico)
		{
			sumy++;
			left2--;
			down2++;
		}
		int left2x=left2;
		int down2y=down2;
		if(board[left2][down2]!=ico&&left2-1>=0&&down2+1<=Chessboard.BOARD_SIZE)
		{
			left2--;
			down2++;
			while(left2>=0&&down2<=Chessboard.BOARD_SIZE&&board[left2][down2]==ico)
			{
				flag1y++;
				left2--;
				down2++;
			}		
		}
		int right2=x+1;
		int up2=y-1;
		while(right2<=Chessboard.BOARD_SIZE&&up2>=0&&board[right2][up2]==ico)
		{
			sumy++;
			right2++;
			up2--;
		}
		int right2x=right2;
		int up2y=up2;
		if(board[right2][up2]!=ico&&right2+1<=Chessboard.BOARD_SIZE&&up2-1>=0)
		{
			right2++;
			up2--;
			while(right2<=Chessboard.BOARD_SIZE&&up2>=0&&board[right2][up2]==ico)
			{
				flag2y++;
				right2++;
				up2--;
			}		
		}
		sumy+=flag1y>flag2y?flag1y:flag2y;
		

		int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		if(sumh>=sums&&sumh>=sumz&&sumh>=sumy)
		{
			if(flag2h>=flag1h)
			{
				posX=rightx;
				posY=y;
				while (board[posX][posY] != "十") {
					posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
					posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
				}
			}
			else
			{
				posX=leftx;
				posY=y;
				while (board[posX][posY] != "十") {
					posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
					posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
				}
			}
		}
		if(sums>=sumh&&sums>=sumz&&sums>=sumy)
		{
			if(flag2s>=flag1s)
			{
				posX=x;
				posY=downy;
				while (board[posX][posY] != "十") {
					posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
					posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
				}
			}
			else
			{
				posX=x;
				posY=upy;
				while (board[posX][posY] != "十") {
					posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
					posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
				}
			}
		}
		if(sumz>=sumh&&sumz>=sums&&sumz>=sumy)
		{
			if(flag2z>=flag1z)
			{
				posX=right1x;
				posY=down1y;
				while (board[posX][posY] != "十") {
					posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
					posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
				}
			}
			else
			{
				posX=left1x;
				posY=up1y;
				while (board[posX][posY] != "十") {
					posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
					posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
				}
			}
		}
		if(sumy>=sumh&&sumy>=sums&&sumy>=sumz)
		{
			if(flag2y>=flag1y)
			{
				posX=right2x;
				posY=up2y;
				while (board[posX][posY] != "十") {
					posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
					posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
				}
			}
			else
			{
				posX=left2x;
				posY=down2y;
				while (board[posX][posY] != "十") {
					posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
					posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
				}
			}
		}
		int[] result = { posX, posY };
		return result;
		/*int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		String[][] board = chessboard.getBoard();
		while (board[posX][posY] != "十") {
			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		}
		int[] result = { posX, posY };
		return result;*/
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
		String[][] board = chessboard.getBoard();
		int i, j;
		int cnt = 0;

		for (i = 1; posX - i >= 0 && posX - i < chessboard.BOARD_SIZE; i++)// 竖
		{
			if (board[posX - i][posY] == ico)
				cnt++;
			else
				break;
			if (cnt >= 4)
				return true;
		}

		for (i = 1; posX + i >= 0 && posX + i < chessboard.BOARD_SIZE; i++)// 竖
		{
			if (board[posX + i][posY] == ico)
				cnt++;
			else
				break;
			if (cnt >= 4)
				return true;
		}
		cnt = 0;
		for (j = 1; posY - j >= 0 && posY - j < chessboard.BOARD_SIZE; j++)// 横
		{
			if (board[posX][posY - j] == ico)
				cnt++;
			else
				break;
			if (cnt >= 4)
				return true;
		}
		for (j = 1; posY + j >= 0 && posY + j < chessboard.BOARD_SIZE; j++)// 横
		{

			if (board[posX][posY + j] == ico)
				cnt++;
			else
				break;
			if (cnt >= 4)
				return true;
		}
		cnt = 0;// 左斜
		for (i = 1, j = 1; posX - i >= 0 && posX - i < chessboard.BOARD_SIZE
				&& posY - j >= 0 && posY - j < chessboard.BOARD_SIZE; i++, j++) {
			if (board[posX - i][posY - j] == ico) {
				cnt++;
			} else
				break;
			if (cnt >= 4)
				return true;
		}
		for (i = 1, j = 1; posX + i >= 0 && posX + i < chessboard.BOARD_SIZE
				&& posY + j >= 0 && posY + j < chessboard.BOARD_SIZE; i++, j++) {
			if (board[posX + i][posY + j] == ico) {
				cnt++;
			} else
				break;
			if (cnt >= 4)
				return true;
		}
		cnt = 0;// 右斜
		for (i = 1, j = 1; posX - i >= 0 && posX - i < chessboard.BOARD_SIZE
				&& posY + j >= 0 && posY + j < chessboard.BOARD_SIZE; i++, j++) {
			if (board[posX - i][posY + j] == ico) {
				cnt++;
			} else
				break;
			if (cnt >= 4)
				return true;
		}
		for (i = 1, j = 1; posX + i >= 0 && posX + i < chessboard.BOARD_SIZE
				&& posY - j >= 0 && posY - j < chessboard.BOARD_SIZE; i++, j++) {
			if (board[posX + i][posY - j] == ico) {
				cnt++;
			} else
				break;
			if (cnt >= 4)
				return true;
		}
		return false;

	}

	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}

