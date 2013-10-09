package problem103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int[] inputnum = new int[9];
	static int input_n;
	static ArrayList<Box> boxList = new ArrayList<Box>();

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

				ArrayList<Integer> measurement = new ArrayList<Integer>();

				// n次元
				for (int n = 0; n < input_n; n++) {
					String str = st.nextToken();
					measurement.add(Integer.parseInt(str));

				}

				box.setMeasurement(measurement);
				box.setNum(i);

				box.sort();
				boxList.add(box);
				box.printMesurement();

			}

			System.out.println("printMeasurement");
			for (int i = 0; i < boxList.size(); i++) {
				boxList.get(i).printMesurement();
			}

			int[][] result = new int[input_i][input_n];

			for (int i = 0; i < input_i; i++) {
				for (int j = 0; j < input_i; j++) {
					result[i][j] = boxList.get(i).canInclude(boxList.get(j));
				}
			}

			System.out.print("  ");
			for (int k = 0; k < input_i; k++) {
				System.out.print(k + " ");
			}
			System.out.println();
			for (int i = 0; i < input_i; i++) {

				System.out.print(i + " ");
				for (int j = 0; j < input_i; j++) {
					System.out.print(result[i][j] + " ");
				}
				System.out.println();
			}
			
			
		}
	}
}

class Box {

	ArrayList<Integer> measurement;
	int num;
	int boxnum;

	ArrayList<Integer> getMeasurement() {
		return measurement;
	}

	public int canInclude(Box box) {

		Box resultbox = new Box();

		for (int n = 0; n < box.measurement.size(); n++) {

			int result = this.measurement.get(n) - box.measurement.get(n);

			if (result < 0) {
				return 0;
			}
		}

		return 1;
	}

	public void sort() {
		Collections.sort(measurement);
	}

	void setMeasurement(ArrayList<Integer> measurement) {
		this.measurement = measurement;
	}

	int getNum() {
		return num;
	}

	void setNum(int num) {
		this.num = num;
	}

	public void printMesurement() {

		System.out.print(num + "---");
		for (int i = 0; i < measurement.size(); i++) {
			System.out.print(measurement.get(i) + ",");
		}

		System.out.println();

	}

}