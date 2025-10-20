package org.howard.edu.lsp.midterm.question2;

public class AreaCalculator {
	
	public static double area(double radius) {
		if (radius <= 0) {
			throw new IllegalArgumentException("Radius must be at least 0");
		}
		return (Math.PI * radius * radius);
	}
	
	public static double area(double width, double height) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Dimensions must be at least 0");
		}
		return (width * height);
	}
	
	public static double area(int base, int height) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Dimensions must be at least 0");
        }
        return (0.5 * base * height);
    }
	
	public static double area(int side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Side must be at least 0");
        }
        return (side * side);
    }
}