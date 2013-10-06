package problem102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

		// 9個の整数を入力
		while ((input = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(input, " ");

			for (int i = 0; i < 9; i++) {
				inputnum[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < 9; i++) {
				System.out.println(inputnum[i]);
			}

			printBin();
		}
	}

	private static void printBin() {
		int bin1 = 0;
		int bin2 = 0;
		int bin3 = 0;

		for (int i = 0; i < 3; i++) {
			bin1 += inputnum[i];
		}
		for (int i = 3; i < 6; i++) {
			bin2 += inputnum[i];
		}
		for (int i = 6; i < 9; i++) {
			bin3 += inputnum[i];
		}

		System.out.println(bin1 + " " + bin2 + " " + bin3);
	}

}
