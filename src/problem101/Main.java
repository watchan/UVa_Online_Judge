package problem101;

//101 - The Blocks Problem

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	private static ArrayList<ArrayList<Integer>> position = new ArrayList<ArrayList<Integer>>();

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;

		while ((input = br.readLine()) != null) {

			// 0〜25の整数でブロックの数を入力する
			StringTokenizer st = new StringTokenizer(input, " ");
			String blocknum = st.nextToken();
			int n = Integer.parseInt(blocknum);

			// ブロックの初期化
			initBlocks(n);

			// コマンドを入力する
			while (true) {
				input = br.readLine();
				st = new StringTokenizer(input, " ");
				String command1 = st.nextToken();
				String block_a = null;
				String command2 = null;
				String block_b = null;

				if (!command1.equals("quit")) {
					block_a = st.nextToken();
					command2 = st.nextToken();
					block_b = st.nextToken();
				} else {
					// quitコマンドが入力される結果を出力して終了
					printBlocks();
					return;
				}

				int a = Integer.parseInt(block_a);
				int b = Integer.parseInt(block_b);

				// (move,pile) x (over,onto)で4パターンに場合分け
				if (blockCheckIs(a, b))
					if (command1.equals("move")) {
						if (command2.equals("over")) {
							moveOver(a, b);
						} else if (command2.equals("onto")) {
							moveOnto(a, b);
						}

					} else if (command1.equals("pile")) {
						if (command2.equals("over")) {
							pileOver(a, b);
						} else if (command2.equals("onto")) {
							pileOnto(a, b);
						}

					}

			}
		}
	}

	/**
	 * 入力値チェック
	 * 
	 * @param a
	 *            入力値a
	 * @param b
	 *            入力値b
	 * @return aとbが等しい場合、aのpositionとbのpositionが等しい場合はfalseを返す。それ以外の場合はtrueを返す。
	 */
	private static boolean blockCheckIs(int a, int b) {

		Block aBlock = searchBlock(a);
		Block bBlock = searchBlock(b);

		if (a == b) {
			return false;
		} else if (aBlock.position == bBlock.position) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * n個のposition, n個のブロックの空間を初期化する
	 * 
	 * @param n
	 */
	private static void initBlocks(int n) {

		for (int i = 0; i < n; i++) {
			ArrayList<Integer> blocks = new ArrayList<Integer>();
			blocks.add(i);
			position.add(blocks);
		}
	}

	/**
	 * n個のposition, n個のBlockを持つ空間を初期化
	 * 
	 * @param n
	 */
	private static void initPosition(int n) {
		Block block = searchBlock(n);

		while (position.get(block.position).size() > block.num + 1) {
			int num = position.get(block.position).get(block.num + 1);
			position.get(block.position).remove(block.num + 1);
			position.get(num).add(num);
		}
	}

	/**
	 * 番号がnのブロックを検索してBlock返す
	 * 
	 * @param n
	 * @return 検索結果のBlock
	 */
	private static Block searchBlock(int n) {

		Block block = new Block();
		for (int i = 0; i < position.size(); i++) {
			for (int j = 0; j < position.get(i).size(); j++) {
				if (position.get(i).get(j) == n) {
					block.position = i;
					block.num = j;
					return block;
				}
			}
		}

		return block;
	}

	/**
	 * aをbにmoveする
	 * 
	 * @param a
	 * @param b
	 */
	private static void move(int a, int b) {

		Block aBlock = searchBlock(a);
		Block bBlock = searchBlock(b);

		int num = position.get(aBlock.position).get(aBlock.num);
		position.get(aBlock.position).remove(aBlock.num);
		position.get(bBlock.position).add(num);

	}

	/**
	 * aをbにpileする
	 * 
	 * @param a
	 * @param b
	 */
	private static void pile(int a, int b) {
		Block aBlock = searchBlock(a);
		Block bBlock = searchBlock(b);

		while (position.get(aBlock.position).size() > aBlock.num) {
			int num = position.get(aBlock.position).get(aBlock.num);
			position.get(aBlock.position).remove(aBlock.num);
			position.get(bBlock.position).add(num);
		}
	}

	/**
	 * 番号がaのブロックと、番号がbのブロックの上スタックを 初期位置に戻してから、番号がaのブロックを番号bが含まれているスタックの上に積む
	 * 
	 * @param a
	 *            move するブロック番号
	 * @param b
	 *            move先ブロック
	 */
	private static void moveOnto(int a, int b) {

		initPosition(a);
		initPosition(b);
		move(a, b);
	}

	/**
	 * 番号がaのブロックの上に詰まれたブロックを初期位置に戻してから、 番号bが含まれているスタックの上に積む
	 * 
	 * @param a
	 *            move するブロック番号
	 * @param b
	 *            move先ブロック
	 */
	private static void moveOver(int a, int b) {
		initPosition(a);
		move(a, b);
	}

	/**
	 * 番号がaのブロックの上のスタックごと、番号がbのブロックが含まれるスタックの上に積む
	 * 
	 * @param a
	 *            pileするスタックの一番下のブロック
	 * @param b
	 *            pile先のブロック
	 */
	private static void pileOver(int a, int b) {
		// TODO Auto-generated method stub
		pile(a, b);
	}

	/**
	 * 番号がbのブロックの上のスタックを初期位置に戻す。番号がaのブロックを含むスタックごと、番号がbのブロックの上に積む
	 * 
	 * @param a
	 *            pileするスタックの一番下のブロック
	 * @param b
	 *            pile先のブロック
	 */
	private static void pileOnto(int a, int b) {
		// TODO Auto-generated method stub
		initPosition(b);
		pile(a, b);
	}

	/**
	 * ブロックの状態を出力する
	 */
	private static void printBlocks() {
		// TODO Auto-generated method stub
		for (int i = 0; i < position.size(); i++) {

			System.out.print(i + ":");

			for (int j = 0; j < position.get(i).size(); j++) {
				System.out.print(" " + position.get(i).get(j));
			}
			System.out.print("\n");
		}
	}

}

class Block {
	int position; // Blockの位置
	int num;// Blockの番号
}
