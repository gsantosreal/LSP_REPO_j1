package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Concrete strategy for generating a "basic" password.
 * This implementation uses only digits (0-9) and a standard {@code java.util.Random} object.
 */
public class BasicPasswordStrategy implements PasswordGenerationStrategy {
	private static final String NUMBERS = "0123456789";
	
	/**
     * Generates a password consisting only of digits (0-9).
     * @param length The length of the password.
     * @return A password string composed only of digits.
     */
	@Override
	public String generate(int length) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int index = r.nextInt(NUMBERS.length());
			sb.append(NUMBERS.charAt(index));
		}
		return sb.toString();
	}
}