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