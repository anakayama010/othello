
public class GameBoard {
	int[][] board = new int[4][4];

	//コンストラクタ
	GameBoard(){
		//●を配置
		board[1][1] = 0;
		board[2][2] = 0;
		//○を配置
		board[1][2] = 1;
		board[2][1] = 1;
	}

}
