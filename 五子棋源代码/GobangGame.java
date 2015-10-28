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
	 * һ���򵥵�ai
	 * ������AI�ɲο���http://blog.csdn.net/pi9nc/article/details/10858411
	 * ��ai���������Ͽյ�Ա�����Է��ļ�ֵ--�������ڸõ�����֮�󣬱������������ӵ���Χ�����ɴ������������һ����ά����洢
	 * ͬʱ����������Է���������������Ӻ����˫4��5 ��Է�ĳ����ȵ�5˫4���з��
	 */
	/*
	 * ����ʵ��һ���󵥵��ֵ�ĺ�����icoΪ�õ���ɫ
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
	 * ������Ӻ��������̵ľ���
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
	 * ö��ÿ�������Ӻ���ƣ����������������㣬�������
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
				if(!((chessboard.getBoard()[i][j]).equals("ʮ")))
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
				chessboard.setBoard(i, j, "ʮ");				
			}		
		return ans;
	}
	
	
	
	

	/**
	 * ������������
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
		while (board[ans[0]][ans[1]] != "ʮ") {
			ans[0] = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			ans[1] = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		}
		return ans;
		
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
