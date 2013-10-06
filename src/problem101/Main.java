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

			// 0�`25�̐����Ńu���b�N�̐�����͂���
			StringTokenizer st = new StringTokenizer(input, " ");
			String blocknum = st.nextToken();
			int n = Integer.parseInt(blocknum);

			// �u���b�N�̏�����
			initBlocks(n);

			// �R�}���h����͂���
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
					// quit�R�}���h�����͂���錋�ʂ��o�͂��ďI��
					printBlocks();
					return;
				}

				int a = Integer.parseInt(block_a);
				int b = Integer.parseInt(block_b);

				// (move,pile) x (over,onto)��4�p�^�[���ɏꍇ����
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
	 * n��position, n�̃u���b�N�̋�Ԃ�����������
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
	 * n��position, n��Block������Ԃ�������
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
	 * �ԍ���n�̃u���b�N����������Block�Ԃ�
	 * 
	 * @param n
	 * @return �������ʂ�Block
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
	 * a��b��move����
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
	 * a��b��pile����
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
	 * �ԍ���a�̃u���b�N�ƁA�ԍ���b�̃u���b�N�̏�X�^�b�N�� �����ʒu�ɖ߂��Ă���A�ԍ���a�̃u���b�N��ԍ�b���܂܂�Ă���X�^�b�N�̏�ɐς�
	 * 
	 * @param a
	 *            move ����u���b�N�ԍ�
	 * @param b
	 *            move��u���b�N
	 */
	private static void moveOnto(int a, int b) {

		initPosition(a);
		initPosition(b);
		move(a, b);
	}

	/**
	 * �ԍ���a�̃u���b�N�̏�ɋl�܂ꂽ�u���b�N�������ʒu�ɖ߂��Ă���A �ԍ�b���܂܂�Ă���X�^�b�N�̏�ɐς�
	 * 
	 * @param a
	 *            move ����u���b�N�ԍ�
	 * @param b
	 *            move��u���b�N
	 */
	private static void moveOver(int a, int b) {
		initPosition(a);
		move(a, b);
	}

	/**
	 * �ԍ���a�̃u���b�N�̏�̃X�^�b�N���ƁA�ԍ���b�̃u���b�N���܂܂��X�^�b�N�̏�ɐς�
	 * 
	 * @param a
	 *            pile����X�^�b�N�̈�ԉ��̃u���b�N
	 * @param b
	 *            pile��̃u���b�N
	 */
	private static void pileOver(int a, int b) {
		// TODO Auto-generated method stub
		pile(a, b);
	}

	/**
	 * �ԍ���b�̃u���b�N�̏�̃X�^�b�N�������ʒu�ɖ߂��B�ԍ���a�̃u���b�N���܂ރX�^�b�N���ƁA�ԍ���b�̃u���b�N�̏�ɐς�
	 * 
	 * @param a
	 *            pile����X�^�b�N�̈�ԉ��̃u���b�N
	 * @param b
	 *            pile��̃u���b�N
	 */
	private static void pileOnto(int a, int b) {
		// TODO Auto-generated method stub
		initPosition(b);
		pile(a, b);
	}

	/**
	 * �u���b�N�̏�Ԃ��o�͂���
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
	int position; // Block�̈ʒu
	int num;// Block�̔ԍ�
}
