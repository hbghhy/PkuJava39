
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
				int[] computerPosArr = computerDo(posX, posY, chessman);///
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
		int downy=down;//��¼����λ��
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
				while (board[posX][posY] != "ʮ") {
					posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
					posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
				}
			}
			else
			{
				posX=leftx;
				posY=y;
				while (board[posX][posY] != "ʮ") {
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
				while (board[posX][posY] != "ʮ") {
					posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
					posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
				}
			}
			else
			{
				posX=x;
				posY=upy;
				while (board[posX][posY] != "ʮ") {
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
				while (board[posX][posY] != "ʮ") {
					posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
					posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
				}
			}
			else
			{
				posX=left1x;
				posY=up1y;
				while (board[posX][posY] != "ʮ") {
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
				while (board[posX][posY] != "ʮ") {
					posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
					posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
				}
			}
			else
			{
				posX=left2x;
				posY=down2y;
				while (board[posX][posY] != "ʮ") {
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
		while (board[posX][posY] != "ʮ") {
			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		}
		int[] result = { posX, posY };
		return result;*/
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
		String[][] board = chessboard.getBoard();
		int i, j;
		int cnt = 0;

		for (i = 1; posX - i >= 0 && posX - i < chessboard.BOARD_SIZE; i++)// ��
		{
			if (board[posX - i][posY] == ico)
				cnt++;
			else
				break;
			if (cnt >= 4)
				return true;
		}

		for (i = 1; posX + i >= 0 && posX + i < chessboard.BOARD_SIZE; i++)// ��
		{
			if (board[posX + i][posY] == ico)
				cnt++;
			else
				break;
			if (cnt >= 4)
				return true;
		}
		cnt = 0;
		for (j = 1; posY - j >= 0 && posY - j < chessboard.BOARD_SIZE; j++)// ��
		{
			if (board[posX][posY - j] == ico)
				cnt++;
			else
				break;
			if (cnt >= 4)
				return true;
		}
		for (j = 1; posY + j >= 0 && posY + j < chessboard.BOARD_SIZE; j++)// ��
		{

			if (board[posX][posY + j] == ico)
				cnt++;
			else
				break;
			if (cnt >= 4)
				return true;
		}
		cnt = 0;// ��б
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
		cnt = 0;// ��б
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

