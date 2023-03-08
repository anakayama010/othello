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

		//ゲームを初期化して生成
		Board board = new Board();

		System.out.println(board.getCountBlack());
	}
}
