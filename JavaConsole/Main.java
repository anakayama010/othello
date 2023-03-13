package javaconsole;
 /*
 * マスの状態メモ
 * 何もなし = 0
 * 黒 ● = 1,
 * 白 ○ = 2,
 * 置くことができる ◌ = 3
 */

import java.util.ArrayList;
import java.util.Scanner;

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

	/** プレイヤーのマス選択を待つメソッド */
	public static int[] waitAction(){
		Scanner sc = new Scanner (System.in);
		int[] act = new int[2];
		System.out.println("縦座標を入力してください（０～３）");
		act[0] = sc.nextInt();
		System.out.println("横座標を入力してください（０～３）");
		act[1] = sc.nextInt();
		return act;
	}


	/** 各種メソッドの動作確認を行うメソッド */
	public static void methodCheck(Board board){
		//Board.getBoard()
		int[][] square = board.getBoard();
		for(int[] a : square){
			System.out.print("[ ");
			for(int b : a){
				System.out.print(b + " ");
			}
			System.out.println("]");
		}
		//Board.getCountBlack()
		System.out.println("Black : " + board.getCountBlack());
		//Board.getCountWhite()
		System.out.println("White : " + board.getCountWhite());
		//Board.getTurn()
		System.out.println("Turn : " + board.getTurn());
		//Board.changeTurn()
		for(int i = 1; i <= 4; i++){
			board.changeTurn();
			System.out.println("AfterChange " + i + " Turn : " + board.getTurn());
		}
		//Board.isThereNull()
		System.out.println("isNull : " + board.isThereNull());
		//Judge.placeable()
		ArrayList<ArrayList> placeable = Judge.placeable(board);
		if(placeable.isEmpty()){
			System.out.println("null");
		} else{
			System.out.println(placeable);
		}
		//Main.waitAction()
		int[] act = waitAction();
		System.out.println("プレイヤーの選択 : [" + act[0] + ", " + act[1] + "]" );
		//judge.reverseDisks()
		Judge.reverseDisks(board, act);
		square = board.getBoard();
		for(int[] a : square){
			System.out.print("[ ");
			for(int b : a){
				System.out.print(b + " ");
			}
			System.out.println("]");
		}
	} 	
}
