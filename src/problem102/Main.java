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

			calcSteps();
		}
	}

	private static void calcSteps() {
		int sum = 0;
		int min = 0;
		String str = "";

		// BCG 12 34 68
		sum = inputnum[1] + inputnum[2] + inputnum[3] + inputnum[4]
				+ inputnum[6] + inputnum[8];

		min = sum;
		str = "BCG";
		sum = 0;

		// BGC 12 35 67
		sum = inputnum[1] + inputnum[2] + inputnum[3] + inputnum[5]
				+ inputnum[6] + inputnum[7];

		if (sum < min) {
			min = sum;
			str = "BGC";
		}

		sum = 0;

		// CBG 01 45 68
		sum = inputnum[0] + inputnum[1] + inputnum[4] + inputnum[5]
				+ inputnum[6] + inputnum[8];
		if (sum < min) {
			min = sum;
			str = "CBG";
		}

		sum = 0;

		// CGB 01 35 78
		sum = inputnum[0] + inputnum[1] + inputnum[3] + inputnum[5]
				+ inputnum[7] + inputnum[8];
		if (sum < min) {
			min = sum;
			str = "CGB";
		}
		sum = 0;

		// GBC 02 45 67
		sum = inputnum[0] + inputnum[2] + inputnum[4] + inputnum[5]
				+ inputnum[6] + inputnum[7];
		if (sum < min) {
			min = sum;
			str = "GBC";
		}
		sum = 0;

		// GCB 02 34 78
		sum = inputnum[0] + inputnum[2] + inputnum[3] + inputnum[4]
				+ inputnum[7] + inputnum[8];
		if (sum < min) {
			min = sum;
			str = "GCB";
		}

		System.out.println(str + " " + min);
	}
}
