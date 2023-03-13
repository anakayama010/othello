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

		//System.out.println("手番 : " + turn); //確認用
		int ct = 1; //確認用
		for(int i = 0; i < square.length; i++ ){
			for(int j = 0; j < square[i].length; j++){
				//System.out.println("");
				//System.out.print(ct++ + " ");
				//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について判定");
				if(square[i][j] == 0){//空白のマスであるとき
					if(placeableT(i, j) || placeableRT(i, j) || placeableR(i, j) || placeableRB(i, j) || placeableB(i, j) || placeableLB(i, j) || placeableL(i, j) || placeableLT(i, j)){
						ArrayList<Integer> place = new ArrayList<>();
						place.add(i);
						place.add(j);
						places.add(place);
					}
				}else{
					//System.out.println(" (" + i + ")(" + j + ") == " + square[i][j] + " → false (置けないマス)");
				}
				
			}
		}
		return places;
	}


	/** 右に置けるかどうか判定 */
	private static boolean placeableR(int i, int j){

		//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について右を判定");
		if(square[i][j] == 0){
			try{
				if(square[i][++j] == turn){//右隣が同じ色 → false
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
					return false;
				}else{
					while(square[i][j] != 0){//右隣が空白でないとき
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
						if(square[i][++j] == turn){//対となる同色があった
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
							return true;
						}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
							return false;
						}else{//相手の色が続いている
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
							continue;
						}
					}
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
					return false;
				}
			}catch(IndexOutOfBoundsException e){
				//System.out.println(" (" + i + ")(" + j + ") == catch → false (枠外判定)");
				return false;
			}
		}else{
			//System.out.println(" (" + i + ")(" + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
			return false;
		}
	}

	/** 左に置けるかどうか判定 */
	private static boolean placeableL(int i, int j){

		//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について左を判定");
		if(square[i][j] == 0){
			try{
				if(square[i][--j] == turn){//左隣が同じ色 → false
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
					return false;
				}else{
					while(square[i][j] != 0){//左隣が空白でないとき
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
						if(square[i][--j] == turn){//対となる同色があった
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
							return true;
						}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
							return false;
						}else{//相手の色が続いている
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
							continue;
						}
					}
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (左隣が空白)");
					return false;
				}
			}catch(IndexOutOfBoundsException e){
				//System.out.println(" (" + i + ")(" + j + ") == catch → false (枠外判定)");
				return false;
			}
		}else{
			//System.out.println(" (" + i + ")(" + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
			return false;
		}
	}

	/** 下に置けるかどうか判定 */
	private static boolean placeableB(int i, int j){
		//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について下を判定");
		if(square[i][j] == 0){
			try{
				if(square[++i][j] == turn){//下が同じ色 → false
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
					return false;
				}else{
					while(square[i][j] != 0){//下が空白でないとき
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
						if(square[++i][j] == turn){//対となる同色があった
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
							return true;
						}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
							return false;
						}else{//相手の色が続いている
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
							continue;
						}
					}
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
					return false;
				}
			}catch(IndexOutOfBoundsException e){
				//System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
				return false;
			}
		}else{
			//System.out.println("(" + i + "," + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
			return false;
		}
	}

	/** 上に置けるかどうか判定 */
	private static boolean placeableT(int i, int j){
		//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について上を判定");
		if(square[i][j] == 0){
			try{
				if(square[--i][j] == turn){//上が同じ色 → false
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
					return false;
				}else{
					while(square[i][j] != 0){//上が空白でないとき
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
						if(square[--i][j] == turn){//対となる同色があった
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
							return true;
						}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
							return false;
						}else{//相手の色が続いている
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
							continue;
						}
					}
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
					return false;
				}
			}catch(IndexOutOfBoundsException e){
				//System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
				return false;
			}
		}else{
			//System.out.println("(" + i + "," + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
			return false;
		}
	}

	/** 右下に置けるかどうか判定 */
	private static boolean placeableRB(int i, int j){
		//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について右下を判定");
		if(square[i][j] == 0){
			try{
				if(square[++i][++j] == turn){//右下が同じ色 → false
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
					return false;
				}else{
					while(square[i][j] != 0){//右下が空白でないとき
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
						if(square[++i][++j] == turn){//対となる同色があった
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
							return true;
						}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
							return false;
						}else{//相手の色が続いている
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
							continue;
						}
					}
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
					return false;
				}
			}catch(IndexOutOfBoundsException e){
				//System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
				return false;
			}
		}else{
			//System.out.println("(" + i + "," + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
			return false;
		}
	}

	/** 左下に置けるかどうか判定 */
	private static boolean placeableLB(int i, int j){
		//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について左下を判定");
		if(square[i][j] == 0){
			try{
				if(square[++i][--j] == turn){//左下が同じ色 → false
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
					return false;
				}else{
					while(square[i][j] != 0){//左下が空白でないとき
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
						if(square[++i][--j] == turn){//対となる同色があった
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
							return true;
						}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
							return false;
						}else{//相手の色が続いている
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
							continue;
						}
					}
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
					return false;
				}
			}catch(IndexOutOfBoundsException e){
				//System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
				return false;
			}
		}else{
			//System.out.println("(" + i + "," + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
			return false;
		}
	}

	/** 右上に置けるかどうか判定 */
	private static boolean placeableRT(int i, int j){
		//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について右上を判定");
		if(square[i][j] == 0){
			try{
				if(square[--i][++j] == turn){//右上が同じ色 → false
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
					return false;
				}else{
					while(square[i][j] != 0){//右上が空白でないとき
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
						if(square[--i][++j] == turn){//対となる同色があった
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
							return true;
						}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
							return false;
						}else{//相手の色が続いている
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
							continue;
						}
					}
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
					return false;
				}
			}catch(IndexOutOfBoundsException e){
				//System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
				return false;
			}
		}else{
			//System.out.println("(" + i + "," + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
			return false;
		}
	}
	
	/** 左上に置けるかどうか判定 */
	private static boolean placeableLT(int i, int j){
		//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について左上を判定");
		if(square[i][j] == 0){
			try{
				if(square[--i][--j] == turn){//左上が同じ色 → false
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
					return false;
				}else{
					while(square[i][j] != 0){//左上が空白でないとき
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
						if(square[--i][--j] == turn){//対となる同色があった
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
							return true;
						}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
							return false;
						}else{//相手の色が続いている
							//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
							continue;
						}
					}
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
					return false;
				}
			}catch(IndexOutOfBoundsException e){
				//System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
				return false;
			}
		}else{
			//System.out.println("(" + i + "," + j + ") == " + square[i][j] + " → false (置けないマス)※あらかじめ取り除いてある");
			return false;
		}
	}

	/** 裏返せるか判定し、裏返す */
	public static void reverseDisks(Board board, int[] act){
		square = board.getBoard();
		turn = board.getTurn();
		board.changeDisk(act[0], act[1]);

		//右上
		ArrayList<Integer> possible = reverseDisksRT(act[0], act[1]);
		while(possible != null){//裏返すものがあるとき
			for(int i = 0; i < possible.size();){
				board.changeDisk(possible.get(i++), possible.get(i++));
			}
			possible.clear();
			break;
		}
		
		//右
		possible = reverseDisksR(act[0], act[1]);
		while(possible != null){//裏返すものがあるとき
			for(int i = 0; i < possible.size();){
				board.changeDisk(possible.get(i++), possible.get(i++));
			}
			possible.clear();
			break;
		}

		//右下
		possible = reverseDisksRB(act[0], act[1]);
		while(possible != null){//裏返すものがあるとき
			for(int i = 0; i < possible.size();){
				board.changeDisk(possible.get(i++), possible.get(i++));
			}
			possible.clear();
			break;
		}

		//下
		possible = reverseDisksB(act[0], act[1]);
		while(possible != null){//裏返すものがあるとき
			for(int i = 0; i < possible.size();){
				board.changeDisk(possible.get(i++), possible.get(i++));
			}
			possible.clear();
			break;
		}

		//左下
		possible = reverseDisksLB(act[0], act[1]);
		while(possible != null){//裏返すものがあるとき
			for(int i = 0; i < possible.size();){
				board.changeDisk(possible.get(i++), possible.get(i++));
			}
			possible.clear();
			break;
		}

		//左
		possible = reverseDisksL(act[0], act[1]);
		while(possible != null){//裏返すものがあるとき
			for(int i = 0; i < possible.size();){
				board.changeDisk(possible.get(i++), possible.get(i++));
			}
			possible.clear();
			break;
		}

		//左上
		possible = reverseDisksLT(act[0], act[1]);
		while(possible != null){//裏返すものがあるとき
			for(int i = 0; i < possible.size();){
				board.changeDisk(possible.get(i++), possible.get(i++));
			}
			possible.clear();
			break;
		}

		//上
		possible = reverseDisksT(act[0], act[1]);
		while(possible != null){//裏返すものがあるとき
			for(int i = 0; i < possible.size();){
				board.changeDisk(possible.get(i++), possible.get(i++));
			}
			possible.clear();
			break;
		}
	}

	/** 右上方向に判定し裏返す */
	public static ArrayList<Integer> reverseDisksRT (int i, int j){
		//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について右上を判定");
		ArrayList<Integer> possible = new ArrayList<>();
		try{
			if(square[--i][++j] == turn){//まず隣が同じ色 → false
				//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
				return null;
			}else{//まず隣が相手の色
				while(square[i][j] != 0){//さらに隣が空白でないとき
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
					possible.add(i);
					possible.add(j);
					if(square[--i][++j] == turn){//さらに隣に対となる同色があった
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
						return possible;
					}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
						return null;
					}else{//相手の色が続いている
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
						continue;
					}
				}
				//隣が空白
				//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
				return null;
			}
		}catch(IndexOutOfBoundsException e){//枠外を検知
			//System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
			return null;
		}	
	}

	/** 右方向に判定し裏返す */
	public static ArrayList<Integer> reverseDisksR (int i, int j){
		//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について右を判定");
		ArrayList<Integer> possible = new ArrayList<>();
		try{
			if(square[i][++j] == turn){//右が同じ色 → false
				//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
				return null;
			}else{//まず右が相手の色
				while(square[i][j] != 0){//右が空白でないとき
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
					possible.add(i);
					possible.add(j);
					if(square[i][++j] == turn){//対となる同色があった
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
						return possible;
					}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
						return null;
					}else{//相手の色が続いている
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
						continue;
					}
				}
				//右が空白
				//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
				return null;
			}
		}catch(IndexOutOfBoundsException e){//枠外を検知
			//System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
			return null;
		}
	}

	/** 右下方向に判定し裏返す */
	public static ArrayList<Integer> reverseDisksRB (int i, int j){
		//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について右下を判定");
		ArrayList<Integer> possible = new ArrayList<>();
		try{
			if(square[++i][++j] == turn){//まず隣が同じ色 → false
				//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
				return null;
			}else{//まず隣が相手の色
				while(square[i][j] != 0){//さらに隣が空白でないとき
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
					possible.add(i);
					possible.add(j);
					if(square[++i][++j] == turn){//さらに隣に対となる同色があった
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
						return possible;
					}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
						return null;
					}else{//相手の色が続いている
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
						continue;
					}
				}
				//隣が空白
				//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
				return null;
			}
		}catch(IndexOutOfBoundsException e){//枠外を検知
			//System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
			return null;
		}	
	}

	/** 下方向に判定し裏返す */
	public static ArrayList<Integer> reverseDisksB (int i, int j){
		//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について下を判定");
		ArrayList<Integer> possible = new ArrayList<>();
		try{
			if(square[++i][j] == turn){//まず隣が同じ色 → false
				//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
				return null;
			}else{//まず隣が相手の色
				while(square[i][j] != 0){//さらに隣が空白でないとき
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
					possible.add(i);
					possible.add(j);
					if(square[++i][j] == turn){//さらに隣に対となる同色があった
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
						return possible;
					}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
						return null;
					}else{//相手の色が続いている
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
						continue;
					}
				}
				//隣が空白
				//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
				return null;
			}
		}catch(IndexOutOfBoundsException e){//枠外を検知
			//System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
			return null;
		}	
	}

	/** 左下方向に判定し裏返す */
	public static ArrayList<Integer> reverseDisksLB (int i, int j){
		//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について左下を判定");
		ArrayList<Integer> possible = new ArrayList<>();
		try{
			if(square[++i][--j] == turn){//まず隣が同じ色 → false
				//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
				return null;
			}else{//まず隣が相手の色
				while(square[i][j] != 0){//さらに隣が空白でないとき
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
					possible.add(i);
					possible.add(j);
					if(square[++i][--j] == turn){//さらに隣に対となる同色があった
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
						return possible;
					}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
						return null;
					}else{//相手の色が続いている
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
						continue;
					}
				}
				//隣が空白
				//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
				return null;
			}
		}catch(IndexOutOfBoundsException e){//枠外を検知
			//System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
			return null;
		}	
	}

	/** 左方向に判定し裏返す */
	public static ArrayList<Integer> reverseDisksL (int i, int j){
		//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について左を判定");
		ArrayList<Integer> possible = new ArrayList<>();
		try{
			if(square[i][--j] == turn){//まず隣が同じ色 → false
				//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
				return null;
			}else{//まず隣が相手の色
				while(square[i][j] != 0){//さらに隣が空白でないとき
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
					possible.add(i);
					possible.add(j);
					if(square[i][--j] == turn){//さらに隣に対となる同色があった
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
						return possible;
					}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
						return null;
					}else{//相手の色が続いている
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
						continue;
					}
				}
				//隣が空白
				//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
				return null;
			}
		}catch(IndexOutOfBoundsException e){//枠外を検知
			//System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
			return null;
		}	
	}

	/** 左上方向に判定し裏返す */
	public static ArrayList<Integer> reverseDisksLT (int i, int j){
		//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について左上を判定");
		ArrayList<Integer> possible = new ArrayList<>();
		try{
			if(square[--i][--j] == turn){//まず隣が同じ色 → false
				//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
				return null;
			}else{//まず隣が相手の色
				while(square[i][j] != 0){//さらに隣が空白でないとき
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
					possible.add(i);
					possible.add(j);
					if(square[--i][--j] == turn){//さらに隣に対となる同色があった
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
						return possible;
					}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
						return null;
					}else{//相手の色が続いている
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
						continue;
					}
				}
				//隣が空白
				//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
				return null;
			}
		}catch(IndexOutOfBoundsException e){//枠外を検知
			//System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
			return null;
		}	
	}

	/** 上方向に判定し裏返す */
	public static ArrayList<Integer> reverseDisksT (int i, int j){
		//System.out.println("[" + i + "," + j + "] " + square[i][j] + "について上を判定");
		ArrayList<Integer> possible = new ArrayList<>();
		try{
			if(square[--i][j] == turn){//まず隣が同じ色 → false
				//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (同じ色)");
				return null;
			}else{//まず隣が相手の色
				while(square[i][j] != 0){//さらに隣が空白でないとき
					//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → loop (相手の色)");
					possible.add(i);
					possible.add(j);
					if(square[--i][j] == turn){//さらに隣に対となる同色があった
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → true (対となる同色)");
						return possible;
					}else if(square[i][j] == 0){//対がないまま空白に辿り着いた
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (対がなく空白)");
						return null;
					}else{//相手の色が続いている
						//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → continue (相手の色連続)");
						continue;
					}
				}
				//隣が空白
				//System.out.println("(" + i + "," + j + ") " + square[i][j] + " → false (空白)");
				return null;
			}
		}catch(IndexOutOfBoundsException e){//枠外を検知
			//System.out.println("(" + i + "," + j + ") == catch → false (枠外判定)");
			return null;
		}	
	}

	
}
