package org.howard.edu.lsp.finale.question1;

/**
 * Singleton service responsible for generating passwords using swappable strategies.
 * This class serves as the context for the Strategy Pattern, allowing clients to
 * select a password generation algorithm at runtime.
 */
public class PasswordGeneratorService {
	/*
     * 1. Patterns Used: Singleton and Strategy
     *
     * 2. Explanation:
     * 
     * - Singleton: After seeing the requirements of "Only one instance of the service may exist" 
     * and providing a "single shared access point" Singleton felt like the obvious choice.
     * Doing this ensures consistent state management for the selected algorithm.
     *
     * - Strategy: The requirements of "Support multiple approaches", 
     * allow the caller to "select the approach at run time",
     * "Support future expansion of password-generation approaches", 
     * and "Make password-generation behavior swappable" all seemed to indicate
     * that the Strategy pattern would be the most effective. 
     * Defining an interface (PasswordGenerationStrategy) encapsulates each algorithm 
     * (Basic, Enhanced, Letters) into its own class and allows swapping them dynamically via setAlgorithm().
     * -------------------------------------------------------------------------
     */
	private static PasswordGeneratorService instance;
	private PasswordGenerationStrategy strategy;
	
	/**
     * Private constructor to enforce Singleton pattern.
     */
	private PasswordGeneratorService() {}
	
	/**
     * Returns the single instance of the PasswordGeneratorService.
     * @return The singleton instance.
     */
	public static PasswordGeneratorService getInstance() {
        if (instance == null) {
            instance = new PasswordGeneratorService();
        }
        return instance;
    }
	
	/**
     * Sets the password generation algorithm to be used.
     * Supported algorithm names are "basic", "enhanced", and "letters".
     * @param name The name of the algorithm.
     * @throws IllegalArgumentException if the provided name is not an algorithm.
     */
	public void setAlgorithm(String name) {
        switch (name.toLowerCase()) {
            case "basic":
                this.strategy = new BasicPasswordStrategy();
                break;
            case "enhanced":
                this.strategy = new EnhancedPasswordStrategy();
                break;
            case "letters":
                this.strategy = new LettersPasswordStrategy();
                break;
            default:
                throw new IllegalArgumentException("Not an algorithm: " + name);
        }
    }
	
	/**
     * Generates a password of the specified length using the selected algorithm.
     * @param length The desired length of the password.
     * @return The generated password string.
     * @throws IllegalStateException If this method is called before an algorithm is selected using setAlgorithm().
     */
	public String generatePassword(int length) {
        if (this.strategy == null) {
            throw new IllegalStateException("Password generation algorithm not selected.");
        }
        return this.strategy.generate(length);
    }
}

