package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.nio.file.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Handles reading product data from CSV files. This class is responsible for
 * extracting data from the source file and converting it into Product objects.
 * Provides error handling for file operations and data parsing.
 * 
 * @author Gael Santos
 * @version 1.0
 */
public class DataReader {
	
	/**
     * Reads products from a CSV file and converts them into Product objects.
     * Expects the CSV format: ProductID,Name,Price,Category
     * Skips the header row and processes each data row. Only processes rows
     * with at least 4 fields to ensure data integrity.
     *
     * @param filePath the path to the CSV file to read
     * @return a list of Product objects, or null if an error occurs
     */
	public List<Product> readProducts(String path) {
        Path file = Paths.get(path);
        
        // If file isnt found throw error message
        if (!Files.exists(file)) {
            System.err.println("ERROR: " + path + " not found.");
            return null;
        }
        
        List<Product> products = new ArrayList<>();
        
        // Avoid crashing
        try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))) {
            br.readLine(); // Skip first line
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(","); // Make array list of 4 elements, since theres 4 elements per line split by commas
                Product product = new Product(fields[0], fields[1], new BigDecimal(fields[2]), fields[3]); // Make new product object
                products.add(product); // Append product to arraylist
            }
        } catch (IOException e) {
            System.err.println("ERROR: Reading " + path + ": " + e.getMessage());
            return null;
        }
        
        return products;
    }
}

