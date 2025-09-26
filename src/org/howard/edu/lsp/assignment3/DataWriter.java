package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.*;

/**
 * Handles writing product data to CSV files. This class is responsible for
 * loading transformed data into the output file in the required format.
 * Provides error handling for file output operations.
 * 
 * @author Your Name
 * @version 1.0
 */
public class DataWriter {
	
	/**
     * Writes a list of products to a CSV file. The output format includes:
     * ProductID,Name,Price,Category,PriceRange
     * Writes a header row followed by each product as a CSV row.
     *
     * @param products the list of products to write
     * @param filePath the path where the CSV file will be created
     */
    public void writeProducts(List<Product> products, String filePath) {
    	// Avoid crashing
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Write first line
        	bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();
            
            // Get all variables from each arraylist entry and store in string
            for (Product product : products) {
                String csvLine = String.join(",",
                    product.getId(),
                    product.getName(),
                    product.getPrice().toString(),
                    product.getCategory(),
                    product.getPriceRange()
                );
                
                // Write said string
                bw.write(csvLine);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("ERROR: Writing " + filePath + ": " + e.getMessage());
        }
    }
}