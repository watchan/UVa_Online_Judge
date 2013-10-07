package problem103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int[] inputnum = new int[9];

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;

		// 2個の整数を入力 (i個の箱・・n次元)
		while ((input = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(input, " ");

			// 箱の数
			int input_i = Integer.parseInt(st.nextToken());
			// 次元
			int input_n = Integer.parseInt(st.nextToken());
			System.out.println(input_i + "," + input_n);

			// i個の箱
			for (int i = 0; i < input_i; i++) {
				Box box = new Box();
				input = br.readLine();
				st = new StringTokenizer(input, " ");

				// n次元
				for (int n = 0; n < input_n; n++) {
					System.out.println(st.nextToken());
				}
			}

		}
	}
}

class Box {
	ArrayList<Integer> measurement;

}