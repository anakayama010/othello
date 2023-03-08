package javaconsole;

/**
 * 盤面クラス
 * 4*4サイズの盤面を生成する。
 * コンストラクタで初期の配置を生成する。
 */
public class Board {
	/** 手番 */
	int turn = 0;

	/** 盤面の状態(空白=null, 黒=0, 白=1) */
	int[][] square = new int[4][4];

	//コンストラクタ（初期化）
	public Board(){
		//●を配置
		square[1][1] = 0;
		square[2][2] = 0;
		//○を配置
		square[1][2] = 1;
		square[2][1] = 1;
	}

	//先手を変更できるコンストラクタ
	public Board(int turn){
		this.turn = turn;
	}

	/** 盤面の状態を取得するメソッド */
	public int[][] getBoard(){
		return square;
	}

	/** 黒の数を取得するメソッド */
	public int getCountBlack(){
		int count = 0;
		for(int[] a : square){
			for(int b : a){
				if(b == 0){
					count++;
				}
			}
		}
		return count;
	}
}
