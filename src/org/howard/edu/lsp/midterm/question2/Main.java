package org.howard.edu.lsp.midterm.question2;

public class Main {
	public static void main(String[] args) {
		System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));
        
        try {
            AreaCalculator.area(-7.0); 
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
	}
}

/* Overloading is a better design choice than have different named methods because,
 * all of these function accomplish the same thing, which is get the area of a shape.
 * Overloading allows for more seamless use of methods without needing to memorize a
 * ton of different method names. Code also looks cleaner, and lets parameters
 * decide what shape we are working with.
 */