package JavaConsole;
/**
 * ゲーム進行クラス
 */
public class Main {
	public static void main(String[] args){

		/**
		 * マスの状態を表す配列
		 * 何もなし = null
		 * 黒 ● = 0,
		 * 白 ○ = 1,
		 * 置くことができる ◌ = 2
		 */
		final String[] STATUS = {"●", "○", "◌"}; //[●=0, ○=1, ◌=2]


		//ゲームを初期化して生成
		Board banmen = new Board();

		
	}
}
