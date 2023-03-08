package javaconsole;
 /*
 * マスの状態メモ
 * 何もなし = 0
 * 黒 ● = 1,
 * 白 ○ = 2,
 * 置くことができる ◌ = 3
 */

/**
 * ゲーム進行クラス
 */
public class Main {
	public static void main(String[] args){

		/** ゲームを初期化して生成 */
		Board board = new Board();

		/** 動作確認の実行 */
		methodCheck(board);

	}
	/** 各種メソッドの動作確認を行うメソッド */
	public static void methodCheck(Board board){
		//board.getBoard()
		int[][] square = board.getBoard();
		for(int[] a : square){
			System.out.print("[ ");
			for(int b : a){
				System.out.print(b + " ");
			}
			System.out.println("]");
		}
		//board.getCountBlack()
		System.out.println("Black : " + board.getCountBlack());
		//board.getCountWhite()
		System.out.println("White : " + board.getCountWhite());
		//board.getTurn()
		System.out.println("Turn : " + board.getTurn());
		//board.changeTurn()
		for(int i = 1; i <= 5; i++){
			board.changeTurn();
			System.out.println("AfterChange " + i + " Turn : " + board.getTurn());
		}
		//isThereNull()
		System.out.println("isNull : " + board.isThereNull());
	} 
}
