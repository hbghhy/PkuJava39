import java.io.BufferedReader;
import java.io.InputStreamReader;

 
public class GobangGame {
	// ����ﵽӮ������������Ŀ
	private final int WIN_COUNT = 5;
	// �����û������X����
	private int posX = 0;
	// �����û������X����
	private int posY = 0;
	// ��������
	private Chessboard chessboard;

	/**
	 * �չ�����
	 */
	public GobangGame() {
	}

	/**
	 * ����������ʼ�����̺���������
	 * 
	 * @param chessboard
	 *            ������
	 */
	public GobangGame(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	/**
	 * ��������Ƿ�Ϸ���
	 * 
	 * @param inputStr
	 *            �ɿ���̨������ַ�����
	 * @return �ַ����Ϸ�����true,���򷵻�false��
	 */
	public boolean isValid(String inputStr) {
		// ���û�������ַ����Զ���(,)��Ϊ�ָ����ָ��������ַ���
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("����(����,����)�ĸ�ʽ���룺");
			return false;
		}
		// ���������ֵ�Ƿ��ڷ�Χ֮��
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X��Y����ֻ�ܴ��ڵ���1,��С�ڵ���" + Chessboard.BOARD_SIZE
					+ ",���������룺");
			return false;
		}
		// ��������λ���Ƿ��Ѿ�������
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "ʮ") {
			chessboard.printBoard();
			System.out.println("��λ���Ѿ������ӣ����������룺");
			return false;
		}
		return true;
	}

	/**
	 * ��ʼ����
	 */
	public void start() throws Exception {
		// trueΪ��Ϸ����
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// ��ȡ���̵�����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:ÿ����������һ�����ݰ��س���������������ݱ�br��ȡ��
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// ������Ϸ���Ҫ���������룬�ټ���
				continue;
			}
			// �Ѷ�Ӧ������Ԫ�ظ�Ϊ"��"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// �ж��û��Ƿ�Ӯ��
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// ��������ѡ��λ������
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				// �жϼ�����Ƿ�Ӯ��
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// �������ʤ�ߣ�ѯ���û��Ƿ������Ϸ
			if (isOver) {
				// ������������³�ʼ�����̣�������Ϸ
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				// ������������˳�����
				break;
			}
			chessboard.printBoard();
			System.out.println("����������������꣬Ӧ��x,y�ĸ�ʽ���룺");
		}
	}

	/**
	 * �Ƿ����¿�ʼ���塣
	 * 
	 * @param chessman
	 *            "��"Ϊ�û���"��"Ϊ�������
	 * @return ��ʼ����true�����򷵻�false��
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "��ϲ������Ӯ�ˣ�"
				: "���ź��������ˣ�";
		System.out.println(message + "����һ�֣�(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// ��ʼ��һ��
			return true;
		}
		return false;

	}

	/**
	 * ������������
	 */
	public int[] computerDo_old() {
		
		int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		String[][] board = chessboard.getBoard();
		while (board[posX][posY] != "ʮ") {
			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		}
		int[] result = { posX, posY };
		return result;
	}

	/**
	 * ������������_���ܰ�
	 * �Ľ�˼·����������һ������Ҵ��ڸ��˵���������ͬ��ɫ�����ӵķ��򣬹�8������
 * 				Ȼ������������õ��Ե����ӽ��ж½أ���ֹ�γ�5���ӡ�
	 */
	public int[] computerDo() {
		int[] lastXY=chessboard.getLastXY();
		int lastX = lastXY[0];
		int lastY = lastXY[1];
		int direction=1; //8����������жԷ���ͬ��ɫ�����ӣ�ȷ�����ĸ�����
		int posX,posY;
		
		String[][] board = chessboard.getBoard();
		if((lastX+1)<Chessboard.BOARD_SIZE && board[lastX+1][lastY]==Chessman.BLACK.getChessman()){
			direction=1;
		}else if((lastX+1)<Chessboard.BOARD_SIZE &&(lastY-1)>=0 && board[lastX+1][lastY-1]==Chessman.BLACK.getChessman()){
			direction=2;
		}else if((lastY-1)>=0 && board[lastX][lastY-1]==Chessman.BLACK.getChessman()){
			direction=3;
		}else if((lastX-1)>=0 && (lastY-1)>=0 && board[lastX-1][lastY-1]==Chessman.BLACK.getChessman()){
			direction=4;
		}else if((lastX-1)>=0 && board[lastX-1][lastY]==Chessman.BLACK.getChessman()){
			direction=5;
		}else if((lastX-1)>=0 && (lastY+1)<Chessboard.BOARD_SIZE && board[lastX-1][lastY+1]==Chessman.BLACK.getChessman()){
			direction=6;
		}else if((lastY+1)<Chessboard.BOARD_SIZE && board[lastX][lastY+1]==Chessman.BLACK.getChessman()){
			direction=7;
		}else if((lastX+1)<Chessboard.BOARD_SIZE && (lastY+1)<Chessboard.BOARD_SIZE && board[lastX+1][lastY+1]==Chessman.BLACK.getChessman()){
			direction=8;
		}
		
		
		if(direction==1){
			for(int i=2;lastX+i<Chessboard.BOARD_SIZE;i++){
				if(board[lastX+i][lastY]=="ʮ"){
					posX = lastX+i;
					posY = lastY;
					int[] result = { posX, posY };
					return result;
				}
			}
			for(int i=1;lastX-i>=0;i++){
				if(board[lastX-i][lastY]=="ʮ"){
					posX = lastX-i;
					posY = lastY;
					int[] result = { posX, posY };
					return result;
				}
			}
		}else if(direction==5){
			for(int i=2;lastX-i>=0;i++){
				if(board[lastX-i][lastY]=="ʮ"){
					posX = lastX-i;
					posY = lastY;
					int[] result = { posX, posY };
					return result;
				}
			}
			for(int i=1;(lastX+i)<Chessboard.BOARD_SIZE;i++){
				if(board[lastX+i][lastY]=="ʮ"){
					posX = lastX+i;
					posY = lastY;
					int[] result = { posX, posY };
					return result;
				}
			}
		}else if(direction==2){
			for(int i=2;(lastX+i)<Chessboard.BOARD_SIZE && (lastY-i)>=0;i++){
				if(board[lastX+i][lastY-i]=="ʮ"){
					posX = lastX+i;
					posY = lastY-i;
					int[] result = { posX, posY };
					return result;
				}
			}
			for(int i=1;(lastX-i)>=0&&(lastY+i)<Chessboard.BOARD_SIZE;i++){
				if(board[lastX-i][lastY+i]=="ʮ"){
					posX = lastX-i;
					posY = lastY+i;
					int[] result = { posX, posY };
					return result;
				}
			}
		}else if(direction==6){
			for(int i=2;(lastX-i)>=0 && (lastY+i)<Chessboard.BOARD_SIZE;i++){
				if(board[lastX-i][lastY+i]=="ʮ"){
					posX = lastX-i;
					posY = lastY+i;
					int[] result = { posX, posY };
					return result;
				}
			}
			for(int i=1;(lastX+i)<Chessboard.BOARD_SIZE&&(lastY-i)>=0;i++){
				if(board[lastX+i][lastY-i]=="ʮ"){
					posX = lastX+i;
					posY = lastY-i;
					int[] result = { posX, posY };
					return result;
				}
			}
		}else if(direction==3){
			for(int i=2;lastY-i>=0;i++){
				if(board[lastX][lastY-i]=="ʮ"){
					posX = lastX;
					posY = lastY-i;
					int[] result = { posX, posY };
					return result;
				}
			}
			for(int i=1;(lastY+i)<Chessboard.BOARD_SIZE;i++){
				if(board[lastX][lastY+i]=="ʮ"){
					posX = lastX;
					posY = lastY+i;
					int[] result = { posX, posY };
					return result;
				}
			}
		}else if(direction==7){
			for(int i=2;(lastY+i)<Chessboard.BOARD_SIZE;i++){
				if(board[lastX][lastY+i]=="ʮ"){
					posX = lastX;
					posY = lastY+i;
					int[] result = { posX, posY };
					return result;
				}
			}
			for(int i=1;(lastY-i)>=0;i++){
				if(board[lastX][lastY-i]=="ʮ"){
					posX = lastX;
					posY = lastY-i;
					int[] result = { posX, posY };
					return result;
				}
			}
		}else if(direction==4){
			for(int i=2;(lastX-i)>=0 && (lastY-i)>=0;i++){
				if(board[lastX-i][lastY-i]=="ʮ"){
					posX = lastX-i;
					posY = lastY-i;
					int[] result = { posX, posY };
					return result;
				}
			}
			for(int i=1;(lastX+i)<Chessboard.BOARD_SIZE&&(lastY+i)<Chessboard.BOARD_SIZE;i++){
				if(board[lastX+i][lastY+i]=="ʮ"){
					posX = lastX+i;
					posY = lastY+i;
					int[] result = { posX, posY };
					return result;
				}
			}
		}else if(direction==8){
			for(int i=2;(lastX+i)<Chessboard.BOARD_SIZE&&(lastY+i)<Chessboard.BOARD_SIZE;i++){
				if(board[lastX+i][lastY+i]=="ʮ"){
					posX = lastX+i;
					posY = lastY+i;
					int[] result = { posX, posY };
					return result;
				}
			}
			for(int i=1;(lastX-i)>=0&&(lastY-i)>=0;i++){
				if(board[lastX-i][lastY-i]=="ʮ"){
					posX = lastX-i;
					posY = lastY-i;
					int[] result = { posX, posY };
					return result;
				}
			}
		}
		
		//�����û���壬�����һ������
		posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		while (board[posX][posY] != "ʮ") {
			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		}
		int[] result = { posX, posY };
		return result;
	}
	
	/**
	 * �ж���Ӯ
	 * 
	 * @param posX
	 *            ���ӵ�X���ꡣ
	 * @param posY
	 *            ���ӵ�Y����
	 * @param ico
	 *            ��������
	 * @return ��������������������һ��ֱ�ӣ������棬�����෴��
	 */
	public boolean isWon(int posX, int posY, String ico) {
		int count,i,j;
		String[][] board = chessboard.getBoard();
		
		//����
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
		
		//����
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
		
		//б�� "\"
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
		
		//б�� "/"
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
