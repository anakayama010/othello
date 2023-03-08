package JavaConsole;
/**
 * 盤面クラス
 * 4*4サイズの盤面を生成する。
 * コンストラクタで初期の配置を生成する。
 */
public class Board {
	int[][] square = new int[4][4];

	//コンストラクタ（初期化）
	Board(){
		//●を配置
		square[1][1] = 0;
		square[2][2] = 0;
		//○を配置
		square[1][2] = 1;
		square[2][1] = 1;
	}

}
