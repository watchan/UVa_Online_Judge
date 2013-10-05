package problem101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;

		// �Q�̐����̓��͂��󂯕t����
		while ((input = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(input, " ");

			long i = Long.parseLong(st.nextToken());
			long j = Long.parseLong(st.nextToken());

			long i_org = i;
			long j_org = j;
			long maxsteps = -1;

			if (i > j) {

				long tmp = i;
				i = j;
				j = tmp;
			}

			// �͂��߂ɓ��͂���i�̕����傫���ꍇ�̓X���b�v����
			for (; i <= j; i++) {

				long steps = countSteps(i);

				if (steps > maxsteps) {
					maxsteps = steps;
				}
			}

			// ���ʂ̏o��
			System.out.println(i_org + " " + j_org + " " + maxsteps);
		}
	}

	// �X�e�b�v�����J�E���g���郁�\�b�h
	private static long countSteps(long i) {

		long steps = 1;
		long n = i;

		while (n > 1) {
			if (n % 2 == 1) {
				n = 3 * n + 1;
			} else {
				n = n / 2;
			}
			steps++;
		}
		return steps;
	}
}
