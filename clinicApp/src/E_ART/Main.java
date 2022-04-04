package E_ART;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Key key = new Key(676, 88);

		String input_text = "wael wael";
		System.out.println("input text:     " + input_text);

		System.out.println();

		String encrypted_text = Encryption.encrypt(key, input_text);
		System.out.println("encrypted text: " + encrypted_text);

		System.out.println();

		String decrypted_text = Decryption.decrypt(key, encrypted_text);
		System.out.println("decrypted text: " + decrypted_text);

	}

}
