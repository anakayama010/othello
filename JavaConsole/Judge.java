package javaconsole;
import java.util.ArrayList;
/**
 * 判定するクラス
 */
public class Judge {
	/** 手番 */
	private static int turn = 1;
	/** 盤面の状態(空白=0, 黒=1, 白=2) */
	private static int[][] square = new int[4][4];

	/** 置ける場所の一覧を返すメソッド */
	public static ArrayList<Integer> placeable(Board board){
		turn = board.getTurn();
		square = board.getBoard();
		ArrayList<Integer> place = new ArrayList<>();

		for(int i = 0; i < square.length; i++ ){
			for(int j = 0; j < square[i].length; j++){
				placeableW(i, j);
			}
		}
		return place;
	}

	/** 横に置けるかどうか判定 */
	private static boolean placeableW(int i, int j){
		System.out.println(square[i][j] + "(" + i + "," + j + ")について横判定");
		
		boolean p = false;
		return p;
	}
	
}
