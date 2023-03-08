 /*
 * マスの状態メモ
 * 何もなし = null
 * 黒 ● = 0,
 * 白 ○ = 1,
 * 置くことができる ◌ = 2
 */



import JavaConsole.Board;

/**
 * ゲーム進行クラス
 */
public class Main {
	public static void main(String[] args){

		//ゲームを初期化して生成
		Board board = new Board();

		System.out.println(board.getCountBlack());
	}
}
