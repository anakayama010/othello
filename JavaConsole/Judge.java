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
	public static ArrayList<ArrayList> placeable(Board board){
		turn = board.getTurn();
		square = board.getBoard();
		ArrayList<ArrayList> places = new ArrayList<>();

		System.out.println("手番 : " + turn); //確認用
		int ct = 1; //確認用
		for(int i = 0; i < square.length; i++ ){
			for(int j = 0; j < square[i].length; j++){
				System.out.println("");
				System.out.print(ct++ + " ");
				System.out.println("[" + i + "," + j + "] " + square[i][j] + "について判定");
				if(square[i][j] == 0){//空白のマスであるとき
					if(placeableT(i, j) || placeableRT(i, j) || placeableR(i, j) || placeableRB(i, j) || placeableB(i, j) || placeableLB(i, j) || placeableL(i, j) || placeableLT(i, j)){
						ArrayList<Integer> place = new ArrayList<>();
						place.add(i);
						place.add(j);
						places.add(place);
					}
				}else{
					System.out.println(" (" + i + ")(" + j + ") == " + square[i][j] + " → false (置けないマス)");
				}
			}
		}
		return places;
	}

	/** 右に置けるかどうか判定 */
	private static boolean placeableR(int i, int j){

		System.out.println("[" + i + "," + j + "] " + square[i][j] + "について右を判定");
		if(square[i][j] == 0){
			try{
				if(square[i][++j] == turn){//右隣が同じ色 → false
					System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
					return false;
				}else{
					while(square[i][j] != 0){//右隣が空白でないとき
						System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
						if(square[i][++j] == turn){//対となる同色があった
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
							return true;
						}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
							return false;
						}else{//相手の色が続いている
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
							continue;
						}
					}
					System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
					return false;
				}
			}catch(IndexOutOfBoundsException e){
				System.out.println(" (" + i + ")(" + j + ") == catch → false (枠外判定)");
				return false;
			}
		}else{
			System.out.println(" (" + i + ")(" + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
			return false;
		}
	}
	/** 左に置けるかどうか判定 */
	private static boolean placeableL(int i, int j){

		System.out.println("[" + i + "," + j + "] " + square[i][j] + "について左を判定");
		if(square[i][j] == 0){
			try{
				if(square[i][--j] == turn){//左隣が同じ色 → false
					System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
					return false;
				}else{
					while(square[i][j] != 0){//左隣が空白でないとき
						System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
						if(square[i][--j] == turn){//対となる同色があった
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
							return true;
						}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
							return false;
						}else{//相手の色が続いている
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
							continue;
						}
					}
					System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (左隣が空白)");
					return false;
				}
			}catch(IndexOutOfBoundsException e){
				System.out.println(" (" + i + ")(" + j + ") == catch → false (枠外判定)");
				return false;
			}
		}else{
			System.out.println(" (" + i + ")(" + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
			return false;
		}
	}
	/** 下に置けるかどうか判定 */
	private static boolean placeableB(int i, int j){
		System.out.println("[" + i + "," + j + "] " + square[i][j] + "について下を判定");
		if(square[i][j] == 0){
			try{
				if(square[++i][j] == turn){//下が同じ色 → false
					System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
					return false;
				}else{
					while(square[i][j] != 0){//下が空白でないとき
						System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
						if(square[++i][j] == turn){//対となる同色があった
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
							return true;
						}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
							return false;
						}else{//相手の色が続いている
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
							continue;
						}
					}
					System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
					return false;
				}
			}catch(IndexOutOfBoundsException e){
				System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
				return false;
			}
		}else{
			System.out.println("(" + i + "," + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
			return false;
		}
	}
	/** 上に置けるかどうか判定 */
	private static boolean placeableT(int i, int j){
		System.out.println("[" + i + "," + j + "] " + square[i][j] + "について上を判定");
		if(square[i][j] == 0){
			try{
				if(square[--i][j] == turn){//上が同じ色 → false
					System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
					return false;
				}else{
					while(square[i][j] != 0){//上が空白でないとき
						System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
						if(square[--i][j] == turn){//対となる同色があった
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
							return true;
						}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
							return false;
						}else{//相手の色が続いている
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
							continue;
						}
					}
					System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
					return false;
				}
			}catch(IndexOutOfBoundsException e){
				System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
				return false;
			}
		}else{
			System.out.println("(" + i + "," + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
			return false;
		}
	}
	/** 右下に置けるかどうか判定 */
	private static boolean placeableRB(int i, int j){
		System.out.println("[" + i + "," + j + "] " + square[i][j] + "について右下を判定");
		if(square[i][j] == 0){
			try{
				if(square[++i][++j] == turn){//右下が同じ色 → false
					System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
					return false;
				}else{
					while(square[i][j] != 0){//右下が空白でないとき
						System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
						if(square[++i][++j] == turn){//対となる同色があった
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
							return true;
						}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
							return false;
						}else{//相手の色が続いている
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
							continue;
						}
					}
					System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
					return false;
				}
			}catch(IndexOutOfBoundsException e){
				System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
				return false;
			}
		}else{
			System.out.println("(" + i + "," + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
			return false;
		}
	}
	/** 左下に置けるかどうか判定 */
	private static boolean placeableLB(int i, int j){
		System.out.println("[" + i + "," + j + "] " + square[i][j] + "について左下を判定");
		if(square[i][j] == 0){
			try{
				if(square[++i][--j] == turn){//左下が同じ色 → false
					System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
					return false;
				}else{
					while(square[i][j] != 0){//左下が空白でないとき
						System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
						if(square[++i][--j] == turn){//対となる同色があった
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
							return true;
						}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
							return false;
						}else{//相手の色が続いている
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
							continue;
						}
					}
					System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
					return false;
				}
			}catch(IndexOutOfBoundsException e){
				System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
				return false;
			}
		}else{
			System.out.println("(" + i + "," + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
			return false;
		}
	}
	/** 右上に置けるかどうか判定 */
	private static boolean placeableRT(int i, int j){
		System.out.println("[" + i + "," + j + "] " + square[i][j] + "について右上を判定");
		if(square[i][j] == 0){
			try{
				if(square[--i][++j] == turn){//右上が同じ色 → false
					System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
					return false;
				}else{
					while(square[i][j] != 0){//右上が空白でないとき
						System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
						if(square[--i][++j] == turn){//対となる同色があった
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
							return true;
						}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
							return false;
						}else{//相手の色が続いている
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
							continue;
						}
					}
					System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
					return false;
				}
			}catch(IndexOutOfBoundsException e){
				System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
				return false;
			}
		}else{
			System.out.println("(" + i + "," + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
			return false;
		}
	}
	/** 左上に置けるかどうか判定 */
	private static boolean placeableLT(int i, int j){
		System.out.println("[" + i + "," + j + "] " + square[i][j] + "について左上を判定");
		if(square[i][j] == 0){
			try{
				if(square[--i][--j] == turn){//左上が同じ色 → false
					System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
					return false;
				}else{
					while(square[i][j] != 0){//左上が空白でないとき
						System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
						if(square[--i][--j] == turn){//対となる同色があった
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
							return true;
						}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
							return false;
						}else{//相手の色が続いている
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
							continue;
						}
					}
					System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
					return false;
				}
			}catch(IndexOutOfBoundsException e){
				System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
				return false;
			}
		}else{
			System.out.println("(" + i + "," + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
			return false;
		}
	}

	/** 置いた後に裏返せる場所の一覧を返すメソッド */
	public static ArrayList<ArrayList> reversible(Board board, int[] act){
		System.out.println("[" + act[0] + "," + act[1] + "] について判定");

		int[][] square = board.getBoard();
		int turn = board.getTurn();

		int[] a = {act[0], ++act[0], --act[0]};
		int[] b = {act[1], ++act[1], --act[1]};

		ArrayList<ArrayList> reversibleDisks = new ArrayList<>();
		ArrayList<Integer> place = new ArrayList<>();
			place.add(i);
			place.add(j);
			reversibleDisks.add(place);

		//１方向を全方位に繰り返す
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				int ii = i;
				int jj = j;
				ArrayList<ArrayList> stack = new ArrayList<>();
				if(i == 0 && j == 0){//[act[0], act[1]] = 置きたい場所　なので判定対象から除外
					continue;
				}
				//１方向に行う判定内容
				
					try{
						if(square[a[ii]][b[jj]] == 0){//空白なので何もできない
						}else if(square[i][j] != turn){//相手の色 → 次のマスを探索
							ArrayList<Integer> disk = new ArrayList<>();
							disk.add(a[i]);
							disk.add(b[j]);
							stack.add(disk);
						}else{//自分の色 → 間にあるマスを裏返す
							reversibleDisks.addAll(stack);
						}
					}catch(IndexOutOfBoundsException e){//盤面外なので何もできない
					}
				
			}
		}

			if(square[i][j] == 0){
				try{
					if(square[i][++j] == turn){//右隣が同じ色 → false
						System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
						return false;
					}else{
						while(square[i][j] != 0){//右隣が空白でないとき
							System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
							if(square[i][++j] == turn){//対となる同色があった
								System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
								return true;
							}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
								System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
								return false;
							}else{//相手の色が続いている
								System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
								continue;
							}
						}
						System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
						return false;
					}
				}catch(IndexOutOfBoundsException e){
					System.out.println(" (" + i + ")(" + j + ") == catch → false (枠外判定)");
					return false;
				}
			}else{
				System.out.println(" (" + i + ")(" + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
				return false;
			}
	}
}
