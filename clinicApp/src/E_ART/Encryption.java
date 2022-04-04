package E_ART;

import java.util.Random;

public class Encryption {
	private static final int OFFSET_CONSTANT = 32;
	private static final int DELTA = 32;

	private static long variableOffset(long Nl, long Nr) {
		return Nl < BinaryTree.R ? (BinaryTree.R * Nl) % Nr : (BinaryTree.R * Nr) % Nl;
	}

	private static long prngGenerator(int seed) {
		Random prng = new Random(seed);
		return Math.abs(prng.nextLong());
	}

	public static String encrypt(Key key, String plainText) {
		long N = key.N, variance = key.variance;
		StringBuilder cipherText = new StringBuilder();

		int Nl = (int) (N % BinaryTree.LEN_MAX); // N%127
		int Nr = (int) BinaryTree.reflection(Nl); // LEN_MAX-Nl + 1
		int varOffset = (int) variableOffset(Nl, Nr); // calculate OffsetVar
		int valInitRef;

		for (int pos = 0; pos < plainText.length(); pos++) {
			int orgVal = plainText.charAt(pos);

			if (orgVal < 32) {
				cipherText.append((char) (orgVal));
			} else {
				valInitRef = (int) BinaryTree.reflection(orgVal);

				long prn = prngGenerator(pos);
				// dynamic offset
				int dynOffset = (int) (prn % variance);

				if ((valInitRef + varOffset + OFFSET_CONSTANT) > BinaryTree.LEN_MAX) {
					if (((valInitRef + varOffset + OFFSET_CONSTANT) % BinaryTree.LEN_MAX) < 32) {
						valInitRef = (int) ((valInitRef + varOffset + OFFSET_CONSTANT) % BinaryTree.LEN_MAX) + DELTA
								+ dynOffset;
					} else {
						valInitRef = (int) ((valInitRef + varOffset + OFFSET_CONSTANT) % BinaryTree.LEN_MAX)
								+ dynOffset;
					}
				} else {
					valInitRef = valInitRef + varOffset + OFFSET_CONSTANT + dynOffset;
				}
				cipherText.append((char) (valInitRef));

			}

		}

		return cipherText.toString();
	}
}
