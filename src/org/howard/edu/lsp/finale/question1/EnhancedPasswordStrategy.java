package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Concrete strategy for generating an "enhanced" password.
 * This implementation uses alphanumeric characters (A-Z, a-z, 0-9) 
 * and a cryptographically strong {@code SecureRandom} generator.
 */
public class EnhancedPasswordStrategy implements PasswordGenerationStrategy {
	private static final String ALPHANUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	
	/**
     * Generates a password consisting of alphanumeric characters.
     * @param length The length of the password.
     * @return A password string composed of letters and digits.
     */
	@Override
	public String generate(int length) {
		SecureRandom r = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int index = r.nextInt(ALPHANUM.length());
			sb.append(ALPHANUM.charAt(index));
		}
		return sb.toString();
	}
}