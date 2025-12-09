package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Concrete strategy for generating a "letters" password.
 * This implementation uses only letters (A-Z, a-z) and a standard {@code java.util.Random} object.
 */
public class LettersPasswordStrategy implements PasswordGenerationStrategy {
	private static final String ALPHAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	/**
     * Generates a password consisting only of letters (A-Z, a-z).
     * @param length The length of the password.
     * @return A password string composed only of letters.
     */
	@Override
	public String generate(int length) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int index = r.nextInt(ALPHAS.length());
			sb.append(ALPHAS.charAt(index));
		}
		return sb.toString();
	}
}