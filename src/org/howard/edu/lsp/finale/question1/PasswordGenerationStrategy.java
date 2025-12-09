package org.howard.edu.lsp.finale.question1;

/**
 * Defines the common interface for the password generation algorithms.
 * This is the Strategy interface in the Strategy Design Pattern,
 * which allows the PasswordGeneratorService (Context) to use any concrete strategy.
 */
public interface PasswordGenerationStrategy {
	/**
     * Generates a password of length given.
     * @param length The length of the password.
     * @return The generated password.
     */
    String generate(int length);
}