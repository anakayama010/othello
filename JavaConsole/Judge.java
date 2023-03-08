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
		try{
			if(square[i][++j] == turn){//右隣が同じ色 → false
				System.out.print("[" + i + "][" + ++j + "]" + " == " + turn)
				return false;}
			while(square[i][j+1] != 0){//右隣が空白でないとき
				if(square[i][j+1] == turn){//対となる同色があった
					System.out.print("[" + i + "][" + j+1 + "]" + " == " + turn)
					return true;
				}else if(square[i][j+1] == 0){//対がないまま空白に辿り着いた
					System.out.print("[" + i + "][" + j+1 + "]" + " == " + 0)
					return false;
				}else{//相手の色が続いている
					System.out.print("[" + i + "][" + j+1 + "]" + " != " + turn + "(else)")
					j++;
					continue;
				}
			}
		}catch(IndexOutOfBoundsException){
			System.out.print("catch ")
			return false;
		}

		if(square[i][j+1] == turn)//右隣が同じ色 → false
		return false;

		while(square[i][j+1] != 0){//右隣が空白でないとき
			if(square[i][j+1] == turn){//対となる同色があった
				return true;
			}else if(square[i][j+1] == 0){//対がないまま空白に辿り着いた
				return false;
			}else{//相手の色が続いている
				continue;
			}
		}

		switch(square[i][j+1]){
			case 0:
				break;
			case 1:
				if(turn == 1){
					break;
				}else{
					return true;
				}
			case 2:
				if(turn == 1){
					return true;
				}else{
					break;
				}

		}
	}
	
}
