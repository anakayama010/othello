package javaconsole;

/**
 * 盤面クラス
 * 4*4サイズの盤面を生成する。
 * コンストラクタで初期の配置を生成する。
 */
public class Board {
	/** 手番 */
	private int turn = 1;

	/** 盤面の状態(空白=0, 黒=1, 白=2) */
	private int[][] square = new int[4][4];

	//コンストラクタ（初期化）
	public Board(){
		//●を配置
		square[1][1] = 1;
		square[2][2] = 1;
		//○を配置
		square[1][2] = 2;
		square[2][1] = 2;
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
				if(b == 1){
					count++;
				}
			}
		}
		return count;
	}

	/** 白の数を取得するメソッド */	
	public int getCountWhite(){
		int count = 0;
		for(int[] a : square){
			for(int b : a){
				if(b == 2){
					count++;
				}
			}
		}
		return count;
	}

	/** 手番を変えるメソッド */
	public void changeTurn(){
		if(turn == 1)
		turn = 2;
		else
		turn = 1;
	}

	/** 手番を取得するメソッド */
	public int getTurn(){
		return turn;
	}

	/** 盤面にまだ置かれていないマスがあるか確認するメソッド */
	public boolean isThereNull(){
		boolean isNull = false;
		for(int[] a : square){
			System.out.print("[ ");//確認用
			for(int b : a){
				System.out.print(b + " ");//確認用
				if(b == 0){
					isNull = true;
					break;
				}
			}
			if(isNull)
			break;
			System.out.println("]");//確認用
		}
		System.out.println("");//確認用
		return isNull;
	}
}
