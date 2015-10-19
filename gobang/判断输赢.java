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