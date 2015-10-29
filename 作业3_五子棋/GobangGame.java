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
	 * 计算机随机下棋
	 */
	public int[] computerDo_old() {
		
		int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		String[][] board = chessboard.getBoard();
		while (board[posX][posY] != "十") {
			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		}
		int[] result = { posX, posY };
		return result;
	}

	/**
	 * 计算机随机下棋_智能版
	 * 改进思路：在人下完一步棋后，找存在跟人的棋子有相同颜色的棋子的方向，共8个方向，
 * 				然后在这个方向用电脑的棋子进行堵截，防止形成5连子。
	 */
	public int[] computerDo() {
		int[] lastXY=chessboard.getLastXY();
		int lastX = lastXY[0];
		int lastY = lastXY[1];
		int direction=1; //8个方向可能有对方相同颜色的棋子，确定是哪个方向
		int tmpWeight=0;
		int[] weight={0,0,0,0,0,0,0,0};	//8个方向的权重，这个方向棋子越多权重越大
		int posX,posY;
		
		String[][] board = chessboard.getBoard();
		if((lastX+1)<Chessboard.BOARD_SIZE && board[lastX+1][lastY]==Chessman.BLACK.getChessman()){
			direction=1;
			tmpWeight=1;
			for(int i=lastX+1;(i<Chessboard.BOARD_SIZE)&& board[i][lastY]==Chessman.BLACK.getChessman();i++){
				tmpWeight++;
			}
			weight[direction-1]=tmpWeight;
		}else if((lastX+1)<Chessboard.BOARD_SIZE &&(lastY-1)>=0 && board[lastX+1][lastY-1]==Chessman.BLACK.getChessman()){
			direction=2;
			tmpWeight=1;
			for(int i=lastX+1,j=lastY-1;(i<Chessboard.BOARD_SIZE)&&(j>=0)&&board[i][j]==Chessman.BLACK.getChessman();i++,j--){
				tmpWeight++;
			}
			weight[direction-1]=tmpWeight;
		}else if((lastY-1)>=0 && board[lastX][lastY-1]==Chessman.BLACK.getChessman()){
			direction=3;
			tmpWeight=1;
			for(int j=lastY-1;(j>=0)&& board[lastX][j]==Chessman.BLACK.getChessman();j--){
				tmpWeight++;
			}
			weight[direction-1]=tmpWeight;
		}else if((lastX-1)>=0 && (lastY-1)>=0 && board[lastX-1][lastY-1]==Chessman.BLACK.getChessman()){
			direction=4;
			tmpWeight=1;
			for(int i=lastX-1,j=lastY-1;(i>=0)&&(j>=0)&&board[i][j]==Chessman.BLACK.getChessman();i--,j--){
				tmpWeight++;
			}
			weight[direction-1]=tmpWeight;
		}else if((lastX-1)>=0 && board[lastX-1][lastY]==Chessman.BLACK.getChessman()){
			direction=5;
			tmpWeight=1;
			for(int i=lastX-1;(i>=0)&&board[i][lastY]==Chessman.BLACK.getChessman();i--){
				tmpWeight++;
			}
			weight[direction-1]=tmpWeight;
		}else if((lastX-1)>=0 && (lastY+1)<Chessboard.BOARD_SIZE && board[lastX-1][lastY+1]==Chessman.BLACK.getChessman()){
			direction=6;
			tmpWeight=1;
			for(int i=lastX-1,j=lastY+1;(i>=0)&&(j<Chessboard.BOARD_SIZE)&&board[i][j]==Chessman.BLACK.getChessman();i--,j++){
				tmpWeight++;
			}
			weight[direction-1]=tmpWeight;
		}else if((lastY+1)<Chessboard.BOARD_SIZE && board[lastX][lastY+1]==Chessman.BLACK.getChessman()){
			direction=7;
			tmpWeight=1;
			for(int j=lastY+1;(j<Chessboard.BOARD_SIZE)&&board[lastX][j]==Chessman.BLACK.getChessman();j++){
				tmpWeight++;
			}
			weight[direction-1]=tmpWeight;
		}else if((lastX+1)<Chessboard.BOARD_SIZE && (lastY+1)<Chessboard.BOARD_SIZE && board[lastX+1][lastY+1]==Chessman.BLACK.getChessman()){
			direction=8;
			tmpWeight=1;
			for(int i=lastX+1,j=lastY+1;(i<Chessboard.BOARD_SIZE)&&(j<Chessboard.BOARD_SIZE)&&board[i][j]==Chessman.BLACK.getChessman();i++,j++){
				tmpWeight++;
			}
			weight[direction-1]=tmpWeight;
		}
		
		//选择权重最大的方向进行堵截
		int maxWeight=0;
		for(int k=0;k<weight.length;k++){
			if(weight[k]>maxWeight){
				maxWeight=weight[k];
				direction=k+1;
			}
		}
		
		if(direction==1){
			for(int i=2;lastX+i<Chessboard.BOARD_SIZE;i++){
				if(board[lastX+i][lastY]=="十"){
					posX = lastX+i;
					posY = lastY;
					int[] result = { posX, posY };
					return result;
				}
			}
			for(int i=1;lastX-i>=0;i++){
				if(board[lastX-i][lastY]=="十"){
					posX = lastX-i;
					posY = lastY;
					int[] result = { posX, posY };
					return result;
				}
			}
		}else if(direction==5){
			for(int i=2;lastX-i>=0;i++){
				if(board[lastX-i][lastY]=="十"){
					posX = lastX-i;
					posY = lastY;
					int[] result = { posX, posY };
					return result;
				}
			}
			for(int i=1;(lastX+i)<Chessboard.BOARD_SIZE;i++){
				if(board[lastX+i][lastY]=="十"){
					posX = lastX+i;
					posY = lastY;
					int[] result = { posX, posY };
					return result;
				}
			}
		}else if(direction==2){
			for(int i=2;(lastX+i)<Chessboard.BOARD_SIZE && (lastY-i)>=0;i++){
				if(board[lastX+i][lastY-i]=="十"){
					posX = lastX+i;
					posY = lastY-i;
					int[] result = { posX, posY };
					return result;
				}
			}
			for(int i=1;(lastX-i)>=0&&(lastY+i)<Chessboard.BOARD_SIZE;i++){
				if(board[lastX-i][lastY+i]=="十"){
					posX = lastX-i;
					posY = lastY+i;
					int[] result = { posX, posY };
					return result;
				}
			}
		}else if(direction==6){
			for(int i=2;(lastX-i)>=0 && (lastY+i)<Chessboard.BOARD_SIZE;i++){
				if(board[lastX-i][lastY+i]=="十"){
					posX = lastX-i;
					posY = lastY+i;
					int[] result = { posX, posY };
					return result;
				}
			}
			for(int i=1;(lastX+i)<Chessboard.BOARD_SIZE&&(lastY-i)>=0;i++){
				if(board[lastX+i][lastY-i]=="十"){
					posX = lastX+i;
					posY = lastY-i;
					int[] result = { posX, posY };
					return result;
				}
			}
		}else if(direction==3){
			for(int i=2;lastY-i>=0;i++){
				if(board[lastX][lastY-i]=="十"){
					posX = lastX;
					posY = lastY-i;
					int[] result = { posX, posY };
					return result;
				}
			}
			for(int i=1;(lastY+i)<Chessboard.BOARD_SIZE;i++){
				if(board[lastX][lastY+i]=="十"){
					posX = lastX;
					posY = lastY+i;
					int[] result = { posX, posY };
					return result;
				}
			}
		}else if(direction==7){
			for(int i=2;(lastY+i)<Chessboard.BOARD_SIZE;i++){
				if(board[lastX][lastY+i]=="十"){
					posX = lastX;
					posY = lastY+i;
					int[] result = { posX, posY };
					return result;
				}
			}
			for(int i=1;(lastY-i)>=0;i++){
				if(board[lastX][lastY-i]=="十"){
					posX = lastX;
					posY = lastY-i;
					int[] result = { posX, posY };
					return result;
				}
			}
		}else if(direction==4){
			for(int i=2;(lastX-i)>=0 && (lastY-i)>=0;i++){
				if(board[lastX-i][lastY-i]=="十"){
					posX = lastX-i;
					posY = lastY-i;
					int[] result = { posX, posY };
					return result;
				}
			}
			for(int i=1;(lastX+i)<Chessboard.BOARD_SIZE&&(lastY+i)<Chessboard.BOARD_SIZE;i++){
				if(board[lastX+i][lastY+i]=="十"){
					posX = lastX+i;
					posY = lastY+i;
					int[] result = { posX, posY };
					return result;
				}
			}
		}else if(direction==8){
			for(int i=2;(lastX+i)<Chessboard.BOARD_SIZE&&(lastY+i)<Chessboard.BOARD_SIZE;i++){
				if(board[lastX+i][lastY+i]=="十"){
					posX = lastX+i;
					posY = lastY+i;
					int[] result = { posX, posY };
					return result;
				}
			}
			for(int i=1;(lastX-i)>=0&&(lastY-i)>=0;i++){
				if(board[lastX-i][lastY-i]=="十"){
					posX = lastX-i;
					posY = lastY-i;
					int[] result = { posX, posY };
					return result;
				}
			}
		}
		
		//如果还没走棋，随机下一个棋子
		posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		while (board[posX][posY] != "十") {
			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		}
		int[] result = { posX, posY };
		return result;
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
		int count,i,j;
		String[][] board = chessboard.getBoard();
		
		//横向
		count=1;
		for(i=posX-1,j=posY;i>=0;i--){
			if(board[i][j] == ico){
				count++;
			}
			if(count==5){
				return true;
			}
		}
		for(i=posX+1,j=posY;i<Chessboard.BOARD_SIZE;i++){
			if(board[i][j] == ico){
				count++;
			}
			if(count==5){
				return true;
			}
		}
		
		//竖向
		count=1;
		for(i=posX,j=posY-1;j>=0;j--){
			if(board[i][j] == ico){
				count++;
			}
			if(count==5){
				return true;
			}
		}
		for(i=posX,j=posY+1;j<Chessboard.BOARD_SIZE;j++){
			if(board[i][j] == ico){
				count++;
			}
			if(count==5){
				return true;
			}
		}
		
		//斜向 "\"
		count=1;
		for(i=posX-1,j=posY-1;i>=0&&j>=0;i--,j--){
			if(board[i][j] == ico){
				count++;
			}
			if(count==5){
				return true;
			}
		}
		for(i=posX+1,j=posY+1;i<Chessboard.BOARD_SIZE&&j<Chessboard.BOARD_SIZE;i++,j++){
			if(board[i][j] == ico){
				count++;
			}
			if(count==5){
				return true;
			}
		}
		
		//斜向 "/"
		count=1;
		for(i=posX+1,j=posY-1;i<Chessboard.BOARD_SIZE&&j>=0;i++,j--){
			if(board[i][j] == ico){
				count++;
			}
			if(count==5){
				return true;
			}
		}
		for(i=posX-1,j=posY+1;i>=0&&j<Chessboard.BOARD_SIZE;i--,j++){
			if(board[i][j] == ico){
				count++;
			}
			if(count==5){
				return true;
			}
		}
		
		return false;
	}

	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}
